package ua.epam.training.kukuruza.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String gender;
    private String email;
    private String phone;
    private String passportCode;
    private Integer userTypeId;
    private Integer userStatusId;

    public static class Builder {
        private Long id;
        private String login;
        private String password;
        private String firstName;
        private String secondName;
        private String gender;
        private String email;
        private String phone;
        private String passportCode;
        private Integer userTypeId;
        private Integer userStatusId;

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

        public Builder setUserStatusId(Integer userStatusId) {
            this.userStatusId = userStatusId;
            return this;
        }

        public User build() {
            return new User(id, login, password, firstName, secondName, gender,
                    email, phone, passportCode, userTypeId, userStatusId);
        }
    }

    public User() {
    }

    public User(Long id, String login, String password, String firstName, String secondName,
                String gender, String email, String phone, String passportCode,
                Integer userTypeId, Integer userStatusId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.passportCode = passportCode;
        this.userTypeId = userTypeId;
        this.userStatusId = userStatusId;
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

    public Integer getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(Integer userStatusId) {
        this.userStatusId = userStatusId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passportCode='" + passportCode + '\'' +
                ", userTypeId=" + userTypeId +
                ", userStatusId=" + userStatusId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, secondName,
                gender, email, phone, passportCode, userTypeId, userStatusId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(passportCode, user.passportCode) &&
                Objects.equals(userTypeId, user.userTypeId) &&
                Objects.equals(userStatusId, user.userStatusId);
    }
}
