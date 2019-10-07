package ua.company.training.kukuruza.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

public class Subject implements Serializable {
    private Integer id;
    private String name;

    public static class Builder {
        private Integer id;
        private String name;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Subject build() {
            return new Subject(id, name);
        }
    }

    public Subject() {
    }

    public Subject(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject that = (Subject) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }
}
