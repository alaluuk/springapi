# Spring Boot REST API with Jwt

## Database 

Execute below code in SQL-client
<pre>
CREATE DATABASE spring_db;
CREATE USER 'netuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'netpass';
GRANT ALL on spring_db.* to 'netuser'@'localhost';

USE spring_db;

CREATE TABLE book(
  id int primary key auto_increment,
  name VARCHAR(255),
  author VARCHAR(50),
  isbn VARCHAR(15)
);

INSERT INTO book VALUES(1, 'JS Programming','Jim Jones','123-456-77');
INSERT INTO book VALUES(2, 'PHP Programming','Lisa Smith','111-222-33');

CREATE TABLE user(
  id int primary key auto_increment,
  username VARCHAR(20),
  password VARCHAR(255),
  UNIQUE (username)
);
</pre>