package net.minecraft.src;

public class SocksMobsEntityAIFoxSit extends EntityAIBase
{
    private final SocksMobsEntityFox theFox;
    private final float field_75404_b;

    /** Tracks for how long the task has been executing */
    private int currentTick = 0;
    private int sittingTimer = 0;

    /** For how long the Ocelot should be sitting */
    private int maxSittingTicks = 0;

    /** X Coordinate of a nearby sitable block */
    private int sitableBlockX = 0;

    /** Y Coordinate of a nearby sitable block */
    private int sitableBlockY = 0;

    /** Z Coordinate of a nearby sitable block */
    private int sitableBlockZ = 0;

    public SocksMobsEntityAIFoxSit(SocksMobsEntityFox entityFox, float par2)
    {
        this.theFox = entityFox;
        this.field_75404_b = par2;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return this.theFox.isTamed() && !this.theFox.isSitting() && this.theFox.getRNG().nextDouble() <= 0.006500000134110451D && this.getNearbySitableBlockDistance();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.currentTick <= this.maxSittingTicks && this.sittingTimer <= 60 && this.isSittableBlock(this.theFox.worldObj, this.sitableBlockX, this.sitableBlockY, this.sitableBlockZ);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.theFox.getNavigator().tryMoveToXYZ((double)((float)this.sitableBlockX) + 0.5D, (double)(this.sitableBlockY + 1), (double)((float)this.sitableBlockZ) + 0.5D, this.field_75404_b);
        this.currentTick = 0;
        this.sittingTimer = 0;
        this.maxSittingTicks = this.theFox.getRNG().nextInt(this.theFox.getRNG().nextInt(1200) + 1200) + 1200;
        this.theFox.func_70907_r().setSitting(false);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.theFox.setSitting(false);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        ++this.currentTick;
        this.theFox.func_70907_r().setSitting(false);

        if (this.theFox.getDistanceSq((double)this.sitableBlockX, (double)(this.sitableBlockY + 1), (double)this.sitableBlockZ) > 1.0D)
        {
            this.theFox.setSitting(false);
            this.theFox.getNavigator().tryMoveToXYZ((double)((float)this.sitableBlockX) + 0.5D, (double)(this.sitableBlockY + 1), (double)((float)this.sitableBlockZ) + 0.5D, this.field_75404_b);
            ++this.sittingTimer;
        }
        else if (!this.theFox.isSitting())
        {
            this.theFox.setSitting(true);
        }
        else
        {
            --this.sittingTimer;
        }
    }

    /**
     * Searches for a block to sit on within a 8 block range, returns 0 if none found
     */
    private boolean getNearbySitableBlockDistance()
    {
        int var1 = (int)this.theFox.posY;
        double var2 = 2.147483647E9D;

        for (int var4 = (int)this.theFox.posX - 8; (double)var4 < this.theFox.posX + 8.0D; ++var4)
        {
            for (int var5 = (int)this.theFox.posZ - 8; (double)var5 < this.theFox.posZ + 8.0D; ++var5)
            {
                if (this.isSittableBlock(this.theFox.worldObj, var4, var1, var5) && this.theFox.worldObj.isAirBlock(var4, var1 + 1, var5))
                {
                    double var6 = this.theFox.getDistanceSq((double)var4, (double)var1, (double)var5);

                    if (var6 < var2)
                    {
                        this.sitableBlockX = var4;
                        this.sitableBlockY = var1;
                        this.sitableBlockZ = var5;
                        var2 = var6;
                    }
                }
            }
        }

        return var2 < 2.147483647E9D;
    }

    /**
     * Determines whether the Ocelot wants to sit on the block at given coordinate
     */
    private boolean isSittableBlock(World world, int par2, int par3, int par4)
    {
        int var5 = world.getBlockId(par2, par3, par4);
        int var6 = world.getBlockMetadata(par2, par3, par4);
        boolean var8 = world.canBlockSeeTheSky(par2, par3, par4);
        if (var5 == FCBetterThanWolves.fcBlockChest.blockID)
        {
            TileEntityChest var7 = (TileEntityChest)world.getBlockTileEntity(par2, par3, par4);

            if (var7.numUsingPlayers < 1)
            {
                return true;
            }
        }
        else
        {
            if (var5 == FCBetterThanWolves.fcBlockFurnaceBrickBurning.blockID) 
            {
                return true;
            }

            if (var5 == Block.bed.blockID && !BlockBed.isBlockHeadOfBed(var6))
            {
                return true;
            }
            
            if (var8 == false) {
            	return true;
            }
            
        }

        return false;
    }
}
