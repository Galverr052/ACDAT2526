PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;

-- TABLA
CREATE TABLE contactos (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre varchar (32) NOT NULL, apellido varchar (32) NOT NULL, mail varchar (64) NOT NULL, telefono int (11) NOT NULL, direccion varchar (64) NOT NULL, fecha_nacimiento date NOT NULL, categoria varchar (64) DEFAULT NULL);

-- INSERCIÓN DE REGISTROS
INSERT INTO contactos VALUES(1,'Ermelinda','Mancuso','Erme@server.com',1169975195,'La Pampa 4139','2000-07-14','AMIGO');
INSERT INTO contactos VALUES(2,'Benjamin','Rosero','Benj@server.com',1149810077,'La Rioja 3920','2002-05-11','COMP_FACU');
INSERT INTO contactos VALUES(3,'Fabiola','Toruño','Fabi@server.com',1138345233,'Santa Fe 2679','1992-04-30','COMP_TRABAJO');
INSERT INTO contactos VALUES(4,'Margarita','Lavinier','Marg@server.com',1129064518,'Entre Rios 1553','2002-10-21','AMIGO');
INSERT INTO contactos VALUES(5,'Jeronimo','Calero','Jero@server.com',1145071527,'Chubut 2407','2003-09-07','COMP_TRABAJO');
INSERT INTO contactos VALUES(6,'Lisandro','Campbell','Lisa@server.com',1161638027,'Misiones 3092','1996-12-06','FAMILIAR');
INSERT INTO contactos VALUES(7,'Delfina','Cassell','Delf@server.com',1108877343,'Corrientes 4499','1993-07-29','COMP_FACU');
INSERT INTO contactos VALUES(8,'Isabella','Torales','Isab@server.com',1164004740,'Jujuy 3798','1992-11-04',NULL);
INSERT INTO contactos VALUES(9,'Matilda','Gomes','Mati@server.com',1105329251,'Salta 4431','2009-12-15','AMIGO');
INSERT INTO contactos VALUES(10,'Gaspar','Bezerra','Gasp@server.com',1157850637,'Formosa 5000','2000-08-07',NULL);
INSERT INTO contactos VALUES(11,'Zulema','Batista','Zule@server.com',1107409775,'Chaco 3023','2003-12-15','COMP_FACU');
INSERT INTO contactos VALUES(12,'Anna','Ventura','Anna@server.com',1128462349,'Tucuman 1353','1999-07-31','FAMILIAR');
INSERT INTO contactos VALUES(13,'Lucia','Rivadeneira','Luci@server.com',1136176420,'Santa Cruz 2443','1994-08-18','AMIGO');
INSERT INTO contactos VALUES(14,'Ruben','Aparicio','Rube@server.com',1163696055,'Rio Negro 723','1997-08-29','COMP_TRABAJO');
INSERT INTO contactos VALUES(15,'Mayra','Carrasco','Mayr@server.com',1177200775,'Cordoba 4903','2002-02-04','AMIGO');
INSERT INTO contactos VALUES(16,'Eloisa','Miller','Eloi@server.com',1165131587,'Santiago Del Estero 3093','1993-08-26',NULL);
INSERT INTO contactos VALUES(17,'Arnaldo','Choque','Arna@server.com',1193313830,'San Juan 1495','2002-08-15','AMIGO');
INSERT INTO contactos VALUES(18,'Santos','Garofalo','Sant@server.com',1169185503,'Neuquen 532','2009-11-30',NULL);
INSERT INTO contactos VALUES(19,'Isabel','Lozada','Isab@server.com',1168200862,'Mendoza 2862','1994-08-15','COMP_FACU');
INSERT INTO contactos VALUES(20,'Jonas','Winkler','Jona@server.com',1162040726,'Argentina 4803','2003-10-06','FAMILIAR');
DELETE FROM sqlite_sequence;
INSERT INTO sqlite_sequence VALUES('contactos',20);


COMMIT;