# HATEOAS

## HAL representation

HAL representation contains hypermedia links.

giSingle resource

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

Resource list

```json
{
  "_embedded": {
    "customerResourceList": [
      {
        "name": "Joe",
        "lastname": "Bonamassa",
        "email": "joe.bonamassa@gmail.com",
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/v1/customer/1"
          }
        }
      },
      {
        "name": "Joe",
        "lastname": "Bonamassa",
        "email": "joe.bonamassa@gmail.com",
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/v1/customer/2"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/v1/customer"
    }
  }
}
```

## TODO

- Change `_embedded` field name from `customerResourceList` to `customers`
- Move creating self link for customers list outside controller
- Add pagination
- Add sorting/filtering
- Try with more embedded resources
