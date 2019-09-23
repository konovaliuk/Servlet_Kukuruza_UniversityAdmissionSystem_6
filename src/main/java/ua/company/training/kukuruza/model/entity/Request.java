package ua.company.training.kukuruza.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    private Long id;
    private Integer rating;
    private Long userId;
    private Long educationOptionId;

    public static class Builder {
        private Long id;
        private Integer rating;
        private Long userId;
        private Long educationOptionId;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setEducationOptionId(Long educationOptionId) {
            this.educationOptionId = educationOptionId;
            return this;
        }

        public Request build(){
            return new Request(id, rating, userId, educationOptionId);
        }
    }

    public Request() {
    }

    public Request(Long id, Integer rating, Long userId, Long educationOptionId) {
        this.id = id;
        this.rating = rating;
        this.userId = userId;
        this.educationOptionId = educationOptionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
                "id=" + id +
                ", rating=" + rating +
                ", userId=" + userId +
                ", educationOptionId=" + educationOptionId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, userId, educationOptionId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id) &&
                Objects.equals(rating, request.rating) &&
                Objects.equals(userId, request.userId) &&
                Objects.equals(educationOptionId, request.educationOptionId);
    }
}
