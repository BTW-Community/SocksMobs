package btw.community.sockthing.socksmobs.entities;

import btw.community.sockthing.socksmobs.enums.PigType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import net.minecraft.src.EntityPig;
import net.minecraft.src.World;

public class EntitySpottedPig extends EntityPig {
    public EntitySpottedPig(World par1World) {
        super(par1World);
        ((EntityAnimalInterface)this).setType(PigType.SPOTTED.ordinal());
    }
}
