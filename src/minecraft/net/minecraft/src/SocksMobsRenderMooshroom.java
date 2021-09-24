package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class SocksMobsRenderMooshroom extends RenderLiving
{
    FCClientModelCowUdder m_ModelUdder = new FCClientModelCowUdder();

    public SocksMobsRenderMooshroom(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
        this.setRenderPassModel(this.m_ModelUdder);
    }

    public void renderLivingMooshroom(SocksMobsEntityMooshroom par1EntityMooshroom, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityMooshroom, par2, par4, par6, par8, par9);
    }

    protected void renderMooshroomEquippedItems(SocksMobsEntityMooshroom par1EntityMooshroom, float par2)
    {
        super.renderEquippedItems(par1EntityMooshroom, par2);

        if (!par1EntityMooshroom.isChild())
        {
            this.loadTexture("/terrain.png");
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glPushMatrix();
            GL11.glScalef(1.0F, -1.0F, 1.0F);
            GL11.glTranslatef(0.2F, 0.4F, 0.5F);
            GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
            this.renderBlocks.renderBlockAsItem(Block.mushroomBrown, 0, 1.0F);
            GL11.glTranslatef(0.1F, 0.0F, -0.6F);
            GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);

            if (!par1EntityMooshroom.IsStarving())
            {
                this.renderBlocks.renderBlockAsItem(Block.mushroomBrown, 0, 1.0F);
            }

            GL11.glPopMatrix();
            GL11.glPushMatrix();
            ((ModelQuadruped)this.mainModel).head.postRender(0.0625F);
            GL11.glScalef(1.0F, -1.0F, 1.0F);
            GL11.glTranslatef(0.0F, 0.75F, -0.2F);
            GL11.glRotatef(12.0F, 0.0F, 1.0F, 0.0F);

            if (par1EntityMooshroom.IsFullyFed())
            {
                this.renderBlocks.renderBlockAsItem(Block.mushroomBrown, 0, 1.0F);
            }

            GL11.glPopMatrix();
            GL11.glDisable(GL11.GL_CULL_FACE);
        }
    }

    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
    {
        this.renderMooshroomEquippedItems((SocksMobsEntityMooshroom)par1EntityLiving, par2);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingMooshroom((SocksMobsEntityMooshroom)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingMooshroom((SocksMobsEntityMooshroom)par1Entity, par2, par4, par6, par8, par9);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving var1, int var2, float var3)
    {
        if (var2 == 0 && ((FCEntityCow)var1).GotMilk())
        {
            this.loadTexture("/btwmodtex/cow_udder.png");
            return 1;
        }
        else
        {
            return -1;
        }
    }
}
