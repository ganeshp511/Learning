# 📘 Roadmap: Learning REST API Development with Spring Boot

---

## 1. **REST API Basics**

* What is REST? (principles, statelessness, client-server, layered system, uniform interface)
* HTTP basics for REST:

  * Methods: `GET`, `POST`, `PUT`, `PATCH`, `DELETE`
  * Status Codes (2xx, 4xx, 5xx)
  * Headers (`Content-Type`, `Accept`, `Authorization`, etc.)
* JSON as the common response format
* Designing **resources & URIs** (nouns vs verbs, plural vs singular)

---

## 2. **Building REST APIs with Spring**

* `@RestController` and `@RequestMapping`
* `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@PatchMapping`
* Returning JSON with `ResponseEntity`
* Path Variables vs Query Parameters
* Request Body (`@RequestBody`) and Validation (`@Valid`)
* Exception Handling (`@ControllerAdvice`, `@ExceptionHandler`)

---

## 3. **Data & Persistence**

* Integrating with a database (Spring Data JPA or MongoDB)
* Mapping DTOs vs Entities
* Using ModelMapper or MapStruct for object conversions
* Pagination & Sorting with `Pageable`
* Filtering results

---

## 4. **REST API Best Practices**

* Consistent **response structure** (e.g., wrapping responses)
* Proper use of HTTP status codes
* Idempotency in `PUT` & `DELETE`
* Error handling format (RFC 7807 Problem Details or custom error object)
* Versioning REST APIs (`/v1/...`, headers, or content negotiation)
* Naming conventions

---

## 5. **Advanced REST API Concepts**

* **Validation**: JSR-380 (Hibernate Validator)
* **HATEOAS**: Adding links to responses
* **Content Negotiation**: Supporting JSON, XML
* **File Upload/Download** in REST
* **Streaming APIs** (returning large data efficiently)
* Rate Limiting & Throttling

---

## 6. **Security in REST APIs**

* Authentication:

  * Basic Auth
  * JWT (JSON Web Tokens)
  * OAuth2/OpenID Connect
* Authorization (RBAC, permissions)
* Securing endpoints with Spring Security
* CORS (Cross-Origin Resource Sharing)

---

## 7. **Testing REST APIs**

* Unit Testing Controllers (with `MockMvc`)
* Integration Testing with `@SpringBootTest`
* Using **Testcontainers** for DB integration tests
* API testing tools (Postman, Insomnia, REST Assured)

---

## 8. **Documentation & API Discovery**

* Swagger / OpenAPI (`springdoc-openapi` or `Springfox`)
* Generating API docs automatically
* Writing clear API contracts
* API versioning strategies & backward compatibility

---

## 1. **REST API Basics**

# 0) Core vocabulary (used everywhere below)

* **API**: A way for programs to talk to each other.
* **HTTP**: The protocol (set of rules) the web uses to send requests and responses.
* **Client**: The side that *sends a request* (e.g., browser, mobile app, another service).
* **Server**: The side that *receives the request* and returns a response.
* **Resource**: A thing your API exposes (e.g., a `customer`, an `order`).
* **Representation**: How a resource is serialized on the wire (often JSON).
* **URI**: The address of a resource on the web (e.g., `/customers/42`).
* **Header**: Key–value metadata sent with requests/responses (e.g., `Content-Type: application/json`).
* **Body**: The actual content/payload of a request or response (e.g., a JSON document).

---

# 1) What is REST?

**REST (Representational State Transfer)** is an architectural style for building web APIs on top of HTTP. It isn’t a library or a framework; it’s a set of constraints that, when followed, produce simple, scalable, cache-friendly web services.

### Key REST principles (constraints)

1. **Client–Server**
   The client (caller) and server (API) evolve independently. The client only needs to know *the contract* (URIs, methods, formats), not server internals.

2. **Statelessness**
   Each request contains everything needed to process it. The server does **not** remember client session state between requests.

   * Example: send the auth token on every request; don’t rely on server session memory.

