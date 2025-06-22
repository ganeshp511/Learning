import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Stack;

/**
 * TaskList manages a thread-safe list of tasks, supports undoing the last task addition,
 * and maintains a history of the 5 most recently added tasks.
 */
class TaskList {
    // Thread-safe list to store all tasks
    private final List<TaskModel> taskList = new CopyOnWriteArrayList<>();
    // Deque to keep track of the last 5 added tasks (history)
    private final Deque<TaskModel> recentTasks = new ArrayDeque<>();
    // Stack to support undo operation for the last added task
    private final Stack<TaskModel> undoStack = new Stack<>();

    /**
     * Adds a new task to the list, records it for undo, and updates the recent history.
     * Uses synchronized block to ensure thread safety for recentTasks deque.
     * @param taskName Name of the task
     * @param taskDescription Description of the task
     * @param taskStatus Status of the task
     */
    public void addTask(String taskName, String taskDescription, TaskStatus taskStatus) {
        TaskModel task = new TaskModel(taskName, taskDescription, taskStatus);
        taskList.add(task);
        undoStack.push(task);
        // Synchronize to ensure only one thread modifies recentTasks at a time
        synchronized (recentTasks) {
            recentTasks.addLast(task);
            if (recentTasks.size() > 5) {
                recentTasks.removeFirst(); // Keep only the last 5 tasks
            }
        }
    }

    /**
     * Undoes the last task addition by removing it from the task list and history.
     * Uses synchronized block to ensure thread safety for recentTasks deque.
     * If no task is available to undo, prints a message.
     */
    public void undoLastTask() {
        if (!undoStack.isEmpty()) {
            TaskModel lastTask = undoStack.pop();
            taskList.remove(lastTask);
            // Synchronize to ensure only one thread modifies recentTasks at a time
            synchronized (recentTasks) {
                recentTasks.remove(lastTask);
            }
            System.out.println("Undo: Removed last task: " + lastTask.getTaskName());
        } else {
            System.out.println("No task to undo.");
        }
    }

    /**
     * Prints the 5 most recently added tasks.
     * Uses synchronized block to ensure thread safety for recentTasks deque during iteration.
     */
    public void printRecentTasks() {
        System.out.println("Recent 5 tasks:");
        // Synchronize to ensure safe iteration over recentTasks
        synchronized (recentTasks) {
            for (TaskModel task : recentTasks) {
                System.out.println(task);
            }
        }
    }

    /**
     * Edits an existing task identified by its name. Updates the name, description, and status.
     * Uses synchronized block to ensure thread safety for taskList during iteration and modification.
     * @param oldTaskName The current name of the task to edit
     * @param newTaskName The new name to set
     * @param newTaskDescription The new description to set
     * @param newTaskStatus The new status to set
     */
    public void editTask(String oldTaskName, String newTaskName, String newTaskDescription, TaskStatus newTaskStatus) {
        // Synchronize to ensure only one thread modifies taskList at a time
        synchronized (taskList) {
            for (TaskModel model : taskList) {
                if (model.getTaskName().equals(oldTaskName)) {
                    model.setTaskName(newTaskName);
                    model.taskDescription(newTaskDescription);
                    model.setTaskStatus(newTaskStatus);
                }
            }
        }
    }

    /**
     * Removes a task from the list by its name.
     * Uses synchronized block to ensure thread safety for taskList during removal.
     * @param taskName The name of the task to remove
     */
    public void removeTask(String taskName) {
        // Synchronize to ensure only one thread modifies taskList at a time
        synchronized (taskList) {
            taskList.removeIf(model -> model.getTaskName().equals(taskName));
        }
        System.out.println("Removed task: " + taskName);
    }

    /**
     * Prints all tasks in the list. If the list is empty, prints a message.
     * No explicit synchronization needed for CopyOnWriteArrayList iteration.
     */
    public void getTasks() {
        if (taskList.isEmpty()) {
            System.out.println("No task available");
        } else {
            for (TaskModel model : taskList) {
                System.out.println(model);
            }
        }
    }
    
}