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
