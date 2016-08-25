package TheaterSimulator;


public class BoxOfficeAgent implements Runnable
{
    private int agentNumber;
    private Customer tempC;
    BoxOfficeAgent(int n)
    {
        agentNumber=n;
    }

    @Override
    public void run()
    {
        System.out.println("Box Office Agent "+ agentNumber + " created");

        while(true) {
            Main.s1.in();
            Main.s2.in();
            if(!Main.boxOfficeLine.isEmpty()) {
                do {
                    tempC = Main.boxOfficeLine.poll();

                }while(tempC == null);
                System.out.println("Box Office Agent " + agentNumber + " now serving customer " + tempC.getCustomerID());
                this.sellTicket(tempC);
            }
            Main.s2.out();
            Main.s4.out();
        }
    }

    public void sellTicket(Customer c)
    {
        try
        {
            Thread.sleep(1500);
        }
        catch(InterruptedException e)
        {
            System.out.println("Couldn't sleep Box office Agent "+agentNumber);
        }
        String movieName = c.getCustomerMovieSelection();

        for (int x=0; x<Main.tonightsListing.size(); x++)
        {
           if (Main.tonightsListing.get(x).getMovieName() == movieName)
           {
               if (Main.tonightsListing.get(x).isSoldOut())
               {
                   System.out.println(""+movieName+" is sold out.");
                   System.out.println("Customer "+ c.getCustomerID() + " went home");
                   c.setHasTicket(false);
               }
               else
               {
                   Main.tonightsListing.get(x).sellTicket();
                   System.out.println("Box Office Agent "+agentNumber+" sold a ticket for "+movieName+" to customer "+c.getCustomerID());
                   c.setHasTicket(true);
               }
           }
        }

//        Main.s13.out();

    }

}
