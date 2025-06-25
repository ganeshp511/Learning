public class Thread3 implements Runnable {
    public void run(){
        //let other thread run
        Thread.yield();
        for (int i = 0; i <=50; i++) {
            
                    //Thread.currentThread().setName("Thread-3");
                   System.out.println("Worker-3 "+Thread.currentThread().getName()+"Priority: "+Thread.currentThread().getPriority());
        }
    }
    
}
