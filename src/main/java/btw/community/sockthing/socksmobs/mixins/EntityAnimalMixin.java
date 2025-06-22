package btw.community.sockthing.socksmobs.mixins;

import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityAnimal.class)
public class EntityAnimalMixin {

    @Redirect(
            method = "onLivingUpdate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/src/World;spawnParticle(Ljava/lang/String;DDDDDD)V"
            )
    )
    private void redirectSpawnParticle(World world, String name, double x, double y, double z, double dx, double dy, double dz) {
        EntityAnimal thisObject = (EntityAnimal)(Object)this;
        if (!(thisObject instanceof EntityChicken)) {
            world.spawnParticle(name, x, y, z, dx, dy, dz);
        }

//        world.spawnParticle(name, x, y, z, dx, dy, dz);
    }

}
