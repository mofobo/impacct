CREATE TABLE IF NOT EXISTS categories
(
    category_id INTEGER      NOT NULL AUTO_INCREMENT,
    name        VARCHAR(128) NOT NULL,
    description VARCHAR(256) NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS subcategories
(
    subcategory_id INTEGER      NOT NULL AUTO_INCREMENT,
    category_id    INTEGER      NOT NULL,
    name           VARCHAR(128) NOT NULL,
    description    VARCHAR(256) NOT NULL,
    PRIMARY KEY (subcategory_id),
    FOREIGN KEY (category_id) REFERENCES categories (category_id)
);

CREATE TABLE IF NOT EXISTS currencies
(
    currency_id     INTEGER    NOT NULL AUTO_INCREMENT,
    numeric_code    INTEGER    NOT NULL,
    alphabetic_code VARCHAR(3) NOT NULL,
    decimal_digits  INTEGER(2) NOT NULL,
    PRIMARY KEY (currency_id)
);

CREATE TABLE IF NOT EXISTS users
(
    user_id VARCHAR(128) NOT NULL,
    name    VARCHAR(256) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS transactions
(
    id             INTEGER                                                                                                                 NOT NULL AUTO_INCREMENT,
    owner_id       VARCHAR(128)                                                                                                            NOT NULL,
    subcategory_id INTEGER                                                                                                                 NOT NULL,
    title          VARCHAR(128)                                                                                                            NOT NULL,
    description    VARCHAR(256)                                                                                                            NOT NULL,
    currency_id    INTEGER                                                                                                                 NOT NULL,
    amount         INTEGER                                                                                                                 NOT NULL,
    date           VARCHAR(20)                                                                                                             NOT NULL,
    fiscal_period  ENUM ('JANUARY','FEBRUARY','MARCH', 'APRIL','MAY','JUNE','JULY', 'AUGUST','SEPTEMBER','OCTOBER','NOVEMBER', 'DECEMBER') NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (subcategory_id) REFERENCES subcategories (subcategory_id),
    FOREIGN KEY (currency_id) REFERENCES currencies (currency_id),
    FOREIGN KEY (owner_id) REFERENCES users (user_id)
);