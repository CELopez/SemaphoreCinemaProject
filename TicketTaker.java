package TheaterSimulator;

public class TicketTaker implements Runnable {
    private Customer tempC2;
    TicketTaker()
    {

    }

    @Override
    public void run()
    {
        System.out.println("Ticket taker created");
        while(true) {
            Main.s5.in();
            try
            {
                Thread.sleep(250);
            }
            catch(InterruptedException e)
            {
                System.out.println("Couldn't sleep ticket taker ");
            }

            do {
                tempC2 = Main.ticketTakerLine.poll();

            } while (tempC2 == null);

            System.out.println("Ticket taken from customer " + tempC2.getCustomerID());
            Main.s6.out();
        }
    }

}