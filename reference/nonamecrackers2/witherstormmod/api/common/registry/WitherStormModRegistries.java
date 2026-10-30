/*    */ package nonamecrackers2.witherstormmod.api.common.registry;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraftforge.registries.IForgeRegistry;
/*    */ import net.minecraftforge.registries.NewRegistryEvent;
/*    */ import net.minecraftforge.registries.RegistryBuilder;
/*    */ import nonamecrackers2.witherstormmod.WitherStormMod;
/*    */ import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModRegistries
/*    */ {
/* 16 */   public static final ResourceLocation SPELL_TYPES_NAME = WitherStormMod.id("symbiont_spells");
/*    */   
/*    */   public static Supplier<IForgeRegistry<SpellType>> SPELL_TYPES;
/*    */ 
/*    */   
/*    */   public static void registerRegistries(@Nonnull NewRegistryEvent event) {
/* 22 */     SPELL_TYPES = event.create((new RegistryBuilder()).setName(SPELL_TYPES_NAME));
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\api\common\registry\WitherStormModRegistries.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */