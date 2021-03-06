package com.av3.springcloudappuserinfo.services.users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
  private Integer id;
  private String firstName;
  private String lastName;
  private final static SimpleDateFormat formatBirthDate = new SimpleDateFormat("dd/MM/yyyy");
  private Date birthDate;
  private String username;
  private String email;

  public User() {
  }

  public User(
      Integer id,
      String firstName,
      String lastName,
      String birthDate,
      String username,
      String email) {
    this.id = id;
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setBirthDate(birthDate);
    this.setUsername(username);
    this.setEmail(email);
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
    if (birthDate == null)
      return;
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
