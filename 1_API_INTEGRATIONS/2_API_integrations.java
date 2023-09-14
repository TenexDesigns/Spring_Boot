API (Application Programming Interface) integration is the connection between two or more applications, via their APIs, that enables these systems to exchange data. APIs are sets of subroutine definitions, protocols, and tools for building application software. They are a set of clearly defined methods of communication between various software components [Source 1](https://tray.io/blog/what-is-an-api-integration-for-non-technical-people), [Source 2](https://www.postman.com/api-platform/api-integration/), [Source 3](https://rapidapi.com/blog/api-glossary/api-integration/).

API integrations can be used to automate and streamline business processes by allowing different software systems to interact and share data. They can connect private APIs to build highly scalable microservice meshes, or incorporate third-party functionality into an application by integrating a public API [Source 2](https://www.postman.com/api-platform/api-integration/).

In a Spring Boot application, you can integrate an API by using tools like RestTemplate or WebClient to consume the API, and annotations like @RestController to create APIs.

Here's an example of how to integrate the OpenWeatherMap API into a Spring Boot application to fetch weather data:

1. **Add Dependencies**

First, add the necessary dependencies to your `pom.xml` file:

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
</dependencies>
```

2. **Create a Service**

Next, create a service to consume the OpenWeatherMap API:

```java
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeather(String city, String apiKey) {
        ResponseEntity<String> response =
          restTemplate.getForEntity(WEATHER_URL, String.class, city, apiKey);
        return response.getBody();
    }
}
```

In this example, `RestTemplate` is used to consume the OpenWeatherMap API. The `getForEntity` method sends an HTTP GET request to the API URL and returns the response as a `ResponseEntity` [Source 12](https://www.geeksforgeeks.org/how-to-call-or-consume-external-api-in-spring-boot/).

3. **Create a Controller**

Then, create a controller to expose an endpoint that uses the service to fetch the weather data:

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{city}")
    public String getWeather(@PathVariable String city) {
        String apiKey = "your-api-key";
        return weatherService.getWeather(city, apiKey);
    }
}
```

The `@RestController` annotation is used to define a controller. The `@GetMapping` annotation maps HTTP GET requests onto the `getWeather` method. The `@PathVariable` annotation is used to bind the method parameter to a path variable [Source 12](https://www.geeksforgeeks.org/how-to-call-or-consume-external-api-in-spring-boot/).

With this setup, you can now start your Spring Boot application and access the `http://localhost:8080/weather/{city}` endpoint to fetch the weather data for a specific city.

Remember to replace "your-api-key" with your actual OpenWeatherMap API key. Always secure your API keys and never expose them in your code or version control systems.
