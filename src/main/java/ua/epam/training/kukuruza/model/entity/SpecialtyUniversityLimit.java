package ua.epam.training.kukuruza.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class SpecialtyUniversityLimit implements Serializable {
    private Long id;
    private Integer universityId;
    private Integer specialtyId;
    private Integer studentLimit;

    public static class Builder {
        private Long id;
        private Integer universityId;
        private Integer specialtyId;
        private Integer studentLimit;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUniversityId(Integer universityId) {
            this.universityId = universityId;
            return this;
        }

        public Builder setSpecialtyId(Integer specialtyId) {
            this.specialtyId = specialtyId;
            return this;
        }

        public Builder setStudentLimit(Integer studentLimit) {
            this.studentLimit = studentLimit;
            return this;
        }

        public SpecialtyUniversityLimit build() {
            return new SpecialtyUniversityLimit(id, universityId, specialtyId, studentLimit);
        }
    }

    public SpecialtyUniversityLimit() {
    }

    public SpecialtyUniversityLimit(Long id, Integer universityId, Integer specialtyId, Integer studentLimit) {
        this.id = id;
        this.universityId = universityId;
        this.specialtyId = specialtyId;
        this.studentLimit = studentLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    public Integer getStudentLimit() {
        return studentLimit;
    }

    public void setStudentLimit(Integer studentLimit) {
        this.studentLimit = studentLimit;
    }

    @Override
    public String toString() {
        return "SpecialtyUniversityLimit{" +
                "id=" + id +
                ", universityId=" + universityId +
                ", specialtyId=" + specialtyId +
                ", studentLimit=" + studentLimit +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, universityId, specialtyId, studentLimit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialtyUniversityLimit that = (SpecialtyUniversityLimit) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(universityId, that.universityId) &&
                Objects.equals(specialtyId, that.specialtyId) &&
                Objects.equals(studentLimit, that.studentLimit);
    }
}
