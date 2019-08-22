package ua.epam.training.kukuruza.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserEntity implements Serializable {
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String gender;
    private String year;
    private String email;
    private String phone;
    private String passportCode;
    private Integer userTypeId;

    public static class Builder {
        private Long id;
        private String login;
        private String password;
        private String firstName;
        private String secondName;
        private String gender;
        private String year;
        private String email;
        private String phone;
        private String passportCode;
        private Integer userTypeId;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setYear(String year) {
            this.year = year;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setPassportCode(String passportCode) {
            this.passportCode = passportCode;
            return this;
        }

        public Builder setUserTypeId(Integer userTypeId) {
            this.userTypeId = userTypeId;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(id, login, password, firstName, secondName, gender,
                    year, email, phone, passportCode, userTypeId);
        }
    }

    public UserEntity() {
    }

    public UserEntity(Long id, String login, String password, String firstName, String secondName, String gender,
                      String year, String email, String phone, String passportCode, Integer userTypeId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.year = year;
        this.email = email;
        this.phone = phone;
        this.passportCode = passportCode;
        this.userTypeId = userTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassportCode() {
        return passportCode;
    }

    public void setPassportCode(String passportCode) {
        this.passportCode = passportCode;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", gender='" + gender + '\'' +
                ", year='" + year + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passportCode='" + passportCode + '\'' +
                ", userTypeId=" + userTypeId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, secondName, gender, year,
                email, phone, passportCode, userTypeId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(year, that.year) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(passportCode, that.passportCode) &&
                Objects.equals(userTypeId, that.userTypeId);
    }
}
