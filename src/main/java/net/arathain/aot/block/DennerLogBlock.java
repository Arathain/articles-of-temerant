package net.arathain.aot.block;

import com.terraformersmc.terraform.wood.block.StrippableLogBlock;
import net.arathain.aot.ArticlesOfTemerant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Supplier;


public class DennerLogBlock extends StrippableLogBlock {

    public DennerLogBlock(Supplier<Block> stripped, MaterialColor top, Settings settings) {
        super(stripped, top, settings);
        setDefaultState(getDefaultState().with(ArticlesOfTemerant.WEEPING, false));
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos blockpos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (!state.get(ArticlesOfTemerant.WEEPING) && stack.getItem() instanceof SwordItem) {
            if (!world.isClient) {
                world.setBlockState(blockpos, state.with(ArticlesOfTemerant.WEEPING, true));
                world.playSound(null, blockpos, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, SoundCategory.BLOCKS, 2, 1);
                stack.damage(16, player, (user) -> user.sendToolBreakStatus(hand));
            }
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, blockpos, player, hand, hit);
    }
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(ArticlesOfTemerant.WEEPING)) {
            if (random.nextInt(16) == 0) {
                for (int i = 0; i < 8; i++) {
                    BlockPos offset = pos.offset(Direction.Type.HORIZONTAL.random(random));
                    if (world.getBlockState(offset).isAir()) {
                        ItemScatterer.spawn(world, offset.getX() + 0.5, offset.getY() + 0.5, offset.getZ() + 0.5, new ItemStack(ArticlesOfTemerant.DENNER_RESIN));
                        world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1, 1);
                        break;
                    }

                }
            }
        }
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(ArticlesOfTemerant.WEEPING);
    }
}
