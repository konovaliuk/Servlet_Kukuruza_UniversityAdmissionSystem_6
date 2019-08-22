package ua.epam.training.kukuruza.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Grade implements Serializable {
    private Long id;
    private Long userId;
    private Integer subjectId;
    private Integer grade;

    public static class Builder {
        private Long id;
        private Long userId;
        private Integer subjectId;
        private Integer grade;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
            return this;
        }

        public Builder setGrade(Integer grade) {
            this.grade = grade;
            return this;
        }

        public Grade build() {
            return new Grade(id, userId, subjectId, grade);
        }
    }

    public Grade() {
    }

    public Grade(Long id, Long userId, Integer subjectId, Integer grade) {
        this.id = id;
        this.userId = userId;
        this.subjectId = subjectId;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", userId=" + userId +
                ", subjectId=" + subjectId +
                ", grade=" + grade +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, subjectId, grade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade that = (Grade) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(subjectId, that.subjectId) &&
                Objects.equals(grade, that.grade);
    }
}
