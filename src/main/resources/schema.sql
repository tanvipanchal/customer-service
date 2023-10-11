create table customer(
    id int auto_increment,
    firstName varchar(255) NOT NULL,
    lastName varchar(225) NOT NULL,
    age int NOT NULL,
    email varchar(225) NOT NULL,
    PRIMARY KEY (id)
);

insert into customer(firstName, lastName, age, email) values('Tara', 'Gillies', 19, 'tara.gillies@gmail.com');
insert into customer(firstName, lastName, age, email) values('Myra', 'Gomes', 19, 'Myra.Gomes@gmail.com');
insert into customer(firstName, lastName, age, email) values('John', 'Dough', 19, 'john@gmail.com');
insert into customer(firstName, lastName, age, email) values('Kiya', 'k', 19, 'kiya@gmail.com');
insert into customer(firstName, lastName, age, email) values('Lily', 'Love', 19, 'lily@gmail.com');
insert into customer(firstName, lastName, age, email) values('Yug', 'Ray', 19, 'yug@gmail.com');

commit;