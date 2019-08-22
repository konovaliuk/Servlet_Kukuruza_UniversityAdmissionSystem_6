package ua.epam.training.kukuruza.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class ApplicationEntity implements Serializable {
  private Long userId;
  private Integer rating;
  private Integer universityId;
  private Integer specialtyId;

  public static class Builder {
    private Long userId;
    private Integer rating;
    private Integer universityId;
    private Integer specialtyId;

    public Builder setUserId(Long userId) {
      this.userId = userId;
      return this;
    }

    public Builder setRating(Integer rating) {
      this.rating = rating;
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

    public ApplicationEntity build() {
      return new ApplicationEntity(userId, rating, universityId, specialtyId);
    }
  }

  public ApplicationEntity() {
  }

  public ApplicationEntity(Long userId, Integer rating, Integer universityId, Integer specialtyId) {
    this.userId = userId;
    this.rating = rating;
    this.universityId = universityId;
    this.specialtyId = specialtyId;
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

  @Override
  public String toString() {
    return "ApplicationEntity{" +
            "userId=" + userId +
            ", rating=" + rating +
            ", universityId=" + universityId +
            ", specialtyId=" + specialtyId +
            '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, rating, universityId, specialtyId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ApplicationEntity that = (ApplicationEntity) o;
    return Objects.equals(userId, that.userId) &&
            Objects.equals(rating, that.rating) &&
            Objects.equals(universityId, that.universityId) &&
            Objects.equals(specialtyId, that.specialtyId);
  }
}
