Data Transfer Objects (DTOs) are objects used to transfer data between different layers or components of an application. They encapsulate the data that needs to be transferred and provide a clean interface for communication. DTOs are commonly used in RESTful APIs to control the data exposed to clients and decouple the internal representation of the data from the API.

Here's an example to illustrate the use of DTOs and their usefulness:

Consider a simple Employee entity class:

```java
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    // Other properties, getters, and setters
}
```

In a RESTful API, you may have an endpoint to retrieve employee details:

```java
@GetMapping("/employees/{id}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
    Employee employee = employeeService.getEmployeeById(id);
    return ResponseEntity.ok(employee);
}
```

In this example, the controller returns the `Employee` entity object directly. However, returning the entity object as-is may expose sensitive information or unnecessary details to the clients. It also tightly couples the API response to the internal representation of the data.

To address these issues, you can introduce a DTO to encapsulate the response data:

```java
public class EmployeeDTO {
    private Long id;
    private String fullName;
    // Other properties, getters, and setters
}
```

Now, you can modify the controller to use the DTO:

```java
@GetMapping("/employees/{id}")
public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
    Employee employee = employeeService.getEmployeeById(id);
    EmployeeDTO employeeDTO = mapEmployeeToDTO(employee);
    return ResponseEntity.ok(employeeDTO);
}
```

In this updated code, the `getEmployeeById` method retrieves the `Employee` entity from the service layer. Then, it maps the entity to the `EmployeeDTO` using a mapping method (`mapEmployeeToDTO`). Finally, the controller returns the `EmployeeDTO` object in the response.

By using DTOs, you have more control over the data exposed to clients. You can include only the necessary fields and exclude any sensitive or unnecessary information. DTOs also decouple the API response from the internal representation of the data, allowing you to make changes to the entity without affecting the API contract.

Additionally, DTOs can be used for request payloads as well. When accepting data from clients, you can use DTOs to map the incoming JSON/XML to Java objects, perform validations, and apply transformations before passing the data to the service layer.

Overall, DTOs help improve the maintainability, flexibility, and security of your API by providing a clear separation between the internal data representation and the API contract.
