package ua.epam.training.kukuruza.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Exam implements Serializable {
    private Integer id;
    private Date date;
    private String address;
    private Integer subjectId;

    public static class Builder {
        private Integer id;
        private Date date;
        private String address;
        private Integer subjectId;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setDate(Date date) {
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

    public Exam(Integer id, Date date, String address, Integer subjectId) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
        Exam exam = (Exam) o;
        return Objects.equals(id, exam.id) &&
                Objects.equals(date, exam.date) &&
                Objects.equals(address, exam.address) &&
                Objects.equals(subjectId, exam.subjectId);
    }
}
