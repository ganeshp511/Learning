import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class FlightAggregator {
    /**
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executerService=Executors.newFixedThreadPool(2);
        try{
            List<Callable<Flight>> FlightTasks = Arrays.asList(
                new IndigoAirlineService(), new SpiceJetAirlines()
            );
            List<Future<Flight>> returnedData = executerService.invokeAll(FlightTasks);
            for(Future<Flight> data : returnedData){
                System.out.println(data.get());
            }

        }catch(Exception e){
            System.out.println(e);
        }
        finally{
            executerService.shutdown();
        }
        
    }
    
}
