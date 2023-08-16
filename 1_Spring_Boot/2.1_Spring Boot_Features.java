Spring Boot comes with a rich set of features that simplify the development of Java applications. Lets take a look at some of its key features and how they are used:

1. **Spring Initializer:**
The Spring Initializer is a web-based tool that helps you create a new Spring Boot project with your desired configurations. It allows you to choose project metadata, dependencies, and packaging options. You can access it at https://start.spring.io/.

2. **Starter Dependencies:**
Spring Boot provides a collection of starter dependencies, which are opinionated and pre-configured bundles of dependencies for various functionalities. They allow you to quickly add required dependencies without worrying about version conflicts. Here's an example of adding a dependency for Spring Web:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

3. **Auto-Configuration:**
Auto-configuration is a powerful feature that automatically configures beans and components based on the classpath and the libraries used. This reduces the need for manual configuration. For instance, if you include the Spring Data JPA starter, Spring Boot will automatically configure a data source, EntityManager, and other related components.

4. **Spring Boot Annotations:**
Spring Boot introduces various annotations to simplify common tasks:

   - `@SpringBootApplication`: Combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
   - `@RestController`: Combines `@Controller` and `@ResponseBody`.
   - `@RequestMapping`: Maps HTTP requests to controller methods.
   - `@Autowired`: Injects dependencies.
   
5. **Developer Tools:**
Spring Boot provides a set of developer tools to enhance the development experience:

   - Automatic application restart during development.
   - LiveReload for browser refresh after changes.
   - Remote debugging support.
   - Property overrides using `application.properties` files.

6. **Actuator:**
Spring Boot Actuator adds production-ready features to your application. It exposes endpoints that provide information about application health, metrics, and other operational aspects. To enable Actuator, you add the dependency and configure the desired endpoints. For example:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

7. **Externalized Configuration:**
Spring Boot allows you to externalize configuration using property files (e.g., `application.properties` or `application.yml`). You can configure properties specific to your environment, such as database connection details or external service URLs.

Here's a minimal example of a Spring Boot application that showcases some of these features:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}

@RestController
class HelloController {

    @GetMapping("/hello")
    String hello() {
        return "Hello, Spring Boot!";
    }
}
```

In this example, we've seen the use of `@SpringBootApplication`, `@RestController`, and a simple request mapping with `@GetMapping`.

Remember that Spring Boot is designed to streamline your development process by providing these features, reducing the need for manual configuration and boilerplate code.

