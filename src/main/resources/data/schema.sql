create table band(
    id  integer primary key,
    name varchar(255)
)

create table song(
    id  integer primary key,
    name varchar(255),
    release_year integer,
    artist varchar(255),
    shortname varchar(255),
    bpm integer,
    duration integer,
    genre varchar(255),
    spotifyid varchar(255),
    album varchar(255)
)


