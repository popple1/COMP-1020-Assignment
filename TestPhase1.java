/* A file that you can use to get you started testing your Phase 1 code.  Note that this is not a complete
 * or thorough set of tests.  Rather, we supply the output from running this file on our sample solutations to give you
 * a better sense of how the methods should behave.
 * 
 */
public class TestPhase1 {
  
  public static void main(String[] args) {

    //Calling methods in the Entry class
    // Creating 4 Entry instances
    Entry e1 = new Entry("Dentist Appointment", 9, 180);
    Entry e2 = new Entry("COMP 1020 class", 11, 60);
    Entry e3 = new Entry("Graduate student meeting", 12, 50);
    Entry e4 = e2.clone();
    Entry e5 = new Entry("WSO concert", 19, 60);

    //Calling the toString methods
    System.out.println("Created 5 entries:");
    System.out.println("e1: " + e1);
    System.out.println("e2: " + e2);
    System.out.println("e3: " + e3);
    System.out.println("e4: " + e4);
    System.out.println("e5: " + e5);

    //Calling the accessor methods for one event
    System.out.println("Calling accessor methods for e1: " + " " + e1.getName() + " " + e1.getStartTime() + " " + e1.getDuration());

    //Calling the .equals and overlaps
    System.out.println("e1 equals e2? " + e1.equals(e2));
    System.out.println("e1 overlaps with e2? " + e1.overlaps(e2));
    System.out.println("e2 overlaps with e3? " + e2.overlaps(e3));

    //Trying out the two static methods in the Calendar class
    System.out.println("\nChecking some times and dates: ");
    System.out.println("Is 0 a valid time?: " + Calendar.isValidTime(0));
    System.out.println("Is 25 a valid time?: " + Calendar.isValidTime(25));

    System.out.println("Is 1/15 a valid date?: " + Calendar.isValidDate(1, 15));
    System.out.println("Is 12/31 a valid date?: " + Calendar.isValidDate(12, 31));
    System.out.println("Is 14/15 a valid date?: " + Calendar.isValidDate(14, 15));
    System.out.println("Is 2/30 a valid date?: " + Calendar.isValidDate(2, 30));


    //Trying out the methods in the calendar class
    System.out.println("\nCreating a new calendar: ");
    Calendar sophieCalendar = new Calendar("Sophie's Calendar", 2021);
    System.out.println(sophieCalendar);

    System.out.println("\nAdding three entries on two different days");

    sophieCalendar.addEntry(2, 14, e1);
    sophieCalendar.addEntry(1, 29, e2);
    sophieCalendar.addEntry(2, 14, e3);

    System.out.println(sophieCalendar);
    sophieCalendar.displayEntries(2, 14);
    sophieCalendar.displayEntries(1, 29);
    sophieCalendar.displayEntries(3, 17); // there shouldn't be any
    sophieCalendar.displayEntries(2, 30); // this is an invalid date

    System.out.println("Event on 2/14 at 9:00: " + sophieCalendar.getEntry(2, 14, 9));
    System.out.println("Event on 2/14 at 10:00: " + sophieCalendar.getEntry(2, 14, 10));

    System.out.println("Number of entries on 2/14: " + sophieCalendar.getNumEntries(2,14) +
                       " for a total duration of " + sophieCalendar.getDurationOfEntriesOnDay(2, 14) + " mins.");
    System.out.println("Number of entries in the calendar: " + sophieCalendar.getNumEntries());

    System.out.println("\nChecking conflicts");
    System.out.println("Does e2 conflict with any entries on 2/14? " + sophieCalendar.isConflicting(2, 14, e2));
    System.out.println("Does e5 conflict with any entries on 2/14? " + sophieCalendar.isConflicting(2, 14, e5));
    System.out.println("Does e1 conflict with any entries on 2/15? " + sophieCalendar.isConflicting(2, 15, e1));

    System.out.println("\nDeleting e1 from 2/14");
    if(sophieCalendar.deleteEntry(2, 14, e1)) {
      System.out.println("As expected, entry was deleted");
    }
    sophieCalendar.displayEntries(2, 14);
    System.out.println(sophieCalendar);


    System.out.println("Trying to Delete e2 from 2/14");
    if(!sophieCalendar.deleteEntry(2, 14, e2)) {
      System.out.println("As expected, entry was not deleted");
    }
    System.out.println(sophieCalendar);

    System.out.println("\nAdding e1 to multiple dates");
    sophieCalendar.addEntry(5, 17, e1);
    sophieCalendar.addEntry(7, 20, e1);
    sophieCalendar.addEntry(9, 4, e1);
    sophieCalendar.addEntry(11, 30, e1);
    System.out.println(sophieCalendar);

    System.out.println("\nDeleting all occurrences of e1");
    sophieCalendar.deleteAllOccurences(e1);
    System.out.println(sophieCalendar);
    sophieCalendar.displayEntries(9, 4);


  }

}
  