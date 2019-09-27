package ua.company.training.kukuruza.util;

public enum UserType {
    STUDENT(1),
    ADMIN(2);

    private final int id;

    UserType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
