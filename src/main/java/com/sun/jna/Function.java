package com.sun.jna;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

public class Function extends Pointer {
   public static final int MAX_NARGS = 256;
   public static final int C_CONVENTION = 0;
   public static final int ALT_CONVENTION = 63;
   private static final int MASK_CC = 63;
   public static final int THROW_LAST_ERROR = 64;
   public static final int USE_VARARGS = 384;
   static final Integer INTEGER_TRUE = -1;
   static final Integer INTEGER_FALSE = 0;
   private NativeLibrary library;
   private final String functionName;
   final String encoding;
   final int callFlags;
   final Map options;
   static final String OPTION_INVOKING_METHOD = "invoking-method";
   private static final VarArgsChecker IS_VARARGS = VarArgsChecker.create();

   public static Function getFunction(String libraryName, String functionName) {
      return NativeLibrary.getInstance(libraryName).getFunction(functionName);
   }

   public static Function getFunction(String libraryName, String functionName, int callFlags) {
      return NativeLibrary.getInstance(libraryName).getFunction(functionName, callFlags, (String)null);
   }

   public static Function getFunction(String libraryName, String functionName, int callFlags, String encoding) {
      return NativeLibrary.getInstance(libraryName).getFunction(functionName, callFlags, encoding);
   }

   public static Function getFunction(Pointer p) {
      return getFunction(p, 0, (String)null);
   }

   public static Function getFunction(Pointer p, int callFlags) {
      return getFunction(p, callFlags, (String)null);
   }

   public static Function getFunction(Pointer p, int callFlags, String encoding) {
      return new Function(p, callFlags, encoding);
   }

   Function(NativeLibrary library, String functionName, int callFlags, String encoding) {
      this.checkCallingConvention(callFlags & 63);
      if (functionName == null) {
         throw new NullPointerException("Function name must not be null");
      } else {
         this.library = library;
         this.functionName = functionName;
         this.callFlags = callFlags;
         this.options = library.options;
         this.encoding = encoding != null ? encoding : Native.getDefaultStringEncoding();

         try {
            this.peer = library.getSymbolAddress(functionName);
         } catch (UnsatisfiedLinkError var6) {
            throw new UnsatisfiedLinkError("Error looking up function '" + functionName + "': " + var6.getMessage());
         }
      }
   }

   Function(Pointer functionAddress, int callFlags, String encoding) {
      this.checkCallingConvention(callFlags & 63);
      if (functionAddress != null && functionAddress.peer != 0L) {
         this.functionName = functionAddress.toString();
         this.callFlags = callFlags;
         this.peer = functionAddress.peer;
         this.options = Collections.EMPTY_MAP;
         this.encoding = encoding != null ? encoding : Native.getDefaultStringEncoding();
      } else {
         throw new NullPointerException("Function address may not be null");
      }
   }

   private void checkCallingConvention(int convention) throws IllegalArgumentException {
      if ((convention & 63) != convention) {
         throw new IllegalArgumentException("Unrecognized calling convention: " + convention);
      }
   }

   public String getName() {
      return this.functionName;
   }

   public int getCallingConvention() {
      return this.callFlags & 63;
   }

   public Object invoke(Class returnType, Object[] inArgs) {
      return this.invoke(returnType, inArgs, this.options);
   }

   public Object invoke(Class returnType, Object[] inArgs, Map options) {
      Method invokingMethod = (Method)options.get("invoking-method");
      Class[] paramTypes = invokingMethod != null ? invokingMethod.getParameterTypes() : null;
      return this.invoke(invokingMethod, paramTypes, returnType, inArgs, options);
   }

