package btw.community.sockthing.socksmobs.interfaces;

public interface EntityChickenInterface {

    void setChickenType(int type);
    int getChickenType();

    void setFertilized(boolean isFertilized);
    boolean getIFertilized();

    void setRooster(boolean isRooster);
    boolean isRooster();

    void setAmount(int amount);
    int getAmount();

    void setTimer(int amount);
    int getTimer();
}
