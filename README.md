# ClockWise
Projet d'application pour un système de gestion de temps pour une entreprise



## L'API

### Accés non sécurisé :

#### 1 Login
Permet d'identifier l'utilisateur, s'il existe en base de donnée, un token (JWT) sera renvoyé.
##### - Endpoint -
```
POST @ http://host:8000/cwise/api/v2/user/login
```
##### - Requête body -
```
{
    "email":"exemple@domain.fr",
    "password":"password"
}
```
#### - Réponse '200 OK' body -
```
{
    "message":"Success",
    "data": {
        "token": "generatedToken
    }
}
```

#### 2 Init
Permet de créer un utilisateur ayant role 'ADMIN', n'est fonctionnel que dans le cas où aucun utilisateur 'ADMIN' n'est trouvé dans la base de donnée.

##### - Endpoint -
```
GET @ http://host:8000/cwise/api/v2/user/init
```

#### - Réponse '200 OK' -
Aucun ADMIN en base de donnée
```
'ok'
```

#### - Réponse '401 UNauthorized'
Il existe un 'Admin' en base de donnée

#### - Variable d'environnement à définir -
```
API_ROOT_MAIL=root@mail.fr
API_ROOT_PASSWORD=admin

```

#### Roadmap

##### rénititialisation du mot de passe
Les utilisateur doivent pourvoir demander un lien en case d'oublis de leur mot de passe.


### Accès administrateur
 Tous les endpoints de cette section necessitent une ***Authorization*** par 'Bearer token' ayant un role ***ADMIN***

### 1 créer un utilisateur
Ajouter un utilisateur (ADMIN ou EMPLOYEE)

##### - Endpoint -
```
Post @ http://host:8000/cwise/api/v2/admin/new
```

##### - Requête body -
```
{
    "email":"exemple@domain.fr",
    "password":"password",
    "role":"ADMIN"
}
```
##### variables
- email (String), actuellement aucun formatage n'est imposé sur l'email

##### valeurs acceptées pour créer un adminitrateur
```
"ADMIN" "admin" "ROLE_ADMIN"
```

##### valeur acceptées pour créer un employé
Il existe un endpoint dédié à la création d'employé prenant en compte les informations complémentaires.
```
"EMPLOYEE" "employee" "ROLE_EMPLOYEE"
```

#### - Réponse '200 OK' body -
```
{
    "message": "Success",
    "data": null
}
```

### 2 - créer un employee
Ajouter spécifiquement un utilisateur de type employee

##### - Endpoint -
```
Post @ http://host:8000/cwise/api/v2/admin/employee
```

##### - Requête body -
```
{
    "email":"exemple@domain.fr",
    "password":"password",
    "firstname":"prenom",
    "lastname":"nom",
    "weekWorkingHour":35.5
}
```

##### Nombre d'heure de travail dans le semaine.
- weekWorkingHour' (type float), indique le nombre d'heure qui doit être effectuée par l'employé par semaine. La référence est utilisé pour le calcul des heures supplémentaires


#### - Réponse '200 OK' body -
```
employee created
```

### 2 - récupéer la liste des employees
Ajouter spécifiquement un utilisateur de type employee

##### - Endpoint -
```
GET @ http://host:8000/cwise/api/v2/admin/employees
```

#### - Réponse '200 OK' body -
```
[
    {
        "id": 4,
        "email": "user1@mail.fr",
        "password": null,
        "role": "ROLE_EMPLOYEE",
        "firstname": "prenom",
        "lastname": "nom",
        "weekWorkingHour": 35.5
    },
    {
        "id": 5,
        "email": "user@mail.fr",
        "password": null,
        "role": "ROLE_EMPLOYEE",
        "firstname": "prenom",
        "lastname": "nom",
        "weekWorkingHour": 35.5
    }
]
```
###### variables
- "id" (type Long)
- "passord" (null) le mot de passe ne peut être récupéré


### 3 - récupéer les détails d'un employée
Permet de récupérer les informations spécifique d'un empployé, nottament la liste des pointages effectués

##### - Endpoint -
```
Get @ http://host:8000/cwise/api/v2/admin/employee/{id}
```

#### - Réponse '200 OK' body -
```
{
    "message": "success",
    "data": {
        "messages": [],
        "employee": {
            "id": 5,
            "email": "user@email.fr",
            "password": null,
            "role": "ROLE_EMPLOYEE",
            "firstname": "prenom",
            "lastname": "nom",
            "weekWorkingHour": 35.5
        },
        "timeStamps": [
            {
                "id": 1,
                "startStamp": 1714036191403,
                "endStamp": 1714036198025,
                "employee": null,
                "milliDuration": 6622
            },
            {
                "id": 2,
                "startStamp": 1714036204371,
                "endStamp": 1714036395423,
                "employee": null,
                "milliDuration": 191052
            }
        ]
    }
}
```

