
INSERT INTO people ( person_id, first_name, last_name, person_type )
VALUES('1', 'Ivan' ,'Ristic', 'author');

INSERT INTO people ( person_id, first_name, last_name, person_type )
VALUES('2', 'Tom' ,'Long', 'author');

INSERT INTO people (  person_id, first_name, last_name, person_type )
VALUES(3, 'Neil' ,'Madden', 'author');

INSERT INTO people (  person_id, first_name, last_name, person_type )
VALUES(4, 'Jelena' ,'Giric-Ristic', 'editor');

INSERT INTO people (  person_id, first_name, last_name, person_type )
VALUES(5, 'Emily' ,'Stark', 'reviewer');

INSERT INTO people (  person_id, first_name, last_name, person_type )
VALUES(6, 'Matt' ,'Caswell', 'reviewer');

INSERT INTO people (  person_id, first_name, last_name , person_type)
VALUES(7, 'Toni' ,'Arritola', 'editor');

INSERT INTO people (  person_id, first_name, last_name, person_type )
VALUES(8, 'Aleks' ,'Dragosavljevic', 'reviewer');

INSERT INTO people (  person_id, first_name, last_name, person_type )
VALUES(9, 'Ivan' ,'Martinovic', 'reviewer');

INSERT INTO books (isbn,  title,  rating, genre, publisher, published_on )
VALUES('9781907117091', 'Bulletproof TLS and PKI', 5, 'Computer Science', 'Feisty Duck', '2022-01-10');

INSERT INTO books (isbn,  title, rating, genre, publisher, published_on)
VALUES('9781617298936', 'Good Code Bad Code', 5, 'Computer Science', 'Manning', '2021-10-05');

INSERT INTO books (isbn,  title, rating, genre, publisher, published_on )
VALUES('9781617296024', 'API Security In Action', 5, 'Computer Science', 'Manning', '2020-12-08');

INSERT INTO people_books (person_book_id, person_id, book_id)
VALUES(1, 1, '9781907117091');

INSERT INTO people_books (person_book_id, person_id, book_id)
VALUES(2, 4, '9781907117091');

INSERT INTO people_books (person_book_id, person_id, book_id)
VALUES(3, 5, '9781907117091');

INSERT INTO people_books (person_book_id, person_id, book_id)
VALUES(4, 6, '9781907117091');

INSERT INTO people_books (person_book_id, person_id, book_id)
VALUES(5, 2, '9781617298936');

INSERT INTO people_books (person_book_id, person_id, book_id)
VALUES(6, 7, '9781617298936');

INSERT INTO people_books (person_book_id, person_id, book_id)
VALUES(7, 8, '9781617298936');

INSERT INTO people_books (person_book_id, person_id, book_id)
VALUES(8, 3, '9781617296024');

INSERT INTO people_books (person_book_id, person_id, book_id)
VALUES(9, 7, '9781617296024');

INSERT INTO people_books (person_book_id, person_id, book_id)
VALUES(10, 9, '9781617296024');

-- Flyway migration scripts make the table sequences for which INSERT statements were generated to go out of sync
-- hence we need to update the table sequences to the next value with the following scripts
-- The names of the sequences of the tables adhere the following syntax:
-- "<table-name>_<column_name>_seq"
SELECT SETVAL('public."people_person_id_seq"', COALESCE(MAX(person_id), 1)) FROM public."people";
SELECT SETVAL('public."people_books_person_book_id_seq"', COALESCE(MAX(person_book_id), 1)) FROM public."people_books";
SELECT SETVAL('public."reviews_review_id_seq"', COALESCE(MAX(review_id), 1)) FROM public."reviews";
