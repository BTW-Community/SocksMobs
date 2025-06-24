package btw.community.sockthing.socksmobs.entities;

import btw.community.sockthing.socksmobs.enums.CowType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import net.minecraft.src.EntityCow;
import net.minecraft.src.World;

public class EntityWarmCow extends EntityCow {
    public EntityWarmCow(World par1World) {
        super(par1World);
        EntityCow thisCow = (EntityCow)(Object)this;
        ((EntityAnimalInterface)thisCow).setType(CowType.WARM.ordinal());
    }
}
