/***
 * The Station class contains a list of Track
 * @author Michael Hom
 * Stony brook id: 112536073
 * Recitation 09
 */

import java.util.Scanner;
public class Station {
    Track head;
    Track tail;
    Track cursor;

    /**
     * This is the constructor for the Station class
     */
    public Station() {
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * This is a getter method that gets the cursor
     * @return cursor
     */
    public Track getCursor() {
        return cursor;
    }

    /**
     * This is a setter method that sets the cursor
     * @param cursor
     */
    public void setCursor(Track cursor) {
        this.cursor = cursor;
    }

    /**
     * This is a getter method that gets the head
     * @return head
     */
    public Track getHead() {
        return head;
    }

    /**
     * This is a setter method that sets the head
     * @param head
     */
    public void setHead(Track head) {
        this.head = head;
    }

    /**
     * This is a getter method that gets the tail
     * @return
     */
    public Track getTail() {
        return tail;
    }
    /**
     * This is a setter method that sets the tail
     */
    public void setTail(Track tail) {
        this.tail = tail;
    }

    /**
     * This method adds a new track to the station
     * @param newTrack
     */
    public void addTrack(Track newTrack) {
        Track brack = newTrack;

        if (head == null) {
            head = brack;
            cursor = head;
            tail = head;


        } else if (cursor == tail) {
            cursor.setNext(brack);
            brack.setPrev(cursor);
            cursor = cursor.getNext();
            tail = brack;

        } else {
            while (cursor.getNext() != null) {
                if (brack.getTrackNumber() < cursor.getTrackNumber()) {
                    brack.setNext(cursor);
                    brack.setPrev(cursor.getPrev());
                    cursor.getPrev().setNext(brack);
                    brack.setPrev(brack);
                }

                cursor = cursor.getNext();
            }
        }
    }

    /**
     * This method removes the selected track from the station and returns it
     * @return temp
     * @throws Exception
     */
    public Track removeSelectedTrack() throws Exception {
        Track temp = cursor;
        if (head == null) {
            throw new Exception("There are no tracks to be removed");
        } else if (head == tail) {
            head = null;
            cursor = null;
            tail = null;

        } else if (cursor == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
            cursor = tail;

        } else if (cursor == head) {
            head = cursor.getNext();
            head.setPrev(null);
            cursor = head;

        } else {
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = cursor.getPrev();

        }
        System.out.println("Closed Track" + temp.getTrackNumber());
        return temp;
    }

    /**
     * This method moves the reference of the selected node to the node which has the same track number
     * @param trackToSelect
     * @return
     */
    public boolean selectTrack(int trackToSelect) {
        Track bursor = head;
        while (bursor != null) {
            if (bursor.getTrackNumber() == trackToSelect) {
                cursor = bursor;
                return true;
            }
            bursor = bursor.getNext();
        }
        return false;
    }

    /**
     * This method prints out the selected track list
     */
    public void printSelectedTrack() {
        System.out.println(cursor.toString());
    }

    /**
     * This method prints all the track list in the station
     */
    public void printAllTracks() {
        Track temp = head;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.getNext();
        }
    }

    /**
     * This is a helper method that helps count the track Number for the toString
     * @return trackNumber
     */
    public int listLength(){
        int trackNumber=0;
        Track temp=head;
        while(temp!=null){
            trackNumber++;
            temp=temp.getNext();
        }
        return trackNumber;
    }

    /**
     * The toString method returns a neatly formatted representation of this Station object
     * @return
     */
    public String toString() {
        Track temp = head;
        String table = "";
        String header = "Station (" + listLength() + "):";

        while (temp != null) {
            header += "\n Track" + temp.getTrackNumber() + ": " + temp.listLength() + " trains arriving (" + temp.getUtilizationRate() + "% Utilization Rate )";
            temp = temp.getNext();
        }
        return table + header;

    }