3. **Uniform Interface**
   A consistent way to interact with resources:

   * **Identify resources** with URIs (e.g., `/orders/123`).
   * **Manipulate via representations** (send JSON to update an order).
   * **Self-descriptive messages** (status codes + headers + body tell you what happened).
   * **Hypermedia (links) when useful** (responses can include URLs to related actions/resources).

4. **Layered System**
   You can insert layers (load balancers, caches, gateways) without changing client–server interaction.

(There are other optional ideas like cacheability and code-on-demand, but the above are the essentials.)

---

# 2) HTTP basics for REST

HTTP is request/response based. A request has a **method**, **URI**, **headers**, and optionally a **body**. A response has a **status code**, **headers**, and optionally a **body**.

## 2.1 Methods (the verbs)

> A **method** expresses *what you want to do* to a resource identified by a URI.

* **GET** – Fetch a resource (read only).

  * *Safe* (doesn’t change state) and *idempotent* (calling it many times is same as once).

* **POST** – Create a new resource *under the target URI* or trigger a server-side process.

  * *Not idempotent* (posting twice may create two things).
  * Typical: `POST /orders` with order JSON ⇒ returns `201 Created`.

* **PUT** – Replace a resource *entirely* at the target URI (or create it at that URI if allowed).

  * *Idempotent* (same request repeated leaves the same result).
  * Typical: `PUT /customers/42` with the full customer document.

* **PATCH** – Partially update a resource.

  * Not guaranteed idempotent (depends on patch format), but commonly used for partial changes.
  * Typical: `PATCH /customers/42` with `{ "email": "new@x.com" }`.

* **DELETE** – Remove a resource.

  * *Idempotent*: deleting the same thing twice still results in “it’s gone”.

> **Idempotent** means: doing the same request once or many times leaves the server in the same state.

### Choosing methods—quick rules

* Create → **POST** to the collection (`POST /orders`).
* Read → **GET** resource (`GET /orders/123`) or collection (`GET /orders?status=PAID`).
* Replace whole resource → **PUT** (`PUT /orders/123`).
* Update part of a resource → **PATCH** (`PATCH /orders/123`).
* Delete → **DELETE** (`DELETE /orders/123`).

## 2.2 Status codes (what happened?)

A **status code** is a 3-digit number in the response telling you the outcome.

* **2xx – Success**

  * **200 OK**: Request succeeded, response has a body.
  * **201 Created**: New resource created; include a `Location` header pointing to it.
  * **202 Accepted**: Request accepted for async processing (result later).
  * **204 No Content**: Success but no body (common for `DELETE`, sometimes `PUT/PATCH`).

* **4xx – Client errors** (the request had a problem)

  * **400 Bad Request**: Malformed request (e.g., invalid JSON or missing fields).
  * **401 Unauthorized**: No valid authentication credentials supplied.
  * **403 Forbidden**: Authenticated, but not allowed to do this.
  * **404 Not Found**: Resource/route doesn’t exist.
  * **409 Conflict**: Conflicting state (e.g., duplicate unique field).
  * **422 Unprocessable Entity**: Semantically invalid data (validation failed).

* **5xx – Server errors** (the server failed)

  * **500 Internal Server Error**: Unexpected error.
  * **503 Service Unavailable**: Temporary outage or overloaded.

## 2.3 Headers (metadata that guides behavior)

A **header** is a key–value pair that modifies how the request/response should be interpreted.

* **`Content-Type`** – Format of the body you’re sending/returning.

  * For JSON: `Content-Type: application/json`.

* **`Accept`** – What response formats the client will accept.

  * Example: `Accept: application/json`.

* **`Authorization`** – Credentials to authenticate the client.

  * Example: `Authorization: Bearer <JWT_TOKEN>`.

Other useful ones you’ll meet soon:

* **`Location`** – URI of a newly created resource (with `201 Created`).
* **`Cache-Control`**, **`ETag`**, **`If-None-Match`** – HTTP caching/conditional requests.
* **`Correlation-Id`/`Traceparent`** – Request tracing across services.

