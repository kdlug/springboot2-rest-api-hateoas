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

## Changing Relation name

_embedded field name `customerResourceList` is auto-generated. We can change it using `@Relation` annotation.

```java
@Relation(value = "customer", collectionRelation = "customers")
public class CustomerResource extends ResourceSupport {
    ...
}
```

## Naming strategy

We can either configure the whole application to expect the snake case input by adding below line in application.properties

```console
spring.jackson.property-naming-strategy=SNAKE_CASE
```

Or you can add below annotation to model class if you want only specific model to accept and produce snake case JSON keys

```java
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomerResource {
    ...
}
```

### camelCase

```json
{
    "name": "Joe",
    "lastname": "Bonamassa",
    "email": "joe.bonamassa@gmail.com",
    "fullName": "Joe Bonamassa",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/v1/customer/1"
        }
    }
}

```

### snake_case

```json
{
    "name": "Joe",
    "lastname": "Bonamassa",
    "email": "joe.bonamassa@gmail.com",
    "full_name": "Joe Bonamassa",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/v1/customer/1"
        }
    }
}
```



## TODO

- [x] Change `_embedded` field name from `customerResourceList` to `customers`
- [x] Add snake_case strategy for output json
- [ ] Move creating self link for customers list outside controller
- [ ] Add pagination
- [ ] Add sorting/filtering
- [ ] Try with more embedded resources
