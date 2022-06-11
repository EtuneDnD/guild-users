CREATE TABLE "users"
(
    user_id UUID PRIMARY KEY,
    username VARCHAR,
    email VARCHAR,
    profile VARCHAR
);


CREATE TABLE "dungeon_masters"
(
    rank VARCHAR --Change rank to proper enum
) INHERITS("users");

CREATE TABLE "players"
(
    strikes NUMERIC
) INHERITS("users");
