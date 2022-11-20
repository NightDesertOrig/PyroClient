package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.util.HiDPIUtils;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;

public class JBRCustomDecorations {
   private static boolean initialized;
   private static Method Window_hasCustomDecoration;
   private static Method Window_setHasCustomDecoration;
   private static Method WWindowPeer_setCustomDecorationHitTestSpots;
   private static Method WWindowPeer_setCustomDecorationTitleBarHeight;
   private static Method AWTAccessor_getComponentAccessor;
   private static Method AWTAccessor_ComponentAccessor_getPeer;

   public static boolean isSupported() {
      initialize();
      return Window_setHasCustomDecoration != null;
   }

   static void install(final JRootPane rootPane) {
      if (isSupported()) {
         if (rootPane.getParent() == null) {
            HierarchyListener addListener = new HierarchyListener() {
               public void hierarchyChanged(HierarchyEvent e) {
                  if (e.getChanged() == rootPane && (e.getChangeFlags() & 1L) != 0L) {
                     Container parent = e.getChangedParent();
                     if (parent instanceof Window) {
                        JBRCustomDecorations.install((Window)parent);
                     }

                     EventQueue.invokeLater(() -> {
                        rootPane.removeHierarchyListener(this);
                     });
                  }
               }
            };
            rootPane.addHierarchyListener(addListener);
         }
      }
   }

   static void install(Window window) {
      if (isSupported()) {
         if (!UIManager.getLookAndFeel().getSupportsWindowDecorations()) {
            if (window instanceof JFrame) {
               JFrame frame = (JFrame)window;
               if (!JFrame.isDefaultLookAndFeelDecorated() && !FlatSystemProperties.getBoolean("flatlaf.useJetBrainsCustomDecorations", false)) {
                  return;
               }

               if (frame.isUndecorated()) {
                  return;
               }

               setHasCustomDecoration(frame);
               frame.getRootPane().setWindowDecorationStyle(1);
            } else if (window instanceof JDialog) {
               JDialog dialog = (JDialog)window;
               if (!JDialog.isDefaultLookAndFeelDecorated() && !FlatSystemProperties.getBoolean("flatlaf.useJetBrainsCustomDecorations", false)) {
                  return;
               }

               if (dialog.isUndecorated()) {
                  return;
               }

               setHasCustomDecoration(dialog);
               dialog.getRootPane().setWindowDecorationStyle(2);
            }

         }
      }
   }

   static boolean hasCustomDecoration(Window window) {
      if (!isSupported()) {
         return false;
      } else {
         try {
            return (Boolean)Window_hasCustomDecoration.invoke(window);
         } catch (Exception var2) {
            Logger.getLogger(FlatLaf.class.getName()).log(Level.SEVERE, (String)null, var2);
            return false;
         }
      }
   }

   static void setHasCustomDecoration(Window window) {
      if (isSupported()) {
         try {
            Window_setHasCustomDecoration.invoke(window);
         } catch (Exception var2) {
            Logger.getLogger(FlatLaf.class.getName()).log(Level.SEVERE, (String)null, var2);
         }

      }
   }

   static void setHitTestSpotsAndTitleBarHeight(Window window, List hitTestSpots, int titleBarHeight) {
      if (isSupported()) {
         try {
            Object compAccessor = AWTAccessor_getComponentAccessor.invoke((Object)null);
            Object peer = AWTAccessor_ComponentAccessor_getPeer.invoke(compAccessor, window);
            WWindowPeer_setCustomDecorationHitTestSpots.invoke(peer, hitTestSpots);
            WWindowPeer_setCustomDecorationTitleBarHeight.invoke(peer, titleBarHeight);
         } catch (Exception var5) {
            Logger.getLogger(FlatLaf.class.getName()).log(Level.SEVERE, (String)null, var5);
         }

      }
   }

   private static void initialize() {
      if (!initialized) {
         initialized = true;
         if (SystemInfo.IS_JETBRAINS_JVM_11_OR_LATER && SystemInfo.IS_WINDOWS_10_OR_LATER) {
            if (FlatSystemProperties.getBoolean("flatlaf.useJetBrainsCustomDecorations", true)) {
               try {
                  Class awtAcessorClass = Class.forName("sun.awt.AWTAccessor");
                  Class compAccessorClass = Class.forName("sun.awt.AWTAccessor$ComponentAccessor");
                  AWTAccessor_getComponentAccessor = awtAcessorClass.getDeclaredMethod("getComponentAccessor");
                  AWTAccessor_ComponentAccessor_getPeer = compAccessorClass.getDeclaredMethod("getPeer", Component.class);
                  Class peerClass = Class.forName("sun.awt.windows.WWindowPeer");
                  WWindowPeer_setCustomDecorationHitTestSpots = peerClass.getDeclaredMethod("setCustomDecorationHitTestSpots", List.class);
                  WWindowPeer_setCustomDecorationTitleBarHeight = peerClass.getDeclaredMethod("setCustomDecorationTitleBarHeight", Integer.TYPE);
                  WWindowPeer_setCustomDecorationHitTestSpots.setAccessible(true);
                  WWindowPeer_setCustomDecorationTitleBarHeight.setAccessible(true);
                  Window_hasCustomDecoration = Window.class.getDeclaredMethod("hasCustomDecoration");
                  Window_setHasCustomDecoration = Window.class.getDeclaredMethod("setHasCustomDecoration");
                  Window_hasCustomDecoration.setAccessible(true);
                  Window_setHasCustomDecoration.setAccessible(true);
               } catch (Exception var3) {
               }

            }
         }
      }
   }

