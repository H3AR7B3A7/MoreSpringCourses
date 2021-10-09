# Spring JDBC

- Framework
  - Less error prone
- Pattern based
  - No boilerplate
- Injected resources
  - Uses Spring DI libraries
- ORM (like Hibernate and JPA, but simpler)
- Works well with existing databases

## Why

- Reduce complexity
- Design
  - Testability
- Portability
- Business Focus
  - No configuration

## Example

Regular JDBC:
```java
public Car getById(String id) {
	Connection con = null;
	PreparedStatement stmt = null;
	Resultset = null;
	
	try {
		String sql = "SELECT * FROM car WHERE id = ?";
		con = DriverManager.getConnection("localhost:3306/cars);
		stmt = con.prepareStatement(sql);
		stmt.setString(1, id);
		rs = stmt.executeQuery();
		if (rs.next()) {
			Car car = new Car();
			car.setMake(rs.getString(1));
			return car;
		} else {
			return null;
		}
	} catch (SQLException e) { 
		e.printStackTRrace(); 
	} finally {
		try {
			if (rt != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (Exception e) {}
	}
	return null;
}
```

Spring JDBC:
```java
public Car findCar(Strin id) {
	return jdbcTemplate.queryForObject(sql, Car.class, id);
}
```

## My SQL & phpMyAdmin

To download and run a docker container instance of My SQL run [MySQL.sh](MySQL.sh).

Additionally, an instance of phpMyAdmin will be available on: 

http://localhost:8081

```
username: root
password: pass
```

## Dependencies

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

## Example Contents

- JdbcTemplate
- NamedJdbcTemplate & named parameters
- ExceptionHandler
- Transactions

## Some Interesting Articles

[JDBC or Hibernate with/without Spring Data](https://stackoverflow.com/questions/42470060/spring-data-jdbc-spring-data-jpa-vs-hibernate/42488593)

[Difference between Hibernate & Data JPA](https://dzone.com/articles/what-is-the-difference-between-hibernate-and-sprin-1)