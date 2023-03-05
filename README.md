# Bank Management System
***
## Description
A simplified bank management system API created using java spring boot framework and postgresql database. The API is used to create, read, update and delete bank accounts and transactions.
### Data Structure
The data structure is as follows:
* Manager
    * id
    * name
    * email
    * password
    * workers
* Worker
    * id
    * name
    * email
    * password
    * managerId
* Client
    * id
    * name
    * email
    * password
    * workerId
* Transaction
    * id
    * amount
    * senderId
    * receiverId
    * type
    * date
### Features
* Create, read, update and delete managers, workers and clients
* Assign workers to managers and clients to workers
* Make transactions between clients
* Withdraw and deposit money
* Get all transactions of a client

### Endpoints
* Manager
    * GET /api/managers
    * GET /api/managers/{id}
    * POST /api/managers
    * PUT /api/managers/{id}
    * DELETE /api/managers/{id}
    * PUT /api/managers/{managerId}/assign_worker/{workerId}

* Worker
    * GET /api/workers
    * GET /api/workers/{id}
    * POST /api/workers
    * PUT /api/workers/{id}
    * DELETE /api/workers/{id}
    * PUT /api/workers/{workerId}/assign_client/{clientId}

* Client
    * GET /api/clients
    * GET /api/clients/{id}
    * POST /api/clients
    * PUT /api/clients/{id}
    * DELETE /api/clients/{id}
    * PUT /api/clients/{clientId}/deposit?amount={amount}
    * PUT /api/clients/{clientId}/withdraw?amount={amount}
    * PUT /api/clients/{senderId}/transfer?receiverId={receiverId}&amount={amount}
    * GET /api/clients/{clientId}/transactions
    * GET /api/clients/{clientId}/outgoing-transactions
    * GET /api/clients/{clientId}/incoming-transactions
* Transaction
    * GET /api/transactions
    * GET /api/transactions/{id}

## Installation
### Requirements
* Java installed
* A running postgresql database
* Postman or any other API testing tool
### Steps
1. Clone the repository
2. Open the project in your IDE
3. Open the application.properties file and change the database url, username and password to your database credentials
4. Run the project
5. Open Postman and test the endpoints
### Helpers
Use the file [thunder-collection_BMS.json] in the VSCode Thunder Client extension to test the endpoints.

## Notes
This project is just a very simplified showcase on how to create a REST API using java spring boot framework and postgresql database. It does not have any authentication or authorization. It is not meant to be used in production.
It is just a showcase of how to create a REST API using java spring boot framework and postgresql database. It does not have any authentication or authorization and lacks proper error handling.
Use this project just as a reference on how to create a REST API using java spring boot framework and postgresql database.

## License
[MIT](https://choosealicense.com/licenses/mit/)

