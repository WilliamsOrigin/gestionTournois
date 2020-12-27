SET search_path TO gtournoi;


-- Supprimer toutes les données
DELETE FROM utilisateur;
DELETE FROM joueur;
DELETE FROM equipe;
DELETE FROM arbitre;
DELETE FROM t_match;
DELETE FROM appartenir;



INSERT INTO utilisateur (idutilisateur, pseudo, motdepasse, role) VALUES
( 1, 'bertrand', 'admin', 0),
( 2, 'julio', 'user', 1);

ALTER TABLE utilisateur ALTER COLUMN idutilisateur RESTART WITH 3;

INSERT INTO  joueur(id_joueur, nom, nationalite, classementmondial, sexe, image, isSelected, description) VALUES
( 1, 'RaphaelN' , 'Espagne' , 2 , 'Masculin', 'RaphaelN.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'), 
( 2,  'NovakD' , 'Croatie' , 1 , 'Masculin', 'NovakD.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'), 
( 3,'SerenaW' , 'USA' , 10, 'Feminin', 'SerenaW.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'), 
( 4,' AshleighB' , 'Australie' , 1 , 'Feminin', 'AshleighB.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'), 
( 5,'DominicT' , 'Autriche' ,  3, 'Masculin', 'DominicT.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'), 
( 6,'DanilM' , 'Russie' ,  4, 'Masculin', 'DanilM.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'), 
( 7,'RogerF' , 'Suisse' , 5 , 'Masculin', 'RogerF.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'), 
( 8,'StefanosT' , 'Grece' , 6, 'Masculin', 'StefanosT.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'), 
( 9,'AlexanderZ' , 'Allemagne' , 7, 'Masculin', 'AlexanderZ.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 10,'MatteoB' , 'Italie' , 10, 'Masculin', 'MatteoB.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 11,'NaomiO' , 'Japon' , 3 , 'Feminin', 'NaomiO.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 12,'SofiaK' , 'USA' , 4 , 'Feminin', 'SofiaK.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 13,'BiancaA' , 'Suisse' , 7 , 'Feminin', 'BiancaA.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 14,'JohannaK' , 'Angleterre' , 14, 'Feminin', 'JohannaK.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 15,'EliseM' , 'Allemagne' , 21 , 'Feminin', 'EliseM.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 16,'VeronikaK' , 'Russie' , 46 , 'Feminin', 'VeronikaK.jpg', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.');

ALTER TABLE joueur ALTER COLUMN id_joueur RESTART WITH 17;

INSERT INTO equipe(id_equipe, id_joueur, id_equipier) VALUES
(1, 1, 2),
(2, 9, 10),
(3, 15, 16),
(4, 11, 13),
(5, 7, 4),
(6, 2, 3);

ALTER TABLE equipe ALTER COLUMN id_equipe RESTART WITH 7;

INSERT INTO arbitre(id_arbitre, nom, nationalite, isSelected, description) VALUES 
( 1, 'MarijanaV' , 'Serbie', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 2,'JulieK' , 'Norvege', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 3,'AurelieT' , 'France', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'), 
( 4,'PaulaVieiraS' , 'Bresil', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'), 
( 5,'AliNili' , 'Iran', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 6,'CarlosR' , 'Portugal', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 7,'DamianS' , 'Argentine', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 8,'CarlosB' , 'Bresil', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 9,'JohnB' , 'Australie', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 10,'FergusM' , 'Irlande', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.'),
( 11,'KaderNouni' , 'France', 0, 'Lorem ipsum dolor sit ameted consectetur adipisicing elit Lorem Ipsum dolor sit amet.');

ALTER TABLE arbitre ALTER COLUMN id_arbitre RESTART WITH 12;

INSERT INTO t_match(id_match, jour, heureDb, heureFin, court, score, statut, sets, categorie) VALUES
(1, {d'2020-12-23'}, {t '12:20'}, {t '14:20'}, 1, '2 - 1', 1, 3, 1),
(2, {d'2020-12-23'}, {t '10:00'}, {t '12:00'}, 4, '2 - 3', 1, 3, 2),
(3, {d'2020-12-23'}, {t '14:30'}, {t '16:20'}, 5, '3 - 1', 1, 5, 3),
(4, {d'2020-12-23'}, {t '16:00'}, {t '18:00'}, 7, '5 - 4', 1, 5, 4),
(5, {d'2020-12-23'}, {t '12:20'}, {t '14:20'}, 9, '5 - 4', 1, 5, 5);

ALTER TABLE t_match ALTER COLUMN id_match RESTART WITH 6;

INSERT INTO appartenir(id_app, id_match, id_joueur, id_equipe, categorie) VALUES
(1, 1, 1, null, 1),
(2, 1, 2, null, 1),
(3, 2, 11, null, 2),
(4, 2, 13, null, 2),
(5, 3, null, 1, 3),
(6, 3, null, 2, 3),
(7, 4, null, 3, 4),
(8, 4, null, 4, 4),
(9, 5, null, 5, 5),
(10, 5, null,6, 5);

ALTER TABLE appartenir ALTER COLUMN id_app RESTART WITH 11;