
CREATE TABLE IF NOT EXISTS people  (
                                     person_id        SERIAL PRIMARY KEY,
                                     first_name       VARCHAR(255) NOT NULL,
                                     last_name        VARCHAR(30)  NOT NULL,
                                     person_type      VARCHAR(10)  NOT NULL
);

CREATE TABLE IF NOT EXISTS books (
                                     isbn            VARCHAR(13)  NOT NULL,
                                     title           VARCHAR(255) NOT NULL,
                                     rating          SMALLINT NOT NULL,
                                     genre	         VARCHAR(255),
                                     publisher       VARCHAR(255) NOT NULL,
                                     published_on    DATE,
                                     PRIMARY KEY (isbn)
);

-- Many to Many relation between:
-- 1. Authors and Books
-- 2. Editors and Books
-- 3. Reviewers and Books
CREATE TABLE IF NOT EXISTS people_books (
                                     person_book_id  SERIAL PRIMARY KEY,
                                     person_id       INTEGER   NOT NULL,
                                     book_id         VARCHAR(13) NOT NULL,
                                     CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES people(person_id),
                                     CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books(isbn)
);

-- One to Many relation between books and reviews
CREATE TABLE IF NOT EXISTS  reviews (
                                     review_id        SERIAL PRIMARY KEY,
                                     isbn             VARCHAR(13) NOT NULL,
                                     comment          TEXT,
                                     rating           SMALLINT    NOT NULL,
                                     CONSTRAINT fk_review_book FOREIGN KEY (isbn) REFERENCES books(isbn)
);