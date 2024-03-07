#### WebServices

Web services usually has a REST API for communication. REST API provides different urls to get data. then from app, we need to make an HTTP request to an URL. HTTP request will also contain headers and other data for path, and query parameters. after receiving our request, the API sends back response object to our app. This response object contains a respond code(info about success or failure of the request)

 #### JSON

 The recieved data will be in JSON format and the app needs to parse those JSON formatted data to JAVA or Kotlin and display them to user.

 #### Retrofit

Library provides a powerful framework for authenticating and interacting with APIs and sending network requests with OKHTTP.

Retrofit naturally serializes the JSON reaction utilizing POJO which must be characterized in cutting edge for the JSON structure.

This library makes downloading JSON or XML data from a web API fairly starightforward. Once the data is downloaded then it is parsed into a POJO which must be defined for each resource in response.

**3 Steps to use Retrofit library**
1. Data Classes : Fetching response we need POJO class that automatically parse JSON data using GSON in background.To serialize JSON we require a converter like gson.
- add @SerializedName to each filed (for another serialized name) its optional
[Creating POJO](www.jsonschema2pojo.org/)
2. Service Instance (API/ Service Instance) : Create an interface to define our different methods that will be used for network transactions.
3. Retrofit Instance : Create a class that provides us instance of retrofit.

#### JSON Elements

1. Array : []
2. Objects : Curly bracket represents JSON object, {}
3. Key and Value : Key -> String, Value -> String, Integer, Double, Object, Boolean, Null etc. and key value seperated by colon, each JSON obj seperated by quama , 
4. JSON Arrays : here key is effect and value is json array with one obj in it. JSON array values must be of type string, number, object, array, boolean, null. [] represents JSON array.
```json
{
    "effect":[
        {
            "name":"heal",
            "desc":"heal wound"
        }
    ]


} 
```
#### HTTP Requests

HTTP client sends an HTTP request to a server in form of request message. Req method indicates the method to be performed on the resource identified by given request URI

1. GET : used to retieve infromation from given server using a given URI. 
2. POST : used to send data to server.

#### HTTP Response 

HTTP response is made by server to client. The aim of response is to provide the client with resources it requested or inform the client that the action it rquested has been carried out, or else to inform client that error has occured in processing its request.an HTTP response contains:
- Status Line -> contains status code 3 digit indicating result of request
success/ok -> 200
unauthorized error -> 401
forbidden -> 403
Not found -> 404
- Series of HTTP headers or header fields
- Message body

#### Retrofit instance and serivce instance

https://jsonplaceholder.typicode.com/albums

https://jsonplaceholder.typicode.com -> Base URL retrofit instance

/albums -> END Point include in service interface methods

#### Query Parameters

A query parameter is a key-value pair that is appended to the end of a URL in an HTTP request. These parameters are used to modify the behavior of an HTTP request or to filter the response returned by the server.

In a URL, query parameters are separated from the base URL by a question mark `?`, and multiple parameters are separated by ampersands `&`. For example:

```
https://example.com/api/search?query=kotlin&limit=10
```

In this URL:
- `https://example.com/api/search` is the base URL.
- `query=kotlin` and `limit=10` are the query parameters.
- `query` and `limit` are the parameter keys, and `kotlin` and `10` are their corresponding values.

Query parameters are commonly used in APIs for various purposes such as searching, filtering, sorting, and pagination. In the context of web development, they allow clients to specify additional information for the server to process and respond accordingly.

#### Path Parameters

Path parameters are parts of the URL path that are used to identify a specific resource or endpoint in a RESTful API. Unlike query parameters, which are appended to the end of the URL after a question mark (`?`), path parameters are included directly in the URL path itself.

Path parameters are typically used to provide data that is essential for the server to process the request, such as identifying a specific resource or specifying a unique identifier. They are denoted by a placeholder in the URL path, surrounded by curly braces `{}`. 

For example, consider the following URL:

```
https://api.example.com/users/{userId}
```

In this URL, `{userId}` is a path parameter, and it represents the unique identifier of a user. When making a request to this endpoint, you would replace `{userId}` with the actual identifier of the user you are interested in, like so:

```
https://api.example.com/users/123
```

In this example, `123` is the value of the `userId` path parameter.

Path parameters allow for a more organized and meaningful URL structure, making it easier to understand the purpose of each endpoint and the data being manipulated or retrieved. They are commonly used in RESTful APIs to design clean and intuitive URLs.

Sure, here's a simple example of how you can use Retrofit in Kotlin to make an HTTP GET request to an API and parse the JSON response using Gson:

1. First, make sure to include the necessary dependencies in your `build.gradle` file:

```gradle
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.google.code.gson:gson:2.8.8'
}
```

2. Define your data model class representing the structure of the JSON response. Let's say you have a simple model class `User`:

```kotlin
data class User(
    val id: Int,
    val name: String,
    val email: String
)
```

3. Create a Retrofit interface that defines your API endpoints. In this example, let's assume we're making a GET request to fetch a user by ID:

```kotlin
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): User
}
```

4. Now, you can use Retrofit to create an instance of your API service and make requests. Here's an example:

```kotlin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

suspend fun main() {
    // Create Retrofit instance
    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create an instance of the ApiService interface
    val apiService = retrofit.create(ApiService::class.java)

    // Make a request to get a user by ID
    val userId = 1 // Assuming user ID 1
    val user = apiService.getUser(userId)

    // Print user details
    println("User ID: ${user.id}")
    println("User Name: ${user.name}")
    println("User Email: ${user.email}")
}
```

In this example, we're using the JSONPlaceholder API (`https://jsonplaceholder.typicode.com`) which provides mock data for testing purposes. Replace this base URL with the actual base URL of the API you're working with. Additionally, this example is using a simple `suspend` function in Kotlin to make the API call, but Retrofit supports various methods of asynchronous execution.