### Example HTTP exchange

**Request**

```
POST /customers HTTP/1.1
Host: api.example.com
Content-Type: application/json
Accept: application/json
Authorization: Bearer <token>

{
  "name": "Aisha Khan",
  "email": "aisha@example.com"
}
```

**Response**

```
HTTP/1.1 201 Created
Location: /customers/42
Content-Type: application/json

{
  "id": 42,
  "name": "Aisha Khan",
  "email": "aisha@example.com"
}
```

---

# 3) JSON as the common response format

**JSON (JavaScript Object Notation)** is a lightweight data format used to serialize objects into text. It’s human-readable and language-neutral.

* **Data types**: object `{}`, array `[]`, string, number, boolean, null.
* **Why popular**: concise, easy for clients/servers to parse, maps well to most languages.
* **Tell the world it’s JSON**: use `Content-Type: application/json`.

### JSON example

```json
{
  "id": 123,
  "name": "Laptop Stand",
  "price": 19.99,
  "inStock": true,
  "tags": ["ergonomic", "aluminum"]
}
```

---

# 4) Designing resources & URIs

We now know: a **resource** is a thing; a **URI** is its address; a **method** is what you want to do. Good design makes your API predictable.

## 4.1 Nouns vs. verbs

Use **nouns** in paths (resources), and use **HTTP methods** for actions.

* ✅ `GET /customers/42` (noun in path, method says “read”)
* ❌ `GET /getCustomerById?id=42` (verb in path duplicates method’s job)

## 4.2 Plural vs. singular

Use **plural** nouns for collections, **singular** for specific resources.

* **Collection**: `/customers`
* **Item**: `/customers/42`

## 4.3 Hierarchies & relationships

Express containment/ownership hierarchically:

* **Orders of a customer**:

  * Collection: `/customers/42/orders`
  * Item: `/customers/42/orders/7`

Use **query parameters** for filtering/sorting, not path segments:

* `/orders?status=PAID&sort=-createdAt&page=2&size=20`

## 4.4 When you need “actions”

Sometimes you need an action that isn’t a plain CRUD operation (e.g., “cancel an order”). Prefer modeling it as a **sub-resource** or a **state change**, still keeping nouns:

* ✅ `POST /orders/123/cancellation`
* ✅ `PATCH /orders/123` with `{ "status": "CANCELLED" }`
* ❌ `POST /orders/123/cancelOrderNow` (verb-y, RPC-like)

## 4.5 Consistency tips

* **Case & separators**: lowercase + hyphens for readability: `/credit-cards`.
* **No trailing slash variance**: pick one style; commonly **no trailing slash** for items: `/customers/42`.
* **Stable IDs**: Use opaque IDs in URIs (avoid leaking DB internals).
* **Pagination**: `page`, `size`; return `totalElements`, `totalPages` in the body (and/or Link headers).
* **Discoverability**: Include links where helpful:

  ```json
  {
    "id": 42,
    "name": "Aisha Khan",
    "_links": {
      "self": { "href": "/customers/42" },
      "orders": { "href": "/customers/42/orders" }
    }
  }
  ```

## 4.6 Status codes + headers that pair well with design

* **Create**: `POST /customers` → **201 Created** + `Location: /customers/42`.
* **Update**: `PUT/PATCH /customers/42` → **200 OK** (with body) or **204 No Content**.
* **Delete**: `DELETE /customers/42` → **204 No Content**.
* **Conditional GET**: client sends `If-None-Match` with an **ETag**; server can reply **304 Not Modified**.

---

## Quick checklist you can apply to any endpoint

* Path uses **nouns** (plural for collections).
* Operation chosen by **HTTP method**, not verbs in the path.
* Clear **status codes** and useful **headers** (`Location`, `ETag`, etc.).
* Payloads are **JSON** with `Content-Type: application/json`.
* Supports **filtering/pagination** via query params.
* Easy to **discover** related resources via links when it helps.

---

Perfect 👍 Let’s build both:

