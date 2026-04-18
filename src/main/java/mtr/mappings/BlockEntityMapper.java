package mtr.mappings;

import berries.mods.ts.mvapi.MVBlockEntity;
import berries.mods.ts.mvapi.MVBlockEntityComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BlockEntityMapper extends MVBlockEntity {
    public BlockEntityMapper(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public final void loadTag(MVBlockEntityComponent tag) {
        super.loadTag(tag);
        readCompoundTag(tag.getTag());
    }

    @Override
    public final void saveTag(MVBlockEntityComponent tag) {
        super.saveTag(tag);
        writeCompoundTag(tag.getTag());
    }

    public void readCompoundTag(CompoundTag compoundTag) {
    }

    public void writeCompoundTag(CompoundTag compoundTag) {
    }
}
