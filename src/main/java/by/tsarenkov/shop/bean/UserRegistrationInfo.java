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
    private Date dateOfBirth;

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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
        UserRegistrationInfo user = (UserRegistrationInfo) o;
        return Objects.equals(name, user.name)
                && surname.equals(user.surname)
                && email.equals(user.email)
                && password.equals(user.password)
                && phoneNumber.equals(user.phoneNumber)
                && dateOfBirth.equals(user.dateOfBirth);
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
