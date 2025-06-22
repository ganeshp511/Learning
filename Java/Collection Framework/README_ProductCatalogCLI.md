
# Product Catalog CLI (Java)

## Overview
**ProductCatalogCLI** is a console-based product catalog management application built using the Java Collection Framework. It is designed as a learning project that demonstrates various collection interfaces and classes **without using the Stream API**.

This project allows users to manage a product catalog with features like adding, editing, deleting, searching, grouping, sorting, filtering, importing, exporting, and undoing recent additions.

---

## Features

- 📦 **Product Management**: Add, edit, delete, and search products by name.
- 📁 **Persistence**: Save/load products to/from a file.
- 🔍 **Search and Filter**: Filter by category, partial match search by name.
- 🔄 **Undo Support**: Undo last added product using Stack.
- 📊 **Sort and Group**:
  - Sort by price (ascending/descending)
  - Sort by name using `TreeSet`
  - Group by category using `HashMap`, `LinkedHashMap`, `TreeMap`
- 🕒 **Recent Additions**: Track last 5 added products using `Deque`
- 📥 **Batch Import**: Simulate importing products using `Queue`
- 💎 **Find Max**: Get highest priced product using `PriorityQueue`
- 🧠 **Unique Values**: Show all unique product categories using `HashSet`
- 🚫 **Safe Deletion**: Delete by name using `Iterator`

---

## Collection Framework Concepts Used

| Interface/Class     | Purpose/Usage                                                                 |
|---------------------|-------------------------------------------------------------------------------|
| `ArrayList`         | Main catalog storage for dynamic product list                                 |
| `Deque` (`LinkedList`) | Stores recent additions efficiently from both ends                          |
| `Stack`             | Stores history for undo functionality (LIFO)                                  |
| `HashMap`           | Groups products by category without preserving order                          |
| `LinkedHashMap`     | Groups products by category while preserving insertion order                  |
| `TreeMap`           | Groups products by category in sorted (natural key) order                     |
| `TreeSet`           | Automatically sorts products by name                                          |
| `HashSet`           | Tracks unique categories                                                      |
| `PriorityQueue`     | Retrieves highest priced product using max-heap logic                         |
| `Queue` (`LinkedList`) | Simulates importing products in FIFO order                                  |
| `Iterator`          | Safely removes product during iteration                                       |
| `Comparator`        | Custom sorting by price and name                                              |

---

## How It Works

- All interactions are handled via a console menu inside the `main()` method.
- Data is stored in a list of `Product` objects, each having `name`, `price`, and `category`.
- The menu supports various options that call helper methods for different operations.
- File I/O is used to save and load product data from a file named `catalog.txt`.
- Additional data structures (Stack, Deque, etc.) enhance usability features like undo and recent tracking.

---

## File Structure

```
ProductCatalogCLI.java     # Main application
catalog.txt                # Product data (auto-created on save)
```

---

## Learning Objectives

- Practice core Java collection classes
- Understand differences between collection types (ordered vs. unordered, sorted, etc.)
- Learn real-world application of Deque, Stack, Queue, Map, Set, etc.
- Gain familiarity with file operations and CLI-based user interaction

---

## Note
This project avoids the Stream API intentionally to reinforce mastery of core collection patterns and algorithms.
