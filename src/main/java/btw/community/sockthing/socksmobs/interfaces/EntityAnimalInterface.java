package btw.community.sockthing.socksmobs.interfaces;

public interface EntityAnimalInterface {

    void setType(int type);
    int getType();

    void setGender(int gender);
    int getGender();

    void setPregnant(boolean isPregnant);
    boolean getIsPregnant();

    void setAmount(int amount);
    int getAmount();

    void setDelayTimer(int delaytimer);
    int getDelayTimer();

    void setExtraState(int extraState);
    int getExtraState();
}
