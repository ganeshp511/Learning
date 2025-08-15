Absolutely! Let's break down **📌 1. Client-Server Fundamentals** in detail. These are *core concepts* that every developer must understand to build or work with any web or networked application.

---

## 📌 1. Client-Server Fundamentals (Explained in Detail)

---

### ✅ **1.1 What is a Client vs a Server?**

#### 💡 Client:

* A **consumer** of services.
* It **initiates requests** to the server.
* Examples:

  * Web browsers (Chrome, Firefox)
  * Mobile apps
  * Postman (for testing APIs)
  * Frontend JavaScript apps (React, Angular)

#### 💡 Server:

* A **provider** of services.
* It **listens for requests**, processes them, and sends back responses.
* Examples:

  * Web servers (e.g., Apache, NGINX)
  * Application servers (e.g., Spring Boot, Node.js)
  * Database servers (e.g., MySQL)

> 🔁 Think of the client as a **customer** ordering food, and the server as the **kitchen** preparing and serving the meal.

---

### ✅ **1.2 Request/Response Model**

* The **client** sends a **request** to the server (e.g., “Give me the list of users”).
* The **server** processes it and returns a **response** (e.g., a JSON array of users).
* This is the core of **HTTP-based communication**.

#### 💬 Example:

```
Client → GET /users → Server
Server → 200 OK + JSON response → Client
```

This model is:

* **Stateless**: Each request is independent.
* **Synchronous by default**: Client waits for a response.

> ✅ Why it matters: All REST APIs and most web services follow this pattern.

---

### ✅ **1.3 IP Addresses and Ports**

#### 🌐 IP Address:

* A **unique identifier** for a device on a network.
* Like a phone number for your computer or server.
* Examples:

  * `192.168.1.10` (private)
  * `142.250.180.14` (public, e.g., Google)

#### 🔢 Port:

* A **software channel** that lets a server handle multiple services.
* Default ports:

  * 80 → HTTP
  * 443 → HTTPS
  * 3306 → MySQL
  * 8080 → Spring Boot (default)

#### Example:

```
http://192.168.1.5:8080/users
```

* `192.168.1.5` → IP address
* `8080` → Port number

> ✅ Why it matters: Understanding IPs and ports helps you deploy, test, and debug services.

---

### ✅ **1.4 TCP vs HTTP**

#### ⚙️ TCP (Transmission Control Protocol)

* A **low-level transport protocol**.
* Guarantees **reliable**, **ordered**, and **error-checked** delivery of data.
* Works at the **transport layer** of the OSI model.
* Used by higher-level protocols like HTTP, FTP, SMTP.

#### 🌐 HTTP (HyperText Transfer Protocol)

* An **application-layer protocol** built on top of TCP.
* Used to **request and deliver web resources** (HTML, JSON, files).
* Stateless: Each request is **independent** — server does not remember past requests.
* HTTP/1.1 is widely used; HTTP/2 offers performance improvements.

> ✅ Why it matters: HTTP defines *how* clients and servers talk; TCP ensures the *data gets there safely*.

---
Absolutely — let’s dive into each of these core client-server architecture concepts in detail.

---

## 🔥 Key Server Types

### 🔹 **1. Web Servers (e.g., Apache, NGINX)**

#### What it does:

* Serves **static content** like:

  * HTML, CSS, JS, images
* Can **reverse proxy** or **forward** requests to an application server
* Handles SSL termination, caching, load balancing

#### Examples:

* **Apache HTTP Server** – widely used for hosting websites
* **NGINX** – known for speed, reverse proxy, and load balancing

> 🔁 Think of a web server as a **doorman** that filters and forwards requests to your main app.

---

### 🔹 **2. Application Servers (e.g., Spring Boot, Node.js)**

#### What it does:

* Hosts and runs your **business logic**
* Handles **dynamic content** like REST APIs, database operations
* Processes requests forwarded by a web server or directly from the client

#### Examples:

* **Spring Boot (Java)** – serves REST APIs and handles backend logic
* **Node.js (JavaScript)** – runs event-driven JS on the server

> 🧠 Think of it as the **brains of your application**.

---

### 🔹 **3. Database Servers (e.g., MySQL)**

#### What it does:

