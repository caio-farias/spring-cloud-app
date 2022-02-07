package com.av3.springcloudappusers.common.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class DiscoveryController {

  @Autowired
  Environment enviroment;

  @Value("${spring.application.name}")
  private String appName;

  @GetMapping
  public ResponseEntity<String> ping() {
    final String serverPort = enviroment.getProperty("local.server.port");
    return ResponseEntity.ok("OK from "
        + String.format("%s:" + serverPort, appName));
  }
}
