package com.av3.springcloudappuserdata;

import java.util.function.Function;

public class Exchange implements Function<String, User> {

  @Override
  public User apply(String value) {
    return new User(value);
  }

}
