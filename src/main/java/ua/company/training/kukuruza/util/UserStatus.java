package ua.company.training.kukuruza.util;

public enum UserStatus {
    UNKNOWN(1),
    SUCCESS(2),
    FAIL(3);

    private final int id;

    UserStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
