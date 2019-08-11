# HATEOAS

HATEOAS (Hypermedia as the Engine of Application State) is a constraint of the REST application architecture. A hypermedia driven REST API provides information to help to navigate through the API dynamically. This is done by passing hypermedia links with the responses.HATEOAS is a fundamental concept to create Discoverable REST APIs.

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

## Dependency

```groovy
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-hateoas")

}
```

## Resource

Resource class is a DAO class which extends base class `ResourceSupport` class available as part of the Spring support for HATEOAS. It allows us to add instances of Link that is useful while creating the `_links` element.

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
The ResourceSupport class provides add() method for link building. 
It's important that getter method names determines names of fields in json output. F.ex. field `fullName` does not exist in CustomerResource, but it's rendered in json output, because there is a getter called `getFullName()`.


## Changing Relation name

_embedded field name `customerResourceList` is auto-generated. We can change it using `@Relation` annotation.

```java
@Relation(value = "customer", collectionRelation = "customers")
public class CustomerResource extends ResourceSupport {
    ...
}
```

## Embedding 

Single CustomerResource with embedded notes under link: `/api/v1/customers/1?embedded=notes`

```json
{
    "name": "Joe",
    "lastname": "Bonamassa",
    "email": "joe.bonamassa@gmail.com",
    "_embedded": {
        "notes": [
            {
                "customer_id": 1,
                "created_at": "2019-08-11T11:58:28.008+0000",
                "note": "Note 1",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/v1/notes/1"
                    }
                }
            },
            {
                "customer_id": 1,
                "created_at": "2019-08-11T11:58:28.008+0000",
                "note": "Note 2",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/v1/notes/2"
                    }
                }
            },
            {
                "customer_id": 1,
                "created_at": "2019-08-11T11:58:28.008+0000",
                "note": "Note 3",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/v1/notes/3"
                    }
                }
            }
        ]
    },
    "full_name": "Joe Bonamassa",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/v1/customers/1"
        },
        "customer_notes": {
            "href": "http://localhost:8080/api/v1/customers/1/notes"
        },
        "customers": {
            "href": "http://localhost:8080/api/v1/customers"
        }
    }
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
