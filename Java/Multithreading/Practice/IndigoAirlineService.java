import java.util.concurrent.Callable;

public class IndigoAirlineService implements Callable<Flight> {

    @Override
    public Flight call() throws Exception {
        Thread.sleep(800);
        return new Flight("Indigo", "Pune", "Hyderabad", 5000);
    }    
}
