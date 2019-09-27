package ua.company.training.kukuruza.entity;

import java.io.Serializable;
import java.util.Objects;

public class SpecialtySubject implements Serializable {
    private Long id;
    private Integer specialtyId;
    private Integer subjectId;

    public static class Builder {
        private Long id;
        private Integer specialtyId;
        private Integer subjectId;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setSpecialtyId(Integer specialtyId) {
            this.specialtyId = specialtyId;
            return this;
        }

        public Builder setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
            return this;
        }

        public SpecialtySubject build() {
            return new SpecialtySubject(id, specialtyId, subjectId);
        }
    }

    public SpecialtySubject() {
    }

    public SpecialtySubject(Long id, Integer specialtyId, Integer subjectId) {
        this.id = id;
        this.specialtyId = specialtyId;
        this.subjectId = subjectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "SpecialtySubject{" +
                "id=" + id +
                ", specialtyId=" + specialtyId +
                ", subjectId=" + subjectId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specialtyId, subjectId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialtySubject that = (SpecialtySubject) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(specialtyId, that.specialtyId) &&
                Objects.equals(subjectId, that.subjectId);
    }
}