---

# 📝 REST API One-Page Cheat Sheet

## 🌍 REST Principles

* **Client–Server**: UI (client) and data logic (server) are separate.
* **Stateless**: Each request has all info needed (no server memory of sessions).
* **Uniform Interface**: Same consistent rules everywhere.
* **Layered System**: Can insert proxies, caches, gateways without breaking.

---

## ⚡ HTTP Methods

* **GET** – Read data. Safe, idempotent.
* **POST** – Create new resource. Not idempotent.
* **PUT** – Replace full resource. Idempotent.
* **PATCH** – Partially update resource. Usually idempotent.
* **DELETE** – Remove resource. Idempotent.

---

## ✅ Status Codes

* **200 OK** – Success with body
* **201 Created** – Resource created (`Location` header → new URI)
* **202 Accepted** – Request accepted, processing later
* **204 No Content** – Success, no body
* **400 Bad Request** – Invalid request format
* **401 Unauthorized** – No/invalid credentials
* **403 Forbidden** – Authenticated but no permission
* **404 Not Found** – Resource doesn’t exist
* **409 Conflict** – State conflict (e.g., duplicate)
* **500 Internal Server Error** – Unexpected error

---

## 📦 Headers

* `Content-Type`: Format of request/response body (`application/json`).
* `Accept`: Client’s expected format.
* `Authorization`: Auth info (e.g., `Bearer <JWT>`).
* `Location`: New resource URI after creation.
* `Cache-Control`, `ETag`: Caching and conditional requests.

---

## 🧾 JSON Format

```json
{
  "id": 123,
  "name": "Laptop Stand",
  "price": 19.99,
  "inStock": true
}
```

---

## 🛣️ Resource & URI Design

* Use **nouns**, not verbs.
* Collections: plural → `/customers`
* Item: singular resource → `/customers/42`
* Relationships: `/customers/42/orders`
* Filtering & pagination → `/orders?status=PAID&page=2&size=10`
* Sub-actions as resources → `POST /orders/42/cancellation`

---

## 🔑 Best Practices

* Always use proper **status codes**.
* Use **plural nouns** for collections.
* Provide **pagination** & filtering for lists.
* Return `Location` header after creation.
* Document API with **Swagger/OpenAPI**.
* Secure with **HTTPS** + tokens (JWT/OAuth2).

---

---

# 🎯 Practice Project: “E-Commerce REST API”

You will build an API for a **simple e-commerce system**.

## Entities

* **Products**
* **Customers**
* **Orders**

---

### 1. Products

* `GET /products` → list all products (support pagination, filtering by category).
* `POST /products` → create a product.
* `GET /products/{id}` → get one product.
* `PUT /products/{id}` → replace product.
* `PATCH /products/{id}` → update part of a product.
* `DELETE /products/{id}` → delete product.

---

### 2. Customers

* `GET /customers` → list all customers.
* `POST /customers` → create new customer.
* `GET /customers/{id}` → get customer by ID.
* `PATCH /customers/{id}` → update email/phone.
* `DELETE /customers/{id}` → delete customer.

---

### 3. Orders

* `POST /orders` → create order for a customer.
* `GET /orders/{id}` → fetch order.
* `GET /customers/{id}/orders` → all orders for customer.
* `PATCH /orders/{id}` → update status (e.g., `SHIPPED`).
* `POST /orders/{id}/cancellation` → cancel order.

---

### Example: Create a customer

**Request**

```
POST /customers
Content-Type: application/json

{
  "name": "Aisha Khan",
  "email": "aisha@example.com"
}
```

**Response**

```
201 Created
Location: /customers/42

{
  "id": 42,
  "name": "Aisha Khan",
  "email": "aisha@example.com"
}
```

---

👉 If you build this, you’ll practice:

* All **HTTP methods**
* Proper **status codes**
* JSON request/response design
* **URI conventions**
* Handling relationships (`/customers/{id}/orders`)
* Sub-resource actions (`/orders/{id}/cancellation`)

---

