### BroadcastReceivers

In Android development, an `IntentFilter` is a component used to declare the types of intents that a particular component, such as an activity, service, or broadcast receiver, can respond to. It specifies the types of intents that an application component is willing to receive.

Here's a breakdown of how `IntentFilter` works:

1. **Declaring in the Manifest:**
   You typically define `IntentFilter` in the AndroidManifest.xml file for the component that should respond to specific types of intents. For example, you might declare an `IntentFilter` for an activity like this:

    ```xml
    <activity android:name=".MainActivity">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
    ```

   In this example, the `IntentFilter` declares that the activity should respond to the `MAIN` action and be a launcher activity.

2. **Action and Category:**
   - **Action (`<action>`):** Describes the action that the component is capable of performing. Actions are strings that specify the general action to be performed, such as `MAIN`, `VIEW`, `EDIT`, etc.
   - **Category (`<category>`):** Describes the category of the component. Categories are optional and provide additional information about the component's behavior. Common categories include `LAUNCHER` for activities that can be the main entry point for the application.

3. **Data Specification (Optional):**
   You can also specify data attributes for an `IntentFilter` to further refine the types of intents the component can handle. For example, you might specify a particular URI pattern that the activity can handle.

    ```xml
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <data android:scheme="http" />
        <data android:host="www.example.com" />
    </intent-filter>
    ```

   In this example, the activity can handle `VIEW` actions for URIs with the "http" scheme and the host "www.example.com".

4. **Priority:**
   You can also assign priority to intent filters using the `android:priority` attribute. Higher priority values take precedence when multiple components can handle the same intent.

    ```xml
    <intent-filter android:priority="100">
        <!-- ... -->
    </intent-filter>
    ```

   In this case, the intent filter has a priority of 100.
In Android, a `BroadcastReceiver` is a component that responds to system-wide broadcast announcements. These broadcasts are sent by the system or other applications and can include events like the device booting up, the battery being low, incoming SMS messages, etc. `BroadcastReceiver` allows your application to respond to these events and perform tasks in the background.

There are two main types of `BroadcastReceiver`:

1. **Static Receiver:**
   - **Declared in the Manifest:** You declare a static `BroadcastReceiver` in the AndroidManifest.xml file. This means that the receiver is registered statically at compile-time, and the system automatically starts it when the specified broadcast event occurs.
   - **Example:**

     ```xml
     <receiver android:name=".MyReceiver">
         <intent-filter>
             <action android:name="android.intent.action.BOOT_COMPLETED" />
             <action android:name="android.intent.action.AIRPLANE_MODE" />
             <!-- Other actions as needed -->
         </intent-filter>
     </receiver>
     ```

   - **Usage:** Static receivers are useful for responding to system-level events, such as device boot, connectivity changes, etc.

2. **Dynamic Receiver:**
   - **Registered at Runtime:** You can register a `BroadcastReceiver` dynamically at runtime using the `registerReceiver()` method in the `Context` class. This allows you to register and unregister the receiver based on the lifecycle of your application or component.
   - **Example:**

     ```java
     MyReceiver myReceiver = new MyReceiver();
     IntentFilter intentFilter = new IntentFilter("custom.action");
     registerReceiver(myReceiver, intentFilter);
     ```

   - **Usage:** Dynamic receivers are useful when you need to receive broadcasts in response to user interactions or specific conditions within your application. They provide more flexibility in terms of when the receiver is active.

Additionally, you can categorize `BroadcastReceiver` based on whether they are system-level or custom:

- **System Broadcast Receivers:**
  These respond to system-wide events and are typically declared in the manifest. Examples include receiving broadcasts about device boot, battery state changes, connectivity changes, etc.

- **Custom Broadcast Receivers:**
  These respond to custom events or broadcasts sent within your application or by other applications. You can define custom actions for your broadcasts, and both static and dynamic receivers can handle them.

It's worth noting that starting from Android 8.0 (API level 26), static registration of certain implicit broadcasts in the manifest is restricted to improve app security and performance. Developers are encouraged to use dynamic registration for such cases.