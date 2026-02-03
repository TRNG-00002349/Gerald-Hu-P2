INSERT INTO users (id, username, email, hashed_password, deleted, created_at)
VALUES (1, 'test-user', 'test@example.com', 'not actually hashed', FALSE, CURRENT_TIMESTAMP);

INSERT INTO users (id, username, email, hashed_password, deleted, created_at)
VALUES (2, 'test-user-deleted', 'deleted@example.com', 'not hashed', TRUE, CURRENT_TIMESTAMP);

INSERT INTO posts (id, content, author_id, created_at)
VALUES (1, 'test post please ignore', 1, CURRENT_TIMESTAMP);

INSERT INTO posts (id, content, author_id, created_at)
VALUES (2, 'test post by deleted author', 2, CURRENT_TIMESTAMP);

INSERT INTO comments (id, content, author_id, post_id, created_at)
VALUES (1, 'comment by active user on post by active user', 1, 1, CURRENT_TIMESTAMP);

INSERT INTO comments (id, content, author_id, post_id, created_at)
VALUES (2, 'comment by deleted user on post', 2, 1, CURRENT_TIMESTAMP);

INSERT INTO comments (id, content, author_id, post_id, created_at)
VALUES (3, 'comment by active user on post by deleted user', 1, 2, CURRENT_TIMESTAMP);

INSERT INTO comments (id, content, author_id, post_id, created_at)
VALUES (4, 'comment by deleted user on post by deleted user', 2, 2, CURRENT_TIMESTAMP);

INSERT INTO comments (id, content, author_id, post_id, created_at)
VALUES (5, 'same user can leave multiple comments', 1, 1, CURRENT_TIMESTAMP);

INSERT INTO likes (id, user_id, post_id)
VALUES (1,1,1);

INSERT INTO likes (id, user_id, post_id)
VALUES (2,2,1);

COMMIT;