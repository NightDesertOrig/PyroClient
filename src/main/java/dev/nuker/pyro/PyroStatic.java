/*
 * Decompiled with CFR 0.150.
 */
package dev.nuker.pyro;

import dev.nuker.pyro.f6L;
import dev.nuker.pyro.f6R;
import dev.nuker.pyro.f6U;
import dev.nuker.pyro.f6V;
import dev.nuker.pyro.f6W;
import dev.nuker.pyro.f6Y;
import dev.nuker.pyro.f6j;
import dev.nuker.pyro.f6m;
import dev.nuker.pyro.f7Q;
import dev.nuker.pyro.f7R;
import dev.nuker.pyro.f7W;
import dev.nuker.pyro.f7X;
import dev.nuker.pyro.f7Y;
import dev.nuker.pyro.f7d;
import dev.nuker.pyro.f7p;
import dev.nuker.pyro.f7s;
import dev.nuker.pyro.f7t;
import dev.nuker.pyro.f7u;
import dev.nuker.pyro.f7v;
import dev.nuker.pyro.f8A;
import dev.nuker.pyro.f8D;
import dev.nuker.pyro.f8E;
import dev.nuker.pyro.f8K;
import dev.nuker.pyro.f8c;
import dev.nuker.pyro.f8g;
import dev.nuker.pyro.f8j;
import dev.nuker.pyro.f8l;
import dev.nuker.pyro.f8s;
import dev.nuker.pyro.f8t;
import dev.nuker.pyro.f8u;
import dev.nuker.pyro.f9A;
import dev.nuker.pyro.f9C;
import dev.nuker.pyro.f9D;
import dev.nuker.pyro.f9E;
import dev.nuker.pyro.f9F;
import dev.nuker.pyro.f9H;
import dev.nuker.pyro.f9T;
import dev.nuker.pyro.f9V;
import dev.nuker.pyro.f9Z;
import dev.nuker.pyro.f9j;
import dev.nuker.pyro.faI;
import dev.nuker.pyro.faJ;
import dev.nuker.pyro.faN;
import dev.nuker.pyro.faQ;
import dev.nuker.pyro.faS;
import dev.nuker.pyro.fbB;
import dev.nuker.pyro.fbC;
import dev.nuker.pyro.fbD;
import dev.nuker.pyro.fbE;
import dev.nuker.pyro.fbF;
import dev.nuker.pyro.fbJ;
import dev.nuker.pyro.fbO;
import dev.nuker.pyro.fbZ;
import dev.nuker.pyro.fbh;
import dev.nuker.pyro.fbn;
import dev.nuker.pyro.fbu;
import dev.nuker.pyro.fby;
import dev.nuker.pyro.fda;
import dev.nuker.pyro.fdh;
import dev.nuker.pyro.fdk;
import dev.nuker.pyro.modules.advanced.AutoPilot;
import dev.nuker.pyro.modules.advanced.StashSorter;
import dev.nuker.pyro.modules.combat.*;
import dev.nuker.pyro.modules.exploit.*;
import dev.nuker.pyro.modules.misc.*;
import dev.nuker.pyro.modules.movement.*;
import dev.nuker.pyro.modules.movement.elytraflight2.ElytraFlight2;
import dev.nuker.pyro.modules.render.*;
import dev.nuker.pyro.modules.world.*;

