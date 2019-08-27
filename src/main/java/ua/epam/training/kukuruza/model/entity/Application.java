package ua.epam.training.kukuruza.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Application implements Serializable {
    private Long userId;
    private Integer rating;
    private Long specialtyUniversityLimitId;

    public static class Builder {
        private Long userId;
        private Integer rating;
        private Long specialtyUniversityLimitId;

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder setSpecialtyUniversityLimitId(Long specialtyUniversityLimitId) {
            this.specialtyUniversityLimitId = specialtyUniversityLimitId;
            return this;
        }

        public Application build() {
            return new Application(userId, rating, specialtyUniversityLimitId);
        }
    }

    public Application() {
    }

    public Application(Long userId, Integer rating, Long specialtyUniversityLimitId) {
        this.userId = userId;
        this.rating = rating;
        this.specialtyUniversityLimitId = specialtyUniversityLimitId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getSpecialtyUniversityLimitId() {
        return specialtyUniversityLimitId;
    }

    public void setSpecialtyUniversityLimitId(Long specialtyUniversityLimitId) {
        this.specialtyUniversityLimitId = specialtyUniversityLimitId;
    }

    @Override
    public String toString() {
        return "Application{" +
                "userId=" + userId +
                ", rating=" + rating +
                ", specialtyUniversityLimitId=" + specialtyUniversityLimitId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, rating, specialtyUniversityLimitId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(specialtyUniversityLimitId, that.specialtyUniversityLimitId);
    }
}
