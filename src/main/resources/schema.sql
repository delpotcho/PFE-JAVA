CREATE TABLE user (
  id                INTEGER PRIMARY KEY,
  login             VARCHAR(64) NOT NULL,
  password          VARCHAR(64) NOT NULL,
  enabled           INTEGER DEFAULT 1 NOT NULL,
  loginAttempts     INTEGER DEFAULT 0 NOT NULL,
  group_id          INTEGER not null
  );

CREATE TABLE user_group (
  id                INTEGER PRIMARY KEY,
  name              VARCHAR(64) NOT NULL
  );

INSERT INTO user_group VALUES (1,'admin');
INSERT INTO user_group VALUES (2,'member');

INSERT INTO user VALUES (1,'admin','adminPWD',1,0,1);
INSERT INTO user VALUES (2,'test','testPWD',1,0,2);
INSERT INTO user VALUES (3,'test2','test2PWD',0,5,2);
