package ua.company.training.kukuruza.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserStatus implements Serializable {
    private Integer id;
    private String status;

    public static class Builder {
        private Integer id;
        private String status;

        public UserStatus.Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public UserStatus.Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public UserStatus build() {
            return new UserStatus(id, status);
        }
    }

    public UserStatus() {
    }

    public UserStatus(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatus that = (UserStatus) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(status, that.status);
    }
}
