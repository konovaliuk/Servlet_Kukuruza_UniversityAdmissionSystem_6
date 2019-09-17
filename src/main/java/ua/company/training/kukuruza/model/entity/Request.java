package ua.company.training.kukuruza.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    private Long userId;
    private Integer rating;
    private Long educationOptionId;

    public static class Builder {
        private Long userId;
        private Integer rating;
        private Long educationOptionId;

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder setEducationOptionId(Long educationOptionId) {
            this.educationOptionId = educationOptionId;
            return this;
        }

        public Request build() {
            return new Request(userId, rating, educationOptionId);
        }
    }

    public Request() {
    }

    public Request(Long userId, Integer rating, Long educationOptionId) {
        this.userId = userId;
        this.rating = rating;
        this.educationOptionId = educationOptionId;
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

    public Long getEducationOptionId() {
        return educationOptionId;
    }

    public void setEducationOptionId(Long educationOptionId) {
        this.educationOptionId = educationOptionId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "userId=" + userId +
                ", rating=" + rating +
                ", educationOptionId=" + educationOptionId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, rating, educationOptionId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request that = (Request) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(educationOptionId, that.educationOptionId);
    }
}
