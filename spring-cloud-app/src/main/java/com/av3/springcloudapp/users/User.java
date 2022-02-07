package com.av3.springcloudapp.users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = { "password" }, allowGetters = false, allowSetters = true)
public class User {
  @Id
  @SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)

  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")

  private Integer id;
  private String firstName;
  private String lastName;
  private final static SimpleDateFormat formatBirthDate = new SimpleDateFormat("dd/MM/yyyy");
  private Date birthDate;
  private String username;
  private String email;
  private String password;

  public User() {
  }

  public User(
      Integer id,
      String firstName,
      String lastName,
      String birthDate,
      String username,
      String email,
      String password) {
    this.id = id;
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setBirthDate(birthDate);
    this.setUsername(username);
    this.setEmail(email);
    this.setPassword(password);
  }

  public User(
      String username,
      String password) {
    this.setUsername(username);
    this.setPassword(password);
  }

  @Override
  public String toString() {
    return "{" +
        " id ='" + getId() + "'" +
        ", firstName='" + getFirstName() + "'" +
        ", lastName='" + getLastName() + "'" +
        ", birthDate='" + getBirthDate() + "'" +
        ", username='" + getUsername() + "'" +
        ", email='" + getEmail() + "'" +
        "}";
  }

  public String getBirthDate() {
    if (birthDate == null)
      return null;
    return formatBirthDate.format(birthDate).toString();
  }

  public void setBirthDate(String birthDate) {
    try {
      this.birthDate = formatBirthDate.parse(birthDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Integer getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
