/**
 * The track class holds the list of train
 * @author Michael Hom
 * Stony Brook id: 112536073
 * Recitation 09
 */
public class Track {
    Train head;
    Train tail;
    Train cursor;
    Track next;
    Track prev;
    double utilizationRate;
    int trackNumber;

    /**
     * This is the constructor for track
     */
    public Track(){
        head=null;
        tail=null;
        cursor=null;
    }

    /**
     * This method adds the train to the track
     * @param newTrain
     * @throws Exception
     */
    public void addTrain(Train newTrain) throws Exception {
        Train lol=head;
        while(lol!=null) {
            if (newTrain.getArrivalTime() >= lol.getArrivalTime() && newTrain.getArrivalTime() <= lol.getDepartureTime()) {
                throw new Exception("Train not added: There is a Train already scheduled on Track " + trackNumber);
            }
            if(newTrain.getTrainNumber()==lol.getTrainNumber()){
                throw new Exception("They have the same train number");
            }
            lol=lol.getNext();
        }
        Train temp=newTrain;
        String hi=Integer.toString(newTrain.getArrivalTime());
        String yer=Integer.toString(newTrain.getTransferTime());
        String sup=String.format("%1$" + 4 + "s",Integer.valueOf(hi)).replace(' ', '0');

        int hour = Integer.parseInt(sup.substring(0, 2));
        int minute = Integer.parseInt(sup.substring(2));

        if (hour > 23 || minute > 59) {
            throw new Exception("Train not added: Invalid arrival time");
        }
        if (head == null) {
            head = temp;
            cursor = head;
            tail = head;
            return;
        }  if (cursor == tail) {
            cursor.setNext(temp);
            temp.setPrev(cursor);
            cursor = cursor.getNext();
            tail = temp;
            return;

        }
            while(cursor!=null){

                if(newTrain.getArrivalTime()<cursor.getArrivalTime()){
                    temp.setNext(cursor);
                    temp.setPrev(cursor.getPrev());
                    cursor.getPrev().setNext(temp);
                    cursor.setPrev(temp);

                }
                cursor=cursor.getNext();
            }


        }

    /**
     * This method removes the selected train from the track and returns the reference to it
     * @return temp
     * @throws Exception
     */
    public Train removeSelectedTrain() throws Exception{
        Train temp = cursor;
        if (head == null) {
            throw new Exception("There are no values");
        }
        else if(head ==tail){
            head=null;
            cursor = null;
            tail = null;

        }
        else if(cursor==tail){
            tail=tail.getPrev();
            tail.setNext(null);
            cursor=tail;

        }
        else if (cursor==head){
            head=cursor.getNext();
            head.setPrev(null);
            cursor=head;

        }
        else {
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = cursor.getPrev();

        }
        return temp;


    }

    /**
     * This method prints the data of the selected train
     */
    public void printSelectedTrain(){
        System.out.println(cursor.toString());
    }

    /**
     * This method moves the reference of the selected Train node forwards to the next Train if it exists and returns true.
     * If there is no next Train, then the selected Train should remain the same and return false.
     * @return
     */
    public boolean selectNextTrain(){
        if(head==null){
            System.out.println("The head is null");
        }
        if (cursor==tail){
            System.out.println("There is no other value after cursor");
            return false;
        }

            cursor=cursor.getNext();
            return true;

    }

    /**
     *This method moves the reference of the selected Train node backwards to the previous Train
     * @return
     */
    public boolean selectPrevTrain(){
        if(head==null){
            System.out.println("There is nothing in the list.");
        }
        if(cursor==head){
            System.out.println("You cannot go backwards.");
            return false;
        }
            cursor = cursor.getPrev();
            return true;
    }

    /**
     * This is a getter method that returns the trackNumber
     * @return trackNumber
     */
    public int getTrackNumber() {
        return trackNumber;
    }

