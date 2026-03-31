# Software Requirements & Endpoints (SRE)
## Flopkart — Spring Boot Backend

**Frontend**: Angular 21 (this repo)  
**Backend**: Spring Boot 3.x  
**Database**: MySQL (local)  
**Auth**: JWT (Bearer tokens)  
**Base URL**: `http://localhost:8080/api`

---

## 1. Domain Models

### 1.1 User
| Field        | Type         | Constraints                        |
|--------------|--------------|------------------------------------|
| id           | BIGINT PK    | Auto-increment                     |
| username     | VARCHAR(50)  | Unique, Not Null                   |
| password     | VARCHAR(255) | BCrypt hashed, Not Null            |
| firstName    | VARCHAR(100) | Not Null                           |
| lastName     | VARCHAR(100) | Not Null                           |
| email        | VARCHAR(255) | Unique, Not Null                   |
| role         | ENUM         | `BUYER` \| `OWNER`, default BUYER  |
| createdAt    | DATETIME     | Auto-set on insert                 |

### 1.2 Product
| Field       | Type           | Constraints               |
|-------------|----------------|---------------------------|
| id          | BIGINT PK      | Auto-increment            |
| title       | VARCHAR(255)   | Not Null                  |
| price       | DECIMAL(10,2)  | Not Null, > 0             |
| description | TEXT           | Nullable                  |
| category    | VARCHAR(100)   | Not Null                  |
| image       | VARCHAR(512)   | Nullable (URL)            |
| ratingRate  | DECIMAL(3,2)   | Default 0.0               |
| ratingCount | INT            | Default 0                 |
| createdAt   | DATETIME       | Auto-set on insert        |
| updatedAt   | DATETIME       | Auto-set on update        |

### 1.3 CartItem
| Field     | Type          | Constraints                          |
|-----------|---------------|--------------------------------------|
| id        | BIGINT PK     | Auto-increment                       |
| userId    | BIGINT FK     | References User.id, Not Null         |
| productId | BIGINT FK     | References Product.id, Not Null      |
| quantity  | INT           | Not Null, >= 1                       |
| addedAt   | DATETIME      | Auto-set on insert                   |
| UNIQUE    |               | (userId, productId)                  |

---

## 2. Authentication Endpoints

### POST `/api/auth/login`
Authenticates a user and returns JWT access + refresh tokens.

**Access**: Public

**Request Body**:
```json
{
  "username": "emilys",
  "password": "emilyspass",
  "expiresInMins": 30
}
```

**Response `200 OK`**:
```json
{
  "id": 1,
  "username": "emilys",
  "email": "emily.johnson@x.dummyjson.com",
  "firstName": "Emily",
  "lastName": "Johnson",
  "role": "BUYER",
  "accessToken": "eyJ...",
  "refreshToken": "eyJ..."
}
```

**Errors**:
- `401 Unauthorized` — invalid credentials

---

### POST `/api/auth/refresh`
Issues a new access token from a valid refresh token.

**Access**: Public

**Request Body**:
```json
{ "refreshToken": "eyJ..." }
```

**Response `200 OK`**:
```json
{ "accessToken": "eyJ...", "refreshToken": "eyJ..." }
```

**Errors**:
- `401 Unauthorized` — expired or invalid refresh token

---

### POST `/api/auth/logout`
Invalidates the refresh token server-side.

**Access**: Authenticated (any role)  
**Headers**: `Authorization: Bearer <accessToken>`

**Response `204 No Content`**

---

## 3. Product Endpoints

### GET `/api/products`
Returns all products.

**Access**: Public

**Response `200 OK`**:
```json
[
  {
    "id": 1,
    "title": "Fjallraven Backpack",
    "price": 109.95,
    "description": "...",
    "category": "men's clothing",
    "image": "https://...",
    "rating": { "rate": 3.9, "count": 120 }
  }
]
```

---

### GET `/api/products/{id}`
Returns a single product by ID.

**Access**: Public

**Path Param**: `id` — product ID

**Response `200 OK`**: Single product object (same shape as above)

**Errors**:
- `404 Not Found` — product does not exist

---

### GET `/api/products/categories`
Returns a deduplicated list of all product category strings.

**Access**: Public

**Response `200 OK`**:
```json
["electronics", "jewelery", "men's clothing", "women's clothing"]
```

---

### GET `/api/products/category/{category}`
Returns all products in the given category.

**Access**: Public

**Path Param**: `category` — e.g. `electronics`

**Response `200 OK`**: Array of product objects

**Errors**:
- `404 Not Found` — unknown category

---

### POST `/api/products`
Creates a new product.

**Access**: `OWNER` role only  
**Headers**: `Authorization: Bearer <accessToken>`

**Request Body**:
```json
{
  "title": "New Product",
  "price": 29.99,
  "description": "A great product",
  "category": "electronics",
  "image": "https://..."
}
```

**Response `201 Created`**: Full product object including generated `id` and default `rating`

**Errors**:
- `400 Bad Request` — validation failure (missing/invalid fields)
- `401 Unauthorized` — missing or invalid token
- `403 Forbidden` — authenticated but not OWNER

---

### PUT `/api/products/{id}`
Replaces an existing product entirely.

**Access**: `OWNER` role only  
**Headers**: `Authorization: Bearer <accessToken>`

**Path Param**: `id` — product ID

**Request Body**: Same shape as POST (all fields required)

**Response `200 OK`**: Updated product object

**Errors**:
- `400 Bad Request` — validation failure
- `401 Unauthorized`
- `403 Forbidden`
- `404 Not Found`

---

