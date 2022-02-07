package com.av3.springcloudconfigserver.config_server;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("sprinc-cloud-app-config")
public class ConfigServerProperties {
  private String connection;
  private String host;
  private int port;
  private String dbUserUsername;
  private String dbUserPassword;

  public String getDbUserPassword() {
    return dbUserPassword;
  }

  public String getConnection() {
    return connection;
  }

  public void setConnection(String connection) {
    this.connection = connection;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getDbUserUsername() {
    return dbUserUsername;
  }

  public void setDbUserUsername(String dbUserUsername) {
    this.dbUserUsername = dbUserUsername;
  }

  public void setDbUserPassword(String dbUserPassword) {
    this.dbUserPassword = dbUserPassword;
  }

}
