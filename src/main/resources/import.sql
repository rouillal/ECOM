insert into categorie(categorie_name) values ('Fruits'), ('Légumes'), ('Aromates'),('Crèmerie'),('Boissons'),('Produits du terroir');
insert into saison(saison_name) values ('Printemps'), ('Eté'), ('Automne'), ('Hiver');
insert into produit(categorie_id, produit_name, produit_variete, produit_unite, produit_quantite, produit_stock, produit_prix, produit_provenance, produit_dateCueillette, produit_dureeConservation, produit_calories, produit_glucides, produit_fibres, produit_proteines, produit_filename) values (1,'Abricot','Bergeron bio','g',1000,78,7.37,'La Ferme à Dédé','15/12/2016',5,49,9,2,1,'AbricotBergeron'),(1,'Abricot','Rouge du Roussillon bio','g',1000,53,4.16,'La Ferme à Dédé','14/12/2016',6,49,9,2,1,'AbricotRoussillon'),(1,'Abricot','Orangé de Provence bio','g',2000,22,5.20,'La Ferme des Marmottes','16/12/2016',4,49,9,2,1,'AbricotOrange'),(3,'Ail','Violet bio','g',1000,78,3.60,'La Ferme des Marmottes','10/12/2016',180,131,22,5,8,'AbricotViolet'),(1,'Amande','Avola bio','g',200,121,2.00,'La Ferme à Dédé','15/12/2016',150,634,1,13,25,'AmandeAvola'),(1,'Ananas','Cayenne bio','g',2400,18,3.10,'Ferme de Namières','15/12/2016',3,53,11,2,1,'AnanasCayenne'),(2,'Artichaut','Kerlouan bio','g',1000,34,4.25,'Ferme de Namières','13/12/2016',4,44,5,5,3,'ArtichautKerlouan'),(2,'Asperge','Blanche bio','g',420,5,3.50,'Ferme de Namières','15/12/2016',3,30,3,2,3,'AspergeBlanche'),(2,'Aubergine','Diamond bio','g',500,12,1.40,'La Ferme du Clos','16/12/2016',5,35,6,3,1,'AubergineDiamond'),(1,'Avocat','Hass bio','g',350,45,1.38,'La Ferme du Clos','13/12/2016',5,169,3,5,2,'AvocatHass'),(1,'Avocat','Bacon bio','g',4000,48,11.00,'La Ferme du Clos','15/12/2016',5,169,3,5,2,'AvocatBacon'),(1,'Banane','Enano bio','g',500,90,2.30,'La Ferme à Dédé','15/12/2016',4,94,21,3,1,'BananeEnano'),(1,'Banane','Cavendish bio','g',1000,132,4.20,'La Ferme à Dédé','15/12/2016',4,94,21,3,1,'BananeCavendish'),(3,'Basilic','Sacré bio','g',500,13,4.80,'Ferme de Namières','10/12/2016',15,31,1,3,3,'BasilicSacre'),(2,'Bette','à carde bio','g',500,4,1.25,'Ferme de Namières','12/12/2016',6,21,2,2,2,'BetteCarde'),(2,'Betterave','rouge bio','g',500,46,1.55,'La Ferme des Marmottes','16/12/2016',2,43,7,2,2,'BetteraveRouge'),(2,'Brocoli','Calabrais bio','g',500,43,2.91,'La Ferme des Marmottes','12/12/2016',5,29,3,2,2,'BrocoliCalabrais'),(2,'Carotte','Flyaway F1 bio','g',300,11,1.59,'La Ferme à Dédé','15/12/2016',6,36,7,2,1,'CarotteFlyaway'),(1,'Cassis','Arno bio','g',500,19,5.25,'La Ferme à Dédé','16/12/2016',4,73,10,8,1,'CassisArno'),(2,'Céleri','Rave bio','g',300,13,2.30,'La Ferme des Marmottes','14/12/2016',6,37,6,3,1,'CeleriRave'),(3,'Cerfeuil','Frisé bio','g',150,12,2.80,'La Ferme du Clos','14/12/2016',6,48,6,2,3,'CerfeuilFrise'),(1,'Cerise','Armara bio','g',1000,9,4.50,'La Ferme du Clos','14/12/2016',6,71,14,2,1,'CeriseArmara'),(2,'Champignon','de Paris bio','g',1000,23,6.69,'La Ferme du Clos','15/12/2016',4,30,0,2,3,'ChampignonParis'),(2,'Chou','de Bruxelles bio','g',1000,27,2.70,'La Ferme à Dédé','15/12/2016',7,41,7,3,3,'ChouBruxelles'),(2,'Chou','Fleur bio','g',2000,32,4.20,'La Ferme des Marmottes','14/12/2016',7,28,3,2,2,'ChouFleur'),(2,'Chou','Rouge bio','g',1500,5,3.90,'La Ferme des Marmottes','15/12/2016',7,33,5,2,2,'ChouRouge'),(3,'Ciboulette bio','Polycross','g',500,8,4.50,'Ferme de Namières','14/12/2016',7,34,5,2,2,'CiboulettePolycross'),(1,'Citron','Verna bio','g',500,54,2.84,'La Ferme à Dédé','3/12/2016',30,34,2,2,1,'CitronVerna'),(1,'Citron','Vert bio','g',500,67,2.90,'La Ferme à Dédé','5/12/2016',30,36,3,3,1,'CitronVert'),(1,'Clémentine','de Corse bio','g',1000,17,3.90,'La Ferme à Dédé','15/12/2016',6,49,9,2,1,'ClementineCorse'),(1,'Clémentine','Nules bio','g',800,19,3.90,'La Ferme à Dédé','14/12/2016',6,49,9,2,1,'ClementineNules'),(1,'Coing','Champion bio','g',1000,65,1.80,'La Ferme à Dédé','11/12/2016',21,58,11,3,0,'CoingChampion'),(2,'Concombre','Hollandais bio','g',250,62,1.80,'Ferme de Namières','13/12/2016',7,12,1,1,1,'ConcombreHollandais'),(3,'Coriandre','Delfino bio','g',250,23,3.70,'Ferme de Namières','14/12/2016',4,346,13,42,13,'ConriandreDelfino'),(2,'Courgette','Natura bio','g',1000,25,3.90,'Ferme de Namières','14/12/2016',5,20,2,1,1,'CourgetteNatura'),(3,'Echalote','Ronde bio','g',500,87,3.26,'Ferme de Namières','25/11/2016',60,76,16,2,2,'EchaloteRonde'),(2,'Endive','Ecrine bio','g',500,43,2.50,'Ferme de Namières','15/12/2016',6,17,2,1,1,'EndiveEcrine'),(1,'Figue','de Barbarie bio','g',300,13,6.50,'La Ferme des Marmottes','16/12/2016',2,55,10,4,1,'FigueBarbarie'),(1,'Fraise','Gariguette bio','g',500,87,6.00,'La Ferme des Marmottes','16/12/2016',2,29,4,2,1,'FraiseGariguette'),(1,'Framboise','Meeker bio','g',150,87,6.60,'La Ferme des Marmottes','16/12/2016',2,45,4,7,1,'FramboiseMeeker'),(3,'Gingembre','Sabah bio','g',250,4,2.50,'Ferme de Namières','12/12/2016',30,332,58,14,9,'GingembreSabah'),(2,'Haricot','Vert bio','g',1000,34,3.00,'Ferme de Namières','16/12/2016',3,33,5,3,1,'HaricotVert'),(2,'Laitue','Iceberg bio','g',250,41,2.30,'Ferme de Namières','16/12/2016',1,14,1,1,1,'LaitueIceberg'),(1,'Litchi','bio','g',500,53,3.75,'La Ferme à Dédé','10/12/2016',15,69,14,1,1,'Litchi'),(2,'Maïs','en épi bio','g',500,50,2.65,'La Ferme à Dédé','15/12/2016',4,100,16,4,3,'MaisEpi'),(1,'Marron','Judia bio','g',1000,123,4.90,'La Ferme à Dédé','16/12/2016',5,133,26,4,2,'MarronJudia'),(1,'Melon','Charentais bio','g',500,32,2.23,'La Ferme à Dédé','16/12/2016',6,32,6,1,1,'MelonCharentais'),(3,'Menthe','Fraise bio','g',350,3,3.60,'La Ferme à Dédé','10/12/2016',4,49,3,7,4,'MentheFraise'),(1,'Mirabelle','bio','g',1000,51,2.10,'La Ferme à Dédé','14/12/2016',5,62,12,2,1,'Mirabelle'),(1,'Mûre','Noire bio','g',125,48,4.10,'Ferme de Namières','16/12/2016',3,49,10,2,1,'MureNoire'),(1,'Myrtille','Sauvage bio','g',250,53,4.95,'Ferme de Namières','15/12/2016',7,60,11,2,1,'MyrtilleSauvage'),(2,'Navet','de Milan bio','g',500,11,1.20,'La Ferme à Dédé','16/12/2016',3,21,2,3,1,'NavetMilan'),(1,'Noisette','Ennis bio','g',500,59,5.50,'La Ferme du Clos','10/12/2016',21,683,6,8,16,'NoisetteEnnis'),(1,'Noix','de Grenoble bio','g',500,43,2.80,'La Ferme du CLos','10/12/2016',21,698,11,6,15,'NoixGrenoble'),(3,'Oignon','Jaune bio','g',500,51,1.20,'La Ferme des Marmottes','10/12/2016',15,43,5,1,1,'OignonJaune'),(1,'Orange','Valencia bio','g',2500,67,3.75,'La Ferme à Dédé','10/12/2016',10,46,8,2,1,'OrangeValencia'),(3,'Oseille','Commune bio','g',250,89,2.40,'La Ferme à Dédé','10/12/2016',3,21,0,3,2,'OseilleCommune'),(1,'Pastèque','Yellow Belly bio','g',3500,18,2.30,'La Ferme des Marmottes','13/12/2016',7,34,7,1,1,'PastequeYellowBelly'),(2,'Patate','Douce bio','g',1000,3,3.14,'La Ferme à Dédé','13/12/2016',8,79,16,3,2,'PatateDouce'),(3,'Persil','Frisé bio','g',100,32,0.55,'Ferme des Namières','16/12/2016',4,47,5,4,3,'PersilFrise'),(2,'Pomme de terre','Charlotte bio','g',2500,35,3.50,'La Ferme à Dédé','14/12/2016',14,119,27,3,3,'PdtCharlotte'),(1,'Poire','Williams bio','g',1000,78,1.70,'La Ferme à Dédé','13/12/2016',2,53,11,3,0,'PoireWilliams'),(2,'Poireau','Primeur bio','g',1000,54,1.90,'La Ferme à Dédé','15/12/2016',5,29,3,3,1,'PoireauPrimeur'),(2,'Poivron','Rouge bio','g',500,30,1.80,'La Ferme du Clos','15/12/2016',8,34,6,1,1,'PoivronRouge'),(2,'Poivron','Vert bio','g',500,22,1.20,'La Ferme à Dédé','16/12/2016',8,21,4,1,1,'PoivronVert'),(1,'Pomme','Golden bio','g',500,12,1.20,'La Ferme à Dédé','15/12/2016',4,65,11,2,0,'PommeGolden'),(1,'Pomme','Granny bio','g',1000,13,3.10,'La Ferme à Dédé','15/12/2016',4,65,11,2,0,'PommeGolden'),(1,'Pomme','Pink Lady bio','g',500,43,2.50,'La Ferme à Dédé','15/12/2016',4,65,11,2,0,'PommePinkLady'),(1,'Raisin','Blanc bio','g',500,90,2.30,'La Ferme à Dédé','16/12/2016',3,70,16,1,1,'RaisinBlanc'),(3,'Thym','Commun bio','g',250,41,2.50,'Ferme des Namières','16/12/2016',4,291,42,28,6,'ThymCommun'),(1,'Tomate','Ronde bio','g',1000,90,1.50,'La Ferme à Dédé','16/12/2016',3,16,2,1,1,'TomateRonde'),(1,'Tomate','Coeur de Boeuf bio','g',1000,9,2.50,'La Ferme à Dédé','16/12/2016',3,16,2,1,1,'TomateCoeurBoeuf'),(4,'Oeufs','gros calibre bio','unités',4,67,1.74,'La Ferme à Dédé','16/12/2016',15,155,1,0,13,'OeufGrosCalibre'),(4,'Oeufs','plein air bio','unités',6,54,2.10,'La Ferme à Dédé','15/12/2016',15,155,1,0,13,'OeufPleinAir'),(4,'Lait','de brebis bio','L',1,21,3.25,'La Ferme à Dédé','15/12/2016',15,108,5,0,6,'LaitBrebis'),(4,'Lait','de chèvre bio','L',1,100,3.50,'La Ferme à Dédé','11/12/2016',15,108,5,0,6,'LaitChevre'),(4,'Lait','demi écrémé bio','L',1,110,1.10,'La Ferme à Dédé','13/12/2016',15,45,5,0,3,'LaitDemiEcreme'),(4,'Lait','écrémé bio','L',1,115,1.50,'La Ferme à Dédé','16/12/2016',15,31,4,0,3,'LaitEcreme'),(4,'Crème','de brebis épaisse bio','cl',20,14,2.95,'La Ferme à Dédé','16/12/2016',4,109,5,0,5,'CremeBrebis'),(4,'Crème','fraîche bio','cl',20,43,1.75,'La Ferme à Dédé','16/12/2016',4,109,5,0,5,'CremeFraiche'),(4,'Fromage Blanc','au lait de brebis bio','g',400,20,2.95,'La Ferme à Dédé','16/12/2016',4,109,5,0,5,'FromageBlancBrebis'),(4,'Fromage Blanc','au lait de chèvre bio','g',400,20,3.30,'La Ferme à Dédé','16/12/2016',4,109,5,0,5,'FromageBlancChevre'),(4,'Beurre','doux bio','g',250,20,2.45,'La Ferme à Dédé','14/12/2016',14,717,0,0,1,'BeurreDoux'),(4,'Bûche','de chèvre bio','g',150,20,5.15,'La Ferme à Dédé','16/12/2016',14,364,0,0,22,'BucheChevre'),(4,'Reblochon','au lait cru bio','g',240,20,5.55,'La Ferme à Dédé','16/12/2016',14,330,0,0,21,'Reblochon'),(4,'Camembert','au lait cru bio','g',250,20,4.90,'La Ferme à Dédé','15/12/2016',14,299,0,0,20,'Camembert'),(4,'Brique de brebis','bio','g',150,16,4.49,'La Ferme à Dédé','15/12/2016',8,109,5,0,5,'BriqueBrebis'),(4,'Tomme blanche','de brebis bio','g',240,19,7.34,'La Ferme à Dédé','12/12/2016',10,109,5,0,5,'TommeBrebis'),(5,'Jus de tomate','bio','cl',50,19,1.95,'La Ferme des Marmottes','15/12/2016',5,22,4,1,1,'JusTomate'),(5,'Jus de carotte','bio','cl',70,19,3.75,'La Ferme des Marmottes','15/12/2016',5,24,5,1,1,'JusCarotte'),(5,'Jus de betterave','bio','cl',75,19,3.62,'La Ferme des Marmottes','16/12/2016',5,21,6,1,1,'JusBetterave'),(5,'Jus de citron','bio','L',1,19,4.30,'La Ferme des Marmottes','16/12/2016',14,28,3,0,0,'JusCitron'),(5,'Jus de poire','bio','L',1,19,4.30,'La Ferme des Marmottes','15/12/2016',5,45,11,0,0,'JusPoire'),(5,'Jus de pommes','bio','L',1,19,3.25,'La Ferme des Marmottes','15/12/2016',5,65,11,0,0,'JusPomme'),(5,'Jus de cassis','bio','cl',75,23,4.99,'La Ferme des Marmottes','15/12/2016',5,75,11,0,0,'JusCassis'),(5,'Jus de raisin','bio','L',1,23,3.85,'Domaine Finot','15/12/2016',5,62,11,0,0,'JusRaisin'),(6,'Vin Blanc','Tracteur 2013','L',1,6,8.00,'La Ferme des Marmottes','15/08/2013',300,77,4,0,0,'VinBlanc'),(6,'Pinot Noir','2013','L',1,6,14.00,'Domaine Finot','10/08/2013',300,72,4,0,0,'PinotNoir'),(6,'Confiture de châtaigne','bio','g',220,6,3.50,'Saveurs du Vercors','30/11/2016',15,243,58,2,1,'ConfitureChataigne'),(6,'Gelée de coing','bio','g',220,6,3.30,'Saveurs du Vercors','30/11/2016',15,212,45,2,1,'ConfitureCoing'),(6,'Confiture de figues','bio','g',220,6,3.50,'Saveurs du Vercors','5/12/2016',15,265,62,2,1,'ConfitureFigue'),(6,'Compote abricot','bio','g',380,6,3.20,'Saveurs du Vercors','5/12/2016',15,245,45,2,1,'CompoteAbricot'),(6,'Compote pomme','bio','g',480,6,3.60,'Saveurs du Vercors','10/12/2016',15,213,40,2,1,'CompotePomme'),(6,'Miel acacia','bio','g',500,12,9.30,'Saveurs du Vercors','10/12/2016',60,327,81,0,1,'MielAcacia'),(6,'Miel chataignier','bio','g',500,14,8.60,'Saveurs du Vercors','10/12/2016',60,327,81,0,1,'MielChataignier'),(6,'Vinaigre Noix du Vercors','bio','cl',25,14,4.75,'Saveurs du Vercors','10/12/2016',60,24,0,0,0,'VinaigreNoix'),(6,'Huile olives','bio','cl',25,14,5.75,'Saveurs du Vercors','10/12/2016',60,824,0,0,0,'HuileOlive'),(6,'Confit oignon','bio','g',210,15,4.00,'Saveurs du Vercors','10/12/2016',60,153,37,0,1,'ConfitOignon'); 
insert into produitsaison(produit_id, saison_id) values (1,1), (1,2),(2,1),(2,2),(3,1),(3,2),(4,1),(4,2),(4,3),(4,4),(5,2),(5,3),(6,1),(6,3),(6,4),(7,1),(7,2),(8,1),(9,2),(10,1),(10,3),(10,4),(11,1),(11,3),(11,4),(12,1),(12,2),(12,3),(12,4),(13,1),(13,2),(13,3),(13,4),(14,1),(14,2),(14,3),(14,4),(15,1),(15,3),(15,4),(16,3),(17,3),(18,3),(18,4),(19,2),(20,3),(20,4),(21,1),(21,2),(22,1),(22,2),(23,1),(23,3),(23,4),(24,3),(24,4),(25,1),(25,3),(25,4),(26,2),(26,3),(26,4),(27,1),(27,2),(28,1),(28,2),(28,3),(28,4),(29,1),(29,2),(29,3),(29,4),(30,3),(30,4),(31,3),(31,4),(32,3),(33,1),(33,2),(34,1),(34,2),(35,1),(35,2),(36,3),(37,1),(37,3),(37,4),(38,2),(38,3),(39,1),(40,2),(41,1),(41,2),(41,3),(41,4),(42,2),(43,1),(43,2),(44,3),(44,4),(45,2),(45,3),(46,3),(47,2),(48,1),(48,2),(49,2),(50,2),(50,3),(51,2),(52,1),(53,3),(53,4),(54,3),(55,3),(55,1),(55,4),(56,1),(56,4),(57,1),(57,2),(57,3),(58,2),(59,1),(59,2),(59,3),(59,4),(60,1),(60,2),(61,3),(61,4),(62,1),(62,2),(62,3),(62,4),(63,1),(63,3),(63,4),(64,2),(65,2),(66,1),(66,3),(66,4),(67,1),(67,3),(67,4),(68,1),(68,3),(68,4),(69,2),(69,3),(70,1),(70,2),(71,1),(71,2),(72,1),(72,2),(73,1),(73,2),(73,3),(73,4),(74,1),(74,2),(74,3),(74,4),(75,1),(75,2),(75,3),(75,4),(76,1),(76,2),(76,3),(76,4),(77,1),(77,2),(77,3),(77,4),(78,1),(78,2),(78,3),(78,4),(79,1),(79,2),(79,3),(79,4),(80,1),(80,2),(80,3),(80,4),(81,1),(81,2),(81,3),(81,4),(82,1),(82,2),(82,3),(82,4),(83,1),(83,2),(83,3),(83,4),(84,1),(84,2),(84,3),(84,4),(85,1),(85,2),(85,3),(85,4),(86,1),(86,2),(86,3),(86,4),(87,1),(87,2),(87,3),(87,4),(88,1),(88,2),(88,3),(88,4),(89,1),(89,2),(89,3),(89,4),(90,1),(90,2),(90,3),(90,4),(91,1),(91,2),(91,3),(91,4),(92,1),(92,2),(92,3),(92,4),(93,1),(93,2),(93,3),(93,4),(94,1),(94,2),(94,3),(94,4),(95,1),(95,2),(95,3),(95,4),(96,1),(96,2),(96,3),(96,4),(97,1),(97,2),(97,3),(97,4),(98,1),(98,2),(98,3),(98,4),(99,1),(99,2),(99,3),(99,4),(100,1),(100,2),(100,3),(100,4),(101,1),(101,2),(101,3),(101,4),(102,1),(102,2),(102,3),(102,4),(103,1),(103,2),(103,3),(103,4),(104,1),(104,2),(104,3),(104,4),(105,1),(105,2),(105,3),(105,4),(106,1),(106,2),(106,3),(106,4),(107,1),(107,2),(107,3),(107,4),(108,1),(108,2),(108,3),(108,4);