   Object invoke(Method invokingMethod, Class[] paramTypes, Class returnType, Object[] inArgs, Map options) {
      Object[] args = new Object[0];
      if (inArgs != null) {
         if (inArgs.length > 256) {
            throw new UnsupportedOperationException("Maximum argument count is 256");
         }

         args = new Object[inArgs.length];
         System.arraycopy(inArgs, 0, args, 0, args.length);
      }

      TypeMapper mapper = (TypeMapper)options.get("type-mapper");
      boolean allowObjects = Boolean.TRUE.equals(options.get("allow-objects"));
      boolean isVarArgs = args.length > 0 && invokingMethod != null ? isVarArgs(invokingMethod) : false;
      int fixedArgs = args.length > 0 && invokingMethod != null ? fixedArgs(invokingMethod) : 0;

      for(int i = 0; i < args.length; ++i) {
         Class paramType = invokingMethod != null ? (isVarArgs && i >= paramTypes.length - 1 ? paramTypes[paramTypes.length - 1].getComponentType() : paramTypes[i]) : null;
         args[i] = this.convertArgument(args, i, invokingMethod, mapper, allowObjects, paramType);
      }

      Class nativeReturnType = returnType;
      FromNativeConverter resultConverter = null;
      if (NativeMapped.class.isAssignableFrom(returnType)) {
         NativeMappedConverter tc = NativeMappedConverter.getInstance(returnType);
         resultConverter = tc;
         nativeReturnType = tc.nativeType();
      } else if (mapper != null) {
         resultConverter = mapper.getFromNativeConverter(returnType);
         if (resultConverter != null) {
            nativeReturnType = ((FromNativeConverter)resultConverter).nativeType();
         }
      }

      Object result = this.invoke(args, nativeReturnType, allowObjects, fixedArgs);
      if (resultConverter != null) {
         Object context;
         if (invokingMethod != null) {
            context = new MethodResultContext(returnType, this, inArgs, invokingMethod);
         } else {
            context = new FunctionResultContext(returnType, this, inArgs);
         }

         result = ((FromNativeConverter)resultConverter).fromNative(result, (FromNativeContext)context);
      }

      if (inArgs != null) {
         for(int i = 0; i < inArgs.length; ++i) {
            Object inArg = inArgs[i];
            if (inArg != null) {
               if (inArg instanceof Structure) {
                  if (!(inArg instanceof Structure.ByValue)) {
                     ((Structure)inArg).autoRead();
                  }
               } else if (args[i] instanceof Function.PostCallRead) {
                  ((Function.PostCallRead)args[i]).read();
                  if (args[i] instanceof Function.PointerArray) {
                     Function.PointerArray array = (Function.PointerArray)args[i];
                     if (Structure.ByReference[].class.isAssignableFrom(inArg.getClass())) {
                        Class type = inArg.getClass().getComponentType();
                        Structure[] ss = (Structure[])((Structure[])inArg);

                        for(int si = 0; si < ss.length; ++si) {
                           Pointer p = array.getPointer((long)(Pointer.SIZE * si));
                           ss[si] = Structure.updateStructureByReference(type, ss[si], p);
                        }
                     }
                  }
               } else if (Structure[].class.isAssignableFrom(inArg.getClass())) {
                  Structure.autoRead((Structure[])((Structure[])inArg));
               }
            }
         }
      }

      return result;
   }

   Object invoke(Object[] args, Class returnType, boolean allowObjects) {
      return this.invoke(args, returnType, allowObjects, 0);
   }

