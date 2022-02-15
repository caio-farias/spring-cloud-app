package com.av3.springcloudappuserdata;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Payload implements Supplier<UserData> {

  @Override
  public UserData get() {
    UserData userData = new UserData();
    userData.setUsers(this.users());
    return userData;
  }

  private List<User> users() {
    return new ArrayList<>() {
      {
        add(new User("TESTINHO"));
      }
    };
  }
}
