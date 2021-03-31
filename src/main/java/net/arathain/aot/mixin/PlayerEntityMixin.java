package net.arathain.aot.mixin;

import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import net.arathain.aot.ArticlesOfTemerant;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity{


    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }


    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        LivingEntity entity = this;
        if (entity.getHealth() <= 4) {
            if (!entity.hasStatusEffect(ArticlesOfTemerant.NUMBING)) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80, 2));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 80, 2));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 80, 2));

            }


        }
    }
}
