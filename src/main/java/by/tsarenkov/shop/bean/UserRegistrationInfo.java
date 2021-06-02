package by.tsarenkov.shop.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class UserRegistrationInfo implements Serializable {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private String dateOfBirth;

    public UserRegistrationInfo() {

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRegistrationInfo that = (UserRegistrationInfo) o;
        return Objects.equals(name, that.name)
                && Objects.equals(surname, that.surname)
                && Objects.equals(email, that.email)
                && Objects.equals(password, that.password)
                && Objects.equals(phoneNumber, that.phoneNumber)
                && Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, password, phoneNumber, dateOfBirth);
    }

    @Override
    public String toString() {
        return "UserRegistrationInfo{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
