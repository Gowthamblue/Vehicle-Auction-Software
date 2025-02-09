public class Bid extends Vehicles{  
    int bidprice;
    public Bid (int id, String type, int price, int bidprice)
    {
        super(id, type, price);
        this.bidprice=bidprice;
    }
    public int getBidprice() {
        return bidprice;
    }
    public void setBidprice(int bidprice) {
        this.bidprice = bidprice;
    }
    
    
}
