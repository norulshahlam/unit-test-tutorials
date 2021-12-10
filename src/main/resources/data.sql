create table item (id integer not null, name varchar(255), price integer not null, quantity integer not null, primary key (id));

insert into item(id, name, price, quantity) values(10001,'Item1',10,20);
insert into item(id, name, price, quantity) values(10002,'Item2',5,10);
insert into item(id, name, price, quantity) values(10003,'Item3',15,2);