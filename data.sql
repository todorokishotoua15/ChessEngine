CREATE USER 'root'@'%' IDENTIFIED BY 'password';
ALTER USER 'root'@'%' IDENTIFIED BY 'password';
use chess;
GRANT ALL PRIVILEGES ON chess.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;


create table user (
    email varchar(255),
    computer_games int,
    computer_games_won int,
    name varchar(255),
    password varchar(255),
    rating int,
    vs_games int,
    vs_games_won int,
    primary key(email)
);

insert into user values ("example@gmail.com", 0, 0, "example", "$2a$10$hUe38DLSvZoQXXteQYQ1xuFjFBaUKW/WGouYq6nxpL1Hnya03HQSO", 0, 0, 0); 

create table engine (
    id int not null,
    depth int,
    algo int,
    primary key(id)
);

create table game (
    id int not null,
    fen_string varchar(255),
    player1 varchar(255),
    player2 varchar(255),
    engine_id int,
    primary key (id),
    foreign key (player1) references user(email),
    foreign key (engine_id) references engine(id)
);

create table history (
    history_id int,
    user_email varchar(255),
    game_id int,
    primary key(history_id),
    foreign key (user_email) references user(email),
    foreign key (game_id) references game(id)
);
 