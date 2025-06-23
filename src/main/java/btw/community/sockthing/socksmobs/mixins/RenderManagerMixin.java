package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.entities.models.CustomModelChicken;
import btw.community.sockthing.socksmobs.entities.models.CustomModelCow;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RenderManager.class)
public class RenderManagerMixin {

    @Shadow private Map entityRenderMap;

    @Inject(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
                    ordinal = 54
            )
    )
    private void onConstructed(CallbackInfo ci) {
        this.entityRenderMap.remove(EntityChicken.class, new RenderChicken(new ModelChicken(), 0.3f));
        this.entityRenderMap.put(EntityChicken.class, new RenderChicken(new CustomModelChicken(), 0.3f));

        this.entityRenderMap.remove(EntityCow.class, new RenderCow(new ModelCow(), 0.7f));
        this.entityRenderMap.put(EntityCow.class, new RenderCow(new CustomModelCow(), 0.7f));
    }

}
