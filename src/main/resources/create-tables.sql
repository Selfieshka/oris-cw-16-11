CREATE TABLE users
(
    user_id  SERIAL NOT NULL,
    name     VARCHAR(20),
    login    VARCHAR(20) UNIQUE,
    password VARCHAR(256)
);

CREATE TABLE attempt
(
    attempt_id SERIAL NOT NULL,
    login      VARCHAR(20),
    status     BOOLEAN,
    time       timestamp
);

CREATE TABLE weather
(
    weather_id SERIAL      NOT NULL,
    user_login VARCHAR(20) NOT NULL,
    city       VARCHAR(20) NOT NULL
)