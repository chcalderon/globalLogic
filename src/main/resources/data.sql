--
--
--CREATE TABLE userglobal (
--  user_id INT AUTO_INCREMENT  PRIMARY KEY,
--  name VARCHAR(250) NOT NULL,
--  email VARCHAR(250) NOT NULL,
--  password VARCHAR(250) DEFAULT ,
--  creador DATETIME,
--  modificado DATETIME,
--  last_login DATETIME,
--  active BOOLEAN,
--  token VARCHAR(250)
--);
--
--
--
--CREATE TABLE phone (
--  phone_id INT AUTO_INCREMENT  PRIMARY KEY,
--  number VARCHAR(250) NOT NULL,
--  city_code VARCHAR(250) NOT NULL,
--  country_code VARCHAR(250) DEFAULT ,
--  EntityUser_id INT CONSTRAINT FOREIGN KEY REFERENCES User(user_id)
--);

insert into usuario(user_id,name,email,password,creado,modificado,last_login,active,token)
values(random_uuid(),'admin','admin@globallogic.com','Admin123',null,null,null,TRUE,'');

INSERT INTO phone(phone_id,number,city_code,country_code)
VALUES (random_uuid(),'1234567','1','57');