   Object invoke(Object[] args, Class returnType, boolean allowObjects, int fixedArgs) {
      Object result = null;
      int callFlags = this.callFlags | (fixedArgs & 3) << 7;
      if (returnType != null && returnType != Void.TYPE && returnType != Void.class) {
         if (returnType != Boolean.TYPE && returnType != Boolean.class) {
            if (returnType != Byte.TYPE && returnType != Byte.class) {
               if (returnType != Short.TYPE && returnType != Short.class) {
                  if (returnType != Character.TYPE && returnType != Character.class) {
                     if (returnType != Integer.TYPE && returnType != Integer.class) {
                        if (returnType != Long.TYPE && returnType != Long.class) {
                           if (returnType != Float.TYPE && returnType != Float.class) {
                              if (returnType != Double.TYPE && returnType != Double.class) {
                                 if (returnType == String.class) {
                                    result = this.invokeString(callFlags, args, false);
                                 } else if (returnType == WString.class) {
                                    String s = this.invokeString(callFlags, args, true);
                                    if (s != null) {
                                       result = new WString(s);
                                    }
                                 } else {
                                    if (Pointer.class.isAssignableFrom(returnType)) {
                                       return this.invokePointer(callFlags, args);
                                    }

                                    if (Structure.class.isAssignableFrom(returnType)) {
                                       Structure s;
                                       if (Structure.ByValue.class.isAssignableFrom(returnType)) {
                                          s = Native.invokeStructure(this, this.peer, callFlags, args, Structure.newInstance(returnType));
                                          s.autoRead();
                                          result = s;
                                       } else {
                                          result = this.invokePointer(callFlags, args);
                                          if (result != null) {
                                             s = Structure.newInstance(returnType, (Pointer)result);
                                             s.conditionalAutoRead();
                                             result = s;
                                          }
                                       }
                                    } else if (Callback.class.isAssignableFrom(returnType)) {
                                       result = this.invokePointer(callFlags, args);
                                       if (result != null) {
                                          result = CallbackReference.getCallback(returnType, (Pointer)result);
                                       }
                                    } else {
                                       Pointer p;
                                       if (returnType == String[].class) {
                                          p = this.invokePointer(callFlags, args);
                                          if (p != null) {
                                             result = p.getStringArray(0L, this.encoding);
                                          }
                                       } else if (returnType == WString[].class) {
                                          p = this.invokePointer(callFlags, args);
                                          if (p != null) {
                                             String[] arr = p.getWideStringArray(0L);
                                             WString[] warr = new WString[arr.length];

                                             for(int i = 0; i < arr.length; ++i) {
                                                warr[i] = new WString(arr[i]);
                                             }

                                             result = warr;
                                          }
                                       } else if (returnType == Pointer[].class) {
                                          p = this.invokePointer(callFlags, args);
                                          if (p != null) {
                                             result = p.getPointerArray(0L);
                                          }
                                       } else {
                                          if (!allowObjects) {
                                             throw new IllegalArgumentException("Unsupported return type " + returnType + " in function " + this.getName());
                                          }

                                          result = Native.invokeObject(this, this.peer, callFlags, args);
                                          if (result != null && !returnType.isAssignableFrom(result.getClass())) {
                                             throw new ClassCastException("Return type " + returnType + " does not match result " + result.getClass());
                                          }
                                       }
                                    }
                                 }
                              } else {
                                 result = Native.invokeDouble(this, this.peer, callFlags, args);
                              }
                           } else {
                              result = Native.invokeFloat(this, this.peer, callFlags, args);
                           }
                        } else {
                           result = Native.invokeLong(this, this.peer, callFlags, args);
                        }
                     } else {
                        result = Native.invokeInt(this, this.peer, callFlags, args);
                     }
                  } else {
                     result = (char)Native.invokeInt(this, this.peer, callFlags, args);
                  }
               } else {
                  result = (short)Native.invokeInt(this, this.peer, callFlags, args);
               }
            } else {
               result = (byte)Native.invokeInt(this, this.peer, callFlags, args);
            }
         } else {
            result = valueOf(Native.invokeInt(this, this.peer, callFlags, args) != 0);
         }
      } else {
         Native.invokeVoid(this, this.peer, callFlags, args);
         result = null;
      }

      return result;
   }

   private Pointer invokePointer(int callFlags, Object[] args) {
      long ptr = Native.invokePointer(this, this.peer, callFlags, args);
      return ptr == 0L ? null : new Pointer(ptr);
   }

