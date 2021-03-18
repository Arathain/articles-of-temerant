package net.arathain.aot.item;

import net.arathain.aot.ArticlesOfTemerant;
import net.minecraft.block.Block;;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


import java.util.List;



public class BloodlessItem extends BlockItem {
    public BloodlessItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
        public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
    {
        if (!world.isClient) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            Vec3d pos = player.getPos();
            if (!player.getItemCooldownManager().isCoolingDown(Item.fromBlock(ArticlesOfTemerant.BLOODLESS))) {
                List<ArrowEntity> entities = player.getEntityWorld().getEntitiesByClass(
                        ArrowEntity.class,
                        new Box(
                                pos.getX() - 4, pos.getY() - 4, pos.getZ() - 4,
                                pos.getX() + 4, pos.getY() + 4, pos.getZ() + 4
                        ), (ArrowEntity) -> true
                );

                for (ArrowEntity nearbyEntity : entities) {
                    Vec3d velocity = nearbyEntity.getVelocity();
                    if (nearbyEntity.getVelocity().x != 0) {
                        if (nearbyEntity.getVelocity().z != 0) {
                            nearbyEntity.setVelocity(0, velocity.y, 0);
                            player.getItemCooldownManager().set(this, 40);
                        }
                    }
                }
            }

        }
    }

}
