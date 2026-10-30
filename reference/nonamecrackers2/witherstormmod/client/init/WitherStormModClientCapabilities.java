/*     */ package nonamecrackers2.witherstormmod.client.init;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.player.LocalPlayer;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*     */ import net.minecraftforge.common.capabilities.CapabilityToken;
/*     */ import net.minecraftforge.common.capabilities.ICapabilityProvider;
/*     */ import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
/*     */ import net.minecraftforge.common.util.LazyOptional;
/*     */ import net.minecraftforge.common.util.NonNullSupplier;
/*     */ import net.minecraftforge.event.AttachCapabilitiesEvent;
/*     */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*     */ import nonamecrackers2.witherstormmod.client.audio.ISoundManager;
/*     */ import nonamecrackers2.witherstormmod.client.audio.bosstheme.BossThemeManager;
/*     */ import nonamecrackers2.witherstormmod.client.capability.BowelsEffectsManager;
/*     */ import nonamecrackers2.witherstormmod.client.capability.CommandBlockSoundManager;
/*     */ import nonamecrackers2.witherstormmod.client.capability.FormidiBladeLoopManager;
/*     */ import nonamecrackers2.witherstormmod.client.capability.FormidibombEffectsManager;
/*     */ import nonamecrackers2.witherstormmod.client.capability.PlayerCameraShaker;
/*     */ import nonamecrackers2.witherstormmod.client.capability.PlayerScreenBlinder;
/*     */ import nonamecrackers2.witherstormmod.client.capability.PlayerTractorBeamEffects;
/*     */ import nonamecrackers2.witherstormmod.client.capability.SoundManagersHolder;
/*     */ import nonamecrackers2.witherstormmod.client.capability.WitherStormDistantRenderer;
/*     */ import nonamecrackers2.witherstormmod.client.capability.WitherStormHeadSoundManager;
/*     */ import nonamecrackers2.witherstormmod.client.capability.WitherStormLoopingSoundManager;
/*     */ import nonamecrackers2.witherstormmod.client.capability.WitheredSymbiontSoundManager;
/*     */ import nonamecrackers2.witherstormmod.client.capability.WitheredSymbiontSpellLoopManager;
/*     */ import nonamecrackers2.witherstormmod.client.event.WitherStormAmbienceEffects;
/*     */ 
/*     */ public class WitherStormModClientCapabilities {
/*  36 */   public static final Capability<WitherStormDistantRenderer> DISTANT_RENDERER = CapabilityManager.get(new CapabilityToken<WitherStormDistantRenderer>() {  }
/*  37 */     ); public static final Capability<SoundManagersHolder> SOUND_MANAGERS = CapabilityManager.get(new CapabilityToken<SoundManagersHolder>() {  }
/*  38 */     ); public static final Capability<WitherStormLoopingSoundManager> LOOPING_MANAGER = CapabilityManager.get(new CapabilityToken<WitherStormLoopingSoundManager>() {  }
/*  39 */     ); public static final Capability<PlayerCameraShaker> CAMERA_SHAKER = CapabilityManager.get(new CapabilityToken<PlayerCameraShaker>() {  }
/*  40 */     ); public static final Capability<FormidibombEffectsManager> FORMIDIBOMB_EFFECTS = CapabilityManager.get(new CapabilityToken<FormidibombEffectsManager>() {  }
/*  41 */     ); public static final Capability<PlayerScreenBlinder> SCREEN_BLINDER = CapabilityManager.get(new CapabilityToken<PlayerScreenBlinder>() {  }
/*  42 */     ); public static final Capability<CommandBlockSoundManager> COMMAND_BLOCK_SOUND_MANAGER = CapabilityManager.get(new CapabilityToken<CommandBlockSoundManager>() {  }
/*  43 */     ); public static final Capability<WitheredSymbiontSpellLoopManager> WITHERED_SYMBIONT_SPELL_LOOP_MANAGER = CapabilityManager.get(new CapabilityToken<WitheredSymbiontSpellLoopManager>() {  }
/*  44 */     ); public static final Capability<WitheredSymbiontSoundManager> WITHERED_SYMBIONT_SOUND_MANAGER = CapabilityManager.get(new CapabilityToken<WitheredSymbiontSoundManager>() {  }
/*  45 */     ); public static final Capability<BossThemeManager> BOSS_THEME_MANAGER = CapabilityManager.get(new CapabilityToken<BossThemeManager>() {  }
/*  46 */     ); public static final Capability<BowelsEffectsManager> BOWELS_EFFECTS_MANAGER = CapabilityManager.get(new CapabilityToken<BowelsEffectsManager>() {  }
/*  47 */     ); public static final Capability<WitherStormHeadSoundManager> WITHER_STORM_HEAD_SOUND_MANAGER = CapabilityManager.get(new CapabilityToken<WitherStormHeadSoundManager>() {  }
/*  48 */     ); public static final Capability<PlayerTractorBeamEffects> TRACTOR_BEAM_EFFECTS = CapabilityManager.get(new CapabilityToken<PlayerTractorBeamEffects>() {  }
/*  49 */     ); public static final Capability<WitherStormAmbienceEffects> AMBIENT_EFFECTS = CapabilityManager.get(new CapabilityToken<WitherStormAmbienceEffects>() {  }
/*  50 */     ); public static final Capability<FormidiBladeLoopManager> FORMIDI_BLADE_LOOP_MANAGER = CapabilityManager.get(new CapabilityToken<FormidiBladeLoopManager>() {  }
/*     */     );
/*     */   
/*     */   public static void registerCapabilities(RegisterCapabilitiesEvent event) {
/*  54 */     event.register(WitherStormDistantRenderer.class);
/*  55 */     event.register(SoundManagersHolder.class);
/*  56 */     event.register(WitherStormLoopingSoundManager.class);
/*  57 */     event.register(PlayerCameraShaker.class);
/*  58 */     event.register(FormidibombEffectsManager.class);
/*  59 */     event.register(PlayerScreenBlinder.class);
/*  60 */     event.register(CommandBlockSoundManager.class);
/*  61 */     event.register(WitheredSymbiontSpellLoopManager.class);
/*  62 */     event.register(WitheredSymbiontSoundManager.class);
/*  63 */     event.register(BossThemeManager.class);
/*  64 */     event.register(BowelsEffectsManager.class);
/*  65 */     event.register(WitherStormHeadSoundManager.class);
/*  66 */     event.register(PlayerTractorBeamEffects.class);
/*  67 */     event.register(WitherStormAmbienceEffects.class);
/*  68 */     event.register(FormidiBladeLoopManager.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void attachWorldCapabilities(AttachCapabilitiesEvent<Level> event) {
/*  73 */     Level world = (Level)event.getObject();
/*  74 */     if (world.f_46443_) {
/*     */       
/*  76 */       Minecraft mc = Minecraft.m_91087_();
/*  77 */       final LazyOptional<WitherStormDistantRenderer> distantRenderer = LazyOptional.of(() -> new WitherStormDistantRenderer(mc));
/*  78 */       event.addCapability(new ResourceLocation("witherstormmod", "distant_renderer"), new ICapabilityProvider()
/*     */           {
/*     */             
/*     */             public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
/*     */             {
/*  83 */               return (capability == WitherStormModClientCapabilities.DISTANT_RENDERER) ? distantRenderer.cast() : LazyOptional.empty();
/*     */             }
/*     */           });
/*  86 */       Objects.requireNonNull(distantRenderer); event.addListener(distantRenderer::invalidate);
/*     */       
/*  88 */       if (world.m_46472_().m_135782_().equals(WitherStormMod.bowelsLocation())) {
/*     */         
/*  90 */         final LazyOptional<BowelsEffectsManager> bowelsEffects = LazyOptional.of(() -> new BowelsEffectsManager(mc));
/*  91 */         event.addCapability(new ResourceLocation("witherstormmod", "bowels_effects_manager"), new ICapabilityProvider()
/*     */             {
/*     */               
/*     */               public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
/*     */               {
/*  96 */                 return (capability == WitherStormModClientCapabilities.BOWELS_EFFECTS_MANAGER) ? bowelsEffects.cast() : LazyOptional.empty();
/*     */               }
/*     */             });
/*  99 */         Objects.requireNonNull(bowelsEffects); event.addListener(bowelsEffects::invalidate);
/*     */       } 
/*     */       
/* 102 */       final LazyOptional<WitherStormAmbienceEffects> ambientEffects = LazyOptional.of(() -> new WitherStormAmbienceEffects(mc));
/* 103 */       event.addCapability(new ResourceLocation("witherstormmod", "wither_storm_ambience_effects"), new ICapabilityProvider()
/*     */           {
/*     */             
/*     */             public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
/*     */             {
/* 108 */               return (cap == WitherStormModClientCapabilities.AMBIENT_EFFECTS) ? ambientEffects.cast() : LazyOptional.empty();
/*     */             }
/*     */           });
/* 111 */       Objects.requireNonNull(ambientEffects); event.addListener(ambientEffects::invalidate);
/*     */       
/* 113 */       SoundManagersHolder holder = new SoundManagersHolder();
/* 114 */       final LazyOptional<SoundManagersHolder> holderOptional = LazyOptional.of(() -> holder);
/* 115 */       event.addCapability(new ResourceLocation("witherstormmod", "sound_managers_holder"), new ICapabilityProvider()
/*     */           {
/*     */             
/*     */             public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
/*     */             {
/* 120 */               return (capability == WitherStormModClientCapabilities.SOUND_MANAGERS) ? holderOptional.cast() : LazyOptional.empty();
/*     */             }
/*     */           });
/* 123 */       Objects.requireNonNull(holderOptional); event.addListener(holderOptional::invalidate);
/*     */       
/* 125 */       registerSoundManager(event, holder, "looping_sound_manager", (ISoundManager)new WitherStormLoopingSoundManager(mc), LOOPING_MANAGER);
/* 126 */       registerSoundManager(event, holder, "formidibomb_effects_manager", (ISoundManager)new FormidibombEffectsManager(mc), FORMIDIBOMB_EFFECTS);
/* 127 */       registerSoundManager(event, holder, "command_block_sound_manager", (ISoundManager)new CommandBlockSoundManager(mc), COMMAND_BLOCK_SOUND_MANAGER);
/* 128 */       registerSoundManager(event, holder, "withered_symbiont_spell_loop_manager", (ISoundManager)new WitheredSymbiontSpellLoopManager(mc), WITHERED_SYMBIONT_SPELL_LOOP_MANAGER);
/* 129 */       registerSoundManager(event, holder, "withered_symbiont_sound_manager", (ISoundManager)new WitheredSymbiontSoundManager(mc), WITHERED_SYMBIONT_SOUND_MANAGER);
/* 130 */       registerSoundManager(event, holder, "boss_theme_manager", (ISoundManager)new BossThemeManager(mc), BOSS_THEME_MANAGER);
/* 131 */       registerSoundManager(event, holder, "wither_storm_head_sound_manager", (ISoundManager)new WitherStormHeadSoundManager(mc), WITHER_STORM_HEAD_SOUND_MANAGER);
/* 132 */       registerSoundManager(event, holder, "formidi_blade_loop_manager", (ISoundManager)new FormidiBladeLoopManager(mc), FORMIDI_BLADE_LOOP_MANAGER);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static <H> void registerSoundManager(AttachCapabilitiesEvent<Level> event, SoundManagersHolder holder, String id, ISoundManager manager, final Capability<H> capability) {
/* 138 */     final LazyOptional<ISoundManager> optional = LazyOptional.of(() -> manager);
/* 139 */     event.addCapability(new ResourceLocation("witherstormmod", id), new ICapabilityProvider()
/*     */         {
/*     */           
/*     */           public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
/*     */           {
/* 144 */             return (cap == capability) ? optional.cast() : LazyOptional.empty();
/*     */           }
/*     */         });
/* 147 */     Objects.requireNonNull(optional); event.addListener(optional::invalidate);
/* 148 */     holder.putManager(manager);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
/* 153 */     Entity entity = (Entity)event.getObject();
/* 154 */     if (entity instanceof LocalPlayer) {
/*     */       
/* 156 */       LocalPlayer player = (LocalPlayer)entity;
/*     */       
/* 158 */       final LazyOptional<PlayerCameraShaker> cameraShaker = LazyOptional.of(() -> new PlayerCameraShaker(player));
/* 159 */       event.addCapability(new ResourceLocation("witherstormmod", "camera_shaker"), new ICapabilityProvider()
/*     */           {
/*     */             
/*     */             public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
/*     */             {
/* 164 */               return (capability == WitherStormModClientCapabilities.CAMERA_SHAKER) ? cameraShaker.cast() : LazyOptional.empty();
/*     */             }
/*     */           });
/* 167 */       Objects.requireNonNull(cameraShaker); event.addListener(cameraShaker::invalidate);
/*     */       
/* 169 */       final LazyOptional<PlayerScreenBlinder> screenBlinder = LazyOptional.of(PlayerScreenBlinder::new);
/* 170 */       event.addCapability(new ResourceLocation("witherstormmod", "blinder"), new ICapabilityProvider()
/*     */           {
/*     */             
/*     */             public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
/*     */             {
/* 175 */               return (capability == WitherStormModClientCapabilities.SCREEN_BLINDER) ? screenBlinder.cast() : LazyOptional.empty();
/*     */             }
/*     */           });
/* 178 */       Objects.requireNonNull(screenBlinder); event.addListener(screenBlinder::invalidate);
/*     */       
/* 180 */       final LazyOptional<PlayerTractorBeamEffects> tractorBeamEffects = LazyOptional.of(() -> new PlayerTractorBeamEffects(player));
/* 181 */       event.addCapability(new ResourceLocation("witherstormmod", "tractor_beam_effects"), new ICapabilityProvider()
/*     */           {
/*     */             
/*     */             public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
/*     */             {
/* 186 */               return (capability == WitherStormModClientCapabilities.TRACTOR_BEAM_EFFECTS) ? tractorBeamEffects.cast() : LazyOptional.empty();
/*     */             }
/*     */           });
/* 189 */       Objects.requireNonNull(tractorBeamEffects); event.addListener(tractorBeamEffects::invalidate);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\client\init\WitherStormModClientCapabilities.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */