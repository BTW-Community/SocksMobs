package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.entities.models.CustomChickenModel;
import btw.community.sockthing.socksmobs.entities.models.CustomPigModel;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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
        this.entityRenderMap.put(EntityChicken.class, new RenderChicken(new CustomChickenModel(), 0.3f));

        this.entityRenderMap.remove(EntityPig.class, new RenderPig(new ModelPig(), new ModelPig(0.5f), 0.7f));
        this.entityRenderMap.put(EntityPig.class, new RenderPig(new CustomPigModel(), new CustomPigModel(0.5f), 0.7f));
    }

}
