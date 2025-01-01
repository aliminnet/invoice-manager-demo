# Invoice Manager App

##
Build the jar file
```shell
mvn clean install
```

## Running the app
**_Option 1:_** Run with jar file
```shell
java -jar target/invoice-manager-0.0.1-SNAPSHOT.jar
```

**_Option 2:_** Run with docker

Build the docker image
```shell
docker build -t invoice-manager-app .
```
Run the docker image
```shell
docker run -p 8080:8080 invoice-manager-app
```

After running the app, you can access the swagger at http://localhost:8080/swagger-ui/index.html

Or you can import Postman export: file://InvoiceManagerApp.postman_collection.json

## cURL commands
**_Login and get the token:_**
```shell
curl --location 'http://localhost:8080/v1/auth/login' \
--header 'Content-Type: application/json' \
--header 'Accept: */*' \
--header 'Cookie: JSESSIONID=F82067AB4DCCB593ABC45B100374261F' \
--data-raw '{
"email": "johndoe@doe.com",
"password": "password1"
}'
```

**_Create an invoice:_**
Get the token from the login response and replace {{token}} with the token or use the "admin" token
```shell
curl --location 'http://localhost:8080/v1/invoice' \
--header 'Content-Type: application/json' \
--header 'Accept: */*' \
--header 'Authorization: Bearer {{token}}' \
--header 'Cookie: JSESSIONID=F82067AB4DCCB593ABC45B100374261F' \
--data '{
  "firstName": "json",
  "lastName": "data",
  "email": "johndoe@doe.com",
  "amount": 100.0,
  "billNo": "TR001"
}'
```

**_Create a product:_**
Get the token from the login response and replace {{token}} with the token or use the "admin" token
```shell
curl --location 'http://localhost:8080/v1/product' \
--header 'Content-Type: application/json' \
--header 'Accept: */*' \
--header 'Authorization: Bearer {{token}}' \
--header 'Cookie: JSESSIONID=F82067AB4DCCB593ABC45B100374261F' \
--data '{
  "name": "Product1"
}'
```

**_Get products:_**
Get the token from the login response and replace {{token}} with the token or use the "admin" token
```shell
curl --location 'http://localhost:8080/v1/product' \
--header 'Accept: */*' \
--header 'Authorization: Bearer {{token}}' \
--header 'Cookie: JSESSIONID=F82067AB4DCCB593ABC45B100374261F'
```

**Query all activities:**
```shell
curl --location 'http://localhost:8080/v1/activity' \
--header 'Accept: */*' \
--header 'Authorization: Bearer {{token}}' \
--header 'Cookie: JSESSIONID=DD8B0CB8EC9A9F3820C92C7C71FBAB79'
```

**Query activities by status:**
```shell
curl --location 'http://localhost:8080/v1/activity/status/SUCCESS' \
--header 'Accept: */*' \
--header 'Authorization: Bearer {{token}}' \
--header 'Cookie: JSESSIONID=DD8B0CB8EC9A9F3820C92C7C71FBAB79'
```

You can find other api endpoints in the swagger documentation or postman collection.