* Stores and manages structured data
* Responds to queries from application servers
* Ensures data integrity, transactions, indexing, etc.

#### Examples:

* **MySQL**, **PostgreSQL** – relational DBs
* **MongoDB**, **Redis** – NoSQL DBs

> 🗃️ Think of it as your application’s **memory or filing cabinet**.

---

## 🧾 Stateless vs Synchronous

### 🔹 **Stateless Communication**

* Each request is **independent** of previous ones
* The server **does not store session or user data** between requests
* If the client needs to maintain state (like login info), it sends tokens (e.g., JWT) with each request

> ✅ REST APIs are **stateless by design** → helps scalability and simplicity

---

### 🔹 **Synchronous by Default**

* The client sends a request and **waits for the server to respond**
* Only after receiving a response, the client proceeds
* Most HTTP calls are **blocking/synchronous**

> ⏳ This is simple but can introduce **latency** if the server takes time to respond.

✅ **Async alternatives:** WebSockets, `CompletableFuture` (Java), `async/await` (JS)

---

## 🔢 Port – A Software Channel

* Ports are **virtual "doors"** through which services on a machine communicate
* Servers listen on specific ports
* Clients send requests to a specific IP + port

### 🔧 Common Default Ports:

| Service         | Port | Description                       |
| --------------- | ---- | --------------------------------- |
| HTTP            | 80   | Default for web traffic           |
| HTTPS           | 443  | Secure HTTP (TLS/SSL)             |
| MySQL           | 3306 | Database server default port      |
| Spring Boot App | 8080 | Default app port (can be changed) |

> 🔐 Multiple services can run on the **same machine**, each on **different ports**.

---

## 🌐 DNS Basics (Domain Name System)

### What DNS does:

* Translates **human-readable domain names** (like `google.com`) into **IP addresses**
* DNS is like the **internet’s phonebook**

### Flow:

1. You type `www.example.com`
2. DNS resolves it to an IP address, e.g., `93.184.216.34`
3. The browser sends an HTTP request to that IP

> 🧠 Without DNS, we’d need to remember IP addresses — not scalable or friendly.

---

## 🔁 Request-Response Lifecycle (HTTP)

### Full Flow:

1. **Client sends request** (e.g., GET `/users`)
2. **DNS resolves domain** to IP address
3. **Client connects to server** on a specific port (usually 80 or 443)
4. **Web server receives the request**
5. If dynamic: forwards to **application server** (e.g., Spring Boot)
6. **Application processes logic**, maybe queries **DB server**
7. **Application returns response** (e.g., JSON)
8. **Web server sends it back** to client
9. **Client renders or handles response**

> ✅ This cycle happens **in milliseconds**, and it’s the core of every web app interaction.

---

## ✅ Summary Table

| Concept          | Key Idea                                                 |
| ---------------- | -------------------------------------------------------- |
| Web Server       | Serves static content and acts as reverse proxy          |
| App Server       | Runs business logic and APIs (dynamic content)           |
| DB Server        | Stores data and handles queries                          |
| Stateless        | Server doesn’t remember previous client requests         |
| Synchronous      | Client waits for server’s response before continuing     |
| Port             | Software gateway for communication (e.g., 8080 for apps) |
| DNS              | Converts domain names to IP addresses                    |
| Request-Response | Core lifecycle of client-server interaction              |

---

Great question! Understanding **static vs dynamic requests** — and the **types of HTTP requests** — is fundamental for working with any web application or API.

---

## 📌 What Are Static and Dynamic Requests?

### 🔹 **Static Request**

A **static request** is a request for a resource that **does not change**, no matter who requests it or when.

#### 🔸 Examples:

* `GET /index.html`
* `GET /styles.css`
* `GET /images/logo.png`

These are typically:

* Served directly by the **web server** (e.g., NGINX, Apache)
* **Prebuilt** and stored on disk (HTML, CSS, JS, images)

> ✅ **No server-side processing** or logic is needed — just "fetch and deliver".

---

### 🔹 **Dynamic Request**

A **dynamic request** is processed on the server using logic (e.g., Java, Node.js) and may return **different results** based on:

* User input
* Database content
* Authenticated user
* Time or conditions

#### 🔸 Examples:

