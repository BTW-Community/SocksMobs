package btw.community.sockthing.socksmobs.entities;

import btw.community.sockthing.socksmobs.enums.MooshroomType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import net.minecraft.src.EntityMooshroom;
import net.minecraft.src.World;

public class EntityBrownMooshroom extends EntityMooshroom {
    public EntityBrownMooshroom(World par1World) {
        super(par1World);
        EntityMooshroom thisMooshroom = (EntityMooshroom)(Object)this;
        ((EntityAnimalInterface)thisMooshroom).setType(MooshroomType.BROWN.ordinal());
    }
}
