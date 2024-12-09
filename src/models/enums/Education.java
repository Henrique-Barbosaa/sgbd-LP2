package models.enums;

public enum Education {
    ESPECIALIZAÇÃO(1),
    MESTRADO(2),
    DOUTORADO(3);

    private final int id;

    Education(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static Education fromId(int id) throws IllegalArgumentException {
        for (Education education : Education.values()) {
            if (education.getId() == id) {
                return education;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + id);
    }
}
