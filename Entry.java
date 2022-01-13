/*
 *
 *COMP 1020		SECTION A02
 *INSTRUCTOR:	Dr. Andrea Bunt
 *NAME:			Raj Rathod
 *ASSIGNMENT:	Assignment 2
 *QUESTION:		Phase 2
 *PURPOSE:		This program creates a calendar and gets it's input from the .txt file
 */
public class Entry {
    private String name;
    private int startTime;
    private int duration;


    //constructor
    public Entry(String name, int startTime, int duration)
    {
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
    }

    //accessor method this method return the name of the object
    public String getName()
    {
        return name;
    }

    //accessor method this method return the startTime of the object
    public int getStartTime()
    {
        return startTime;
    }

    //accessor method this method return the duration of the object
    public int getDuration()
    {
        return duration;
    }

    //toString method this method return the String when object is printed
    public String toString()
    {
        String temp = this.getStartTime() + ":00 " + this.getName() + ", " + this.getDuration() + " mins";
        return temp;
    }



    //  *This function checks whether the two objects overlap each other or not
    //  *This function uses if condition to check
    //  *This function checks if condition and returns the boolean value
    //  *this return a boolean variable
    public  boolean overlaps (Entry overlap)
    {
        boolean temp = false;
        int min = 60;                                           //60 min in an hour
        int hour = this.duration/min;
        if(this.startTime + hour > overlap.startTime)
        {
            temp = true;
        }
        return temp;
    }

    public boolean equals (Entry temp)
    {
        return this.name.equals(temp.name) && this.startTime == temp.startTime && this.duration == (temp.duration);
    }

    public Entry clone()
    {
        return new Entry(name, startTime, duration);
    }
}