   private Object convertArgument(Object[] args, int index, Method invokingMethod, TypeMapper mapper, boolean allowObjects, Class expectedType) {
      Object arg = args[index];
      Class argClass;
      if (arg != null) {
         argClass = arg.getClass();
         ToNativeConverter converter = null;
         if (NativeMapped.class.isAssignableFrom(argClass)) {
            converter = NativeMappedConverter.getInstance(argClass);
         } else if (mapper != null) {
            converter = mapper.getToNativeConverter(argClass);
         }

         if (converter != null) {
            Object context;
            if (invokingMethod != null) {
               context = new MethodParameterContext(this, args, index, invokingMethod);
            } else {
               context = new FunctionParameterContext(this, args, index);
            }

            arg = ((ToNativeConverter)converter).toNative(arg, (ToNativeContext)context);
         }
      }

      if (arg != null && !this.isPrimitiveArray(arg.getClass())) {
         argClass = arg.getClass();
         Class type;
         if (arg instanceof Structure) {
            Structure struct = (Structure)arg;
            struct.autoWrite();
            if (struct instanceof Structure.ByValue) {
               type = struct.getClass();
               if (invokingMethod != null) {
                  Class[] ptypes = invokingMethod.getParameterTypes();
                  if (IS_VARARGS.isVarArgs(invokingMethod)) {
                     if (index < ptypes.length - 1) {
                        type = ptypes[index];
                     } else {
                        Class etype = ptypes[ptypes.length - 1].getComponentType();
                        if (etype != Object.class) {
                           type = etype;
                        }
                     }
                  } else {
                     type = ptypes[index];
                  }
               }

               if (Structure.ByValue.class.isAssignableFrom(type)) {
                  return struct;
               }
            }

            return struct.getPointer();
         } else if (arg instanceof Callback) {
            return CallbackReference.getFunctionPointer((Callback)arg);
         } else if (arg instanceof String) {
            return (new NativeString((String)arg, false)).getPointer();
         } else if (arg instanceof WString) {
            return (new NativeString(arg.toString(), true)).getPointer();
         } else if (arg instanceof Boolean) {
            return Boolean.TRUE.equals(arg) ? INTEGER_TRUE : INTEGER_FALSE;
         } else if (String[].class == argClass) {
            return new StringArray((String[])((String[])arg), this.encoding);
         } else if (WString[].class == argClass) {
            return new StringArray((WString[])((WString[])arg));
         } else if (Pointer[].class == argClass) {
            return new Function.PointerArray((Pointer[])((Pointer[])arg));
         } else if (NativeMapped[].class.isAssignableFrom(argClass)) {
            return new Function.NativeMappedArray((NativeMapped[])((NativeMapped[])arg));
         } else if (!Structure[].class.isAssignableFrom(argClass)) {
            if (argClass.isArray()) {
               throw new IllegalArgumentException("Unsupported array argument type: " + argClass.getComponentType());
            } else if (allowObjects) {
               return arg;
            } else if (!Native.isSupportedNativeType(arg.getClass())) {
               throw new IllegalArgumentException("Unsupported argument type " + arg.getClass().getName() + " at parameter " + index + " of function " + this.getName());
            } else {
               return arg;
            }
         } else {
            Structure[] ss = (Structure[])((Structure[])arg);
            type = argClass.getComponentType();
            boolean byRef = Structure.ByReference.class.isAssignableFrom(type);
            if (expectedType != null && !Structure.ByReference[].class.isAssignableFrom(expectedType)) {
               if (byRef) {
                  throw new IllegalArgumentException("Function " + this.getName() + " declared Structure[] at parameter " + index + " but array of " + type + " was passed");
               }

               for(int i = 0; i < ss.length; ++i) {
                  if (ss[i] instanceof Structure.ByReference) {
                     throw new IllegalArgumentException("Function " + this.getName() + " declared Structure[] at parameter " + index + " but element " + i + " is of Structure.ByReference type");
                  }
               }
            }

            if (byRef) {
               Structure.autoWrite(ss);
               Pointer[] pointers = new Pointer[ss.length + 1];

               for(int i = 0; i < ss.length; ++i) {
                  pointers[i] = ss[i] != null ? ss[i].getPointer() : null;
               }

               return new Function.PointerArray(pointers);
            } else if (ss.length == 0) {
               throw new IllegalArgumentException("Structure array must have non-zero length");
            } else if (ss[0] == null) {
               Structure.newInstance(type).toArray(ss);
               return ss[0].getPointer();
            } else {
               Structure.autoWrite(ss);
               return ss[0].getPointer();
            }
         }
      } else {
         return arg;
      }
   }

   private boolean isPrimitiveArray(Class argClass) {
      return argClass.isArray() && argClass.getComponentType().isPrimitive();
   }

   public void invoke(Object[] args) {
      this.invoke(Void.class, args);
   }

