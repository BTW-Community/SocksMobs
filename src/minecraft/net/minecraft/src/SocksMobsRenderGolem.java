package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class SocksMobsRenderGolem extends RenderLiving
{
	/**
     * not actually sure this is size, is not used as of now, but the model would be recreated if the value changed and
     * it seems a good match for a bats size
     */
    private int renderedGolemSize;
    
    /** Iron Golem's Model. */
    private SocksMobsModelGolem golemModel;
	
	public SocksMobsRenderGolem(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

	public void renderGoat(SocksMobsEntityGolem par1EntityGoat, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(par1EntityGoat, par2, par4, par6, par8, par9);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderGoat((SocksMobsEntityGolem)par1EntityLiving, par2, par4, par6, par8, par9);
    }
    
    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
        this.scaleGolem((SocksMobsEntityGolem)par1EntityLiving, par2);
    }
    
    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderGoat((SocksMobsEntityGolem)par1Entity, par2, par4, par6, par8, par9);
        
    }
    
    protected void scaleGolem(SocksMobsEntityGolem par1EntityBat, float par2)
    {
    	float scaleFactor = 0.75F;
        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
    }

}