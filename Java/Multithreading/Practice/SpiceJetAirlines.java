import java.util.concurrent.Callable;

public class SpiceJetAirlines implements Callable<Flight> {
    @Override
    public Flight call() throws InterruptedException {
        Thread.sleep(1200);
        return new Flight("Spicejet", "Hyderbad", "Rajasthan", 6000);
    }
}
