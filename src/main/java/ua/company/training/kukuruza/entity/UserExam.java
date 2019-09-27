package ua.company.training.kukuruza.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserExam implements Serializable {
    private Long id;
    private Long userId;
    private Integer examId;

    public static class Builder {
        private Long id;
        private Long userId;
        private Integer examId;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setExamId(Integer examId) {
            this.examId = examId;
            return this;
        }

        public UserExam build() {
            return new UserExam(id, userId, examId);
        }
    }

    public UserExam() {
    }

    public UserExam(Long id, Long userId, Integer examId) {
        this.id = id;
        this.userId = userId;
        this.examId = examId;
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

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    @Override
    public String toString() {
        return "UserExam{" +
                "id=" + id +
                ", userId=" + userId +
                ", examId=" + examId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, examId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExam userExam = (UserExam) o;
        return Objects.equals(id, userExam.id) &&
                Objects.equals(userId, userExam.userId) &&
                Objects.equals(examId, userExam.examId);
    }
}
