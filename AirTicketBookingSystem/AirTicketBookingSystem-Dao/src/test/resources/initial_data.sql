insert into user_acount (id, description, firstName, nick, password, surname) values (1, 'testAdmin', 'AdminFirstName', 'admin', '$2a$10$RE9S3v2J6pkJ9PqNQJcRyukKjdnmxEmjIythEx5GzKTzWs2NuBL76', 'AdminSurname');
insert into user_acount (id, description, firstName, nick, password, surname) values (2, 'testUser', 'UserFirstName', 'user', '$2a$10$Tt6vIyNRPQqqFjUCODduD.E4BZe7liy0QIhqqJhkchfxczGB7cqCS', 'UserSurname');
insert into userrole (id, user_id, role) values (1, 1,'ROLE_ADMIN');
insert into userrole (id, user_id, role) values (2, 2,'ROLE_USER');