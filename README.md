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

Assurez-vous d'avoir accès au programme java depuis le terminal (prankmail-1.0-jar-with-dependencies.jar).

Utilisez la commande maven pour compiler le code s'il ne l'est pas déjà :

`mvn clean package`

Le fichier exécutable .jar se trouvera dans le dossier target.

Ensuite, pour exécuter le .jar, utilisez la commande java et passez les arguments nécessaires :

`java -jar <jarFile> <emailFile> <messageFile> <numberOfGroups> <serverAddress> <serverPort>`

Exemple :

`java -jar ./target/prankmail-1.0-jar-with-dependencies.jar ./email_address.txt ./prank_message.json 2 127.0.0.1 1025`

### 4. Architecture du projet :

<img src=".\figures\class_diagram.png" title="Diagramme de classe" width="600"/>

Notre programme contient 5 classes principales outre la classe Main :

**FileReader :** Lit les fichiers de configuration (adresses e-mail et messages de plaisanterie) et retourne les données lues.

**EmailMessage :** Récupère les données lues par FileReader et crée les messages e-mail forgés à partir de ces données.

**Group :** Représente un groupe d'e-mails avec un message de plaisanterie, la première adresse e-mail du groupe étant
l'expéditeur et les autres étant les destinataires.

**SMTP Client :** Gère une connexion avec un serveur SMTP fictif pour un groupe donné et envoie le message de plaisanterie.  
Une fois le socket créé, il débutera la communication avec un "EHLO [serveraddress]".
Ensuite, il enverra un "MAIL FROM: <sender>" pour chaque groupe.
Puis, il enverra un "RCPT TO: <recipient>" pour chaque destinataire du groupe.
Enfin, il enverra un "DATA" pour chaque groupe et enverra le message de plaisanterie.
Il terminera la communication avec un "QUIT".

**Controller :** Gère la logique principale du programme, génère les groupes et lance les campagnes de plaisanterie.

**Main :** Point d'entrée du programme, récupère les arguments passés en ligne de commande, crée et lance le Controller.

**ManPage :** Affiche le manuel d'utilisation du programme avec les arguments à passer en ligne de commande.

