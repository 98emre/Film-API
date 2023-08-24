-- Movies
INSERT INTO movie (release_year, director, title, genre, picture_url, trailer_link, franchise_id) 
VALUES (1989, 'Tim Burton', 'Batman', 'Action,Superhero,Criminal,Adventure,Drama,Mystery', 'https://upload.wikimedia.org/wikipedia/en/thumb/5/5a/Batman_%281989%29_theatrical_poster.jpg/220px-Batman_%281989%29_theatrical_poster.jpg', 'https://www.youtube.com/watch?v=BHUVctLdw38', NULL); -- 1
INSERT INTO movie (release_year, director, title, genre, picture_url, trailer_link, franchise_id) 
VALUES (1992, 'Tim Burton', 'Batman Returns', 'Action,Superhero,Criminal,Adventure,Thriller', 'https://upload.wikimedia.org/wikipedia/en/thumb/8/83/Batman_returns_poster2.jpg/220px-Batman_returns_poster2.jpg', NULL, NULL); -- 2
INSERT INTO movie (release_year, director, title, genre, picture_url, trailer_link, franchise_id) 
VALUES (2002, 'Chris Columbus', 'Harry Potter and the Chamber of Secrets', 'Adventure', 'https://img-cdn.sfanytime.com/COVERM/COVERM_c1f5236c-cf47-460e-83dd-f4edecda90c6_sv.jpg?w=375&fm=pjpg&s=361d124f20b5c7a188c3191389367a4b', 'https://www.youtube.com/watch?v=Ngz1RG51RT0', NULL); -- 3

-- Characters
INSERT INTO character (alias, character_name, picture_url, gender) 
VALUES ('Batman', 'Bruce Wayne','https://upload.wikimedia.org/wikipedia/en/8/8a/Bruce_Wayne_%28Michael_Keaton%29.jpg', 'MALE');
INSERT INTO character (alias, character_name, picture_url, gender) 
VALUES ('Joker', 'Jack Oswald White', 'https://www.google.com/search?safe=active&sca_esv=557804163&rlz=1C1GCEU_enSE1065SE1065&sxsrf=AB5stBi7zjoz64LUopLtQnVN-tPjE0YZAw:1692282778568&q=batman+1989&tbm=isch&source=lnms&sa=X&ved=2ahUKEwiyzoDP9OOAAxV0RvEDHQrcBvUQ0pQJegQIDRAB&biw=1718&bih=1313&dpr=1#imgrc=Linko4K3jrTHiM', 'MALE');
INSERT INTO character (alias, character_name, picture_url, gender) 
VALUES ('Harry Potter', 'Harry Potter', 'https://i.pinimg.com/originals/cc/e4/08/cce408ade108375ff32c4cae57e386bc.jpg', 'MALE');
INSERT INTO character (alias, character_name, picture_url, gender) 
VALUES ('Hermione', 'Hermione Granger', 'https://i.insider.com/60772a1742061500181757bc?width=1000&format=jpeg&auto=webp', 'FEMALE');

-- Franchises
INSERT INTO franchise (name, description) 
VALUES ('Batman', 'The Batman franchise, based on the fictional superhero Batman who appears in American comic books published by DC Comics, has seen the release of various films. Created by Bob Kane and Bill Finger, the character first starred in two serial films in the 1940s: Batman and Batman and Robin.'); 
INSERT INTO franchise (name, description) 
VALUES ('Harry Potter', 'Harry Potter is a series of seven fantasy novels written by British author J. K. Rowling. The novels chronicle the lives of a young wizard, Harry Potter, and his friends Hermione Granger and Ron Weasley, all of whom are students at Hogwarts School of Witchcraft and Wizardry.'); 

-- Movie-character Relationship
INSERT INTO movie_character (character_id, movie_id) VALUES (1, 1);
INSERT INTO movie_character (character_id, movie_id) VALUES (1, 2);
INSERT INTO movie_character (character_id, movie_id) VALUES (2, 1);
INSERT INTO movie_character (character_id, movie_id) VALUES (2, 2);
INSERT INTO movie_character (character_id, movie_id) VALUES (3, 3);
INSERT INTO movie_character (character_id, movie_id) VALUES (4, 3);

-- Movie-franchise Relationship
UPDATE movie SET franchise_id = 1 WHERE movie_id = 1;
UPDATE movie SET franchise_id = 1 WHERE movie_id = 2;
UPDATE movie SET franchise_id = 2 WHERE movie_id = 3;


