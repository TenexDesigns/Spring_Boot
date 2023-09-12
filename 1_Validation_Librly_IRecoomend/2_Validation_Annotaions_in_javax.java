Java Bean Validation (JSR 380/303 - javax.validation) provides a set of annotations and constraints that you can use to define validation rules for your Java beans. Here are some of the commonly used annotations and constraints provided by Java Bean Validation:

1. **`@NotNull`:** This annotation marks a field as not null. It is used to ensure that a field has a non-null value.

   ```java
   @NotNull
   private String username;
   ```

2. **`@Size`:** The `@Size` annotation is used to specify the size (length) constraints for a field, such as minimum and maximum lengths.

   ```java
   @Size(min = 3, max = 50)
   private String username;
   ```

3. **`@Email`:** The `@Email` annotation is used to validate that a field's value is a valid email address.

   ```java
   @Email
   private String email;
   ```

4. **`@Pattern`:** The `@Pattern` annotation allows you to specify a regular expression pattern that a field's value must match.

   ```java
   @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
   private String phoneNumber;
   ```

5. **`@Min` and `@Max`:** These annotations are used to specify minimum and maximum values for numeric fields.

   ```java
   @Min(value = 18, message = "Must be at least 18 years old")
   private int age;

   @Max(value = 100, message = "Value must be less than or equal to 100")
   private int score;
   ```

6. **`@NotBlank` and `@NotEmpty`:** These annotations are used to ensure that a string field is not blank or not empty.

   ```java
   @NotBlank(message = "Field cannot be blank")
   private String name;

   @NotEmpty(message = "Field cannot be empty")
   private String address;
   ```

7. **`@AssertTrue` and `@AssertFalse`:** These annotations are used to ensure that a boolean field is `true` or `false`, respectively.

   ```java
   @AssertTrue(message = "Value must be true")
   private boolean active;

   @AssertFalse(message = "Value must be false")
   private boolean isDeleted;
   ```

8. **`@Past` and `@Future`:** These annotations are used to validate that a date field represents a date in the past or the future, respectively.

   ```java
   @Past(message = "Date must be in the past")
   private Date birthDate;

   @Future(message = "Date must be in the future")
   private Date eventDate;
   ```

These are just some of the commonly used annotations and constraints provided by Java Bean Validation. You can use these annotations to define validation rules for your Java beans, and the Bean Validation framework will automatically perform the validation when you call `Validator` methods.






----------------------------------------------------------------------------------------------------------------------------------------------------------------


The Java Bean Validation API (JSR 380/303) provides a set of annotations that you can use to validate Java beans. Some of the commonly used annotations are:

1. `@NotNull`: This annotation validates that the annotated property value is not null.
2. `@AssertTrue`: This annotation validates that the annotated property value is true.
3. `@Size`: This annotation validates that the annotated property value has a size between the specified min and max values. It can be used with String, Collection, Map, and array properties.
4. `@Min`: This annotation validates that the annotated property has a value greater than or equal to the specified min value.
5. `@Max`: This annotation validates that the annotated property has a value less than or equal to the specified max value.
6. `@Email`: This annotation validates that the annotated property is a valid email address.

Here's an example of how to use these annotations in a Java bean:

```java
import javax.validation.constraints.*;

public class User {
    @NotNull(message = "Name cannot be null")
    private String name;
    
    @AssertTrue
    private boolean working;
    
    @Size(min = 10, max = 200, message = "About Me must be between 10 and 200 characters")
    private String aboutMe;
    
    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;
    
    @Email(message = "Email should be valid")
    private String email;
    
    // Getters and setters
}
```

You can validate an instance of the `User` class using a `Validator` object. Here's an example:

```java
import javax.validation.*;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        user.setName(null);
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(50);
        user.setEmail("invalid email");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }
}
```

This will output the validation error messages for each violated constraint.

Note: The example above uses the `javax.validation` package, but in some contexts (such as Jakarta EE) you may need to use the `jakarta.validation` package instead.


























