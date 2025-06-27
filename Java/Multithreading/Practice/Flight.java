class Flight{
    String airline;
    String source;
    String destination;
    double price;

    Flight(String airline,String source, String destination, double price){
        this.airline=airline;
        this.source=source;
        this.destination=destination;
        this.price=price;
    }
    @Override
    public String toString(){
        return "Airline: "+airline+" \nSource: "+source+" \nDestination: "+destination+" \nPrice: "+price;
    }
}