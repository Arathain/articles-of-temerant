package net.arathain.aot.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin (DamageSource.class)
public  class DamageSourceMixin {

    @Inject(method = "arrow", at = @At(value = "RETURN"), cancellable = true)
    private static void changeCrossbowPiercingSource( PersistentProjectileEntity projectile, Entity attacker, CallbackInfoReturnable<DamageSource> cir){
        DamageSource source = cir.getReturnValue() ;
        ArrowEntity arrow = ((ArrowEntity) source.getSource());
        if(arrow != null && arrow.isShotFromCrossbow() && arrow.getPierceLevel() > 0){
            DamageSourceAccessor accessor = (DamageSourceAccessor) source;
            accessor.setBypassesArmor(true);
            accessor.setExhaustion(0.0F);
            accessor.setUnblockable(true);
            cir.setReturnValue(source);
        }
    }
}
