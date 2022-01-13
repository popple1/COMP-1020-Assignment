import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 *
 *COMP 1020		SECTION A02
 *INSTRUCTOR:	Dr. Andrea Bunt
 *NAME:			Raj Rathod
 *ASSIGNMENT:	Assignment 2
 *QUESTION:		Phase 2
 *PURPOSE:		This program creates a calendar and gets it's input from the .txt file
 */
public class Calendar {
    private String name;
    private int year;
    private Entry[][][] entries;
    int count = 1;

    private static final int[] NB_DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final int NB_HOURS = 24;
    private static int numberOfEntries = 0;

    public Calendar(String name, int year)
    {
        this.name = name;
        this.year = year;
        arrayInitialize();
    }

    public Calendar(String filename)
    {
        FileReader fileReaderIn;
        BufferedReader fileIn;
        String inputLine;
        try
        {
            fileReaderIn = new FileReader(filename);
            fileIn = new BufferedReader(fileReaderIn);
            inputLine = fileIn.readLine();
            this.name = inputLine;
            inputLine = fileIn.readLine();
            arrayInitialize();
            while (inputLine != null)
            {
                processEntryLine(inputLine);
                inputLine = fileIn.readLine();
            }
            fileIn.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        catch(NumberFormatException num)
        {
            System.out.println("Number is not right");
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void arrayInitialize()
    {
        entries = new Entry[MONTHS.length][][];
        for (int i = 0; i < MONTHS.length; i++)
        {
            entries[i] = new Entry[NB_DAYS_IN_MONTH[i]][NB_HOURS];
            //entries[i][NB_DAYS_IN_MONTH[i]] = new Entry[NB_HOURS];
        }
    }
    public static boolean isValidTime(int time)
    {
        boolean temp = false;
        if(time < NB_HOURS)
        {
            temp = true;
        }
        return temp;
    }

    public static boolean isValidDate(int month, int day)
    {
        boolean temp = false;
        if(month >= 1 && month<= 12 && day> 1 && day <= 31) {
            if (NB_DAYS_IN_MONTH[month-1] >= day && day > 0) {
                temp = true;
            }
        }
        return temp;
    }

    public void addEntry(int month,int day, Entry entry)
    {
        entries[month-1][day-1][entry.getStartTime()] = entry;
        numberOfEntries++;
    }

    public Entry getEntry(int month, int day, int time)
    {
        Entry temp = null;
        if(entries[month-1][day-1][time] != null)
        {
            temp = entries[month-1][day-1][time];
        }
        return temp;
    }

    public boolean isConflicting(int month, int day, Entry entry)
    {
        boolean temp = false;
        int hourIntoMinute = 60;
        for(int i = 0; i < entries[month-1][day-1].length; i++)
        {
            if(entries[month-1][day-1][i] != null)
            {
                if((entry.getStartTime() + entry.getDuration()/hourIntoMinute) >= entries[month-1][day-1][i].getStartTime() && entry.getStartTime() <= entries[month-1][day-1][i].getStartTime()) {
                    temp = true;
                }
            }
        }
        return temp;
    }

    public void displayEntries(int month, int day)              //??
    {
        String temp = "Entries on " + month +"/" + day+": \n";
        int count = 0;
        if(month > 12 || month < 0 || NB_DAYS_IN_MONTH[month-1] < day || day < 0)
        {
            temp += month + "/" + day + " is invalid \n";
        }
        else {
            for (int i = 0; i < entries[month - 1][day - 1].length; i++) {
                if (month >= 1 && month <= 12 && day > 0 && NB_DAYS_IN_MONTH[month - 1] >= day && entries[month - 1][day - 1][i] != null) {
                    temp += entries[month - 1][day - 1][i].getStartTime() + ":00 " + entries[month - 1][day - 1][i].getName() + ", " + entries[month - 1][day - 1][i].getDuration() + " mins" + "\n";
                    count++;
                }
            }
            if (count == 0) {
                temp += "No entries on this data \n";
            }
        }

        System.out.println(temp);
    }

    public int getDurationOfEntriesOnDay(int month, int day)
    {
        int totalMinutes = 0;
        for(int i = 0; i < entries[month-1][day-1].length; i++)
        {
            if(entries[month-1][day-1][i] != null) {
                totalMinutes += entries[month - 1][day - 1][i].getDuration();
            }
        }
        return totalMinutes;
    }

    public int getNumEntries(int month, int day)
    {
        int temp = 0;
        for(int i = 0; i < entries[month-1][day-1].length; i++) {
            if (entries[month-1][day-1][i] != null)
            {
                temp++;
            }
        }
        return temp;
    }

    public int getNumEntries()
    {
        return numberOfEntries;
    }

    public boolean deleteEntry(int month, int day, Entry entry)
    {
        boolean changed = false;
        for(int i = 0; i < entries[month-1][day-1].length; i++)
        {
            if(entries[month-1][day-1][i] == entry)
            {
                entries[month-1][day-1][i] = null;
                changed = true;
                numberOfEntries--;
            }
        }
        return changed;
    }

    public boolean deleteAllOccurences(Entry entry)
    {
        boolean changed = false;
        for(int i = 0; i < MONTHS.length; i++)
        {
            for(int j = 0; j < entries[i].length; j++)
            {
                for(int k = 0; k < entries[i][j].length; k++)
                {
                    if(entries[i][j][k] == entry)
                    {
                        entries[i][j][k] = null;
                        numberOfEntries--;
                        changed = true;
                    }
                }
            }
        }
        return changed;
    }

    public String toString()
    {
        return name + ", " + year + ", Number of Entries: " + numberOfEntries;
    }

    private void processEntryLine(String line)
    {
        String[] arrofStr = new String[4];
        try {
            if(line.contains(","))
            {
                arrofStr = line.split(",");
            }
            Entry nameOfObj = new Entry(arrofStr[0], Integer.parseInt(arrofStr[1]), Integer.parseInt(arrofStr[2]));
            this.addEntry(Integer.parseInt(arrofStr[3]), Integer.parseInt((arrofStr[4])), nameOfObj);
        }
        catch (Exception e)
        {

        }

    }
}
