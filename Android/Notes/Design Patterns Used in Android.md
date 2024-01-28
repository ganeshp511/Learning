### Design Patterns Used in Android

While I don't have direct access to the entire Android Open Source Project (AOSP) codebase, I can guide you on where you might find instances of certain design patterns in Android development. Keep in mind that specific implementations may vary, and patterns may not be explicitly labeled in the code. Here are some pointers:

1. **Singleton Pattern:**
   - Example: The `Application` class in Android is often extended to create a custom application class for your app. This class is instantiated only once during the lifetime of the application.
   - Code: You might find examples by searching for custom `Application` classes in various Android projects.

2. **Observer Pattern:**
   - Example: The `LiveData` and `ViewModel` classes in Android use the Observer pattern to notify UI components about changes in data.
   - Code: Look into the implementation of classes like `LiveData` and `ViewModel` in the AOSP.

3. **Builder Pattern:**
   - Example: The `OneTimeWorkRequest.Builder` and `PeriodicWorkRequest.Builder` classes in the WorkManager library.
   - Code: Search for these classes in the WorkManager source code.

4. **Factory Method Pattern:**
   - Example: The `Fragment` class in Android provides a `newInstance()` method that is commonly used to create fragments.
   - Code: Look into the `Fragment` class and its usage patterns in AOSP.

5. **Adapter Pattern:**
   - Example: The `ArrayAdapter` and `RecyclerView.Adapter` classes in Android adapt data for use in UI components.
   - Code: Explore the implementation of these adapter classes in the AOSP codebase.

6. **Decorator Pattern:**
   - Example: Classes like `BufferedInputStream` or `DataOutputStream` in Java, which are used in Android, often decorate basic `InputStream` or `OutputStream` instances.
   - Code: Check the Java standard library implementations that are used in Android.

7. **Strategy Pattern:**
   - Example: The `Intent` mechanism in Android allows you to use different strategies for starting activities or services.
   - Code: Explore the usage of `Intent` in various Android components.

8. **Command Pattern:**
   - Example: The `Handler` class in Android is based on the Command pattern, encapsulating commands to be executed later or on a different thread.
   - Code: Look into the implementation of the `Handler` class.

9. **State Pattern:**
   - Example: The lifecycles of Android components (e.g., `Activity` and `Fragment`) involve state changes that are managed by the Android framework.
   - Code: Explore the lifecycles of Android components in the AOSP codebase.

10. **Builder Pattern:**
    - Example: The `AlertDialog.Builder` class in Android provides a fluent API for constructing complex dialogs.
    - Code: Investigate the usage of `AlertDialog.Builder` in Android projects.

You can explore the AOSP codebase on GitHub or use tools like Android Studio to navigate the source code and understand how these design patterns are applied in practice. Keep in mind that the Android framework is extensive, and patterns may be applied in various places based on the specific requirements of each component.