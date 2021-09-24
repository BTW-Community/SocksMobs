package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class SocksMobsRenderFox extends RenderLiving
{
	
    public SocksMobsRenderFox(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
    {
        super(par1ModelBase, par3);
        this.setRenderPassModel(par1ModelBase);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving var1, int var2, float var3)
    {
        return this.renderGlowingEyes((SocksMobsEntityFox)var1, var2);
    }
    
    protected int renderGlowingEyes(SocksMobsEntityFox var1, int var2)
    {
    	if (var2 != 0)
        {
            return -1;
        }
        else
        {
            this.loadTexture("/socksmobs/fox_eyes.png");
            float var4 = 1.0F;
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

            if (var1.isInvisible())
            {
                GL11.glDepthMask(false);
            }
            else
            {
                GL11.glDepthMask(true);
            }

            char var5 = 61680;
            int var6 = var5 % 65536;
            int var7 = var5 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var6 / 1.0F, (float)var7 / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var4);
            return 1;
        }
    }
    

    public void renderLivingFox(SocksMobsEntityFox par1EntityFox, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityFox, par2, par4, par6, par8, par9);
    }


    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingFox((SocksMobsEntityFox)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingFox((SocksMobsEntityFox)par1Entity, par2, par4, par6, par8, par9);
    }
    
}