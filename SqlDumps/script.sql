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

CREATE INDEX creator
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
  upass VARCHAR(64)  NULL
)
  ENGINE = InnoDB;

ALTER TABLE unseen
  ADD CONSTRAINT unseen_ibfk_2
FOREIGN KEY (uid) REFERENCES users (uid);


