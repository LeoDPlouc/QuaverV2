DROP TABLE IF EXISTS song_to_joining_link;
DROP TABLE IF EXISTS song_to_artist_link;
DROP TABLE IF EXISTS song;
DROP TYPE IF EXISTS like_enum;
DROP TABLE IF EXISTS album_to_joining_link;
DROP TABLE IF EXISTS album_to_artist_link;
DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS joining;
DROP TABLE IF EXISTS artist;
DROP TABLE IF EXISTS cover;

CREATE TABLE IF NOT EXISTS cover (
                       id serial primary key,
                       tiny_url varchar(4096),
                       small_url varchar(4096),
                       medium_url varchar(4096),
                       large_url varchar(4096),
                       very_large_url varchar(4096)
);

CREATE TABLE IF NOT EXISTS artist (
                        id serial primary key,
                        name text,
                        cover_id int references cover(id),
                        mbid varchar(32),
                        created_at timestamp,
                        updated_at timestamp
);

CREATE TABLE IF NOT EXISTS joining (
                         id serial primary key,
                         n int,
                         artist_id int references artist(id),
                         joinphrase varchar(20)
);

CREATE TABLE IF NOT EXISTS album (
                       id serial primary key,
                       title text,
                       cover_id int references cover(id),
                       year int,
                       mbid varchar(32),
                       crated_at timestamp,
                       updated_at timestamp,
                       cover_updated_at timestamp
);

CREATE TABLE IF NOT EXISTS album_to_artist_link (
                                      album_id int references album(id),
                                      artist_id int references artist(id),
                                      primary key (album_id, artist_id)
);

CREATE TABLE IF NOT EXISTS album_to_joining_link (
                                       album_id int references album(id),
                                       joining_id int references joining(id),
                                       primary key (album_id, joining_id)
);

CREATE TYPE like_enum AS ENUM ('LIKE', 'NOTHING', 'DISLIKE');

CREATE TABLE IF NOT EXISTS song (
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

CREATE TABLE IF NOT EXISTS song_to_artist_link (
                                     song_id int references song(id),
                                     artist_id int references artist(id),
                                     primary key (song_id, artist_id)
);

CREATE TABLE IF NOT EXISTS song_to_joining_link (
                                      song_id int references song(id),
                                      joining_id int references joining(id),
                                      primary key (song_id, joining_id)
);