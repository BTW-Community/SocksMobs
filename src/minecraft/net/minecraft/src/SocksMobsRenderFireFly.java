package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class SocksMobsRenderFireFly extends RenderLiving
{
    /**
     * not actually sure this is size, is not used as of now, but the model would be recreated if the value changed and
     * it seems a good match for a bats size
     */
    private int renderedBatSize;

    public SocksMobsRenderFireFly(ModelBase var1)
    {
        super(new SocksMobsModelFireFly(), 0.5F);
        this.renderedBatSize = ((SocksMobsModelFireFly)this.mainModel).getSize();
        this.setRenderPassModel(var1);
    }
    
    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving var1, int var2, float var3)
    {
        return this.renderGlow((SocksMobsEntityFireFly)var1, var2);
    }
    
    protected int renderGlow(SocksMobsEntityFireFly var1, int var2)
    {
        this.loadTexture("/socksmobs/firefly_glow.png");
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

    public void func_82443_a(SocksMobsEntityFireFly par1EntityBat, double par2, double par4, double par6, float par8, float par9)
    {
        int var10 = ((SocksMobsModelFireFly)this.mainModel).getSize();

        if (var10 != this.renderedBatSize)
        {
            this.renderedBatSize = var10;
            this.mainModel = new SocksMobsModelFireFly();
        }

        super.doRenderLiving(par1EntityBat, par2, par4, par6, par8, par9);
    }

    protected void func_82442_a(SocksMobsEntityFireFly par1EntityBat, float par2)
    {
        GL11.glScalef(0.35F, 0.35F, 0.35F);
    }

    protected void func_82445_a(SocksMobsEntityFireFly par1EntityBat, double par2, double par4, double par6)
    {
        super.renderLivingAt(par1EntityBat, par2, par4, par6);
    }

    protected void func_82444_a(SocksMobsEntityFireFly par1EntityBat, float par2, float par3, float par4)
    {
        if (!par1EntityBat.getIsBatHanging())
        {
            GL11.glTranslatef(0.0F, MathHelper.cos(par2 * 0.3F) * 0.1F, 0.0F);
        }
        else
        {
            GL11.glTranslatef(0.0F, -0.1F, 0.0F);
        }

        super.rotateCorpse(par1EntityBat, par2, par3, par4);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
        this.func_82442_a((SocksMobsEntityFireFly)par1EntityLiving, par2);
    }

    protected void rotateCorpse(EntityLiving par1EntityLiving, float par2, float par3, float par4)
    {
        this.func_82444_a((SocksMobsEntityFireFly)par1EntityLiving, par2, par3, par4);
    }

    /**
     * Sets a simple glTranslate on a LivingEntity.
     */
    protected void renderLivingAt(EntityLiving par1EntityLiving, double par2, double par4, double par6)
    {
        this.func_82445_a((SocksMobsEntityFireFly)par1EntityLiving, par2, par4, par6);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82443_a((SocksMobsEntityFireFly)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.func_82443_a((SocksMobsEntityFireFly)par1Entity, par2, par4, par6, par8, par9);
    }
}
