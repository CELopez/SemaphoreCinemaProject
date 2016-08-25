package TheaterSimulator;

public class movieTicket {
    private String movieName;
    private int numberOfTicketsAvailable;
    private static boolean isSoldOut =false;

    movieTicket(String s, int n)
    {
            movieName = s;
            numberOfTicketsAvailable = n;
    }

    public void sellTicket()
    {
        numberOfTicketsAvailable--;
        if (numberOfTicketsAvailable==0)
        {
            isSoldOut = true;
        }
    }

    public static boolean isSoldOut()
    {
        return isSoldOut;
    }

    public String getMovieName()
    {
        return movieName;
    }




}