* `GET /users/123` → returns data for user #123
* `POST /login` → processes a login form
* `GET /weather?city=London` → returns weather for the given city

These are handled by:

* **Application servers** (like Spring Boot, Express.js)
* Typically involve database access, business logic, and sometimes authentication

> ⚙️ Dynamic requests are **built on-the-fly** depending on the request.

---

## 🧠 Static vs Dynamic: Quick Comparison

| Feature             | Static Request                 | Dynamic Request                         |
| ------------------- | ------------------------------ | --------------------------------------- |
| Response content    | Fixed                          | Generated on demand                     |
| Handled by          | Web server (Apache, NGINX)     | App server (Spring, Node.js)            |
| Processing required | None                           | Yes (logic, DB, user input)             |
| Common file types   | `.html`, `.css`, `.js`, `.jpg` | `.json`, `.xml`, HTML with dynamic data |
| Performance         | Very fast (cached)             | Slower (requires computation)           |

---

## 📬 Types of HTTP Requests (aka HTTP Methods)

These methods define **what action** the client wants to perform on the server.

| Method    | Purpose                               | Used For                                 |
| --------- | ------------------------------------- | ---------------------------------------- |
| `GET`     | Retrieve data                         | Read data (e.g., `GET /users`)           |
| `POST`    | Submit or create something            | Login, form submission, etc.             |
| `PUT`     | Update existing data (replace)        | Update a record                          |
| `PATCH`   | Partially update data                 | Change one field (e.g., email)           |
| `DELETE`  | Remove data                           | Delete a record (e.g., `DELETE /user/1`) |
| `HEAD`    | Same as GET, but returns only headers | Checking if resource exists              |
| `OPTIONS` | Returns allowed methods for a URL     | Preflight check (CORS)                   |

---

## 🛠 How Static vs Dynamic Requests Are Handled

### 🔸 **Static Request Flow**

1. Browser: `GET /about.html`
2. Web server finds the file on disk
3. Web server sends back file contents
4. No logic or database involved

### 🔸 **Dynamic Request Flow**

1. Client: `GET /products?category=shoes`
2. Web server forwards request to app server
3. App server:

   * Parses query parameters
   * Fetches data from database
   * Builds a response (e.g., JSON or HTML)
4. App server sends response to web server → then to client

---

## 🔄 Real-Life Analogy

| Request Type | Analogy                                                             |
| ------------ | ------------------------------------------------------------------- |
| Static       | Ordering a bottled drink from a fridge (pre-packaged, just grab it) |
| Dynamic      | Ordering a fresh smoothie (made-to-order based on your ingredients) |

---

## ✅ Best Practices

* ✅ Use static files (HTML, CSS, JS) for content that doesn’t change often
* ✅ Use dynamic endpoints for user-specific, real-time, or data-driven content
* ✅ Cache static resources with CDN or web server for performance
* ✅ Secure dynamic endpoints with authentication and rate limiting

---

Absolutely! Let’s now enrich the explanation with **networking concepts**, covering IP addressing, routing, NAT, firewalls, ports, sockets, load balancing, and more — and show how they all fit into the **Client-Server flow** with the previous elements (TCP/UDP, OSI/TCP-IP model, REST APIs, servers).

---

## 🔁 Full Client-Server Networking Flow (Expanded with Networking Concepts)

---

### 🔷 1. Overview of the Components

| Component             | Role                                                  |
| --------------------- | ----------------------------------------------------- |
| **Client**            | Initiates a request (browser, mobile app, etc.)       |
| **Web Server**        | Receives HTTP/S requests, handles routing             |
| **App Server**        | Contains application logic, APIs                      |
| **Database Server**   | Stores and retrieves data                             |
| **DNS Server**        | Resolves domain names to IP addresses                 |
| **Router / Gateway**  | Forwards packets across networks                      |
| **Firewall**          | Controls traffic based on rules                       |
| **Load Balancer**     | Distributes traffic among multiple servers            |
| **Network Interface** | Handles packet-level communication at data link layer |

---

### 🔷 2. Core Networking Concepts Involved

#### 🌐 **IP Addressing**

* Every device in the network (client, server) has an **IP address**.
* IPv4: `192.168.1.1`, IPv6: `2001:0db8:85a3::8a2e:0370:7334`
* Used at **Layer 3 (Network)** of OSI.

