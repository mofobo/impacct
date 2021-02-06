/*********************************
            STRUCTURE
*********************************/
CREATE TABLE IF NOT EXISTS records
(
    id          INTEGER      NOT NULL AUTO_INCREMENT,
    title       VARCHAR(128) NOT NULL,
    description VARCHAR(256) NOT NULL,
    value       INTEGER      NOT NULL,
    date        VARCHAR(20)  NOT NULL,
    PRIMARY KEY (id)
);