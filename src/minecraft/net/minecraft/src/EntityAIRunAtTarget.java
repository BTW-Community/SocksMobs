package net.minecraft.src;

public class EntityAIRunAtTarget extends EntityAIBase
{
    /** The entity that is ramming. */
    EntityLiving rammer;

    /** The entity that the leaper is leaping towards. */
    EntityLiving ramTarget;

    /** The entity's motionX and Z after leaping. */
    float ramMotionX;
    float ramMotionZ;

    public EntityAIRunAtTarget(EntityLiving par1EntityLiving, float par2, float par3)
    {
        this.rammer = par1EntityLiving;
        this.ramMotionX = par2;
        this.ramMotionZ = par3;
        this.setMutexBits(5);
        
        
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        this.ramTarget = this.rammer.getAttackTarget();

        if (this.ramTarget == null)
        {
            return false;
        }
        else
        {
            double var1 = this.rammer.getDistanceSqToEntity(this.ramTarget);
            return var1 >= 4.0D && var1 <= 16.0D ? (this.rammer.getRNG().nextInt(5) == 0) : false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.rammer.isSprinting();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        double var1 = this.ramTarget.posX - this.rammer.posX;
        double var3 = this.ramTarget.posZ - this.rammer.posZ;
        float var5 = MathHelper.sqrt_double(var1 * var1 + var3 * var3);
        
        this.rammer.setSprinting(true);
        
        
        this.rammer.motionX += var1 / (double)var5 * 0.5D * 0.800000011920929D + this.rammer.motionX * 0.20000000298023224D;
        this.rammer.motionZ += var3 / (double)var5 * 0.5D * 0.800000011920929D + this.rammer.motionZ * 0.20000000298023224D;
        //this.rammer.motionY = (double)this.ramMotionY;
    }
}
