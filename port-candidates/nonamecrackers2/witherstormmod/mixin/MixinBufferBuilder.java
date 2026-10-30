package nonamecrackers2.witherstormmod.mixin;

import com.mojang.blaze3d.vertex.BufferBuilder;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({BufferBuilder.class})
public interface MixinBufferBuilder {
  @Accessor("buffer")
  ByteBuffer witherstormmod$getBuffer();
  
  @Accessor("buffer")
  void witherstormmod$setBuffer(@Nullable ByteBuffer paramByteBuffer);
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\MixinBufferBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */