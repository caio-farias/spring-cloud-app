package com.av3.springcloudapptasks.common.discovery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class DiscoveryController {

  @Value("${server.port}")
  private int serverPort;

  @Value("${spring.application.name}")
  private String appName;

  @GetMapping
  public ResponseEntity<String> ping() {
    return ResponseEntity.ok("OK from"
        + String.format("%s:%i", appName, serverPort));
  }
}
