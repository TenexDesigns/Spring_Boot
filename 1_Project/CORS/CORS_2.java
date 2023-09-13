The error you're experiencing is a Cross-Origin Resource Sharing (CORS) error. This occurs when a web application tries to make a request to a domain (or port in this case) that's different from its own. The browser blocks these requests for security reasons, unless the server specifies that it accepts requests from the origin domain of the web application.

In your case, the front-end application at `http://localhost:3000` is trying to make a request to the backend server at `http://localhost:9002`. Because these are different origins (different ports), the browser blocks the request unless the server at `http://localhost:9002` specifies that it accepts requests from `http://localhost:3000`.

To fix this issue in Spring Boot, you can use the `@CrossOrigin` annotation to specify that your server accepts requests from different origins.

Here's an example of how to use the `@CrossOrigin` annotation in your `BookController`:

```java
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/books")
public class BookController {
    // ...
}
```

In this example, the `@CrossOrigin(origins = "http://localhost:3000")` annotation tells Spring Boot to accept requests from `http://localhost:3000` [Source 1](https://spring.io/guides/gs/rest-service-cors/).

You can also allow all origins by using `@CrossOrigin(origins = "*")`, but be careful with this approach because it allows requests from any domain, which might not be what you want for security reasons.

If you want to apply the CORS policy globally to all controllers, you can configure it in a `WebMvcConfigurer`:

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
```

In this example, the `addCorsMappings` method configures the CORS policy for all paths (`/**`) to accept requests from `http://localhost:3000` and allows the `GET`, `POST`, `PUT`, and `DELETE` HTTP methods [Source 2](https://www.baeldung.com/spring-cors).


