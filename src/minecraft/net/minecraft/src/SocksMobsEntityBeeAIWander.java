package net.minecraft.src;

public class SocksMobsEntityBeeAIWander extends EntityAIBase
{
    private SocksMobsEntityBee entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private float speed;

    public SocksMobsEntityBeeAIWander(SocksMobsEntityBee socksMobsEntityBee, float par2)
    {
        this.entity = socksMobsEntityBee;
        this.speed = par2;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.entity.getRNG().nextInt(120) != 0)
        {
            return false;
        }
        else
        {
            Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);

            if (var1 == null)
            {
                return false;
            }
            else
            {
                this.xPosition = var1.xCoord;
                this.yPosition = var1.yCoord;
                this.zPosition = var1.zCoord;
                return true;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.entity.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
}