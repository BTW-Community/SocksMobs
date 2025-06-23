package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(EntityEgg.class)
public abstract class EntityEggMixin extends EntityThrowable {

    public EntityEggMixin(World par1World) {
        super(par1World);
    }

    public EntityEggMixin(World par1World, EntityLivingBase par2EntityLivingBase) {
        super(par1World, par2EntityLivingBase);
    }

    public EntityEggMixin(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }


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

    @Redirect(
            method = "onImpact",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/src/World;spawnEntityInWorld(Lnet/minecraft/src/Entity;)Z"
            )
    )
    private boolean redirectSpawnEntity(World world, Entity entity) {
        if (entity instanceof EntityChicken chicken) {
            // modify chicken before spawning
            ((EntityAnimalInterface) chicken).setType(this.worldObj.rand.nextInt(MobUtils.SPAWN_TYPE_CHANCE));
            ((EntityAnimalInterface) chicken).setGender(this.worldObj.rand.nextInt(MobUtils.EGG_SPAWN_GENDER_CHANCE));
        }

        return world.spawnEntityInWorld(entity);
    }
}
