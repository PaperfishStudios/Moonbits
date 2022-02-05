package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class MBLanternBlockItem extends BlockItem {
    protected final Block wallBlock;

    public MBLanternBlockItem(Block standingBlock, Block wallBlock, Item.Settings settings) {
        super(standingBlock, settings);
        this.wallBlock = wallBlock;
    }

    @Override
    @Nullable
    protected BlockState getPlacementState(ItemPlacementContext context) {
        BlockState blockState = this.wallBlock.getPlacementState(context);
        BlockState blockState2 = null;
        World worldView = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        for (Direction direction : context.getPlacementDirections()) {
            BlockState blockState3;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockState3 = getBlock().getPlacementState(context);
            }
            else {
                blockState3 = wallBlock.getPlacementState(context);
            }
            //BlockState blockState4 = blockState3 = direction == Direction.DOWN ? this.getBlock().getPlacementState(context) : blockState;
            if (blockState3 == null || !blockState3.canPlaceAt(worldView, blockPos)) continue;
            blockState2 = blockState3;
            break;
        }
        return blockState2 != null && worldView.canPlace(blockState2, blockPos, ShapeContext.absent()) ? blockState2 : null;
    }

    @Override
    public void appendBlocks(Map<Block, Item> map, Item item) {
        super.appendBlocks(map, item);
        map.put(this.wallBlock, item);
    }
}
