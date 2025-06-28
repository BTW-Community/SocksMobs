package btw.community.sockthing.socksmobs.entities.pig;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.PigUtils;
import net.minecraft.src.EntityLivingData;
import net.minecraft.src.EntityPig;
import net.minecraft.src.World;

public class EntityMuddyPig extends EntityPig {
    public EntityMuddyPig(World par1World) {
        super(par1World);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        ((EntityAnimalInterface)this).setType(PigUtils.MUDDY);
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
        super.onSpawnWithEgg(par1EntityLivingData);
        ((EntityAnimalInterface)this).setType(PigUtils.MUDDY);
        return par1EntityLivingData;
    }
}
