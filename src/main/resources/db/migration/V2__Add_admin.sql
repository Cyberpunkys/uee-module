insert into usr(id, username, password, enabled)
values (1, 'admin', '123', true);

insert into user_role(user_id, roles)
values (1, 'ADMIN');
