## Springboot-AzureActiveDirectory


### Setup in a Non-Boot Project
4.1. Creating a ClientRegistrationRepository Bean
If we're not working with a Spring Boot application, we'll need to define a ClientRegistrationRepository bean that contains an internal representation of the client information owned by the authorization server:

 ```xml
@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static List<String> clients = Arrays.asList("google", "facebook");
 
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = clients.stream()
          .map(c -> getRegistration(c))
          .filter(registration -> registration != null)
          .collect(Collectors.toList());
         
        return new InMemoryClientRegistrationRepository(registrations);
    }
}
```
#### Here we're creating an InMemoryClientRegistrationRepository with a list of ClientRegistration objects.

https://www.baeldung.com/spring-security-5-oauth2-login