#### 🛣️ **Routing**

* Routers forward packets from source IP to destination IP.
* Internet routers use **routing tables** and **protocols** like BGP.

#### 🔁 **NAT (Network Address Translation)**

* Used in private networks (e.g., client behind a home router).
* Translates private IP (e.g., `192.168.0.2`) to public IP (e.g., `203.0.113.5`) when communicating over the internet.

#### 🔥 **Firewalls**

* Positioned at routers or servers.
* Enforce rules like “Allow TCP port 443 only” or “Block traffic from IP 10.0.0.1”.

#### 🎯 **Ports**

* Logical identifiers for services on a machine (e.g., port 80 = HTTP, port 443 = HTTPS).
* Client uses **ephemeral ports** (e.g., 49201) to establish connection.

#### 🧱 **Sockets**

* Combination of IP + Port (e.g., `192.168.1.10:443`)
* A **socket** uniquely identifies a connection endpoint.

#### ⚖️ **Load Balancer**

* Distributes client traffic across multiple web/app servers.
* Can be **Layer 4 (TCP)** or **Layer 7 (HTTP-aware)**.
* Example: Amazon ELB, Nginx, HAProxy.

#### 📡 **MTU (Maximum Transmission Unit)**

* Defines the max size of a packet at Layer 2 (e.g., Ethernet = 1500 bytes).
* If data exceeds MTU, it gets fragmented.

---

### 🔷 3. Complete Flow with Networking Concepts (Request to Response)

Let’s walk through it step-by-step, including networking layers:

---

### 📍 **STEP 1: DNS Resolution (UDP + Layer 7 → Layer 1)**

* Client wants to reach `https://example.com`
* Sends DNS request over **UDP port 53**
* DNS server responds with **IP address** of the web server
* Occurs at:

  * **OSI Layer 7**: DNS Protocol
  * **Layer 4**: UDP
  * **Layer 3**: IP routing
  * **Layer 2**: Ethernet/Wi-Fi MAC addresses

---

### 📍 **STEP 2: TCP Connection (TCP Handshake)**

* Client sends **TCP SYN** to `example.com:443`
* Server replies with **SYN-ACK**
* Client sends **ACK**
* **Connection established**
* Happens at:

  * **OSI Layer 4**: TCP
  * **OSI Layer 3**: IP
  * **Layer 2**: MAC Addressing
* **Client uses an ephemeral port**, e.g., `192.168.1.10:49152 → 93.184.216.34:443`

---

### 📍 **STEP 3: HTTP REST API Request (Application Layer)**

* Client sends `GET /products HTTP/1.1` with headers
* Web server receives and:

  * **Terminates SSL** (TLS) if HTTPS
  * Checks route (`/products`)
  * Forwards to Application Server (possibly through internal load balancer)

---

### 📍 **STEP 4: Application Server Logic**

* Application Server:

  * Parses request
  * Authenticates token or session
  * Connects to Database Server over internal IP (e.g., `10.0.1.12`)
  * Queries: `SELECT * FROM products`

---

### 📍 **STEP 5: Database Server Interaction**

* Receives SQL over **TCP port 3306** (MySQL) or **5432** (PostgreSQL)
* Responds with rows (e.g., 20 product items)
* App Server serializes result to **JSON**

---

### 📍 **STEP 6: HTTP Response to Client**

* App Server returns:

  ```http
  HTTP/1.1 200 OK
  Content-Type: application/json
  Body: [{"id":1, "name":"Product A"}, ...]
  ```
* Web Server sends the response over same TCP socket
* TCP handles **segmenting, ordering, retransmission**

---

### 📍 **STEP 7: Client Receives and Displays Data**

* Client browser receives the **JSON**, parses it, and renders it on the UI.

---

### 🔷 4. How TCP/UDP, OSI, Servers, and Networking Concepts All Connect

