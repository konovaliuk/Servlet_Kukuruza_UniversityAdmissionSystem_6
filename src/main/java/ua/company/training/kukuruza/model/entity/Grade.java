package ua.company.training.kukuruza.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Grade implements Serializable {
    private Long id;
    private Long userId;
    private Integer subjectId;
    private Integer result;

    public static class Builder {
        private Long id;
        private Long userId;
        private Integer subjectId;
        private Integer result;

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

        public Builder setResult(Integer result) {
            this.result = result;
            return this;
        }

        public Grade build() {
            return new Grade(id, userId, subjectId, result);
        }
    }

    public Grade() {
    }

    public Grade(Long id, Long userId, Integer subjectId, Integer result) {
        this.id = id;
        this.userId = userId;
        this.subjectId = subjectId;
        this.result = result;
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

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", userId=" + userId +
                ", subjectId=" + subjectId +
                ", result=" + result +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, subjectId, result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(id, grade.id) &&
                Objects.equals(userId, grade.userId) &&
                Objects.equals(subjectId, grade.subjectId) &&
                Objects.equals(result, grade.result);
    }
}