    /**
     * This is a setter method that sets the Track Number
     * @param trackNumber
     */
    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    /**
     * This is a helper method that helps count the trains for Station Information
     * @return trainCounter
     */
    public int listLength(){
        int trainCounter=0;
        Train temp=head;
        while(temp!=null){
            trainCounter++;
            temp=temp.getNext();

        }
        return trainCounter;
    }

    /**
     * This is a getter method that gets the Utilization Rate
     * @return answer
     */
    public double getUtilizationRate() {
        Train temp = head;
        utilizationRate = 0;
        double answer=0;
        while (temp != null) {
            utilizationRate += temp.getTransferTime();
            temp = temp.getNext();
        }
        answer=(utilizationRate/1440.0)*100.0;
        return (Math.round(answer * 100.0)) / 100.0;
    }

    /**
     * This is a setter method that sets the Utilization Rate
     * @param utilizationRate
     */
    public void setUtilizationRate(double utilizationRate) {
        this.utilizationRate = utilizationRate;
    }

    /**
     * This is a getter method that gets the prev
     * @return prev
     */
    public Track getPrev() {
        return prev;
    }

    /**
     * This is a setter method that sets the prev
     * @param prev
     */
    public void setPrev(Track prev) {
        this.prev = prev;
    }

    /**
     * This is a getter method that gets the Next
     * @return next
     */
    public Track getNext() {
        return next;
    }

    /**
     * This is a setter method that sets the Next
     * @param next
     */
    public void setNext(Track next) {
        this.next = next;
    }

    /**
     * This is a getter method that gets the cursor
     * @return cursor
     */
    public Train getCursor() {
        return cursor;
    }

    /**
     * This is a setter method that sets the cursor
     * @param cursor
     */
    public void setCursor(Train cursor) {
        this.cursor = cursor;
    }

    /**
     * This is a getter method that gets the tail
     * @return tail
     */
    public Train getTail() {
        return tail;
    }

    /**
     * This is a setter method that sets the tail
     * @param tail
     */
    public void setTail(Train tail) {
        this.tail = tail;
    }

    /**
     * This is a getter method that gets the head
     * @return head
     */
    public Train getHead() {
        return head;
    }

    /**
     * This is a setter method that sets the head
     * @param head
     */
    public void setHead(Train head) {
        this.head = head;
    }

    /**
     * This toString method returns a string representation of all the trains on the track
     * @return
     */
    public String toString() {
        Train yer = head;
        String track = "Track " + trackNumber + "(" + getUtilizationRate() + "% Utilization Rate): \n";

        String header = String.format(("%-26s %19s %18s %16s %20s"), "Selected", "Train Number", "Train Destination", "Arrival Time", "Departure Time");
        String bruh = "\n--------------------------------------------------------------------------------------------------------------------------------";
        String table = "";
        while (yer != null) {
            if (yer == cursor) {
                 track = "Track " + trackNumber + "*  (" + getUtilizationRate() + "%): \n";
                String hi=String.format("%1$" + 4 + "s",Integer.valueOf(yer.getArrivalTime())).replace(' ', '0');
                String yuh=String.format("%1$" + 4 + "s",Integer.valueOf(yer.getDepartureTime())).replace(' ', '0');
                table += String.format(("\n %-26s %19d %18s %16s %20s"), "*", yer.getTrainNumber(), yer.getDestination(),hi, yuh);
                yer = yer.getNext();
            } else {
                String hi=String.format("%1$" + 4 + "s",Integer.valueOf(yer.getArrivalTime())).replace(' ', '0');
               String yuh= String.format("%1$" + 4 + "s",Integer.valueOf(yer.getDepartureTime())).replace(' ', '0');
                table += String.format(("\n %-26s %19d %18s %16s %20s"), " ", yer.getTrainNumber(), yer.getDestination(), hi, yuh);
                yer = yer.getNext();
            }
        }
        return track + header + bruh + table;
    }
}