    /**
     * This is the main method that allows the user to interact with Trains and Tracks at the Station
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("|-----------------------------------------------------------------------------|");
        System.out.println("|  Train Options \t \t \t \t \t  |  Train Options \t \t \t \t \t \t  |");
        System.out.println("|     A. Add new Train \t \t \t \t  |     TA. Add Track \t \t \t \t \t  |");
        System.out.println("|     N. Select next Train \t \t \t  |     TA. Remove Selected Track \t \t  |");
        System.out.println("|     V. Select previous Train \t \t  |     TS. Switch Track \t \t \t \t  |");
        System.out.println("|     R. Remove selected Train \t \t  |     TPS. Print Selected Track \t \t  |");
        System.out.println("|     P. Print selected Train \t \t  |     TPA. Print all Tracks \t \t \t  |");
        System.out.println("|-----------------------------------------------------------------------------|");
        System.out.println("|  Station Options \t \t \t \t \t \t \t \t \t \t \t \t \t \t      |");
        System.out.println("|    SI. Print Station Information \t \t\t \t \t \t\t \t \t \t \t  |");
        System.out.println("|     Q. Quit \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t  |");
        System.out.println("|-----------------------------------------------------------------------------|");
        Station test = new Station();

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an operation: ");
            String input = in.nextLine();
            if (input.equalsIgnoreCase("A")) {
                try {
                    System.out.println("Enter train number: ");
                    int trainNumber = in.nextInt();
                    System.out.println("Enter train destination: ");
                    in.nextLine();
                    String trainDestination = in.nextLine();
                    System.out.println("Enter train arrival time: ");
                    String trainArrivaltime = in.nextLine();
                    int arrive = Integer.parseInt(trainArrivaltime);
                    System.out.println("Enter train transfer time: ");
                    String trainTransfertime = in.nextLine();
                    int transfer = Integer.parseInt(trainTransfertime);

                    int hour=arrive/100;
                    int minutes=arrive%100;
                    int departure = (hour*60)+minutes+transfer;
                    int hourback=departure/60;
                    int minsback=departure%60;
                    String finish=String.format("%02d%02d", hourback,minsback);

                    int done=Integer.parseInt(finish);

                    if(test.getCursor()==null){
                        System.out.println("There are no tracks for the train to be added");
                    }
                    else {

                        Train yer = new Train(trainNumber, trainDestination, arrive, done);
                        yer.setDepartureTime(done);
                        yer.setTransferTime(transfer);
                        test.getCursor().addTrain(yer);
                        System.out.println("Train No. " + trainNumber + " to " + trainDestination + " added to Track " + test.getCursor().getTrackNumber());

                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());


                }
            }

                if (input.equalsIgnoreCase("N")) {
                    if (test.getCursor().selectNextTrain()) {
                        System.out.println("Cursor has been moved to next train");
                    }
                }
                if (input.equalsIgnoreCase("V")) {

                    if (test.getCursor().selectPrevTrain()) {
                        System.out.println("Cursor has been moved to the previous train");
                    }
                }
                if (input.equalsIgnoreCase("R")) {
                    try {
                        test.getCursor().removeSelectedTrain();
                        System.out.println("Train No." + test.getCursor().getTrackNumber() + "to " + test.getCursor().getCursor().getDestination());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if (input.equalsIgnoreCase("P")) {
                    test.getCursor().printSelectedTrain();
                }
                if (input.equalsIgnoreCase("TPS")) {
                    test.printSelectedTrack();
                }
                if (input.equalsIgnoreCase("TPA")) {
                    test.printAllTracks();
                }
                if (input.equalsIgnoreCase("TS")) {
                    System.out.println("Enter the Track number: ");
                    int track1 = in.nextInt();
                    if (test.selectTrack(track1)) {
                        System.out.println("Switch to Track" + track1);
                    } else {
                        System.out.println("Could not switch tracks: Track" + track1 + " does not exist.");
                    }

                }
                if (input.equalsIgnoreCase("TA")) {
                    try {
                        System.out.println("Enter track number");
                        Track yert = new Track();
                        int track1 = in.nextInt();
                        yert.setTrackNumber(track1);
                        test.addTrack(yert);
                        Track d = test.getHead();
                        while (d.getNext() != null) {
                            if (d.getTrackNumber() == (yert.getTrackNumber())) {
                                throw new Exception("Track not added: Track " + yert.getTrackNumber() + " already exists.");
                            }
                            d = d.getNext();
                        }

                        System.out.println("Track " + track1 + " added to the station");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if (input.equalsIgnoreCase("TR")) {
                    try {
                        test.removeSelectedTrack();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if (input.equalsIgnoreCase("si")) {
                    System.out.println(test.toString());
                }
                if (input.equalsIgnoreCase("Q")) {
                    System.exit(0);
                }

            }
        }
    }

