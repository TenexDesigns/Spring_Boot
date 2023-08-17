Data Transfer Objects (DTOs) are objects that carry data between different layers of an application, often between the service layer and the presentation (controller) layer. They are used to encapsulate the data that is sent over the network in order to provide a clear and well-defined interface between different parts of the application. DTOs help to address issues related to data exposure, versioning, and minimizing unnecessary data transfer.

Here's how and why DTOs are used and their benefits:

1. **Encapsulation of Data**: DTOs encapsulate specific data fields that need to be exposed to the client without exposing the entire entity's internal structure. This ensures that sensitive or unnecessary data is not exposed, improving security.

2. **Decoupling**: DTOs help in decoupling the internal structure of the data from the external API. This means that changes to the internal data structures won't directly impact the API contracts with clients.

3. **Versioning**: When the internal structure of entities changes, versioning can become challenging if the same entities are exposed directly to clients. By using DTOs, you can manage versioning more effectively and provide backward compatibility.

4. **Optimized Data Transfer**: DTOs allow you to send only the required data over the network, reducing the amount of data transferred and improving the performance of your API.

Here's an example illustrating the use of DTOs in a Spring Boot application:

Let's assume we have a `User` entity with fields `id`, `username`, `email`, and `password`. We want to expose only the `id` and `username` fields to clients.

1. **Entity**:
```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    // Getters, setters, constructors
}
```

2. **User DTO**:
```java
public class UserDTO {
    private Long id;
    private String username;

    // Getters, setters, constructors

    // Static method to convert Entity to DTO
    public static UserDTO fromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
}
```

3. **User Service**:
```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new NotFoundException("User not found"));
        
        return UserDTO.fromEntity(user);
    }
}
```

4. **User Controller**:
```java
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
}
```

In this example, the `UserDTO` class encapsulates only the required fields (`id` and `username`). The `fromEntity` method converts an entity to its corresponding DTO. The service layer retrieves the entity, converts it to a DTO, and returns the DTO to the controller, which then sends it to the client. This way, you control the data that's exposed and maintain separation between the internal entity structure and the external API representation.
