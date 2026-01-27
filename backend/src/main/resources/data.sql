TRUNCATE users, posts, comments, likes;
--ALTER SEQUENCE users_id_seq RESTART WITH 1;
--ALTER SEQUENCE posts_id_seq RESTART WITH 1;
--ALTER SEQUENCE comments_id_seq RESTART WITH 1;

INSERT INTO users (id, username, email, hashed_password, deleted, created_at)
VALUES (1, 'test-user', 'test@example.com', 'not actually hashed', FALSE, now());

INSERT INTO users (id, username, email, hashed_password, deleted, created_at)
VALUES (2, 'test-user-deleted', 'deleted@example.com', 'not hashed', TRUE, now());
--
--INSERT INTO posts (content, author_id)
--VALUES ('test post please ignore', 1);
--
--INSERT INTO posts (content, author_id)
--VALUES ('test post by deleted author', 2);
--
--INSERT INTO comments (content, author_id, post_id)
--VALUES ('comment by active user on post by active user', 1, 1);
--
--INSERT INTO comments (content, author_id, post_id)
--VALUES ('comment by deleted user on post', 2, 1);
--
--INSERT INTO comments (content, author_id, post_id)
--VALUES ('comment by active user on post by deleted user', 1, 2);
--
--INSERT INTO comments (content, author_id, post_id)
--VALUES ('comment by deleted user on post by deleted user', 2, 2);
--
--INSERT INTO comments (content, author_id, post_id)
--VALUES ('same user can leave multiple comments', 1, 1);
--
--INSERT INTO likes (user_id, post_id)
--VALUES (1,1);
--
--INSERT INTO likes (user_id, post_id)
--VALUES (2,1);
--
