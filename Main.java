package TheaterSimulator;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main{

    public static int threadDoneCount;
    private static int numberOfAgents = 2;
    public static ArrayList<movieTicket> tonightsListing = new ArrayList<>();

    //Semaphores
    public static Sem1 s0 = new Sem1(0); //Theater opening semaphore
    public static Sem1 s1 = new Sem1(0); //Signal Customers are ready
    public static Sem1 s2 = new Sem1(1); //Only one BOA polls from queue at a time
    public static Sem1 s3 = new Sem1(numberOfAgents); // limits customers going to BOA to 2
    public static Sem1 s4 = new Sem1(0); //Send Customer to ticketTakerLine
    public static Sem1 s5 = new Sem1(0); //Signal ticketTaker to take tickets
    public static Sem1 s6 = new Sem1(0); //Signal Customer to go to concession stand/movie
    public static Sem1 s7 = new Sem1(0); //Signals Concession Stand worker there are customers
    public static Sem1 s8 = new Sem1(0); //Signals customer has snack from CSW
    public static Sem1 s9 = new Sem1(0); //Signals to end simulation
    public static Sem1 s10 = new Sem1(1); //Only one customer can enter BOL queue at a time
    public static Sem1 s11 = new Sem1(1); //Only one customer can enter TTL queue at a time
    public static Sem1 s12 = new Sem1(1); //Only one customer can enter CSL queue at a time
    public static Sem1 s13 = new Sem1(0); //Signals ticket has been taken/set

    //Queues
    public static Queue<Customer> boxOfficeLine = new LinkedList<>();
    public static Queue<Customer> ticketTakerLine = new LinkedList<>();
    public static Queue<Customer> concessionsStandLine = new LinkedList<>();

    public static void main(String[] args) throws IOException
    {

        //Request user for file name of movie text
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter name of file: ");
        Scanner file = new Scanner(new File(scan.nextLine()));

        //Create/populate array of movie tickets
        StringTokenizer st;
        while(file.hasNext())
        {
            String temp = file.nextLine().trim();
            st = new StringTokenizer(temp, "\t");
            tonightsListing.add(new movieTicket(st.nextToken(),Integer.parseInt(st.nextToken())));
        }

        //Create Box Office Agents
        Thread boxOffice[] = new Thread[numberOfAgents];

        for(int i=0; i<numberOfAgents; i++)
        {
            BoxOfficeAgent boa = new BoxOfficeAgent(i);
            boxOffice[i] = new Thread(boa);
            boxOffice[i].start();
        }

        //Create Ticket taker
        TicketTaker tt = new TicketTaker();
        Thread ttThread = new Thread(tt);
        ttThread.start();

        //Create Concession Stand Worker
        ConcessionStandWorker csw = new ConcessionStandWorker();
        Thread cswThread = new Thread(csw);
        cswThread.start();

        s0.in(); //wait to announce theater is open till threads are finished
        System.out.println("Theater is Open");

        //Create Customers
        int numberOfCustomers=50;
        Thread customers[] = new Thread[numberOfCustomers];

        for(int i=0; i<numberOfCustomers; i++)
        {
            Customer c = new Customer(i);
            int temp_movieID = c.pickMovie(tonightsListing.size());
            c.setMovieSelection(tonightsListing.get(temp_movieID).getMovieName());
            customers[i] = new Thread(c);
            customers[i].start();
        }

        s9.in();
        //Join Customers
        for(int x=0; x<numberOfCustomers; x++)
        {
            try {
                System.out.println("Joined customer " + x);
                customers[x].join();
            }
            catch (InterruptedException e)
            {
                System.out.println("Error: Couldn't join customer threads");
            }
        }
        System.exit(0);

    }
}
