/*    */ package nonamecrackers2.witherstormmod.common.init;
/*    */ 
/*    */ import net.minecraft.core.registries.Registries;
/*    */ import net.minecraft.nbt.CompoundTag;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.CreativeModeTab;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.minecraft.world.level.ItemLike;
/*    */ import net.minecraftforge.registries.DeferredRegister;
/*    */ import net.minecraftforge.registries.RegistryObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WitherStormModItemTabs
/*    */ {
/* 16 */   public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.f_279569_, "witherstormmod");
/*    */   
/* 18 */   public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = TABS.register("creative_tab", () -> CreativeModeTab.builder().m_257737_(()).m_257941_((Component)Component.m_237115_("itemGroup.wither_storm_mod")).m_257501_(()).m_257652_());
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\init\WitherStormModItemTabs.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */