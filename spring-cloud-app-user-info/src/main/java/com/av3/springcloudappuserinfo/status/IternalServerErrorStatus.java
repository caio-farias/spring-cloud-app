package com.av3.springcloudappuserinfo.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class IternalServerErrorStatus extends RuntimeException {
  public IternalServerErrorStatus(String description) {
    super(description);
  }
}
