import java.util.*;

public class Main{
    public static void main(String[] args) {
        BiddingSystem Bid = new BiddingSystem();
        Scanner sc =new Scanner(System.in);
        Vehicles K = new Vehicles(61,"car",250000);
        Vehicles T = new Vehicles(230,"Bike",61000);
        Bid.ADD(K);
        Bid.ADD(T);
        Bid.Bidd();
        while(true)
        {
            System.out.println("=================Welcome to Vehicle Auction=============");
            System.out.println("Are you a Customer or Admin ?");
            System.out.println("1-> For Admin Login");
            System.out.println("2-> For Customer Login");
            System.out.println("3-> Quit");
            System.out.print("Enter Your Choice: ");
            int c = sc.nextInt();
            if(c==1)
            {
                while(true)
                {
                    int count=0;
                    System.out.println("Hello!! What you want to do ?..");
                    System.out.println("1-> Add a Vehicle to Sale.");
                    System.out.println("2-> Display the Vehicles on sale.");
                    System.out.println("3-> Sell the Vehicle.");
                    System.out.println("4-> View Sold Vehicles.");
                    System.out.println("5-> View Vehicles that are Not Bidded and Remove a Vehicle");
                    System.out.println("6-> View Removed vehicles");
                    System.out.println("7-> Logout.");
                    System.out.print("Enter your Choice: ");
                    int j = sc.nextInt();
                    switch (j) {
                        case 1:
                        {
                            System.out.println("Enter the vehicle details");
                            System.out.print("Id: ");
                            int id = sc.nextInt();
                            System.out.print("Type: ");
                            String type = sc.next();
                            System.out.print("Base price: ");
                            int bp = sc.nextInt();
                            Vehicles v = new Vehicles(id, type, bp);
                            Bid.ADD(v);   
                            Bid.Bidd();                         
                            break;
                        }
                        case 2:
                        {
                            Bid.Display();
                            break;
                        }
                        case 3:
                        {
                            System.out.println("You are on selling Section.");
                            System.out.print("Enter the Vehicle Id to sell: ");
                            int g = sc.nextInt();
                            Bid.Sell(g);
                            break;
                        }
                        case 4:
                        {
                            System.out.println("Vehicles that are Sold: ");
                            Bid.SoldHistory();
                            break;
                        } 
                        case 5:
                        {
                            System.out.println("1-> To get the Vehicles that are not Went for Bidding.");
                            System.out.println("2-> Remove a vehicle");
                            System.out.print("Enter your choice: ");
                            int p = sc.nextInt();
                            switch (p) {
                                case 1:
                                {
                                    Bid.NobiddedVehicle();
                                    break;
                                }
                                case 2:
                                {
                                    System.out.print("Enter a Vehicle Id to Remove: ");
                                    int pp = sc.nextInt();
                                    Bid.Drop(pp);
                                    break;
                                }                            
                                default:
                                {
                                    System.out.println("Invalid option.");
                                    break;
                                }
                            }
                            break;
                        }
                        case 6:
                        {
                            Bid.Dropview();
                            break;
                        }
                        case 7:
                        {
                            count++;
                            break;

                        }
                        default:
                        {
                            System.out.println("Invalid Option");
                            break;
                        }
                    }
                       if (count != 0) {
                        System.out.println("Logged out from Admin.");
                        break;
                    }
                }
            }
            else if(c==2)
            {
                while (true) 
                {
                    int count=0;
                    System.out.println("Hello!! These are the options for auction");
                    System.out.println("1-> Display the Vehicles that are available in Auction");
                    System.out.println("2-> Bid an Vehicle");
                    System.out.println("3-> Logout");
                    System.out.print("Enter your Choice: ");
                    int g = sc.nextInt();
                    switch (g)
                    {
                        case 1:
                        {
                            Bid.Display();
                            break;
                        }
                        case 2:
                        {
                            System.out.print("Enter the Vehicle Id: ");
                            int k = sc.nextInt();
                            int o = Bid.CurrentbidStatus(k);
                            if (o != 0) 
                            {    
                                System.out.println("You are Rising a Bid!!.");
                                System.out.print("Enter your Bid: ");
                                int j = sc.nextInt();
                                Bid.placeBid(k, j);
                            }                    
                            break;
                        }
                        case 3:
                        {
                            count++;
                            break;
                        }
                        default:
                        {
                            System.out.println("Invalid option");
                            break;
                        }
                    }
                    if (count != 0) {
                        System.out.println("Logged out from Customer.");
                        break;
                    }

                }

            }
            else if(c==3)
            {
                System.out.println("Quited....");
                return;

            }
            else
            {
                System.out.println("Invalid Option");
            }
        }
    }

}
