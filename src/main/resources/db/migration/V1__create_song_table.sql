CREATE TYPE like_enum AS ENUM ('LIKE', 'NOTHING', 'DISLIKE');

CREATE TABLE SONG (
    id serial primary key,
    title text,
    n int,
    duration int,
    "like" like_enum default 'NOTHING',
    artist_name text,
    album_name text,
    path varchar(4096),
    acoustid text,
    year int,
    format varchar(10),
    mbid varchar(32),
    last_updated timestamp,
    joinings text
);