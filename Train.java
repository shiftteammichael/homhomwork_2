/**
 * The Train Class contains the information for a train approaching a station
 * @author Michael Hom
 * Stony Brook id:112536073
 * Recitation 09
 */
public class Train {
    Train next;
    Train prev;
    int trainNumber;
    String destination;
    int arrivalTime;
    int transferTime;
    int departureTime;

    /**
     * This is the default constructor for the train class
     */
    public Train(){

    }

    /**
     * This is the constructor for the train that sets the train information
     * @param trainNumber
     * @param destination
     * @param arrivalTime
     * @param transferTime
     */
    public Train(int trainNumber,String destination,int arrivalTime,int transferTime){
        this.trainNumber=trainNumber;
        this.destination=destination;
        this.arrivalTime=arrivalTime;
        this.transferTime=transferTime;

    }

    /**
     * This is a setter method that sets the next Train in the list
     * @param nextTrain
     */
    public void setNext(Train nextTrain){
        this.next=nextTrain;
    }

    /**
     * This is a setter method that sets the previous Train in the list
     * @param prevTrain
     */
    public void setPrev(Train prevTrain){
        this.prev=prevTrain;
    }

    /**
     * This is a getter method that returns the next train in the list
     * @return next
     */
    public Train getNext(){
        if(next==null){
            return null;
        }
        return next;
    }

    /**
     * This is a getter method that returns the previous train in the List
     * @return
     */
    public Train getPrev(){
        if(prev==null){
            return null;
        }
        return prev;
    }

    /**
     * This is a getter method that returns the transfer time of the train
     * @return transferTime
     */
    public int getTransferTime() {
        return transferTime;
    }
    public int getDepartureTime(){
        return departureTime;
    }
    public void setDepartureTime(int departureTime){
        this.departureTime=departureTime;
    }

    /**
     * This is a setter method that sets the transfer time
     * @param transferTime
     */
    public void setTransferTime(int transferTime) {
        this.transferTime = transferTime;
    }

    /**
     * This is a getter method that gets arrivalTime
     * @return
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * This is a setter method that sets the arrivalTime
     * @param arrivalTime
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * This is a getter method that gets the destination of the train
     * @return destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * This is a setter method that sets the destination of the train
     * @param destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * This is a getter method that sets the train number
     * @return
     */
    public int getTrainNumber() {
        return trainNumber;
    }

    /**
     * This is a setter method that sets the train number
     * @param trainNumber
     */
    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    /**
     * This method that checks if the Train object is equal to it
     * @param o
     * @return
     */
    public boolean equals(Object o){
        Train yer= (Train) o;
        return (yer.trainNumber==this.trainNumber);
    }

    /**
     * This toString method returns a texual representation of all the information for this train object
     * @return selectedTrain,trainNum,trainDestination,arrival,departure
     */
    public String toString(){
        String selectedTrain= "Selected Train: "+ "\n";
        String trainNum= "Train Number: "+trainNumber+ "\n";
        String trainDestination="Train Destination "+destination+ "\n";
        String hi=String.format("%1$" + 4 + "s",Integer.valueOf(arrivalTime)).replace(' ', '0');
        String yuh=String.format("%1$" + 4 + "s",Integer.valueOf(departureTime)).replace(' ', '0');
        String arrival="Arrival Time: "+hi+ "\n";
        String departure="Departure Time: "+yuh+"\n";
        return selectedTrain+trainNum+trainDestination+arrival+departure;
    }
}
