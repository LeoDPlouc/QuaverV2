CREATE TABLE cover (
    id serial primary key,
    tiny_url varchar(4096),
    small_url varchar(4096),
    medium_url varchar(4096),
    large_url varchar(4096),
    very_large_url varchar(4096)
);

CREATE TABLE artist (
    id serial primary key,
    name text,
    cover_id int references cover(id),
    mbid varchar(32),
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE joining (
    id serial primary key,
    n int,
    artist_id int references artist(id),
    joinphrase varchar(20)
);

CREATE TABLE album (
    id serial primary key,
    title text,
    cover_id int references cover(id),
    year int,
    mbid varchar(32),
    crated_at timestamp,
    updated_at timestamp,
    cover_updated_at timestamp
);

CREATE TABLE album_to_artist_link (
    album_id int references album(id),
    artist_id int references artist(id),
    primary key (album_id, artist_id)
);

CREATE TABLE album_to_joining_link (
    album_id int references album(id),
    joining_id int references joining(id),
    primary key (album_id, joining_id)
);

CREATE TYPE like_enum AS ENUM ('LIKE', 'NOTHING', 'DISLIKE');

CREATE TABLE song (
    id serial primary key,
    title text,
    n int,
    duration int,
    "like" like_enum,
    album_id int references album(id),
    path varchar(4096),
    acoustid text,
    year int,
    format varchar(10),
    mbid varchar(32),
    created_at timestamp,
    last_updated timestamp
);

CREATE TABLE song_to_artist_link (
    song_id int references song(id),
    artist_id int references artist(id),
    primary key (song_id, artist_id)
);

CREATE TABLE song_to_joining_link (
    song_id int references song(id),
    joining_id int references joining(id),
    primary key (song_id, joining_id)
);