### DELETE `/api/products/{id}`
Deletes a product by ID.

**Access**: `OWNER` role only  
**Headers**: `Authorization: Bearer <accessToken>`

**Path Param**: `id` — product ID

**Response `200 OK`**: Deleted product object (mirrors FakeStore behaviour)

**Errors**:
- `401 Unauthorized`
- `403 Forbidden`
- `404 Not Found`

---

## 4. Cart Endpoints

> Cart state is persisted server-side per user. These endpoints are the
> source of truth; the frontend may also maintain a local in-memory cache.

### GET `/api/cart`
Returns all cart items for the authenticated user.

**Access**: Authenticated (any role)  
**Headers**: `Authorization: Bearer <accessToken>`

**Response `200 OK`**:
```json
[
  {
    "productId": 1,
    "title": "Fjallraven Backpack",
    "price": 109.95,
    "image": "https://...",
    "quantity": 2
  }
]
```

---

### POST `/api/cart`
Adds a product to the cart or sets its quantity if it already exists.

**Access**: Authenticated (any role)  
**Headers**: `Authorization: Bearer <accessToken>`

**Request Body**:
```json
{ "productId": 1, "quantity": 1 }
```

**Response `200 OK`**: Full updated cart item

**Errors**:
- `400 Bad Request` — invalid productId or quantity < 1
- `404 Not Found` — product does not exist

---

### PUT `/api/cart/{productId}`
Updates the quantity of a specific cart item.

**Access**: Authenticated (any role)  
**Headers**: `Authorization: Bearer <accessToken>`

**Path Param**: `productId`

**Request Body**:
```json
{ "quantity": 3 }
```

**Response `200 OK`**: Updated cart item

**Errors**:
- `400 Bad Request` — quantity < 1
- `404 Not Found` — item not in cart

---

### DELETE `/api/cart/{productId}`
Removes a single product from the cart.

**Access**: Authenticated (any role)  
**Headers**: `Authorization: Bearer <accessToken>`

**Path Param**: `productId`

**Response `204 No Content`**

**Errors**:
- `404 Not Found` — item not in cart

---

### DELETE `/api/cart`
Clears all items from the authenticated user's cart.

**Access**: Authenticated (any role)  
**Headers**: `Authorization: Bearer <accessToken>`

**Response `204 No Content`**

---

## 5. Security Model

| Role    | Auth endpoints | GET products | POST/PUT/DELETE products | Cart endpoints |
|---------|---------------|--------------|--------------------------|----------------|
| Guest   | POST login    | ✅           | ❌                       | ❌             |
| BUYER   | All           | ✅           | ❌                       | ✅             |
| OWNER   | All           | ✅           | ✅                       | ✅             |

**JWT Strategy**:
- Access token lifetime: 30 minutes (configurable)
- Refresh token lifetime: 7 days
- Tokens stored on client (localStorage / memory)
- `Authorization: Bearer <token>` header required for protected routes
- Stateless validation via Spring Security `JwtAuthenticationFilter`

---

## 6. Standard Error Response Shape

All error responses return:
```json
{
  "timestamp": "2026-03-31T10:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Product with id 99 not found",
  "path": "/api/products/99"
}
```

---

## 7. MySQL Schema (DDL Summary)

```sql
CREATE TABLE users (
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  username   VARCHAR(50)  NOT NULL UNIQUE,
  password   VARCHAR(255) NOT NULL,
  first_name VARCHAR(100) NOT NULL,
  last_name  VARCHAR(100) NOT NULL,
  email      VARCHAR(255) NOT NULL UNIQUE,
  role       ENUM('BUYER','OWNER') NOT NULL DEFAULT 'BUYER',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products (
  id           BIGINT AUTO_INCREMENT PRIMARY KEY,
  title        VARCHAR(255)  NOT NULL,
  price        DECIMAL(10,2) NOT NULL,
  description  TEXT,
  category     VARCHAR(100)  NOT NULL,
  image        VARCHAR(512),
  rating_rate  DECIMAL(3,2)  DEFAULT 0.00,
  rating_count INT           DEFAULT 0,
  created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE cart_items (
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id    BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  quantity   INT    NOT NULL CHECK (quantity >= 1),
  added_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uq_user_product (user_id, product_id),
  FOREIGN KEY (user_id)    REFERENCES users(id)    ON DELETE CASCADE,
  FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
```

---

## 8. Recommended Spring Boot Project Structure

```
src/main/java/com/flopkart/
├── config/
│   ├── SecurityConfig.java          # Spring Security + JWT filter chain
│   └── JwtConfig.java               # Token secret, expiry settings
├── controller/
│   ├── AuthController.java          # /api/auth/*
│   ├── ProductController.java       # /api/products/*
│   └── CartController.java          # /api/cart/*
├── service/
│   ├── AuthService.java
│   ├── ProductService.java
│   └── CartService.java
├── repository/
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   └── CartItemRepository.java
├── model/
│   ├── User.java
│   ├── Product.java
│   └── CartItem.java
├── dto/
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   ├── ProductRequest.java
│   ├── ProductResponse.java
│   ├── CartItemRequest.java
│   └── CartItemResponse.java
├── security/
│   ├── JwtAuthenticationFilter.java
│   └── JwtUtil.java
└── exception/
    ├── GlobalExceptionHandler.java
    └── ResourceNotFoundException.java
```

---

## 9. Key `application.properties` Settings

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/flopkart
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

jwt.secret=<256-bit-base64-secret>
jwt.access-token-expiry-ms=1800000
jwt.refresh-token-expiry-ms=604800000

server.port=8080
```