public class PyroStatic {
    public static AutoPilot Field6403 = new AutoPilot();
    public static ElytraFlight2 Field6404 = new ElytraFlight2();
    public static YawLock Field6405 = new YawLock();
    public static Step Field6406 = new Step();
    public static f6j Field6407 = new f6j();
    public static Surround Field6408 = new Surround();
    public static fdk Field6409 = new fdk();
    public static AntiGapDisease Field6410 = new AntiGapDisease();
    public static f8u Field6411 = new f8u();
    public static Timer Field6412 = new Timer();
    public static f7W Field6413 = new f7W();
    public static AntiDisconnect Field6414 = new AntiDisconnect();
    public static AutoReconnect Field6415 = new AutoReconnect();
    public static AutoCrystal Field6416 = new AutoCrystal();
    public static Freecam Field6417 = new Freecam();
    public static fbJ Field6418 = new fbJ();
    public static BetterChat Field6419 = new BetterChat();
    public static ClickGUI Field6420 = new ClickGUI();
    public static f8j Field6421 = new f8j();
    public static f6L Field6422 = new f6L();
    public static f6R Field6423 = new f6R();
    public static Wallhack Field6424 = new Wallhack();
    public static Baritone Field6425 = new Baritone();
    public static f8E Field6426 = new f8E();
    public static faN Field6427 = new faN();
    public static AutoTotem Field6428 = new AutoTotem();
    public static f9V Field6429 = new f9V();
    public static f9T Field6430 = new f9T();
    public static Velocity Field6431 = new Velocity();
    public static f9E Field6432 = new f9E();
    public static NameTags Field6433 = new NameTags();
    public static ESP Field6434 = new ESP();
    public static Criticals Field6435 = new Criticals();
    public static Jesus Field6436 = new Jesus();
    public static f9C Field6437 = new f9C();
    public static IceSpeed Field6438 = new IceSpeed();
    public static Avoid Field6439 = new Avoid();
    public static FastPlace Field6440 = new FastPlace();
    public static fbZ Field6441 = new fbZ();
    public static f9D Field6442 = new f9D();
    public static fbu Field6443 = new fbu();
    public static AutoTrap Field6444 = new AutoTrap();
    public static Aura Field6445 = new Aura();
    public static Trigger Field6446 = new Trigger();
    public static Flight Field6447 = new Flight();
    public static AntiHunger Field6448 = new AntiHunger();
    public static f7p Field6449 = new f7p();
    public static f7s Field6450 = new f7s();
    public static LiquidInteract Field6451 = new LiquidInteract();
    public static NewChunks Field6452 = new NewChunks();
    public static HoleESP Field6453 = new HoleESP();
    public static AutoMount Field6454 = new AutoMount();
    public static f9A Field6455 = new f9A();
    public static EntityControl Field6456 = new EntityControl();
    public static f9j Field6457 = new f9j();
    public static fda Field6458 = new fda();
    public static Anchor Field6459 = new Anchor();
    public static AutoMine Field6460 = new AutoMine();
    public static Blink Field6461 = new Blink();
    public static ReverseStep Field6462 = new ReverseStep();
    public static f6Y Field6463 = new f6Y();
    public static faS Field6464 = new faS();
    public static Chams Field6465 = new Chams();
    public static Speedmine Field6466 = new Speedmine();
    public static faJ Field6467 = new faJ();
    public static fbC Field6468 = new fbC();
    public static AntiAim Field6469 = new AntiAim();
    public static Compass Field6470 = new Compass();
    public static fbO Field6471 = new fbO();
    public static NoMiningTrace Field6472 = new NoMiningTrace();
    public static f9F Field6473 = new f9F();
    public static f8K Field6474 = new f8K();
    public static FastSwim Field6475 = new FastSwim();
    public static NameProtect Field6476 = new NameProtect();
    public static NoBob Field6477 = new NoBob();
    public static fbE Field6478 = new fbE();
    public static f7t Field6479 = new f7t();
    public static Burrow Field6480 = new Burrow();
    public static f7u Field6481 = new f7u();
    public static Radar Field6482 = new Radar();
    public static AutoPot Field6483 = new AutoPot();
    public static f7d Field6484 = new f7d();
    public static Swing Field6485 = new Swing();
    public static NoSoundLag Field6486 = new NoSoundLag();
    public static AutoRespawn Field6487 = new AutoRespawn();
    public static PingSpoof Field6488 = new PingSpoof();
    public static fby Field6489 = new fby();
    public static f6U Field6490 = new f6U();
    public static AutoLog Field6491 = new AutoLog();
    public static fbF Field6492 = new fbF();
    public static f9H Field6493 = new f9H();
    public static CombatNotifier Field6494 = new CombatNotifier();
    public static CityESP Field6495 = new CityESP();
    public static Refill Field6496 = new Refill();
    public static ChatNotifier Field6497 = new ChatNotifier();
    public static f7Y Field6498 = new f7Y();
    public static f7X Field6499 = new f7X();
    public static fbh Field6500 = new fbh();
    public static Fall Field6501 = new Fall();
    public static f6V Field6502 = new f6V();
    public static NoInteract Field6503 = new NoInteract();
    public static fdh Field6504 = new fdh();
    public static AutoTool Field6505 = new AutoTool();
    public static f6W Field6506 = new f6W();
    public static ExtraTab Field6507 = new ExtraTab();
    public static f6m Field6508 = new f6m();
    public static MaxHeightInteract Field6509 = new MaxHeightInteract();
    public static HoleFiller Field6510 = new HoleFiller();
    public static AirStrafe Field6511 = new AirStrafe();
    public static faI Field6512 = new faI();
    public static BreakHighlight Field6513 = new BreakHighlight();
    public static KeyPearl Field6514 = new KeyPearl();
    public static Trajectories Field6515 = new Trajectories();
    public static HighJump Field6516 = new HighJump();
    public static CityBoss Field6517 = new CityBoss();
    public static BoatFly Field6518 = new BoatFly();
    public static f8t Field6519 = new f8t();
    public static fbB Field6520 = new fbB();
    public static fbD Field6521 = new fbD();
    public static f8l Field6522 = new f8l();
    public static faQ Field6523 = new faQ();
    public static BowAim Field6524 = new BowAim();
    public static Waypoints Field6525 = new Waypoints();
    public static f7R Field6526 = new f7R();
    public static AutoFish Field6527 = new AutoFish();
    public static ItemViewModel Field6528 = new ItemViewModel();
    public static f8D Field6529 = new f8D();
    public static f9Z Field6530 = new f9Z();
    public static f8g Field6531 = new f8g();
    public static Sneak Field6532 = new Sneak();
    public static CoordTpExploit Field6533 = new CoordTpExploit();
    public static DurabilityAlert Field6534 = new DurabilityAlert();
    public static AntiBots Field6535 = new AntiBots();
    public static f8A Field6536 = new f8A();
    public static HUDEditor Field6537 = new HUDEditor();
    public static AutoTunnel Field6538 = new AutoTunnel();
    public static f8s Field6539 = new f8s();
    public static ChatSuffix Field6540 = new ChatSuffix();
    public static AutoDupe Field6541 = new AutoDupe();
    public static PacketSniffer Field6542 = new PacketSniffer();
    public static f8c Field6543 = new f8c();
    public static StashSorter Field6544 = new StashSorter();
    public static CoordChat Field6545 = new CoordChat();
    public static fbn Field6546 = new fbn();
    public static f7v Field6547 = new f7v();
    public static EntityDesync Field6548 = new EntityDesync();
    public static f7Q Field6549 = new f7Q();
}

