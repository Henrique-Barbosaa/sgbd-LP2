package models.enums;

public enum Level {
    I(1),
    II(2),
    III(3),
    IV(4),
    V(5),
    VI(6),
    VII(7),
    VIII(8);

    private final int id;

    Level(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static Level fromId(int id) throws IllegalArgumentException {
        for (Level level : Level.values()) {
            if (level.getId() == id) {
                return level;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + id);
    }
}
