Pour soumettre vos réponses, merci de : 

- forker/cloner ce repository. 
- commiter vos réponses sous forme de commits 
portant le numéro de la question/scénario.

- A la fin, zipper votre dossier de travail et uploder-le sur Hackerrank.
----
L'application consiste à une simple gestion des utilisateurs.

- Chaque utilisateur est identifié par les informations suivantes :

| User                   |
|------------------------|
 | id    : int            |
 | login    : String      |
 | password    : String   |
 | enabled      : boolean |
 | loginAttempts : int    |

Chaque utilisateur appartient à un groupe, qui est identifié comme suit : 
 
 | Group             |
|-------------------|
| id : int          |
| name:String       |

La base de données est pré-remplie par les données suivante : 

### User
|ID|LOGIN|PASSWORD|ENABLED|LOGINATTEMPTS | GROUP_ID   |
|---|---|---|----|-----|------------|
|1|	admin|	adminPWD|	1|	0| 	1         |
|2|	test|	testPWD|	1|	0| 	2         |
|3|	test2|	test2PWD|	0|	5| 	2         |
          
### Group
|ID| NAME   |
|---|--------|
|1| 	admin  |
|2| 	member| 


# Travail demandé : 
Le travail sera effectué en plusieurs scénarios

### 1. Authentification : Best case
- Description : authentifier un utilisateur par login et mot de passe qui sont corrects.
- REST route : `POST /auth`
- input : 
 ```json 
  {"login" : "aLogin", "password" : "aPassword"}
```
- output :
 
```json 
{
 "id" : 555,
  "login" : "aLogin",
  "group": "aGroup"
  }
```
### 2. Authentification : Mot de passe ou login erronés
- Description : authentifier un utilisateur par login incorrect et/ou un mot de passe incorrect.
- REST route : `POST /auth`
- input :
 ```json 
  {"login" : "aLogin", "password" : "aPassword"}
```
- output :

```json 
{ 
  "error": "Authentication error"
  }
```

### 3. Authentification : Utilisateur désactivé
- Description : authentifier un utilisateur qui est désactivé (`user.enabled = 0`).
- REST route : `POST /auth`
- input :
 ```json 
  {"login" : "aLogin", "password" : "aPassword"}
```
- output :

```json 
{ 
  "error": "User disabled"
  }
```

### 4. Authentification : Dépassement de 3 tentatives
- Description : Désactiver un utilisateur après 3 tentatives d'authentification erronées (`user.loginAttempts >= 3`).
- REST route : `POST /auth`
- input :
 ```json 
  {"login" : "aLogin", "password" : "aPassword"}
```
- output :

```json 
{ 
  "error": "You have reached 3 failed authentication attempts, your account will be disabled"
  }
```
### 5. Création de compte : Best case
- Description : Créer un utilisateur appartenant à un groupe existant.
 Le login de l'utilisateur ne doit pas exister en double sur la base de données.
- REST route : `POST /user`
- input :

 ```json 
  {
  "login" : "aLogin", 
   "password" : "aPassword",  
   "group" :"group"
   }
```
- output :

```json 
{
 "id" : 555,
  "login" : "aLogin",
  "group": "aGroup"
  }
```
### 6. Création de compte : Groupe KO
- Description : Même que scénario 5 avec un groupe inexistant.
- REST route : `POST /user`
- input :

 ```json 
  {
  "login" : "aLogin", 
   "password" : "aPassword",  
   "group" :"invalidGroup"
   }
```
- output :

```json 
{
 "error" : "Group (group in input) is not valid",
  }
```
### 7. Création de compte : Login Ko
- Description : Même que scénario 5 avec un login en double sur la base.
- REST route : `POST /user`
- input :

 ```json 
  {
  "login" : "invalidLogin", 
   "password" : "aPassword",  
   "group" :"aGroup"
   }
```
- output :

```json 
{
 "error" : "Login (login in input) is not valid",
  }
```

### 8. Suppression de compte

- REST route : `DELETE /user/{login}`
- input :

 ```json 
 PathVariable : login = login de l'utilisateur à supprimer
```
- output :

Si `{login}` existe alors afficher un message de succès : 

```json 
{
 "success" : "Login (login in input) is deleted",
  }
```
Si `{login}` n'existe pas alors afficher un message d'erreur :

```json 
{
 "error" : "Login (login in input) is not found",
  }
```

### Bonus 1 : Valdiation des données
- `password` et `passwordConfirmation` doivent être identiques lors de l'ajout
 ```json 
  {
  "login" : "invalidLogin", 
   "password" : "aPassword",  
   "passwordConfirmation" :"confirmation"
   "group" :"aGroup"
   }
```
- les logins doivent respecter l'expression régulière suivante :
``` regexp
^[a-z][a-zA-Z_0-9]{3,7}$
```

### Bonus 2 : Authentifier un admin pour effectuer la suppression
 La suppression se fait par le compte d'un admin. Pour l'authentifier,
 ses informations d'authentification seront passées par le HEADER de la requête, 
Ensuite, elles seront récupérées avant la suppression, et on vérifie si ces informations 
appartiennent à un utilisateur sur le groupe `Admin`



