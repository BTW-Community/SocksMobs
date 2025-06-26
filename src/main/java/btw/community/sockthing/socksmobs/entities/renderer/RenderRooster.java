package btw.community.sockthing.socksmobs.entities.renderer;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.ChickenTextures;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderChicken;
import net.minecraft.src.ResourceLocation;

public class RenderRooster extends RenderChicken {
    public RenderRooster(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @Override
    protected ResourceLocation getChickenTextures(EntityChicken chicken) {
        int subtype = ((EntityAnimalInterface) chicken).getType();
        int gender = ((EntityAnimalInterface) chicken).getGender();
        int hungerLevel = chicken.getHungerLevel();

        if (!chicken.isChild()){
            if (subtype == ChickenTextures.DEFAULT){
                if (hungerLevel == ChickenTextures.FAMISHED) return ChickenTextures.ROOSTER_FAMISHED_TEXTURE;
                else if (hungerLevel == ChickenTextures.STARVING) return ChickenTextures.ROOSTER_STARVING_TEXTURE;
                else return ChickenTextures.ROOSTER_TEXTURE;
            }
            else return ChickenTextures.ROOSTER_TEXTURE;
        }
        else {
            if (subtype == ChickenTextures.DEFAULT){
                if (hungerLevel == ChickenTextures.FAMISHED) return ChickenTextures.BABY_CHICK_FAMISHED_TEXTURE;
                else if (hungerLevel == ChickenTextures.STARVING) return ChickenTextures.BABY_CHICK_STARVING_TEXTURE;
                else return ChickenTextures.BABY_CHICK_TEXTURE;
            }
            else {
                return ChickenTextures.BABY_CHICK_TEXTURE;
            }
        }
    }
}
