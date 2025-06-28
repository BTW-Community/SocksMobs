package btw.community.sockthing.socksmobs.entities.chicken;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityLivingData;
import net.minecraft.src.World;

public class EntityRooster extends EntityChicken {
    public EntityRooster(World par1World) {
        super(par1World);
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
        super.onSpawnWithEgg(par1EntityLivingData);
        EntityChicken thisObject = (EntityChicken)(Object)this;
        ((EntityAnimalInterface) thisObject).setGender(MobUtils.MALE);

        return par1EntityLivingData;
    }
}
