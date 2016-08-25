package TheaterSimulator;

public class ConcessionStandWorker implements Runnable{
    private Customer tempC3;
    ConcessionStandWorker()
    {
    }

    @Override
    public void run() {
        System.out.println("Concession stand worker created");
        Main.s0.out();
        while (true) {
            Main.s7.in();
            fillOrder();

        }
    }


    public void fillOrder()
    {
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            System.out.println("Couldn't sleep concession stand worker");
        }
        do {
            tempC3 = Main.concessionsStandLine.poll();
        }while(tempC3 == null);

        System.out.println(tempC3.getSnack()+" given to customer "+tempC3.getCustomerID());
        tempC3.setHasSnack(true);
        Main.s8.out();
    }
}
