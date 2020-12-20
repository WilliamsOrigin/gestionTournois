

-- Supprime le schéma projet

DROP SCHEMA IF EXISTS gtournoi CASCADE;


-- Creer l'utilisateur projet
-- (apr�s l'avoir supprimer au pr�alable s'il existait d�j�)

DO $code$
BEGIN
	IF EXISTS (SELECT  FROM pg_catalog.pg_roles WHERE rolname  = 'gtournoi')
	THEN
		REVOKE CREATE ON DATABASE postgres FROM gtournoi;
		DROP USER gtournoi;
	END IF;
END
$code$;

CREATE USER projet WITH PASSWORD 'gtournoi';
GRANT CREATE ON DATABASE postgres TO gtournoi;

