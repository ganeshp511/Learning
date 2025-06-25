import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class  ExecuterServiceDemo{
    public static void main(String[] args) {
        ExecutorService executorService=Executors.newFixedThreadPool(10);
        int taskCount=100;
        long startTime=System.currentTimeMillis();
        //CountDownLatch latch=new CountDownLatch(taskCount);
        for(int i=0;i<=taskCount;i++){
            final int taskID=i;
            executorService.submit(()->{
                try{
                    Thread.sleep(10);
                    System.out.println("Execute task: "+taskID);
                }catch(Exception e){
                    System.out.println(e);
                }/*finally{
                    latch.countDown();
                }*/

            });
        }
        try {
            //latch.await();
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.SECONDS);
            long end=System.currentTimeMillis();
            System.out.println("ExecuterService took: " + (end - startTime)+" ms");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}