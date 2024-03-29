# spring-cloud-app

Spring Cloud App is a distributed application that uses Spring Cloud resources to implement a resilient, scalable, and robust solution. The goal of this application is to implement the non-functional requirements mentioned before. In this application, we make use of various resources from the Spring ecosystem. The main resources used are Spring Cloud Gateway, Resilience4j, Spring Cloud Eureka Server, Spring Cloud Config Server, and Spring Web.

Below we have a component diagram of the application:

![diagrama de componentes - spring cloud app](https://user-images.githubusercontent.com/52352222/155230401-658e3025-0083-4819-b323-116362e34f4d.png)

## Spring Cloud Config Server
  The implementation of the Config Server is based on a local Git repository that aggregates all the servers' configurations used on Spring Cloud App application. At the initialization of any service, that service is consulted to provide the configuration of each application.

 ![Screenshot from 2022-02-22 19-39-06](https://user-images.githubusercontent.com/52352222/155231559-a1331ef0-9475-47d4-8b82-ddbb67e6d88f.png)
 
 ### Example of Eureka Server configuration in the Config Server's local Git repository
 ```yaml
 server.port: 8761
spring:
  application.name: spring-cloud-app-eureka
  eureka:
    client:
      registerWithEureka: false
      fetchRegistry: false
  cloud:
    loadbalancer:
      ribbon:
        enabled: false

management:
  endpoints:
    web:
      exposure:
        include: "*"
 ```
## Spring Cloud Eureka Server
   This Eureka Server acts as a Discovery Server, providing service registration (Eureka clients) and a mapping of SERVICE_NAME ---> IP: port and other functionalities. Service monitoring occurs through the HeartBeat pattern. Example below:
   
   ![Screenshot from 2022-02-22 19-51-56](https://user-images.githubusercontent.com/52352222/155233520-b73775cc-5ed9-4e62-9032-ffe21f249e49.png)

### Resilience4j

 In this project, we use the following resilience patterns: CircuitBreaker, Retry, Bulkhead. These patterns are implemented together with OpenFeign to create interfaces that simplify the implementation. Both the API Gateway and the User Info microservice make use of these patterns. The implementation below is one interface used on the User Info microservice (depends on the user microservices).
  
```Java
@FeignClient("${microservices.users.name}${microservices.users.context-path}")
public interface UsersServiceInterface {

  @CircuitBreaker(name = "circuitbreakerDefault", fallbackMethod = "fallback")
  @Retry(name = "retryDefault", fallbackMethod = "retryFallback")
  @Bulkhead(name = "bulkheadDefault", fallbackMethod = "fallback")
  @RequestMapping(method = RequestMethod.GET, value = "${microservices.users.endpoint}/{userId}", consumes = "application/json")
  User getUserById(@PathVariable("userId") Integer userId);

  public default ResponseEntity<String> fallback(Throwable t) {
    return ResponseEntity.ok("FALLBACK(USERS) RUNNING: " + t.getMessage());
  }

  public default ResponseEntity<String> retryFallback(Throwable t) {
    return ResponseEntity.ok("FALLBACK(USERS) RUNNING FOR RETRY!!");
  }
```

## JMeter & Zipkin
  ### JMeter
  Development of threads in the test (only with 5 threads because there are 13 instances + JMeter + Zipkin; the PC is very tired)
  ![test](https://user-images.githubusercontent.com/52352222/155240132-f70bcf77-e24b-4a67-b8d8-60ce4b3119b9.png)
  
  ##### Test using JMeter 
  ![Screenshot from 2022-02-22 20-15-33](https://user-images.githubusercontent.com/52352222/155240011-613b5fce-e41e-46c0-936b-edabf28df493.png)
  
  ### Tracing using Zipkin
  ![Screenshot from 2022-02-22 20-57-04](https://user-images.githubusercontent.com/52352222/155240023-90a1ad56-0513-4595-97be-2824db44c17a.png)

--- PT-BR
## Spring Cloud Config Server
 A implementação do Config Server é feita a partir de um repositório Git local que agrega todas as configurações dos serviços das aplicações do Spring Cloud App. Na inicialização de qualquer serviço, esse serviço é consultado para disponibilziar a configuração de cada aplicação.

 ![Screenshot from 2022-02-22 19-39-06](https://user-images.githubusercontent.com/52352222/155231559-a1331ef0-9475-47d4-8b82-ddbb67e6d88f.png)
 
 ### Exemplo de configuração do Eureka Server no repositório Git local do Config Server
 ```yaml
 server.port: 8761
spring:
  application.name: spring-cloud-app-eureka
  eureka:
    client:
      registerWithEureka: false
      fetchRegistry: false
  cloud:
    loadbalancer:
      ribbon:
        enabled: false

management:
  endpoints:
    web:
      exposure:
        include: "*"
 ```
## Spring Cloud Eureka Server
   Este Eureka Server atua comom Discovery Server, isto é, provém a funcionalidade de registros de serviços (Eureka clients) e um mapeamento de NOME_DE_SERVIÇO ---> IP:porta e entre demais funcionalidades. O monitoramento dos serviços ocorre por meio do padrão HeartBeat. Exemplo abaixo:
   
   ![Screenshot from 2022-02-22 19-51-56](https://user-images.githubusercontent.com/52352222/155233520-b73775cc-5ed9-4e62-9032-ffe21f249e49.png)

### Resilience4j
  Neste projeto utilizamos os seguintes padrões de resiliência: CircuitBreaker, Retry, Bulkhead. Esses padrões são implementados juntamente ao OpenFeign para criar interfaces que simplifiquem a implementação. Tanto API Gateway quanto o microserviço de User Info fazem uso desses padrões. A implementaçãoa abaixo é uma das interfaces do microserviço de User Info (depende do microserviços de usuários).
  
```Java
@FeignClient("${microservices.users.name}${microservices.users.context-path}")
public interface UsersServiceInterface {

  @CircuitBreaker(name = "circuitbreakerDefault", fallbackMethod = "fallback")
  @Retry(name = "retryDefault", fallbackMethod = "retryFallback")
  @Bulkhead(name = "bulkheadDefault", fallbackMethod = "fallback")
  @RequestMapping(method = RequestMethod.GET, value = "${microservices.users.endpoint}/{userId}", consumes = "application/json")
  User getUserById(@PathVariable("userId") Integer userId);

  public default ResponseEntity<String> fallback(Throwable t) {
    return ResponseEntity.ok("FALLBACK(USERS) RUNNING: " + t.getMessage());
  }

  public default ResponseEntity<String> retryFallback(Throwable t) {
    return ResponseEntity.ok("FALLBACK(USERS) RUNNING FOR RETRY!!");
  }
```

## JMeter & Zipkin
  ### JMeter
  #### Desenvolvimento das threads no teste (apenas com 5 threads pois existem 13 instâncias  + JMeter + Zipkin; PC está cansadíssimo)
  ![test](https://user-images.githubusercontent.com/52352222/155240132-f70bcf77-e24b-4a67-b8d8-60ce4b3119b9.png)
  
  ##### Teste da aplicação utilizando JMeter 
  ![Screenshot from 2022-02-22 20-15-33](https://user-images.githubusercontent.com/52352222/155240011-613b5fce-e41e-46c0-936b-edabf28df493.png)
  
  ### Tracing da aplicação utilizando Zipkin
  ![Screenshot from 2022-02-22 20-57-04](https://user-images.githubusercontent.com/52352222/155240023-90a1ad56-0513-4595-97be-2824db44c17a.png)
