package btw.community.sockthing.socksmobs.entities;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.World;

public class EntityRooster extends EntityChicken {
    public EntityRooster(World par1World) {
        super(par1World);
        EntityChicken thisObject = (EntityChicken)(Object)this;
        ((EntityAnimalInterface) thisObject).setGender(MobUtils.MALE);
    }
}
