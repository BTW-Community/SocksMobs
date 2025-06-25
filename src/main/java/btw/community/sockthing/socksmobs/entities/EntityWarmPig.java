package btw.community.sockthing.socksmobs.entities;

import btw.community.sockthing.socksmobs.enums.PigType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import net.minecraft.src.EntityPig;
import net.minecraft.src.World;

public class EntityWarmPig extends EntityPig {
    public EntityWarmPig(World par1World) {
        super(par1World);
        ((EntityAnimalInterface)this).setType(PigType.WARM.ordinal());
    }
}
