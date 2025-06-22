import java.util.Scanner;

public class TaskManagerCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        boolean running = true;
        while (running) {
            System.out.println("\nTask Management System");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Remove Task");
            System.out.println("4. Undo Last Task");
            System.out.println("5. Show All Tasks");
            System.out.println("6. Show Recent 5 Tasks");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter task status (NOT_STARTED, IN_PROGRESS, COMPLETED): ");
                    String statusStr = scanner.nextLine();
                    TaskStatus status;
                    try {
                        status = TaskStatus.valueOf(statusStr.trim().toUpperCase());
                    } catch (Exception e) {
                        System.out.println("Invalid status. Defaulting to NOT_STARTED.");
                        status = TaskStatus.NOT_STARTED;
                    }
                    taskList.addTask(name, desc, status);
                    break;
                case 2:
                    System.out.print("Enter old task name: ");
                    String oldName = scanner.nextLine();
                    System.out.print("Enter new task name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new task description: ");
                    String newDesc = scanner.nextLine();
                    System.out.print("Enter new task status (NOT_STARTED, IN_PROGRESS, COMPLETED): ");
                    String newStatusStr = scanner.nextLine();
                    TaskStatus newStatus;
                    try {
                        newStatus = TaskStatus.valueOf(newStatusStr.trim().toUpperCase());
                    } catch (Exception e) {
                        System.out.println("Invalid status. Defaulting to NOT_STARTED.");
                        newStatus = TaskStatus.NOT_STARTED;
                    }
                    taskList.editTask(oldName, newName, newDesc, newStatus);
                    break;
                case 3:
                    System.out.print("Enter task name to remove: ");
                    String removeName = scanner.nextLine();
                    taskList.removeTask(removeName);
                    break;
                case 4:
                    taskList.undoLastTask();
                    break;
                case 5:
                    taskList.getTasks();
                    break;
                case 6:
                    taskList.printRecentTasks();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting Task Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
