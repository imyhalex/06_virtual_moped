package edu.nyu.cs.assignment6;

/**
 * A virtual moped, roaming the streets of New York.
 * The signatures of a few methods are given and must be completed and used as
 * indicated.
 * Create as many additional properties or methods as you want, as long as the
 * given methods behave as indicated in the instructions.
 * Follow good object-oriented design, especially the principles of abstraction
 * (i.e. the black box metaphor) and encapsulation (i.e. methods and properties
 * belonging to specific objects), as we have learned them.
 * The rest is up to you.
 */
public class Moped {
    /**
     * Constructs a moped initialized to 10th St. and 5th Ave., facing South.
     * This constructor should not take in any arguments and utilize the other 
     * constructor.
     */
     private int street;
     private int avenue;
     private String direction;

     // Create constants
     private static final int GAS_CONSUM_PER_MOVE = 10;

     // Create Bounds of map
     private static final int MAX_STREET = 200;
     private static final int MIN_STREET = 1;
     private static final int MAX_AVE = 10;
     private static final int MIN_AVE = 1;
     
     // Gases tank and current gases
     private static final int MAX_GAS = 100;
     private int gases = 100;
     
     // data field for parking with default false
     private boolean isMove = true;

     public Moped() {
        // Start the moped life at 10th St. and 5th Ave., facing South.
        this.street = 10;
        this.avenue = 5;
        this.direction = "south";
     }

     /**
     * Constructs a moped initialized to the provided parameters
     * 
     * @param street int the street the moped is initially placed at
     * @param avenue int avenue the moped is initially placed at
     * @param direction String "north","south","east","west" indicating the initial direction of the moped
     */

     public Moped(int street, int avenue, String direction) {
        // please fill this out
        this.street = street;
        this.avenue = avenue;
        this.direction = direction;
     }

    /**
     * Sets the orientation of the moped to a particular cardinal direction.
     * 
     * @param orientation A string representing which cardinal direction at which to
     *                    set the orientation of the moped. E.g. "north", "south",
     *                    "east", or "west".
     */
    public void setOrientation(String orientation) {
        this.direction = orientation;
    }

    /**
     * Returns the current orientation of the moped, as a lowercase String.
     * E.g. "north", "south", "east", or "west".
     * 
     * @return The current orientation of the moped, as a lowercase String.
     */
    public String getOrientation() {
        return this.direction; 
    }

    /**
     * Prints the current location, by default exactly following the format:
     * Now at 12th St. and 5th Ave, facing South.
     *
     * If the current location is associated with location-based advertising, this
     * method should print exactly following format:
     * Now at 12th St. and 4th Ave, facing West. Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?
     * 
     * Note that the suffixes for the numbers must be correct: i.e. the "st" in
     * "1st", "nd" in "2nd", "rd" in "3rd", "th" in "4th", etc, must be correct.
     */
    public void printLocation() {
        String suffixSt = getSuffix(this.street), suffixAve = getSuffix(this.avenue), adveString = "";
        if (this.street == 12 && this.avenue == 4) 
            adveString = "Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?";
        System.out.println("Now at " + this.street + suffixSt + ". and " + this.avenue + suffixAve + ", facing " + this.getOrientation() + ". " + adveString);
    }

    /**
     * Handles the command, `turn left`.
     * Causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each turn, and doesn't turn unless there is
     * sufficient gas and it isn't parked, as according to the instructions.
     * This method must not print anything.
     */
    public void turnLeft() {
        int[] arr = getLocation();
        if (this.getGasLevel() != 0 && isMove) {
            switch (this.direction) {
                case "north": 
                    if (inBound(new int[] {arr[0], arr[1] + 1})) arr[1]++;
                    this.direction = "west";
                    break;
                case "south":
                    if (inBound(new int[] {arr[0], arr[1] - 1})) arr[1]--;
                    this.direction = "east";
                    break;
                case "west":
                    if (inBound(new int[] {arr[0] - 1, arr[1]})) arr[0]--;
                    this.direction = "south";
                    break;
                case "east":
                    if (inBound(new int[] {arr[0] + 1, arr[1]})) arr[0]++;
                    this.direction = "north";
                    break;
            }
            this.setLocation(arr);
            gases -= GAS_CONSUM_PER_MOVE;
        }
    }

