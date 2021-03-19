package net.arathain.aot.entity;

import net.arathain.aot.ArticlesOfTemerant;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;


import java.util.List;

public class BloodlessEntity extends BlockEntity implements Tickable {

    public BloodlessEntity() {
        super(ArticlesOfTemerant.BLOODLESS_ENTITY);
    }

    public void tick() {
            List<ArrowEntity> entities = world.getEntitiesByClass(
                    ArrowEntity.class,
                    new Box(
                            pos.getX() - 4, pos.getY() - 4, pos.getZ() - 4,
                            pos.getX() + 4, pos.getY() + 4, pos.getZ() + 4
                    ), (entity) -> true
            );
            if (!world.isClient) {
                for (ArrowEntity entity : entities) {
                    Vec3d velocity = entity.getVelocity();
                    entity.setVelocity(0, velocity.y, 0);
                }
            }

    }
}

