When you make changes to your Spring Boot application, such as removing the Spring Security dependency, the changes will not be automatically reflected in the running server instance. You will need to restart the server for the changes to take effect.

In general, Spring Boot provides a feature known as "Hot Swapping" or "Hot Reloading" that allows you to make code changes without restarting the entire application server. However, not all changes can be hot-swapped, and in some cases, changes may still require a server restart.

For example, adding or removing a dependency, like Spring Security, usually requires a server restart because it affects the application's classpath and configuration. Changes to most application-level code (controllers, services, etc.) can often be hot-swapped without a restart.

To enable hot-swapping in your Spring Boot project, you can include the `spring-boot-devtools` dependency in your project. This dependency provides development-time features, including automatic restarts on code changes.

Here's how you can include the `spring-boot-devtools` dependency in your `pom.xml` (Maven) or `build.gradle` (Gradle):

For Maven:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
</dependency>
```

For Gradle:

```groovy
implementation 'org.springframework.boot:spring-boot-devtools'
```

With this dependency included, when you make code changes, the Spring Boot DevTools will automatically restart the application for you. However, keep in mind that not all changes can be hot-swapped, and sometimes a manual server restart is still required.

Remember that while hot-swapping can save development time, it's important to ensure that your application's state remains consistent after a reload. It's also recommended to perform a full application restart before deploying to production to ensure that all changes are applied correctly.
