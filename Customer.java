package TheaterSimulator;

import java.util.Random;


public class Customer implements Runnable
{
    private int customerID;
    private String movieSelection, Snack;
    private boolean getSnack, hasTicket, hasSnack=false;

    Random rand = new Random();

    Customer(int n)
    {
        customerID = n;
    }

    @Override
    public void run()
    {
        Main.s10.in();
        Main.boxOfficeLine.add(this);
        Main.s10.out();

        Main.s3.in();
        System.out.println("Customer "+ customerID + " created, buying ticket to "+movieSelection);
        Main.s1.out();
        Main.s4.in();
        Main.s3.out();

        Main.s13.in();

        if(hasTicket) {

            Main.s11.in();
            Main.ticketTakerLine.add(this);
            System.out.println("Customer " + customerID + " in line to see ticket taker");
            Main.s5.out();
            Main.s11.out();



            Main.s6.in();
            pickSnack();

            if(getSnack)
            {

                Main.s12.in();
                Main.concessionsStandLine.add(this);
                Main.s12.out();


                System.out.println("Customer "+customerID+" in line to buy "+Snack);
                Main.s7.out();

                Main.s8.in();
            }
                System.out.println("Customer "+customerID+" enters theater to see "+movieSelection);


        }
            else{}

        Main.threadDoneCount++;

        if(Main.threadDoneCount == 50)
        {
            Main.s9.out();
        }

    }

    public int getCustomerID()
    {
        return customerID;
    }
    public String getCustomerMovieSelection()
    {
        return movieSelection;
    }

    public void setMovieSelection(String s)
    {
            movieSelection = s;
    }

    public void setHasSnack(boolean bool)
    {
        hasSnack=bool;
        System.out.println(customerID+" receives "+Snack);
    }

    public void pickSnack()
    {
        int temp = rand.nextInt(2)+1;

        if (temp == 1)
        {
            getSnack=true;
            temp = rand.nextInt(3)+1;
            if (temp == 1)
                Snack = "popcorn";
            else if (temp == 2)
                Snack = "soda";
            else
                Snack = "popcorn and soda";

        }

    }

    public String getSnack()
    {
        return Snack;
    }

    public int pickMovie(int numberOfMovies)
    {

        return rand.nextInt(numberOfMovies);
    }

    public void setHasTicket(boolean bool)
    {
        hasTicket=bool;
        Main.s13.out();
    }

}
