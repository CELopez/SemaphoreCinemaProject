package TheaterSimulator;

import java.util.concurrent.Semaphore;

public class Sem1 {
    private final Semaphore sem;

    public Sem1(int n)
    {
        this.sem= new Semaphore(n, true);
    }

    public void in()
    {
        try{
            sem.acquire();
        } catch(InterruptedException e)
        {
            System.out.println("Couldn't acquire semaphore ");
        }
    }

    public void out()
    {
        sem.release();
    }

}
