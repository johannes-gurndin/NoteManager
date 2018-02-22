CREATE TABLE notes
(
  nid      INT AUTO_INCREMENT
    PRIMARY KEY,
  ntitle   VARCHAR(100)                        NOT NULL,
  ntext    MEDIUMTEXT                          NOT NULL,
  ntopic   VARCHAR(100)                        NOT NULL,
  creator  VARCHAR(100)                        NOT NULL,
  lastedit TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
)
  ENGINE = InnoDB;

CREATE INDEX notes_users_uname_fk
  ON notes (creator);

CREATE TABLE unseen
(
  nid INT NOT NULL,
  uid INT NOT NULL,
  PRIMARY KEY (nid, uid),
  CONSTRAINT unseen_ibfk_1
  FOREIGN KEY (nid) REFERENCES notes (nid)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  ENGINE = InnoDB;

CREATE INDEX uid
  ON unseen (uid);

CREATE TABLE users
(
  uid   INT AUTO_INCREMENT
    PRIMARY KEY,
  uname VARCHAR(100) NOT NULL,
  upass VARCHAR(64)  NULL,
  CONSTRAINT users_uname_uindex
  UNIQUE (uname)
)
  ENGINE = InnoDB;

ALTER TABLE notes
  ADD CONSTRAINT notes_users_uname_fk
FOREIGN KEY (creator) REFERENCES users (uname);

ALTER TABLE unseen
  ADD CONSTRAINT unseen_ibfk_2
FOREIGN KEY (uid) REFERENCES users (uid);


