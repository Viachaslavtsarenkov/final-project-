package by.tsarenkov.shop.bean;

import by.tsarenkov.shop.bean.status.UserStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

    private int userId;
    private String name;
    private String surname;
    private UserRole role;
    private String email;
    private String password;
    private UserStatus status;
    private String phoneNumber;

    public User() {

    }

    public User(UserBuilder builder) {
        this.userId = builder.userId;
        this.name = builder.name;
        this.surname = builder.surname;
        this.role = builder.role;
        this.email = builder.email;
        this.password = builder.password;
        this. status = builder.status;
        this.phoneNumber = builder.phoneNumber;
    }

    public int getUserId() {
        return  userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return userId == user.userId && Objects.equals(name, user.name)
                && Objects.equals(surname, user.surname)
                && role == user.role
                && email.equals(user.email)
                && password.equals(user.password)
                && status.equals(user.status)
                && phoneNumber.equals(user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, surname, password, email, role, password, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static class UserBuilder {
        private int userId;
        private String name;
        private String surname;
        private UserRole role;
        private String email;
        private String password;
        private UserStatus status;
        private String phoneNumber;

        public UserBuilder() {}

        public int getUserId() {
            return userId;
        }

        public UserBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public String getName() {
            return name;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public String getSurname() {
            return surname;
        }

        public UserBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserRole getRole() {
            return role;
        }

        public UserBuilder setRole(UserRole role) {
            this.role = role;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserStatus getStatus() {
            return status;
        }

        public UserBuilder setStatus(UserStatus status) {
            this.status = status;
            return this;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public UserBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public User getInstance() {
            return new User(this);
        }
    }
}
