package com.av3.springcloudappuserinfo.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestStatus extends RuntimeException {
  public BadRequestStatus(String description) {
    super(description);
  }
}
