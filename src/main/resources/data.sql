insert into usuario(user_id,name,email,password,creado,modificado,last_login,active,token)
values(random_uuid(),'admin','admin@globallogic.com','Admin12',null,null,null,TRUE,'');

INSERT INTO phone(phone_id,number,city_code,country_code)
VALUES (random_uuid(),'1234567','1','57');