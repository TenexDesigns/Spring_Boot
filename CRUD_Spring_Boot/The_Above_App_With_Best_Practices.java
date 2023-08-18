Certainly! Here's a modified version of your code that adheres to best practices by implementing a separation of concerns using the service layer pattern:

1. **Model (Employee Entity)**:
   The `Employee` entity remains the same:

```java
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    // Getters and setters
}
```

2. **Repository (Employee Repository)**:
   The repository remains unchanged:

```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
```

3. **Service (Employee Service)**:
   Create an interface `EmployeeService` to define service methods, and an implementation class `EmployeeServiceImpl` that provides the implementation for CRUD operations:

```java
public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee updatedFields);
    void deleteEmployee(Long id);
}

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee of id " + id + " not found"));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employee.getFirstName() == null && employee.getLastName() == null) {
            throw new BadRequestException("Failed to create employee");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedFields) {
        Employee employee = getEmployeeById(id);

        if (updatedFields.getFirstName() != null) {
            employee.setFirstName(updatedFields.getFirstName());
        }
        if (updatedFields.getLastName() != null) {
            employee.setLastName(updatedFields.getLastName());
        }
        if (updatedFields.getEmailId() != null) {
            employee.setEmailId(updatedFields.getEmailId());
        }

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
```

4. **Controller (Employee Controller)**:
   The controller now delegates the request handling to the service methods:

```java
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> createResource(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok("Resource created successfully");
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedFields) {
        return employeeService.updateEmployee(id, updatedFields);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee of id " + id + " has been deleted");
    }
}
```

By separating the concerns into different layers (`Model`, `Repository`, `Service`, and `Controller`), the code follows a cleaner and more maintainable structure. The service layer encapsulates the business logic, and the controller acts as the entry point for handling HTTP requests and responses. This promotes better organization, testability, and scalability in your Spring Boot application.



