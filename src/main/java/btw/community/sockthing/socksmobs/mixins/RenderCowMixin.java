package btw.community.sockthing.socksmobs.mixins;

import net.minecraft.src.EntityCow;
import net.minecraft.src.RenderCow;
import net.minecraft.src.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderCow.class)
public class RenderCowMixin {

    private static final ResourceLocation COW_STARVING_TEXTURES = new ResourceLocation("btw:textures/entity/mob/cow/cow_starving.png");
    private static final ResourceLocation COW_FAMISHED_TEXTURES = new ResourceLocation("btw:textures/entity/mob/cow/cow_starving.png");
    private static final ResourceLocation MR_COW_TEXTURES = new ResourceLocation("btw:textures/entity/mob/cow/cow_breeding_harness.png");

    private static final ResourceLocation COW_TEXTURES = new ResourceLocation("socksmobs:textures/entity/cow/cow.png");

    @Inject(method = "getCowTextures", at = @At(value = "HEAD"), cancellable = true)
    protected void getCowTextures(EntityCow par1EntityCow, CallbackInfoReturnable<ResourceLocation> cir) {
        if (par1EntityCow instanceof EntityCow) {
            EntityCow cowEntity = par1EntityCow;
            if (cowEntity.getWearingBreedingHarness()) {
                cir.setReturnValue( MR_COW_TEXTURES );
                cir.cancel();
            }
            int iHungerLevel = cowEntity.getHungerLevel();
            if (iHungerLevel == 1) {
                cir.setReturnValue( COW_FAMISHED_TEXTURES );
                cir.cancel();
            }
            if (iHungerLevel == 2) {
                cir.setReturnValue( COW_STARVING_TEXTURES );
                cir.cancel();
            }
        }
        cir.setReturnValue( COW_TEXTURES );
        cir.cancel();
    }

}
