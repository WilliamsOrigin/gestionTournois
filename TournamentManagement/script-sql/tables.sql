SET search_path TO gtournoi;


-- Schema

DROP SCHEMA IF EXISTS gtournoi CASCADE;
CREATE SCHEMA gtournoi AUTHORIZATION gtournoi;
GRANT ALL PRIVILEGES ON SCHEMA gtournoi TO gtournoi;


-- Tables

------------------------------------------------------------
-- Table: utilisateur
------------------------------------------------------------

CREATE TABLE utilisateur (
	idutilisateur		INT				GENERATED BY DEFAULT AS IDENTITY,
	pseudo			VARCHAR(25)		NOT NULL,
	motdepasse		VARCHAR(25) 	NOT NULL,
	role			INT				NOT NULL,
	CHECK (role IN (0, 1)),
	PRIMARY KEY (idutilisateur),
	UNIQUE (pseudo)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table: joueur
------------------------------------------------------------

CREATE TABLE Joueur (
        id_joueur    INT  GENERATED BY DEFAULT AS IDENTITY NOT NULL,
        nom          VARCHAR(50) NOT NULL ,
        nationalite  VARCHAR(50) NOT NULL ,
        description  VARCHAR(150) NOT NULL ,
        classementMondial INT NOT NULL ,
        sexe         VARCHAR(10) NOT NULL,
        image		 VARCHAR(20) NOT NULL,
        isSelected	 INT NOT NULL,
        CHECK (isSelected IN (0, 1)),
        CONSTRAINT Joueur_PK PRIMARY KEY (id_joueur)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table: t_match
------------------------------------------------------------

CREATE TABLE t_match(
        id_match    INT  GENERATED BY DEFAULT AS IDENTITY  NOT NULL ,
        jour	   DATE NOT NULL,
        heureDb    TIME NOT NULL,
        heureFin   TIME NOT NULL,
        court INT NOT NULL ,
        score   VARCHAR(10) NOT NULL,
        statut  INT NOT NULL,
        sets INT NOT NULL, 
        categorie INT NOT NULL,
        CHECK (statut IN (0, 1) AND categorie IN (1,2,3,4,5)),
	CONSTRAINT Match_PK PRIMARY KEY (id_match)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table: Arbitre
------------------------------------------------------------

CREATE TABLE Arbitre(
			id_arbitre INT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
			nom VARCHAR(20) NOT NULL, 
			nationalite VARCHAR(20) NOT NULL, 
			description VARCHAR(150) NOT NULL,
			isSelected	 INT NOT NULL,
        	CHECK (isSelected IN (0, 1)),
			CONSTRAINT Arbitre_PK PRIMARY KEY (id_arbitre)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table: Equipe
------------------------------------------------------------

CREATE TABLE Equipe(
			id_equipe INT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
			id_joueur INT NOT NULL,
			id_equipier INT NOT NULL,
			CONSTRAINT Equipe_PK PRIMARY KEY (id_equipe)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: Appartenir
------------------------------------------------------------


CREATE TABLE Appartenir (
			id_app INT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
		    id_match INT NOT NULL,			
			id_joueur INT,
			id_equipe INT,
            categorie INT NOT NULL,
			CHECK (categorie IN (1,2,3,4,5))
			, CONSTRAINT Appartenir_PK PRIMARY KEY (id_app)
            , CONSTRAINT Appartenir_Match_FK FOREIGN KEY (id_match) REFERENCES t_match(id_match)
)WITHOUT OIDS;
