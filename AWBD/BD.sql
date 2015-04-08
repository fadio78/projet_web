
CREATE TABLE utilisateurs (
       nom varchar(100) NOT NULL,
       prenom varchar(100) NOT NULL,
       email varchar(100) NOT NULL,
       login varchar(100) PRIMARY KEY,
       mdp varchar(100) NOT NULL
);

INSERT INTO utilisateurs VALUES('cla','nat','test@hotmail.com','floyd','1234');