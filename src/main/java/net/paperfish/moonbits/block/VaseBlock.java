package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.paperfish.moonbits.registry.MBBlocks;

public class VaseBlock extends FallingBlock {
	public static final VoxelShape SHAPE = VoxelShapes.union(
			Block.createCuboidShape(2, 0, 2, 14, 12, 14),
			Block.createCuboidShape(4, 12, 4, 12, 15, 12)
	);
	public static final VoxelShape MUD_SHAPE = VoxelShapes.union(
			Block.createCuboidShape(2, 0, 2, 14, 10, 14),
			Block.createCuboidShape(4, 10, 4, 12, 13, 12)
	);

	public VaseBlock(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		if (state.isOf(MBBlocks.MUD_VESSEL) || state.isOf(MBBlocks.MUD_VESSEL_REPLICA)) return MUD_SHAPE;
		return SHAPE;
	}

	@Override
	public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
		world.breakBlock(pos, true);
//		world.removeBlock(pos, false);
//		world.syncWorldEvent(null, 2001, pos, Block.getRawIdFromState(fallingBlockState));
	}
}
