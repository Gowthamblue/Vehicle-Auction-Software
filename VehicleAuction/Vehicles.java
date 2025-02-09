public class Vehicles {
    private int id;
    private String type;
    private int Baseprice; 

    public Vehicles(int id, String type, int price) {
        this.id=id;
        this.type = type;
        this.Baseprice = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return Baseprice;
    }

    public void setPrice(int price) {
        this.Baseprice = price;
    }
}
