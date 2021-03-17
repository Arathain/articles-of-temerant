package net.arathain.aot.block;



import net.arathain.aot.entity.BloodlessEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class BloodlessBlock extends LanternBlock implements BlockEntityProvider {
    public static final BooleanProperty HANGING;
    protected static final VoxelShape STANDING_SHAPE;
    protected static final VoxelShape HANGING_SHAPE;

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (Boolean)state.get(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
    }

    public BloodlessBlock(Settings settings) {
        super(settings);
    }

    static {
        HANGING = Properties.HANGING;

        STANDING_SHAPE = VoxelShapes.combineAndSimplify(Block.createCuboidShape(4.5, 0, 4.5, 11.5, 6, 11.5), Block.createCuboidShape(6.5, 6, 6.5, 9.5, 7, 9.5), BooleanBiFunction.OR);
        HANGING_SHAPE = VoxelShapes.combineAndSimplify(Block.createCuboidShape(4.5, 9, 4.5, 11.5, 15, 11.5), Block.createCuboidShape(6.5, 15, 6.5, 9.5, 16, 9.5), BooleanBiFunction.OR);

    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new BloodlessEntity();
    }
}
