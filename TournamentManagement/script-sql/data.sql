SET search_path TO gtournoi;


-- Supprimer toutes les données
DELETE FROM utilisateur;
DELETE FROM joueur;
DELETE FROM arbitre;
DELETE FROM journaliste;



INSERT INTO utilisateur (idutilisateur, pseudo, motdepasse, role) VALUES
( 1, 'bertrand', 'admin', 0),
( 2, 'julio', 'user', 1);

ALTER TABLE utilisateur ALTER COLUMN idutilisateur RESTART WITH 3;

INSERT INTO  joueur(id_joueur, nom, nationalite, classemmentmondial, sexe, image) VALUES
( 1, ' RaphaelN ' , ' Espagne ' , ' 2 ', ' Homme ', 'RaphaelN.jpg'), 
( 2,  ' NovakD ' , ' Croatie ' , ' 1 ', ' Homme ', 'NovakD.jpg'), 
( 3,' SerenaW ' , ' USA ' , ' 10 ', ' Femme', 'SerenaW.jpg'), 
( 4,' AshleighB ' , ' Australie ' , ' 1 ', ' Femme', 'AshleighB.jpg'), 
( 5,' DominicT ' , ' Autriche ' , ' 3 ', ' Homme ', 'DominicT.jpg'), 
( 6,' DanilM ' , ' Russie ' , ' 4 ', ' Homme ', 'DanilM.jpg'), 
( 7,' RogerF ' , ' Suisse ' , ' 5 ', ' Homme ', 'RogerF.jpg'), 
( 8,' StefanosT ' , ' Grece ' , ' 6 ', ' Homme ', 'StefanosT.jpg'), 
( 9,' AlexanderZ ' , ' Allemangne ' , ' 7 ', ' Homme ', 'AlexanderZ.jpg'),
( 10,' MatteoB ' , ' Italie ' , ' 10 ', ' Homme ', 'MatteoB.jpg'),
( 11,' NaomiO ' , ' Japon ' , ' 3 ', ' Femme ', 'NaomiO.jpg'),
( 12,' SofiaK ' , ' USA ' , ' 4 ', ' Femme ', 'SofiaK.jpg'),
( 13,' BiancaA ' , ' Suisse ' , ' 7 ', ' Femme ', 'BiancaA.jpg'),
( 14,' JohannaK ' , ' Angleterre ' , ' 14 ', ' Femme ', 'JohannaK.jpg'),
( 15,' EliseM ' , ' Allemagnee ' , ' 21 ', ' Femme ', 'EliseM.jpg'),
( 16,' VeronikaK ' , ' Russie ' , ' 46 ', ' Femme ', 'VeronikaK.jpg');

ALTER TABLE joueur ALTER COLUMN id_joueur RESTART WITH 17;

INSERT INTO arbitre(id_arbitre, nom, nationalite) VALUES 
( 1, ' MarijanaV ' , ' Serbie '),
( 2,' JulieK ' , ' Norvege '),
( 3,' AurelieT ' , ' France '), 
( 4,' PaulaVieiraS ' , ' Bresil '), 
( 5,' AliNili ' , ' Iran '),
( 6,' CarlosR ' , ' Portugal '),
( 7,' DamianS ' , ' Argentine '),
( 8,' CarlosB ' , ' Bresil '),
( 9,' JohnB ' , ' Australie '),
( 10,' FergusM ' , ' Irlande '),
( 11,' KaderNouni ' , ' France ');

ALTER TABLE arbitre ALTER COLUMN id_arbitre RESTART WITH 12;

INSERT INTO journaliste( id_journaliste, nom) VALUES 
( 1, ' Lionel Chamoulaud '), 
( 2, ' Nelson Monfort '),
( 3, ' Frederic Viard '),
( 4, ' Patrice Dominguez '),
( 5, ' Benoit Maylin '),
( 6, ' Philippe Chartier ');

ALTER TABLE journaliste ALTER COLUMN id_journaliste RESTART WITH 7;