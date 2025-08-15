## **1. What is Pagination?**

Pagination is the process of splitting large sets of data into smaller chunks (pages) so you:

* **Avoid loading too much data** into memory at once.
* **Reduce response size** for faster API responses.
* **Improve user experience** by returning data in parts (like page 1, page 2…).

Example: Instead of sending **10,000 users** in one API call, you send **50 users per page**.

---

## **2. Spring Boot + Spring Data JPA Support**

Spring Data JPA provides built-in pagination support through:

* `Pageable` interface → describes page number, page size, sorting.
* `Page` interface → represents a page of data and metadata.
* `Slice` interface → similar to `Page` but doesn’t require total count.

---

## **3. How It Works**

When you pass a `Pageable` to a Spring Data repository method, Spring Data JPA automatically:

1. Adds `LIMIT` and `OFFSET` clauses to the SQL query.
2. Optionally runs a `COUNT(*)` query to get the total number of rows.

Example query for page 0, size 5:

```sql
SELECT * FROM users LIMIT 5 OFFSET 0;
```

Example query for page 1, size 5:

```sql
SELECT * FROM users LIMIT 5 OFFSET 5;
```

---

## **4. Example in Spring Boot**

### **Entity**

```java
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
}
```

### **Repository**

```java
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByPriceGreaterThan(double price, Pageable pageable);
}
```

### **Service**

```java
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getProducts(double minPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("price").descending());
        return productRepository.findByPriceGreaterThan(minPrice, pageable);
    }
}
```

### **Controller**

```java
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<Product> getProducts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "0") double minPrice
    ) {
        return productService.getProducts(minPrice, page, size);
    }
}
```

---

## **5. Output Structure**

A `Page<Product>` JSON response will include:

```json
{
  "content": [
    { "id": 1, "name": "Product A", "price": 100.0 },
    { "id": 2, "name": "Product B", "price": 90.0 }
  ],
  "pageable": { ... },
  "totalPages": 10,
  "totalElements": 50,
  "last": false,
  "size": 5,
  "number": 0,
  "sort": { ... },
  "first": true,
  "numberOfElements": 5,
  "empty": false
}
```

This extra metadata is **why `Page` is so useful** — you don’t have to manually calculate totals.

---

## **6. Page vs Slice**

* **`Page`** → Runs a `COUNT(*)` query → total pages & total elements included.
* **`Slice`** → Only checks if there’s a next page → faster for infinite scrolling.

---

## **7. Best Practices**

* Avoid very large `pageSize` (can cause memory pressure).
* For huge datasets, consider **keyset pagination** (using `WHERE id > lastSeenId`) instead of offset — better performance for big tables.
* Return only needed fields with projections to reduce payload size.
* Cache frequent queries to avoid repetitive DB hits.

---