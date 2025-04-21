package btw.community.sockthing.socksmobs;

import btw.AddonHandler;
import btw.BTWAddon;

public class SocksMobsAddon extends BTWAddon {
    private static SocksMobsAddon instance;

    public SocksMobsAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }
}