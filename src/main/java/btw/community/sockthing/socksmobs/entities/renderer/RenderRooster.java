package btw.community.sockthing.socksmobs.entities.renderer;

import btw.community.sockthing.socksmobs.utils.ChickenTextures;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderChicken;
import net.minecraft.src.ResourceLocation;

public class RenderRooster extends RenderChicken {
    public RenderRooster(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getChickenTextures(EntityChicken chicken) {
        return ChickenTextures.getRoosterTexture(chicken);
    }
}
