import java.util.ArrayList;
import java.util.Iterator;

public class BiddingSystem {
    ArrayList<Vehicles> VehicleList = new ArrayList<>();
    ArrayList<Bid> Bidingstatus = new ArrayList<>();
    ArrayList<Bid> Sellhistory = new ArrayList<>();
    ArrayList<Bid> DroppedVehicles = new ArrayList<>();

    public void ADD(Vehicles v)
    {
        VehicleList.add(v);
    }
    public void Bidd()
    {
        
        for(Vehicles g:VehicleList)
        {
            boolean alreadyExist = false; 
            for(Bid u:Bidingstatus)
            {
                if(u.getId()==g.getId())
                {
                    alreadyExist=true;
                    break;
                }
            }
            if(!alreadyExist)
            {

                Bid b = new Bid(g.getId(), g.getType(), g.getPrice(),0);
                Bidingstatus.add(b);
            }
        }
    }
    public int CurrentbidStatus(int id)
    {

        for(Bid h:Bidingstatus)
        {
            if(h.getId()==id)
            {
                if(h.getBidprice()==0)
                {
                    System.out.println("Vehicle Id: "+id+" Enter a Bid Amount Greater than : "+h.getPrice());
                }
                else
                {

                    System.out.println("Vehicle Id: "+id+" Current Bid: "+h.getBidprice());
                }
                return 1;
            }
        }
        return 0;

    }
    public void placeBid(int id,int bidAmount)
    {
        for(Bid h:Bidingstatus)
        {
            if(id==h.getId())
            {
                if(h.bidprice==0 && h.getPrice()>bidAmount)
                {
                    System.out.println("Your Bid must be Above the BasePrice.");
                }
                else if(h.bidprice<bidAmount)
                {
                    h.setBidprice(bidAmount);
                    System.out.println("Bid Placed Successfully....");
                }
                else
                {
                    System.out.println("Your Bid Amount must be higher than the Current Bid.");
                }
                return;
            }
        }
        System.out.println("Invalid Vehicle Id");
    }
    public void Sell(int id)
    {
        Iterator<Bid> iterator = Bidingstatus.iterator();
        while(iterator.hasNext())
        {
            Bid K = iterator.next();
            if(K.getId()==id)
            {
                if(K.bidprice==0)
                {
                    System.out.println("No Bids are placed. For this vehicle");
                    return;
                }
                else
                {
                    Bid b = new Bid(K.getId(), K.getType(), K.getPrice(), K.getBidprice());
                    Sellhistory.add(b);
                    System.out.println("The Vehicle: " + id + " was Solded for Bidprice: " + K.getBidprice());
                    iterator.remove();
                    return;
                }
            }
        }
        System.out.println("Enter a valid Vehicle Id.");
    }
    public void SoldHistory()
    {
        if(Sellhistory.isEmpty())
        {
            System.out.println("No vehicles Sold Yet...");
            return;
        }
        for (Bid g : Sellhistory) 
        {
            System.out.println("Id: " + g.getId());
            System.out.println("Type: " + g.getType());
            System.out.println("Base Price: " + g.getPrice());
            System.out.println("Vehicle sold For "+ g.getBidprice());
            System.out.println("=================");
        }
    }
    public void Drop(int id)
    {
        Iterator<Bid> iterator = Bidingstatus.iterator();
        while(iterator.hasNext())
        {
            Bid K = iterator.next();
            if(K.getId()==id)
            {
                if(K.bidprice==0)
                {
                    Bid b = new Bid(K.getId(), K.getType(), K.getPrice(), K.getBidprice());
                    DroppedVehicles.add(b);
                    System.out.println("The Vehicle: " + id + " was Dropped from bidding");
                    iterator.remove();
                    return;                   
                }
                else
                {
                    System.out.println("Bids are placed. For this vehicle");
                    return;
                   
                }
            }
        }
        System.out.println("Enter a valid Vehicle Id.");


    }
    public void NobiddedVehicle()
    {
        boolean j = false;
        System.out.println("Vehicles that are not Went for Bidding: ");
        for(Bid h:Bidingstatus)
        {
            if(h.getBidprice()==0)
            {
                j=true;
                System.out.println("Id: "+h.getId());
                System.out.println("Type: " + h.getType());
                System.out.println("Base Price: " + h.getPrice());
                System.out.println("Current Bid: "+h.getBidprice());
                System.out.println("=================");
            }
        }
        if(!j)
        {
            System.out.println("**All the Vehicles are went for Bidding.***");
        }
    }
    public void Dropview()
    {
        if(DroppedVehicles.isEmpty())
        {
            System.out.println("No Vehicles Dropped");
        }
        for(Bid h:DroppedVehicles)
        {
            System.out.println("Id: " + h.getId());
            System.out.println("Type: " + h.getType());
            System.out.println("Base Price: " + h.getPrice());
            System.out.println("Current Bid: 0");
            System.out.println("=================");
        }
    }

    public void Display()
    {
        if(Bidingstatus.isEmpty())
        {
            System.out.println("No Vehicles are Available for Bidding");
            return;
        }
        System.out.println("The Vehicles that Not Went For Bidding :");
        for(Bid g:Bidingstatus)
        {
            System.out.println("Id: " + g.getId());
            System.out.println("Type: "+g.getType());
            System.out.println("Base Price: "+g.getPrice());
            System.out.println("Current Bid:"+(g.getBidprice()>0 ? g.getBidprice():"No Bids Yet."));            
            System.out.println("=================");
        }
    }


}
