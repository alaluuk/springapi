# Spring Boot REST API with Jwt

This is an example, which uses **MySQL**. 

## Database 

In order to make the application work, you need to create the database. 
So, execute the file **spring_db_dump.sql** . After that you should have a database 
named **spring_db**, which has two tables: book and user.

And then below code in SQL-client
<pre>
CREATE USER 'netuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'netpass';
GRANT ALL on spring_db.* to 'netuser'@'localhost';
</pre>

## Login

Start the application with the command **mvnw clean spring-boot:run** and then you shoul be able to login
**http://localhost:8080/login** using these 
<ul>
<li>username=user01</li>
<li>password=pass01</li>
</ul>
Now the api should return the token and then you shoul be able to read, add, update, and delete users and books.
The endpoints are 
<ul>
<li>http://localhost:8080/user</li>
<li>http://localhost:8080/book</li>
</ul>

## Important files for the JwtToken system

In the file **ApplicationSecurity.java** there is this line 
<pre>
.antMatchers("/login").permitAll()
</pre>
And that's why you can reach the login endpoint without the token.

Then in the folder **jwt** there is files
<ul>
<li>JwtTokenFilter.java</li>
<li>JwtTokenUtil.java</li>
</ul>
In the JwtTokenUtil.java, you can change the token expiration time, by editing this line 
<pre>
private static final long EXPIRE_DURATION = 30*60 * 1000; // 30 minutes
</pre>

In the folder **model** there is files
<ul>
<li>JwtRequest.java</li>
<li>JwtResponse.java</li>
</ul>