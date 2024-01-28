### What are Android Services?
Service is a application component which can perform long-running operations in the background. It may continue running for time, even user dealing with to another applications. Moreover, main android components can bind to service to interact with it, also you can perform interprocess communication.

### Why we should use services?
Developers should use services for long-running operations such as handle network transactions, play music, perform file I/O, or interact with a content provider, all from the background.

**Beware! Services run in the main thread of in hosting process, not create its own thread, so developers should run any blocking operations on the separate thread(manage yourself) for avoiding Application Not Responding (ANR) errors.**

### Types of Services
These are the three different types of services:
```
1. Foreground Service
2. Background Service
3. Bound Service
```
### 1-Foreground:
<https://developer.android.com/develop/background-work/services/foreground-services>
Type of services that perform operations in the background that is noticeable for the users. This kind of services must display a Notification and It should continue running even user is not dealing with the app. Likewise, Foreground services have own lifecycle that independent from activity or fragment that they were created. Examples of applications that will use foreground services can be listed as follows:
-Music Player App(notification of the current song)
-Fitness App(show the distance that user has traveled)

Beware! **The WorkManager API offers the flexible and nearly same way as foreground services. In many cases, developers should prefer using WorkManager instead of Foreground services.**

---
**Only use a foreground service when your app needs to perform a task that is noticeable by the user, even when they're not directly interacting with the app. If the action is of low enough importance that you want to use a minimum-priority notification, create a background task instead.**


