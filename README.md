# Projet Web 5A : réalisation d'un chat avec des websockets.

Ce document décrit le mode opératoire de l'application "ESIEA Messenger" que nous avons créé dans le cadre de nos études à l'ESIEA. 
Equipe : Clara BRIDOUX, Axel PRISER, Alina VALNET, Franck VALOT

## Mode opératoire

Le mode opératoire décrit comment lancer l'aplication ESIEA Messenger sur sa machine locale et comment l'utiliser.

### Comment lancer ESIEA Messenger

Pour lancer l'application ESIEA Messenger sur votre machine locale, vous avez besoin de l'IDE STS (qui gère le framework Spring) et d'une base de données MySQL(ex. le MySQL de Wampserver pour Windows). 
Vous trouverez la base de données MySQL "bddweb.sql" dans le reposiroty GitHub.

Lancez ESIEA Messenger sur votre localhost comme suit: 

```
http://localhost:8080/index.html
```

### Comment utiliser ESIEA Messenger

Lorsque vous lancer ESIEA Messenger, vous arrivez sur l'authentification. Vous pouvez créer des comptes en cliquant sur le lien suivant "Créer un compte" ou utiliser des comptes déjà créés. 
Nous vous proposons d'utiliser 3 utilisateurs déjà existant. Ouvrez 3 onglets (en navigation normale/privée).

Alina : 
```
email : 'alina.valnet@gmail.com'  mot de passe: 'jojo2017'
```

Clara : 
```
email : 'bridoux@et.esiea.fr'  mot de passe: 'clara2017'
```

Franck : 
```
email : 'valot@et.esiea.fr'  mot de passe: 'franck2017'
```

Lorsque vous appuyez sur le bouton "Connexion", vous avez sur votre gauche la liste de conversations d'un utilisateur. Cliquez sur une conversation pour continuer votre discussion avec Alina, Clara ou Franck. 
Vous pouvez également ouvrir un quatrième onglet pour créer un 4ème utilisateur et le faire communiquer avec Alina, Clara, Franck ou les 3.
Pour cela, une fois que vous authetifiez le 4ème utilisateur, appuyez sur le bouton "Nouvelle conversation", saisissez Clara, Alina, Franck ou les trois et validez. Vous pourez ensuite leur envoyer des messages.
