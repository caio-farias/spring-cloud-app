package com.av3.springcloudapptasks.common.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedStatus extends RuntimeException {
  public UnauthorizedStatus(String description) {
    super(description);
  }
}