###### variables
- *message* (non implémenté), il est prévu que l'utilisateur puisse envoyer les messages en base de donnée, *la fonctionnalité n'est pas encore implémentée*
- *timesStamps* : liste des pintages effectués par l'employé
- *startStamp* (Long) valeur Unix time correspondant à l'instant où l'employé a pointé en début de période, ne peut être null
- *endStamp* (Long) valeur Unix time correspondant à l'instant où l'employé a pointé en fin de période, sera *null* si l'utilsateur est au travail.
- *milliDuration* (Long), valeur calculée de la durée entre le début et la fin du pointage.
###### *roadmap* : 
- préciser des informations 
- regrouper les timeStamps par journée / semaine 
- évaluer avec le nombre d'heure à effectuer par semaine, et indiquer les heures supplémentaires.

### 4 - fonctionnalités à implémenter

- modifier les détails d'un employé
- supprimer un employé

- selectionner les pointages d'un employé sur une période de temps
- modifier un timeStamp
- supprimer un timeStamp


### Accès employé
 Tous les endpoints de cette section necessitent une ***Authorization*** par 'Bearer token' ayant un role ***EMPLOYEE***

### 1 - pointer
utiliser pour pointer, que ce soit en début ou en fin de période

##### - Endpoint -
```
Get @ http://host:8000/cwise/api/v2/stamp
```

#### - Réponse '200 OK' body -
```
{
    "startStampMillis": 1714040450471,
    "endStampMillis": null,
    "startStampInstant": "2024-04-25T10:20:50.471Z",
    "endStampInstant": null,
    "startStampStr": "Thu Apr 25 10:20:50 UTC 2024",
    "endStampStr": null,
    "startStampDate": "2024-04-25T10:20:50.471+00:00",
    "endStampDate": null,
    "durationMin": 154
}
```

#### variables
La réponse renvoie le dernier timeStamp en base de données
- *startStamp** : début de période de pointage dans différents formats, ne peut être null
- *endStamp** : fin de période de pointage, sera *null* en période de travail
- *durationMin* (int) : durée de la période d'activité (calculé à partir de l'instant présent si *endStamp* est null)


### 2 - récupére le statut
utiliser pour récupérer des informations utiles.

##### - Endpoint -
```
Get @ http://host:8000/cwise/api/v2/statut
```

#### - Réponse '200 OK' body -
```
{
    "message": "Success",
    "data": {
        "lastTimeStamp": {
            "startStampMillis": 1714045486635,
            "endStampMillis": 0,
            "startStampInstant": "2024-04-25T11:44:46.635Z",
            "endStampInstant": "1970-01-01T00:00:00Z",
            "startStampStr": "Thu Apr 25 11:44:46 UTC 2024",
            "endStampStr": "Thu Jan 01 00:00:00 UTC 1970",
            "startStampDate": "2024-04-25T11:44:46.635+00:00",
            "endStampDate": "1970-01-01T00:00:00.000+00:00",
            "durationMin": 0
        },
        "firstname": "prenom",
        "weekWorkingHour": 35.5,
        "isWorking": true,
        "lastname": "nom"
    }
}
```

#### variables
- *isWorking* (boolean) : durée de la période d'activité (calculé à partir de l'instant présent si *endStamp* est null)



### 3 - récupére la liste des timeStamps
utiliser par l'utilisateur pour récupérer les timeStamps 

##### - Endpoint -
```
Get @ http://host:8000/cwise/api/v2/timestamps
```

#### - Réponse '200 OK' body -
```
[
       {
        "startStampMillis": 1714036204371,
        "endStampMillis": 1714036395423,
        "startStampInstant": "2024-04-25T09:10:04.371Z",
        "endStampInstant": "2024-04-25T09:13:15.423Z",
        "startStampStr": "Thu Apr 25 09:10:04 UTC 2024",
        "endStampStr": "Thu Apr 25 09:13:15 UTC 2024",
        "startStampDate": "2024-04-25T09:10:04.371+00:00",
        "endStampDate": "2024-04-25T09:13:15.423+00:00",
        "durationMin": 3
    },
    {
        "startStampMillis": 1714036885112,
        "endStampMillis": 1714038853788,
        "startStampInstant": "2024-04-25T09:21:25.112Z",
        "endStampInstant": "2024-04-25T09:54:13.788Z",
        "startStampStr": "Thu Apr 25 09:21:25 UTC 2024",
        "endStampStr": "Thu Apr 25 09:54:13 UTC 2024",
        "startStampDate": "2024-04-25T09:21:25.112+00:00",
        "endStampDate": "2024-04-25T09:54:13.788+00:00",
        "durationMin": 32
    }
]
```





