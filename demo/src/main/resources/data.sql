INSERT INTO "genre" ("id", "value") VALUES (28, 'Action'),
(10759, 'Action & Adventure'),
(12, 'Adventure'),
(16, 'Animation'),
(35, 'Comedy'),
(80, 'Crime'),
(99, 'Documentary'),
(18, 'Drama'),
(10751, 'Family'),
(14, 'Fantasy'),
(36, 'History'),
(27, 'Horror'),
(10762, 'Kids'),
(10402, 'Music'),
(9648, 'Mystery'),
(10763, 'News'),
(10764, 'Reality'),
(10749, 'Romance'),
(878, 'Science Fiction'),
(10765, 'Sci-Fi & Fantasy'),
(10766, 'Soap'),
(10767, 'Talk'),
(10770, 'TV Movie'),
(53, 'Thriller'),
(10752, 'War'),
(10768, 'War & Politics'),
(37, 'Western');
INSERT INTO "employee" ("birth_date", "email", "enabled", "first_name", "last_name", "password", "phone_number", "token_expired")
VALUES
(DATE '1998-12-31', 'user1@email.com', TRUE, 'name1', 'lastname1', '$2a$10$xDg2dGehCanRS0WRyjg8Qu3uibPg/3paCavQi//xkSqMCPL4ihlue', 601123123, FALSE),
(DATE '1968-04-22', 'user2@email.com', TRUE, 'name2', 'lastname2', '$2a$10$xDg2dGehCanRS0WRyjg8Qu3uibPg/3paCavQi//xkSqMCPL4ihlue', 602123123, FALSE);
INSERT INTO "movie" ("date_proposed", "director", "length", "rating", "release_date", "title", "implementer_id", "proposer_id")
VALUES
(DATE '2023-08-26', 'Christopher Nolan', 150, 8.9, '2020-08-12', 'Tenet', 1, 2),
(DATE '2023-08-25', 'Steven Spielberg', 127, 8.7, '1993-09-30', 'Jurassic Park', 1, 1),
(DATE '2023-07-21', 'Greta Gerwig', 114, 9.333, '2023-07-21', 'Barbie', 1, 1);
INSERT INTO "movie_genres" ("movie_id", "genres_id")
VALUES
(1,28),(1,53),(1, 878),
(2,99),
(3,35);
INSERT INTO "series" ("creator", "date_proposed", "number_of_episodes", "rating", "release_date", "seasons", "status", "title", "implementer_id", "proposer_id")
VALUES
('Carter Bays', DATE '2023-08-26', 288, 8.9, '2005-09-19', 9, 'Ended', 'How I Met Your Mother', 1, 2),
('David Benioff', DATE '2023-07-14', 76, 9.7, '2011-04-17', 8, 'Ended', 'Game of Thrones', 1, 2),
('Chris Brancato', DATE '2023-06-13', 110, 8.21, '2015-08-28', 4, 'Ended', 'Narcos', 1, 1);
INSERT INTO "series_genres" ("series_id", "genres_id")
VALUES
(1,35),
(2,18), (2,10759), (2,10765),
(3,18), (3,80);
INSERT INTO "privilege" ("name") VALUES ('IMPLEMENTER_PRIVILEGE');
INSERT INTO "role" ("name") VALUES ('ROLE_IMPLEMENTER'),('ROLE_EMPLOYEE');
INSERT INTO "roles_privileges" ("privilege_id", "role_id") VALUES (1,1);
INSERT INTO "employees_roles" ("role_id", "employee_id") VALUES (1,1),(2,2);