/*    */ package nonamecrackers2.witherstormmod.common.packet;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.network.FriendlyByteBuf;
/*    */ import net.minecraft.network.syncher.SynchedEntityData;
/*    */ import nonamecrackers2.crackerslib.common.packet.Packet;
/*    */ import nonamecrackers2.witherstormmod.mixin.IMixinSynchedEntityData;
/*    */ 
/*    */ public abstract class DistantRendererMessage
/*    */   extends Packet
/*    */ {
/*    */   private List<Integer> applicable;
/*    */   
/*    */   protected DistantRendererMessage(boolean valid, List<Integer> applicable) {
/* 18 */     super(valid);
/* 19 */     this.applicable = applicable;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decode(FriendlyByteBuf buffer) throws IllegalArgumentException, IndexOutOfBoundsException {
/* 25 */     int length = buffer.m_130242_();
/* 26 */     List<Integer> applicable = Lists.newArrayList();
/* 27 */     for (int i = 0; i < length; i++)
/* 28 */       applicable.add(Integer.valueOf(buffer.m_130242_())); 
/* 29 */     this.applicable = applicable;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encode(FriendlyByteBuf buffer) {
/* 35 */     buffer.m_130130_(this.applicable.size());
/* 36 */     Objects.requireNonNull(buffer); this.applicable.forEach(buffer::m_130130_);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Integer> getApplicable() {
/* 41 */     return this.applicable;
/*    */   }
/*    */ 
/*    */   
/*    */   protected static List<SynchedEntityData.DataValue<?>> getPackedData(SynchedEntityData data) {
/* 46 */     IMixinSynchedEntityData mixinData = (IMixinSynchedEntityData)data;
/* 47 */     List<SynchedEntityData.DataValue<?>> values = Lists.newArrayList();
/* 48 */     mixinData.getLock().readLock().lock();
/* 49 */     for (ObjectIterator<SynchedEntityData.DataItem> objectIterator = mixinData.getItemsById().values().iterator(); objectIterator.hasNext(); ) { SynchedEntityData.DataItem<?> item = objectIterator.next();
/* 50 */       values.add(item.m_253123_()); }
/* 51 */      mixinData.getLock().readLock().unlock();
/* 52 */     return values;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\common\packet\DistantRendererMessage.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */