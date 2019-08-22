package ua.epam.training.kukuruza.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Exam implements Serializable {
    private Integer id;
    private Timestamp date;
    private String address;
    private Integer subjectId;

    public static class Builder {
        private Integer id;
        private Timestamp date;
        private String address;
        private Integer subjectId;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setDate(Timestamp date) {
            this.date = date;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
            return this;
        }

        public Exam build() {
            return new Exam(id, date, address, subjectId);
        }
    }

    public Exam() {
    }

    public Exam(Integer id, Timestamp date, String address, Integer subjectId) {
        this.id = id;
        this.date = date;
        this.address = address;
        this.subjectId = subjectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", subjectId=" + subjectId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, address, subjectId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam that = (Exam) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(address, that.address) &&
                Objects.equals(subjectId, that.subjectId);
    }
}