    /**
     * Handles the command, `turn right`.
     * Causes the moped to face the appropriate new cardinal direction.
     * Consumes gas with each turn, and doesn't turn unless there is
     * sufficient gas and isn't parked, as according to the instructions.
     * This method must not print anything.
     */
    public void turnRight() {
        int[] arr = getLocation();
        if (this.getGasLevel() != 0 && isMove) {
            switch (this.direction) {
                case "north": 
                    if (inBound(new int[] {arr[0], arr[1] - 1})) arr[1]--;
                    this.direction = "east";
                    break;
                case "south":
                    if (inBound(new int[] {arr[0], arr[1] + 1})) arr[1]++;
                    this.direction = "west";
                    break;
                case "west":
                    if (inBound(new int[] {arr[0] + 1, arr[1]})) arr[0]++;
                    this.direction = "north";
                    break;
                case "east":
                    if (inBound(new int[] {arr[0] - 1, arr[1]})) arr[0]--;
                    this.direction = "south";
                    break;
            }
            this.setLocation(arr);
            gases -= GAS_CONSUM_PER_MOVE;
        }
    }

    /**
     * Handles the command,`straight on`.
     * Moves the moped one block straight ahead.
     * Consumes gas with each block moved, and doesn't move unless there is
     * sufficient gas and isn't parked, as according to the instructions.
     * This method must not print anything.
     */
    public void goStraight() {

    }

    /**
     * Handles the command,`back up`.
     * Moves the moped one block backwards, but does not change the cardinal
     * direction the moped is facing.
     * Consumes gas with each block moved, and doesn't move unless there is
     * sufficient gas and isn't parked, as according to the instructions.
     * This method must not print anything.
     */
    public void goBackwards() {

    }

    /**
     * Handles the command,`how we doin'?`.
     * This method must not print anything.
     * 
     * @return The current gas level, as an integer from 0 to 100.
     */
    public int getGasLevel() {
        return this.gases;
    }

    /**
     * Prints the current gas level, by default exactly following the format:
     * The gas tank is currently 85% full.
     *
     * If the moped is out of gas, this method should print exactly following
     * format:
     * We have run out of gas. Bye bye!
     */
    public void printGasLevel() {
        if (this.gases == 0) System.out.println("We have run out of gas. Bye bye!");
        else {
            int percentage = gases / MAX_GAS * 100;
            System.out.println("The gas tank is currently " + percentage + "% full.");
        }
    }

    /**
     * Handles the command, `fill it up`.
     * This method must not print anything.
     * Fills the gas level to the maximum.
     */
    public void fillGas() {
        this.gases = MAX_GAS;
    }

    /**
     * Handles the command, `park`.
     * Prints out the statement "We have parked." and makes the vehicle park.
     */
    public void park() {
        System.out.println("We have parked.");
        this.isMove = !isMove;
    }

    /**
     * Handles the command, `go to Xi'an Famous Foods`
     * Causes the moped to self-drive, block-by-block, to 8th Ave. and 15th St.
     * Consumes gas with each block, and doesn't move unless there is sufficient
     * gas, as according to the instructions.
     */
    public void goToXianFamousFoods() {

    }

    /**
     * Generates a string, containing a list of all the user commands that the
     * program understands.
     * 
     * @return String containing commands that the user can type to control the
     *         moped.
     */
    public String getHelp() {
        return ""; // dummy return statement
    }

    /**
     * Sets the current location of the moped.
     * 
     * @param location an int array containing the new location at which to place
     *                 the moped, in the order {street, avenue}.
     */
    public void setLocation(int[] location) {
        this.street = location[0];
        this.avenue = location[1];
    }

    /**
     * Gets the current location of the moped.
     * 
     * @return The current location of the moped, as an int array in the order
     *         {street, avenue}.
     */
    public int[] getLocation() {
        return new int[] {this.street, this.avenue}; // dummy return statement
    }

    /*some private methods */
    // Implement a method to implement the suffix of street and avenue
    private String getSuffix(int number) {
        if (number >= 11 && number <= 13) return "th";
        switch (number % 10) {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default: return "th";
        }
    }

    // Constuct a mwthod to check is car moving in bound
    private boolean inBound(int[] arr) {
        boolean streetBound = arr[0] <= MAX_STREET && arr[0] >= MIN_STREET;
        boolean aveBound = arr[1] <= MAX_AVE && arr[1] >= MIN_AVE;
        return streetBound && aveBound;
    }
}