| Layer/Concept   | Involved In…                                   |
| --------------- | ---------------------------------------------- |
| TCP             | Reliable transport for REST APIs (Layer 4)     |
| UDP             | DNS lookup (Layer 4)                           |
| OSI Model       | Full flow from application to physical layers  |
| IP Address      | Identifies source/destination (Layer 3)        |
| Port            | Specifies service on a host (Layer 4)          |
| NAT             | Translates private → public IPs (Router)       |
| Firewall        | Enforces security policies (e.g., port blocks) |
| Load Balancer   | Distributes load across servers (Layers 4–7)   |
| Web Server      | Handles HTTP, static content, reverse proxy    |
| App Server      | Hosts logic, APIs, processes business rules    |
| Database Server | Handles persistent storage and querying        |

---

## 🧠 Bonus: Protocol Summary Chart

| Protocol | Layer | Port | Role                   |
| -------- | ----- | ---- | ---------------------- |
| DNS      | 7 / 4 | 53   | Resolve domains (UDP)  |
| HTTP     | 7 / 4 | 80   | Unsecured REST APIs    |
| HTTPS    | 7 / 4 | 443  | Secured REST APIs      |
| TCP      | 4     | —    | Reliable data delivery |
| UDP      | 4     | —    | Fast, no guarantee     |
| IP       | 3     | —    | Packet routing         |
| Ethernet | 2     | —    | Local network frames   |

---

### ✅ Final Summary

The journey from a client request to a server response involves **layers of abstraction**:

* From **domain resolution (DNS over UDP)**, to
* **Connection establishment (TCP handshake)**,
* **Data exchange (HTTP/REST APIs)**,
* All handled through **network components** like:

  * **NAT, firewalls, load balancers, routers**
* Ending in structured communication between:

  * **Web → App → DB servers**

---
Great question! Let's break it down step by step:

---

## 🔌 What Are **Sockets**?

### ▶️ **Definition**

A **socket** is a software endpoint that enables communication between two machines (or two processes) over a **network**.

It’s defined by:

```plaintext
Socket = IP Address + Port Number
```

A **socket connection** allows two systems to send and receive data over a network using **TCP or UDP** protocols.

---

## 🔧 Where Sockets Are Used in Networking

| Layer                             | Role                                                                |
| --------------------------------- | ------------------------------------------------------------------- |
| **Transport Layer (OSI Layer 4)** | Sockets operate here, using **TCP or UDP**                          |
| **Application Layer (Layer 7)**   | Programs (like browsers, servers) use sockets to talk to each other |

### 🧠 Key Uses in Networking:

* **Web Browsing**: Client connects to a server’s socket (e.g., `example.com:443`) over TCP.
* **Chat/Messaging Apps**: Real-time data over sockets (sometimes using WebSockets).
* **Game Servers**: Low-latency UDP sockets.
* **FTP, SSH, HTTP**: All use sockets under the hood.

---

## ☕ How Sockets Work in Java

Java provides a rich API to create and manage sockets via:

* `java.net.Socket` (for TCP client)
* `java.net.ServerSocket` (for TCP server)
* `java.net.DatagramSocket` (for UDP)

---

### 🔁 TCP Socket Example in Java

#### ✅ Server Code (TCP)

```java
import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000); // Port 5000
        System.out.println("Server is waiting...");

        Socket clientSocket = serverSocket.accept(); // Accept client
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String msg = in.readLine();
        System.out.println("Received: " + msg);

        out.println("Hello from server!");
        clientSocket.close();
        serverSocket.close();
    }
}
```

#### ✅ Client Code (TCP)

```java
import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000); // Connect to server

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("Hi Server!");
        String response = in.readLine();
        System.out.println("Server said: " + response);

        socket.close();
    }
}
```

---

### 📡 UDP Socket Example in Java

#### Server (UDP)

```java
DatagramSocket socket = new DatagramSocket(1234);
byte[] buffer = new byte[1024];
DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
socket.receive(packet); // Waits for packet
```

#### Client (UDP)

```java
DatagramSocket socket = new DatagramSocket();
InetAddress address = InetAddress.getByName("localhost");
byte[] data = "Hello UDP".getBytes();
DatagramPacket packet = new DatagramPacket(data, data.length, address, 1234);
socket.send(packet);
```

---

## 🛠️ Can Sockets Be Used Outside of Networking?

### ✅ Yes, in some special forms:

#### 1. **Inter-Process Communication (IPC)**

