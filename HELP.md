
# Documentation

Application starts at port 8220

### To start the application you need database at first (don't forget to specify volume):

docker run --rm --name localhost-db -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=pizzeria -d -p 5434:5432 -v C:\Users\KorzunMA\.docker\volumes\postgres:/var/lib/postgresql/data postgres

### API (Postman collection in Pizzeria.postman_collection.json):

POST localhost:8220/pizzeria/api/v1/toppings
request body:
{
"email": "my@email.com",
"toppings": [
"topping1",
"topping2",
"topping4"
]
}

GET localhost:8220/pizzeria/api/v1/toppings/rating
