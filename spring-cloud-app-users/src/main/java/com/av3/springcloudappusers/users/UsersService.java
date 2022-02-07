package com.av3.springcloudappusers.users;

import java.util.List;
import java.util.Objects;

import com.av3.springcloudappusers.common.status.ForbiddenStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersService {

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @JsonIgnoreProperties({ "password" })
  public User getUserById(Integer userId) {
    return usersRepository.findById(userId)
        .orElseThrow(() -> new ForbiddenStatus("User does not exists."));
  }

  public List<User> getUsers() {
    return usersRepository.findAll();
  }

  public User getUserByEmail(String email) {
    return usersRepository.findByEmail(email)
        .orElseThrow(() -> new ForbiddenStatus("User does not exists."));
  }

  public User getUserByUsername(String username) {
    return usersRepository.findByUsername(username)
        .orElseThrow(() -> new ForbiddenStatus("User does not exists."));
  }

  public User createUser(User user) {
    boolean sameEmailUser = usersRepository.findByEmail(user.getEmail()).isPresent();

    if (sameEmailUser)
      throw new ForbiddenStatus("Email already in use.");

    boolean sameUsernameUser = usersRepository.findByUsername(user.getUsername()).isPresent();

    if (sameUsernameUser)
      throw new ForbiddenStatus("Username already in use.");

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return usersRepository.save(user);
  }

  @Transactional
  public User updateUser(Integer userId, String firstName, String lastName, String birthDate, String email,
      String username) {
    User user = usersRepository.findById(userId)
        .orElseThrow(() -> new ForbiddenStatus("User does not exists."));

    if (firstName != null
        && firstName.length() > 0
        && !Objects.equals(firstName, user.getFirstName()))
      user.setFirstName(firstName);

    if (lastName != null
        && lastName.length() > 0
        && !Objects.equals(lastName, user.getLastName()))
      user.setLastName(lastName);

    if (username != null
        && username.length() > 0
        && !Objects.equals(username, user.getUsername()))
      user.setUsername(username);

    if (email != null
        && email.length() > 4
        && !Objects.equals(email, user.getEmail()))
      user.setEmail(email);

    if (birthDate != null
        && birthDate.length() > 4
        && !Objects.equals(birthDate, user.getBirthDate()))
      user.setBirthDate(birthDate);

    return user;
  }

  public void deleteUser(Integer userId) {
    boolean exists = usersRepository.existsById(userId);

    if (!exists)
      throw new ForbiddenStatus("User does not exists.");

    usersRepository.deleteById(userId);
  }

}