   private String invokeString(int callFlags, Object[] args, boolean wide) {
      Pointer ptr = this.invokePointer(callFlags, args);
      String s = null;
      if (ptr != null) {
         if (wide) {
            s = ptr.getWideString(0L);
         } else {
            s = ptr.getString(0L, this.encoding);
         }
      }

      return s;
   }

   public String toString() {
      return this.library != null ? "native function " + this.functionName + "(" + this.library.getName() + ")@0x" + Long.toHexString(this.peer) : "native function@0x" + Long.toHexString(this.peer);
   }

   public Object invokeObject(Object[] args) {
      return this.invoke(Object.class, args);
   }

   public Pointer invokePointer(Object[] args) {
      return (Pointer)this.invoke(Pointer.class, args);
   }

   public String invokeString(Object[] args, boolean wide) {
      Object o = this.invoke(wide ? WString.class : String.class, args);
      return o != null ? o.toString() : null;
   }

   public int invokeInt(Object[] args) {
      return (Integer)this.invoke(Integer.class, args);
   }

   public long invokeLong(Object[] args) {
      return (Long)this.invoke(Long.class, args);
   }

   public float invokeFloat(Object[] args) {
      return (Float)this.invoke(Float.class, args);
   }

   public double invokeDouble(Object[] args) {
      return (Double)this.invoke(Double.class, args);
   }

   public void invokeVoid(Object[] args) {
      this.invoke(Void.class, args);
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (o == null) {
         return false;
      } else if (o.getClass() != this.getClass()) {
         return false;
      } else {
         Function other = (Function)o;
         return other.callFlags == this.callFlags && other.options.equals(this.options) && other.peer == this.peer;
      }
   }

   public int hashCode() {
      return this.callFlags + this.options.hashCode() + super.hashCode();
   }

   static Object[] concatenateVarArgs(Object[] inArgs) {
      if (inArgs != null && inArgs.length > 0) {
         Object lastArg = inArgs[inArgs.length - 1];
         Class argType = lastArg != null ? lastArg.getClass() : null;
         if (argType != null && argType.isArray()) {
            Object[] varArgs = (Object[])((Object[])lastArg);

            for(int i = 0; i < varArgs.length; ++i) {
               if (varArgs[i] instanceof Float) {
                  varArgs[i] = (double)(Float)varArgs[i];
               }
            }

            Object[] fullArgs = new Object[inArgs.length + varArgs.length];
            System.arraycopy(inArgs, 0, fullArgs, 0, inArgs.length - 1);
            System.arraycopy(varArgs, 0, fullArgs, inArgs.length - 1, varArgs.length);
            fullArgs[fullArgs.length - 1] = null;
            inArgs = fullArgs;
         }
      }

      return inArgs;
   }

   static boolean isVarArgs(Method m) {
      return IS_VARARGS.isVarArgs(m);
   }

   static int fixedArgs(Method m) {
      return IS_VARARGS.fixedArgs(m);
   }

   static Boolean valueOf(boolean b) {
      return b ? Boolean.TRUE : Boolean.FALSE;
   }

   private static class PointerArray extends Memory implements Function.PostCallRead {
      private final Pointer[] original;

      public PointerArray(Pointer[] arg) {
         super((long)(Pointer.SIZE * (arg.length + 1)));
         this.original = arg;

         for(int i = 0; i < arg.length; ++i) {
            this.setPointer((long)(i * Pointer.SIZE), arg[i]);
         }

         this.setPointer((long)(Pointer.SIZE * arg.length), (Pointer)null);
      }

      public void read() {
         this.read(0L, this.original, 0, this.original.length);
      }
   }

   private static class NativeMappedArray extends Memory implements Function.PostCallRead {
      private final NativeMapped[] original;

      public NativeMappedArray(NativeMapped[] arg) {
         super((long)Native.getNativeSize(arg.getClass(), arg));
         this.original = arg;
         this.setValue(0L, this.original, this.original.getClass());
      }

      public void read() {
         this.getValue(0L, this.original.getClass(), this.original);
      }
   }

   public interface PostCallRead {
      void read();
   }
}
