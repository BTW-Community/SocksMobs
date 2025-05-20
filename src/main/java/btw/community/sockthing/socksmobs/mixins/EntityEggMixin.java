package btw.community.sockthing.socksmobs.mixins;

import net.minecraft.src.EntityEgg;
import net.minecraft.src.MovingObjectPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(EntityEgg.class)
public class EntityEggMixin {

    @Redirect(
            method = "onImpact",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 0
            )
    )
    private int disableRandom(Random random, int bound) {
        return 0; // Force it to always behave as if random == 0
    }
}
