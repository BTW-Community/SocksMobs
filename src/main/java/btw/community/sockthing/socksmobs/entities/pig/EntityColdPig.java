package btw.community.sockthing.socksmobs.entities.pig;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.PigUtils;
import net.minecraft.src.EntityLivingData;
import net.minecraft.src.EntityPig;
import net.minecraft.src.World;

public class EntityColdPig extends EntityPig {
    public EntityColdPig(World par1World) {
        super(par1World);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        ((EntityAnimalInterface)this).setType(PigUtils.COLD);
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
        super.onSpawnWithEgg(par1EntityLivingData);
        ((EntityAnimalInterface)this).setType(PigUtils.COLD);
        return par1EntityLivingData;
    }
}
