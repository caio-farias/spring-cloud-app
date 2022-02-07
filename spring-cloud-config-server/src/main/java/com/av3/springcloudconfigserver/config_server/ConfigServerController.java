package com.av3.springcloudconfigserver.config_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigServerController {

  @Autowired
  private ConfigServerProperties configurationServerProperties;

  @GetMapping("")
  public Config getConfig() {
    return new Config(
        configurationServerProperties.getConnection(),
        configurationServerProperties.getHost(),
        configurationServerProperties.getPort(),
        configurationServerProperties.getDbUserUsername(),
        configurationServerProperties.getDbUserPassword());
  }

}
