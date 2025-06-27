package btw.community.sockthing.socksmobs.entities;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.PigTextures;
import net.minecraft.src.EntityLivingData;
import net.minecraft.src.EntityPig;
import net.minecraft.src.World;

public class EntityWarmPig extends EntityPig {
    public EntityWarmPig(World par1World) {
        super(par1World);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        ((EntityAnimalInterface)this).setType(PigTextures.WARM);
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
        super.onSpawnWithEgg(par1EntityLivingData);
        ((EntityAnimalInterface)this).setType(PigTextures.WARM);
        return par1EntityLivingData;
    }
}
