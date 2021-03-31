package net.arathain.aot.effect;

import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.impl.ResettableManagedShaderEffect;
import net.arathain.aot.ArticlesOfTemerant;
import net.minecraft.block.AbstractRedstoneGateBlock;
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

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int k;
        k = 50 >> amplifier;
        if (k > 0) {
            return duration % k == 0;
        } else {
            return true;
        }
    }

    // This method is called when it applies the status effect. We implement custom functionality here.
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {

            if (!entity.hasStatusEffect(ArticlesOfTemerant.NUMBING)) {
                ((PlayerEntity) entity).damage(DamageSource.STARVE, 5);



            }
        }
    }




}