   static class JBRWindowTopBorder extends EmptyBorderUIResource {
      private static JBRCustomDecorations.JBRWindowTopBorder instance;
      private final Color defaultActiveBorder = new Color(7368816);
      private final Color inactiveLightColor = new Color(11184810);
      private final Color inactiveDarkColor = new Color(4144959);
      private boolean colorizationAffectsBorders;
      private Color activeColor;

      static JBRCustomDecorations.JBRWindowTopBorder getInstance() {
         if (instance == null) {
            instance = new JBRCustomDecorations.JBRWindowTopBorder();
         }

         return instance;
      }

      private JBRWindowTopBorder() {
         super(1, 0, 0, 0);
         this.activeColor = this.defaultActiveBorder;
         this.colorizationAffectsBorders = this.calculateAffectsBorders();
         this.activeColor = this.calculateActiveBorderColor();
         Toolkit toolkit = Toolkit.getDefaultToolkit();
         toolkit.addPropertyChangeListener("win.dwm.colorizationColor.affects.borders", (e) -> {
            this.colorizationAffectsBorders = this.calculateAffectsBorders();
            this.activeColor = this.calculateActiveBorderColor();
         });
         PropertyChangeListener l = (e) -> {
            this.activeColor = this.calculateActiveBorderColor();
         };
         toolkit.addPropertyChangeListener("win.dwm.colorizationColor", l);
         toolkit.addPropertyChangeListener("win.dwm.colorizationColorBalance", l);
         toolkit.addPropertyChangeListener("win.frame.activeBorderColor", l);
      }

      private boolean calculateAffectsBorders() {
         Object value = Toolkit.getDefaultToolkit().getDesktopProperty("win.dwm.colorizationColor.affects.borders");
         return value instanceof Boolean ? (Boolean)value : true;
      }

      private Color calculateActiveBorderColor() {
         if (!this.colorizationAffectsBorders) {
            return this.defaultActiveBorder;
         } else {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Color colorizationColor = (Color)toolkit.getDesktopProperty("win.dwm.colorizationColor");
            if (colorizationColor != null) {
               Object colorizationColorBalanceObj = toolkit.getDesktopProperty("win.dwm.colorizationColorBalance");
               if (colorizationColorBalanceObj instanceof Integer) {
                  int colorizationColorBalance = (Integer)colorizationColorBalanceObj;
                  if (colorizationColorBalance < 0) {
                     colorizationColorBalance = 100;
                  }

                  if (colorizationColorBalance == 0) {
                     return new Color(14277081);
                  } else if (colorizationColorBalance == 100) {
                     return colorizationColor;
                  } else {
                     float alpha = (float)colorizationColorBalance / 100.0F;
                     float remainder = 1.0F - alpha;
                     int r = Math.round((float)colorizationColor.getRed() * alpha + 217.0F * remainder);
                     int g = Math.round((float)colorizationColor.getGreen() * alpha + 217.0F * remainder);
                     int b = Math.round((float)colorizationColor.getBlue() * alpha + 217.0F * remainder);
                     return new Color(r, g, b);
                  }
               } else {
                  return colorizationColor;
               }
            } else {
               Color activeBorderColor = (Color)toolkit.getDesktopProperty("win.frame.activeBorderColor");
               return activeBorderColor != null ? activeBorderColor : UIManager.getColor("MenuBar.borderColor");
            }
         }
      }

      public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
         Window window = SwingUtilities.windowForComponent(c);
         boolean active = window != null ? window.isActive() : false;
         g.setColor(active ? this.activeColor : (FlatLaf.isLafDark() ? this.inactiveDarkColor : this.inactiveLightColor));
         HiDPIUtils.paintAtScale1x((Graphics2D)g, x, y, width, height, this::paintImpl);
      }

      private void paintImpl(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
         g.drawRect(x, y, width - 1, 0);
      }

      void repaintBorder(Component c) {
         c.repaint(0, 0, c.getWidth(), 1);
      }
   }
}
