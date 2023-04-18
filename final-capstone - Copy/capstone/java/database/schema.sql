BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE posts (
	post_id SERIAL,
	user_id INTEGER NOT NULL,
	post varchar(10000) NOT NULL,
	CONSTRAINT PK_post PRIMARY KEY (post_id),
	CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE post_comments (
	comment_id SERIAL,
	post_id INTEGER NOT NULL,
	user_id INTEGER NOT NULL,
	response varchar(10000) NOT NULL,
	CONSTRAINT PK_comment PRIMARY KEY (comment_id),
	CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_post FOREIGN KEY (post_id) REFERENCES posts(post_id)
);

CREATE TABLE profile(
   profile_id SERIAL,
   user_id INTEGER NOT NULL,
   display_name varchar(100) NOT NULL,
   profile_picture varchar(10000),
   CONSTRAINT PK_profile PRIMARY KEY (profile_id),
   CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

COMMIT TRANSACTION;
