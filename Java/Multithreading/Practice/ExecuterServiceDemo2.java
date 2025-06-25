import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ExecuterServiceDemo2{
    public static void main(String[] args) {
        //run 50 tasks using multithreading
        ExecutorService executorService=Executors.newFixedThreadPool(5);
        long startTime=System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            final int taskID = i;
            executorService.submit(()->{
                try{
                    Thread.sleep(10);
                    System.out.println("Thread running: "+taskID);
                }catch(Exception e){
                    System.out.println(e);
                }
            });
            
        }
        executorService.shutdown();
        
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
            long totalTime=System.currentTimeMillis()-startTime;
            System.out.println("Total time taken: "+totalTime+" ms");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}