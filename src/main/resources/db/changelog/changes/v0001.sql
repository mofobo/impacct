CREATE TABLE IF NOT EXISTS categories
(
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        TEXT NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions
(
    id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    owner_email TEXT    NOT NULL,
    type        TEXT    NOT NULL,
    category_id INTEGER NOT NULL,
    title       TEXT    NOT NULL,
    description TEXT,
    amount      INTEGER NOT NULL,
    date        DATE    NOT NULL,

    FOREIGN KEY (category_id) REFERENCES categories (id)
);