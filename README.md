# APPLICATION MOBILE CASTRES AU TRESOR - MMI NOVA
Nous avons pour projet de réaliser une application mobile qui s'appelle Castres au trésor. Cette application a pour but de faire découvrir Castres à son utilisateur, de découvrir de nouveaux lieux et de s'amuser de manière ludique. Elle a ainsi pour cible les Castrais ainsi que les touristes.<br><br>
L'application utilise les fonctionnalités de géolocalisation de manière à jouer au jeu de chaud/froid. En effet, l'utilisateur pourra accéder à une mini-map ainsi qu'à une photo floutée du lieu qu'il souhaite découvrir. Une boussole apparaîtra pour le guider dans la bonne direction sur 100m. Puis, une fois ces 100m passés, un thermomètre remplacera la boussole et indiquera la température relative à sa position et à la position du lieu cible. Plus l'utilisateur sera proche du lieu, plus la température sera élevée. A l'inverse, plus l'utilisateur sera éloigné du lieu, plus la température sera basse. Ainsi, grâce à la boussole et au thermomètre, l'utilisateur pourra accéder au lieu à l'aide de son téléphone portable.<br><br>
Cette application possède un système de récompenses au fur et à mesure que l'utilisateur découvre des lieux. Il débloquera des badges et un petit certificat personnalisé à chaque catégorie de lieux complétée.<br><br>
Il sera nécessaire de se créer un compte à l'aide d'une adresse mail et d'un mot de passe pour accéder à l'application. Cela est nécessaire car l'utilisateur recevra les certificats par mail de manière à pouvoir les imprimer.<br><br>
Voici les identifiants d'un compte test :
- **Email :** demo@collect.com
- **Mot de passe :** demo123.

## RÉPARTITION DES TÂCHES

