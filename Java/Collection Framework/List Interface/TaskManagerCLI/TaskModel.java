public class TaskModel{
    String taskName;
    String taskDescription;
    TaskStatus taskStatus;

    public TaskModel(String taskName, String taskDescription,TaskStatus taskStatus){
        this.taskName=taskName;
        this.taskDescription=taskDescription;
        this.taskStatus=taskStatus;
        
    }
    public String getTaskName(){
        return taskName;
    }

    public String getTaskDescription(){
        return taskDescription;
    }
    public TaskStatus getTaskStatus(){
        return taskStatus;
    }
    public void setTaskStatus(TaskStatus taskStatus){
         this.taskStatus=taskStatus;
    }
    public void setTaskName(String taskName){
        this.taskName=taskName;
    }
    public void taskDescription(String taskDescription){
        this.taskDescription=taskDescription;
    }
    public String toString(){
        return "Task: "+taskName+"\n"+"Task Description: "+taskDescription+""+" \nTask Status: "+taskStatus.getValue();
    }
   
}