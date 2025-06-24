package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.enums.AnimalState;
import btw.community.sockthing.socksmobs.enums.CowType;
import btw.community.sockthing.socksmobs.enums.MooshroomType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.AnimalTextureManager;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import com.prupe.mcpatcher.mob.MobOverlay;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderMooshroom.class)
public abstract class RenderMooshroomMixin extends RenderLiving{
    public RenderMooshroomMixin(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @Inject(
            method = "renderMooshroomEquippedItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/src/RenderLiving;renderEquippedItems(Lnet/minecraft/src/EntityLivingBase;F)V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    public void disableMushroomsOnBack(EntityMooshroom mooshroom, float par2, CallbackInfo ci){
        if ( ((EntityAnimalInterface)mooshroom).getType() == MooshroomType.MILKA.ordinal()){
            MobOverlay.finishMooshroom();
            ci.cancel();
        }
    }


    @Redirect(
            method = "renderMooshroomEquippedItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/src/RenderBlocks;renderBlockAsItem(Lnet/minecraft/src/Block;IF)V"
            )
    )
    private void redirectRenderBlockAsItem(RenderBlocks instance, Block block, int metadata, float brightness, EntityMooshroom mooshroom) {
        int subtype = ((EntityAnimalInterface)mooshroom).getType();
        if (subtype == MooshroomType.BROWN.ordinal()){
            GL11.glTranslatef(0.0f,-0.025f, 0.0f);
            instance.renderBlockAsItem(Block.mushroomBrown, metadata, brightness + 0.75F);
        }
        else {
            GL11.glTranslatef(0.0f,-0.025f, 0.0f);
            instance.renderBlockAsItem(Block.mushroomRed, metadata, brightness + 0.75F);
        }
    }

    @Inject(method = "getMooshroomTextures", at = @At(value = "HEAD"), cancellable = true)
    public void getMooshroomTextures(EntityMooshroom mooshroom, CallbackInfoReturnable<ResourceLocation> cir) {
        int subtype = ((EntityAnimalInterface) mooshroom).getType();

        if (mooshroom.getWearingBreedingHarness()) {
            int iHungerLevel = mooshroom.getHungerLevel();
            if (iHungerLevel == 1) {
                cir.setReturnValue( AnimalTextureManager.getAnimalTexture(MooshroomType.class, subtype, AnimalState.BREEDING_HARNESS_FAMISHED) );
                cir.cancel();
            }
            else if (iHungerLevel == 2) {
                cir.setReturnValue( AnimalTextureManager.getAnimalTexture(MooshroomType.class, subtype, AnimalState.BREEDING_HARNESS_STARVING) );
                cir.cancel();
            }
            else {
                cir.setReturnValue( AnimalTextureManager.getAnimalTexture(MooshroomType.class, subtype, AnimalState.BREEDING_HARNESS) );
                cir.cancel();
            }
        }
        else {
            int iHungerLevel = mooshroom.getHungerLevel();
            if (iHungerLevel == 1) {
                cir.setReturnValue( AnimalTextureManager.getAnimalTexture(MooshroomType.class, subtype, AnimalState.FAMISHED) );
                cir.cancel();
            }
            else if (iHungerLevel == 2) {
                cir.setReturnValue( AnimalTextureManager.getAnimalTexture(MooshroomType.class, subtype, AnimalState.STARVING) );
                cir.cancel();
            }
            else {
                cir.setReturnValue( AnimalTextureManager.getAnimalTexture(MooshroomType.class, subtype, AnimalState.NORMAL) );
                cir.cancel();
            }
        }

    }
}
