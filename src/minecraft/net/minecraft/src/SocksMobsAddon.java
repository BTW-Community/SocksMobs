package net.minecraft.src;

/**
 * @author Sockthing (@socklessthing)
 *
 */
public class SocksMobsAddon extends AddonExt {
	private static SocksMobsAddon instance;

	public SocksMobsAddon() {
		super("Sock's Mobs Addon", " 0.0.0", "SocksMobs");
	}

	public static SocksMobsAddon getInstance() {
		if (instance == null) {
			instance = new SocksMobsAddon();
		}

		return instance;
	}

	@Override
	public void Initialize() {
		AddEntityMappings();
		ReplaceEntityMappings();
		AddEntityRenderer();	
	}
	
	public String GetLanguageFilePrefix()
	{
		return "SocksMobs";
	}
	
	private void AddEntityMappings()
    {
		FCAddOnHandler.LogMessage(this.getName() + " Entities Loaded");
    }
	
	private void ReplaceEntityMappings()
    {
		EntityList.ReplaceExistingMapping(SocksMobsEntitySquid.class, "Squid");
		
		FCAddOnHandler.LogMessage(this.getName() + " Entities Replaced");
    }
	
	private void AddEntityRenderer()
    {
		RenderManager.AddEntityRenderer(SocksMobsEntitySquid.class, new SocksMobsRenderSquid(new FCClientModelSquid()));
		
		FCAddOnHandler.LogMessage(this.getName() + " Models Loaded");
    }
}