package btw.community.sockthing.socksmobs.enums;

public enum EnumWolfType {
    PALE(0),
    ASHEN(1),
    BLACK(2),
    CHESTNUT(3),
    RUSTY(4),
    SNOWY(5),
    SPOTTED(6),
    STRIPED(7),
    WOODS(8);

    private final int id;

    EnumWolfType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private static final EnumWolfType[] BY_ID = values();

    public static EnumWolfType fromId(int id) {
        if (id < 0 || id >= BY_ID.length) {
            throw new IllegalArgumentException("Invalid wolf type ID: " + id);
        }
        return BY_ID[id];
    }
}