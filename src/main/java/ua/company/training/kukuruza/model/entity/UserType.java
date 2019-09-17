package ua.company.training.kukuruza.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserType implements Serializable {
    private Integer id;
    private String userRole;

    public static class Builder {
        private Integer id;
        private String userRole;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setUserRole(String userRole) {
            this.userRole = userRole;
            return this;
        }

        public UserType build() {
            return new UserType(id, userRole);
        }
    }

    public UserType() {
    }

    public UserType(Integer id, String userRole) {
        this.id = id;
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "id=" + id +
                ", userRole='" + userRole + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userRole);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserType that = (UserType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userRole, that.userRole);
    }
}
