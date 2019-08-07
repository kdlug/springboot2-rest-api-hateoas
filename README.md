# HATEOAS

## HAL representation

HAL representation contains hypermedia links:

```json
{
  "name": "Joe",
  "lastname": "Nonamassa",
  "email": "joe.bonamassa@gmail.com",
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/v1/customer/1"
    }
  }
}
```