- [x] Partie “Catégorie” : *Aïolah*
  - Création de l’[API Catégories](https://api-nodejs-sae.onrender.com/categories)
  - **[Application]** Affichage de la liste des catégories
  - **[Application]** Clic sur une catégorie qui redirige vers les lieux de la catégorie sélectionnée

- [x] Partie “Lieux non découverts” : *Calia*
  - Création de l’[API Lieux](https://api-nodejs-sae.onrender.com/lieux)
  - **[Application]** Affichage de la liste des lieux (tous)
  - **[Application]** Clic sur un lieu qui redirige vers la fiche de ce lieu
  - **[Application]** Affichage de la liste des lieux en fonction de la catégorie choisie
  - Modification de l’API Lieux

- [x] Partie “Carte” : *Fabian*
  - **[Application]** Affichage de la carte lors du lancement de l’application

- [x] Partie “Boussole & Thermomètre” : *Jules & Fabian*
  - **[Application]** Switch entre la page du lieu mystère choisi et la boussole 
  - **[Application]** Affichage d’une boussole lorsque l’utilisateur choisit un lieu 
  - **[Application]** Switch de Boussole à Thermomètre lorsque l’utilisateur arrive à moins de cent mètres du lieu
  - **[Application]** Affichage du thermomètre

- [x] Partie “Admin” : *Calia & Aïolah*
  - Création de l’API liée au site d’administration
  - **[Site d'administration]** Création des interfaces : page de connexion au site d’administration, page affichant la liste des lieux, page d’ajout d'un lieu et page de modification d’un lieu
  - **[Site d'administration]** Fonctions de connexion, d’ajout d’un lieu dans la base de données (bdd), modification d’un lieu de la bdd et suppression de lieu déjà présent dans la bdd
     
- [x] Partie “Connexion & Profil” : *Jules*
  - Création de la BDD Firebase pour les utilisateurs
  - **[Application]** Affichage et traitement des formulaires d’inscription et de connexion à l’application
  - **[Application]** Affichage du profil après la connexion

- [x] Partie “QR Code” : *Fabian*
  -  Création des QR Codes
  - **[Application]** Fonctionnalité du scan du QR Code

- [x] Partie “Lieu découvert” : *Calia*
  - **[Application]** Affichage de la fiche du lieu découvert
  - Modification de l’API Lieux

- [x] Partie “Collection” : *Jules & Fabian*
  - **[Application]** Après scan du QR code : affichage des détails d'un lieu et ajout du lieu découvert dans la bdd
  - **[Application]** Affichage des lieux déjà scanné dans la page collection ou d'une page indiquant qu'il n'a encore rien scanné

## DOCUMENTATION TECHNIQUE

L'application mobile a été codée avec Kotlin et Jetpack Compose. Cela signifie qu'elle fonctionne uniquement sur les téléphones portables sous Android.
L'application n'est pas disponible sur le Play Store Google. Ainsi, pour l'installer sur votre appareil, veuillez effectuer les étapes décrites ci-dessous :

1. Installer [Android Studio](https://developer.android.com/studio) sur votre ordinateur
2. Cloner le repository Github disponible [ici](https://github.com/mmicastres/sae501-calia-fabian-jules-aiolah) sur votre ordinateur
3. Ouvrir le dossier cloné dans Android Studio
4. Relier votre téléphone à votre ordinateur par un câble USB
5. Activer le mode développeur sur votre téléphone
6. Vérifier que votre téléphone est bien détecté par Android Studio et que vous avez suffisamment d'espace sur votre téléphone pour installer l'application
7. Activer le wi-fi
8. Lancer l'installation de l'application sur votre téléphone en appuyant sur le bouton Play vert d'Android Studio
9. L'application se lance
10. Félicitations, vous pouvez maintenant utiliser l'application en vous référant au [mode d'emploi](https://drive.google.com/file/d/1PgxkMK_kwXDhUorqANeb1DQUOA9_6dA4/view?usp=sharing) !

## DOCUMENTATION UTILISATEUR

- [Mode d'emploi](https://drive.google.com/file/d/1PgxkMK_kwXDhUorqANeb1DQUOA9_6dA4/view?usp=sharing)

## ETUDE ET ANALYSE DU BESOIN

- [Dossier](https://drive.google.com/file/d/1BE6ElyoEzDoLX1qG9Wxyk7S6qlv9tJjd/view?usp=sharing)

## LIVRABLES UX    
  
- [Maquette interactive](https://www.figma.com/proto/eb2jVZ2wBG65SzdeitfkCj/Maquettes?page-id=112%3A1263&type=design&node-id=126-887&viewport=307%2C396%2C0.23&t=aC1iHGlWLdDdAqOH-1&scaling=scale-down&starting-point-node-id=126%3A887&mode=design)
- [Maquette](https://www.figma.com/file/eb2jVZ2wBG65SzdeitfkCj/Maquettes?type=design&node-id=112%3A1263&mode=design&t=svda4gCIyw66cpDU-1)

## LIVRABLES CONCEPTION

- [Diagramme bêtes à cornes](https://drive.google.com/file/d/17680dEitnraFn44eEiCdIYYVlT9hDzV9/view?usp=sharing)
- [Liste des exigences (MoSCoW)](https://drive.google.com/file/d/1aBR2aIVle5xREsTTs_tqX8pkloJbfmX3/view?usp=sharing)
- [Diagramme des Use Case](https://drive.google.com/file/d/1DkBzDU89QWn4l19wKV0GEn4hEDR8-cK0/view?usp=sharing)
- [Schémas d'architecture logicielle](https://drive.google.com/file/d/1inSompsJSVajKH1Rq6da75etgYVx3M6S/view?usp=sharing)

## LIVRABLES GESTION DE PROJET

- [Scrum Board Instantané](https://github.com/orgs/mmicastres/projects/20/views/1)
- [Scrum Board de Sprint](https://trello.com/invite/b/n5La7cv6/ATTI5b7d23b6b91560ad4db776917f5d01d3F8BB4AEC/carte-au-tresor-organisation)

## REPOSITORIES GITHUB

- [API](https://github.com/aiolah/API-NodeJS-SAE)
- [Site d'administration](https://github.com/aiolah/admin-SAE)
