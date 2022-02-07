package com.av3.springcloudapp.common.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenStatus extends RuntimeException {
  public ForbiddenStatus(String description) {
    super(description);
  }
}
