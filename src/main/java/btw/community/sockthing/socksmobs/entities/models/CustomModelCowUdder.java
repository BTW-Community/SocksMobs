package btw.community.sockthing.socksmobs.entities.models;

import btw.entity.mob.KickingAnimal;
import net.minecraft.src.*;

public class CustomModelCowUdder extends CustomModelCow {
    private final ModelRenderer udder;

    public CustomModelCowUdder()  {
        super();

        udder = new ModelRenderer(this);
        udder.setRotationPoint(2.0F, 6.0F, -7.0F);
        body.addChild(udder);
        this.udder.setTextureOffset(50, 0).addBox(-4.0F, -4.0F, -4.0F, 4, 6, 3, 0.0F);

    }
}
