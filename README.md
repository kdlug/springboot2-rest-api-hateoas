# HATEOAS

## Rest api representation

Single resource

```json
{
  "name": "Joe",
  "lastname": "Nonamassa",
  "email": "joe.bonamassa@gmail.com",
}
```

Resource list

```json
{
  "customers": [
      {
        "name": "Joe",
        "lastname": "Bonamassa",
        "email": "joe.bonamassa@gmail.com"
      },
      {
        "name": "Joe",
        "lastname": "Bonamassa",
        "email": "joe.bonamassa@gmail.com"
      }
  ]
}
```

## HAL representation

HAL representation contains hypermedia links.

Single resource

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

## Resource

Resource class is a DAO class which extends ResourceSupport class. ResourceSupport adds `_links` to a resource.

```
public class CustomerResource extends ResourceSupport {
    private String name;
    private String lastname;
    private String email;

    public String getFullName() {
        return name + " " + lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

It's important that getter method names determines names of fields in json output. F.ex. field `fullName` does not exist in CustomerResource, but it's rendered in json output, because there is a getter called `getFullName()`.


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
