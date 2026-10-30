package nonamecrackers2.witherstormmod.mixin;

import java.util.List;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({StructurePiecesBuilder.class})
public interface IMixinStructurePiecesBuilder {
  @Accessor
  List<StructurePiece> getPieces();
}


/* Location:              C:\Users\Jackbot\Desktop\Jacks Crackers Wither Storm Update\witherstormmod-1.20.1-4.2.1-all.jar!\nonamecrackers2\witherstormmod\mixin\IMixinStructurePiecesBuilder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */