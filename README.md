DAI lab: SMTP
=============

Auteurs : Johan Mikami, Lucas Hussain

### 1. Description du projet :
Ce projet Java vise à créer un outil permettant d'envoyer des emails de plaisanterie à des groupes de destinataires 
via le protocole SMTP. Il offre la possibilité de configurer facilement les groupes cibles et les messages de 
plaisanterie, tout en respectant les bonnes pratiques de gestion des e-mails. L'outil permet également une 
intégration avec un serveur SMTP fictif pour des tests sans envoi réel d'e-mails.

### 2. Mise en place d'un serveur SMTP fictif via Docker (maildev) :

##### 2.1. Lancement du serveur SMTP :

`docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev`

##### 2.2. Accès à l'interface MailDev :

Ouvrez un navigateur et accède à l'interface MailDev via http://localhost:1080.

Utilisez cette interface pour visualiser les e-mails envoyés.

### 3. Intructions d'utilisation :

##### 3.1. Configuration des destinataires :

Éditez le fichier email_address.txt pour ajouter des adresses mails voulues, une par ligne.

##### 3.2. Configuration des messages de plaisanterie :

Ajoutez vos messages de plaisanterie dans le fichier prank_message.json.

Il contient une liste d'élement avec les attributs "subject" et "content".

##### 3.3. Exécution de la campagne de plaisanterie :

Ouvrez un terminal ou une invite de commande.

Assurez-vous d'être dans le répertoire contenant le programme Java (Main.java).

Utilisez la commande javac pour compiler le code s'il ne l'est pas déjà. Par exemple :

`javac Main.java`

Ensuite, pour exécuter Main.java, utilisez la commande java et passez les arguments nécessaires :

`java Main <cheminFichierEmail> <cheminFichierBlagues> <nbGroupes>`

### 4. Architecture du projet :

<img src=".\figures\class diagram.png" title="Diagramme de classe" width="600"/>

Notre programme contient 5 classes principales outre la classe Main :

**FileReader :** Lit les fichiers de configuration (adresses e-mail et messages de plaisanterie) et retourne les données lues.

**EmailMessage :** Récupère les données lues par FileReader et crée les messages e-mail forgés à partir de ces données.

**Group :** Représente un groupe d'e-mails avec un message de plaisanterie, la première adresse e-mail du groupe étant
l'expéditeur et les autres étant les destinataires.

**SMTP Client :** Gère une connexion avec un serveur SMTP fictif pour un groupe donné et envoie le message de plaisanterie.

**Controller :** Gère la logique principale du programme, génère les groupes et lance les campagnes de plaisanterie.

**Main :** Point d'entrée du programme, récupère les arguments passés en ligne de commande, crée et lance le Controller.

