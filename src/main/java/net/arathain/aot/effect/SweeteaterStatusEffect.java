package net.arathain.aot.effect;

import net.arathain.aot.ArticlesOfTemerant;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

public class SweeteaterStatusEffect extends StatusEffect {

    public SweeteaterStatusEffect() {
        super(
                StatusEffectType.BENEFICIAL, // whether beneficial or harmful for entities
                0xf5f5f5); // color in RGB
    }

    // This method is called every tick to check weather it should apply the status effect or not
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        return true;
    }

    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            if (this != ArticlesOfTemerant.BLISS) {

                ((PlayerEntity) entity).damage(DamageSource.STARVE, 5);
            }
        }
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (this == ArticlesOfTemerant.SWEETEATER) {
            ((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance (ArticlesOfTemerant.SWEETEATER, 12000000, 1));
        };
    }

}
