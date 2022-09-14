create table artist
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR,
    genre VARCHAR
);

insert into artist(name, genre) values ('Lady Gaga', 'Pop');
insert into artist(name, genre) values ('Rihanna', 'Pop');
insert into artist(name, genre) values ('Britney Spears', 'Pop');
insert into artist(name, genre) values ('Lana Del Rey', 'Pop');