* **Unix Domain Sockets** allow processes on the same machine to communicate via sockets without TCP/IP.
* Example: `UnixSocket` in Linux for fast local communication (used in Docker, databases).

#### 2. **Named Pipes / FIFO**

* Some OSes use socket-like mechanisms for local IPC via named pipes.

#### 3. **WebSockets**

* Built on TCP sockets to allow **real-time communication** in web browsers (chat apps, live updates).

#### 4. **Simulations**

* You can simulate devices, test networked apps, or mock services using socket-based fakes.

---

## 🧠 Key Concepts Recap

| Concept                | Description                                    |
| ---------------------- | ---------------------------------------------- |
| **Socket**             | A communication endpoint (IP + Port)           |
| **TCP Socket**         | Reliable, ordered stream of data               |
| **UDP Socket**         | Fast, connectionless messaging                 |
| **ServerSocket**       | Java class to accept client connections        |
| **Use in Java**        | Via `Socket`, `ServerSocket`, `DatagramSocket` |
| **Outside Networking** | IPC, local services, WebSockets, simulation    |

---

Yes, **sockets can be used in Bluetooth communication**, but with some important differences compared to traditional TCP/IP sockets used over Wi-Fi or Ethernet.

---

## 🔷 Sockets in Bluetooth Communication

Bluetooth supports **socket-like communication**, especially in the **Bluetooth RFCOMM protocol**, which mimics serial communication (like a COM port). This allows applications to use sockets to **send and receive data wirelessly over Bluetooth**.

---

### ✅ Bluetooth Socket Communication Concepts

| Feature               | Details                                                           |
| --------------------- | ----------------------------------------------------------------- |
| **Protocol**          | Bluetooth **RFCOMM** (based on Serial Port Profile – SPP)         |
| **Socket-Like API**   | Yes, Bluetooth libraries provide socket interfaces                |
| **Type**              | More like **stream sockets** (similar to TCP), not datagram (UDP) |
| **Operating Systems** | Android, Linux, Windows, macOS                                    |
| **Languages**         | Java (Android), C/C++, Python, etc.                               |

---

## ☕ Example: Bluetooth Sockets in Java (Android)

### ✅ Server Side (Accepts Connection)

```java
BluetoothServerSocket serverSocket = bluetoothAdapter
    .listenUsingRfcommWithServiceRecord("MyApp", MY_UUID);

BluetoothSocket socket = serverSocket.accept(); // Wait for client

InputStream inStream = socket.getInputStream();
OutputStream outStream = socket.getOutputStream();

// Read/write data just like with TCP sockets
```

### ✅ Client Side (Initiates Connection)

```java
BluetoothDevice device = bluetoothAdapter.getRemoteDevice(MAC_ADDRESS);
BluetoothSocket socket = device
    .createRfcommSocketToServiceRecord(MY_UUID);

socket.connect(); // Establish connection

InputStream inStream = socket.getInputStream();
OutputStream outStream = socket.getOutputStream();
```

> 💡 **UUID** (Universally Unique Identifier) is used to identify a particular Bluetooth service.

---

## 📡 Protocol Behind Bluetooth Sockets

* **RFCOMM**: Provides serial port emulation over Bluetooth.
* It allows creating socket-like communication channels between devices.
* Operates on **Bluetooth Serial Port Profile (SPP)**.
* Compared to TCP/IP, it's slower and has shorter range (typically <10 meters).

---

## 💻 Other Platforms

### 🔹 Python (with PyBluez)

```python
import bluetooth

server_sock = bluetooth.BluetoothSocket(bluetooth.RFCOMM)
server_sock.bind(("", bluetooth.PORT_ANY))
server_sock.listen(1)

client_sock, address = server_sock.accept()
data = client_sock.recv(1024)
client_sock.send("Hello Client")
```

---

## ✅ Summary

| Feature                   | Bluetooth Sockets                                      |
| ------------------------- | ------------------------------------------------------ |
| **Yes, sockets are used** | via Bluetooth RFCOMM                                   |
| **Used for**              | Wireless device communication                          |
| **Languages**             | Java (Android), Python, C++, etc.                      |
| **Protocols**             | RFCOMM (like TCP), not UDP                             |
| **Common Use Cases**      | Wireless sensors, file transfer, controllers, headsets |

---



