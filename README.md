# CQRS - Event Sourcing
## 1. Développements du Côté Commande (Command Side)
## Add Account
#### Contrôleur de commande : AccountCommandController injectant CommandGateway pour expédier les commandes (Send).
![img.png](img.png)
#### Agrégat (AccountAggregate) : on a un id en response
![img_1.png](img_1.png)
#### Event Store
![img_2.png](img_2.png)
#### Test avec PostgreSql
![img_3.png](img_3.png)
#### Recuperer un event depuis event store
![img_4.png](img_4.png)

## Credit Account
![img_5.png](img_5.png)
![img_6.png](img_6.png)
## Debit Account & Update Status
![img_7.png](img_7.png)
![img_8.png](img_8.png)

## 2. Développements du Côté Query (Query Side)
### création des eventHandlers : 
![img_9.png](img_9.png)
![img_10.png](img_10.png)

### Query Handler
#### Query getAll 
![img_11.png](img_11.png)
#### Query get statement
![img_12.png](img_12.png)


## 3. Streaming des événements en temps réel (SSE)
![img_13.png](img_13.png)
![img_14.png](img_14.png)