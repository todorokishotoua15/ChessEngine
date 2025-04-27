create table engine (
    id int not null,
    depth int,
    algo int,
    primary key(id)
)

create table game (
    id int not null,
    fen_string varchar(255),
    player1 varchar(255),
    player2 varchar(255),
    engine_id int,
    primary key (id),
    foreign key (player1) references user(email),
    foreign key (engine_id) references engine(id)
)

create table history (
    history_id int,
    user_email varchar(255),
    game_id int,
    primary key(history_id),
    foreign key (user_email) references user(email),
    foreign key (game_id) references game(id)
)
 