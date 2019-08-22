package ua.epam.training.kukuruza.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class SpecialtyEntity implements Serializable {
  private Integer id;
  private String name;

  public static class Builder {
    private Integer id;
    private String name;

    public Builder setId(Integer id) {
      this.id = id;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public SpecialtyEntity build() {
      return new SpecialtyEntity(id, name);
    }
  }

  public SpecialtyEntity() {
  }

  public SpecialtyEntity(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "SpecialtyEntity{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SpecialtyEntity that = (SpecialtyEntity) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name);
  }
}
