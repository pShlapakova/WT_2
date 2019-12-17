package beans;

import java.io.Serializable;

/**
 * attraction is a class that describes
 * characteristics for all attractions.
 * An aircraft object encapsulates the state information,
 * such as:
 * <ul>
 *    <li>Type of the current attraction</li>
 *    <li>Rank among visitors</li>
 *    <li>Time of a one ride</li>
 *    <li>Price of a ticket</li>
 * </ul>
 * @author Polina Shlapakova
 * @version 1.0
 */

public final class Attraction
        extends Infrastructure
        implements Comparable<Attraction>, Serializable {
    /** Field type of the attraction*/
    private AttractionType type;

    /** Field rank among visitors*/
    private byte visitorsLove;

    /** Field time of a one ride*/
    private long rideTime;

    /** Field price of a ticket*/
    private int ticketPrice;

    /**
     * Constructor, which calls default constructor
     * of the infrastructure class and sets all the
     * fields in the default values.
     *
     * @see Infrastructure#Infrastructure()
     */
    public Attraction() {
        super();
        type = AttractionType.NO_TYPE;
        visitorsLove = 50;
        rideTime = 0;
    }

    /**
     * Get the type of the attraction.
     * {@link Attraction#type}
     *
     * @return  type of the attraction
     */
    public AttractionType getType() {
        return type;
    }

    /**
     * Set the type of the attraction.
     * {@link Attraction#type}
     *
     * @param type    Type of the attraction
     */
    public void setType(AttractionType type) {
            this.type = type;
    }

    /**
     * Get the rank of the attraction.
     * {@link Attraction#visitorsLove}
     *
     * @return  Rank of the attraction
     */
    public byte getVisitorsLove() {
        return visitorsLove;
    }

    /**
     * Set the height of the steward.
     * {@link Attraction#visitorsLove}
     *
     * @param visitorsLove    Rank of the attraction among visitors
     * @throws IndexOutOfBoundsException    This exception is thrown, when rank is less than 0
     *                                      and above 100.
     */
    public void setVisitorsLove(byte visitorsLove) {
        if ((visitorsLove >= 0) && (visitorsLove <= 100))
            this.visitorsLove = visitorsLove;
        else
            throw new IndexOutOfBoundsException("Rank of the attraction should be less than 100" +
                    " and above 0.");
    }

    /**
     * Get the time of a one ride.
     * {@link Attraction#rideTime}
     *
     * @return  Time of a one ride
     */
    public long getRideTime() {
        return rideTime;
    }

    /**
     * Set the time of a one ride.
     * {@link Attraction#rideTime}
     *
     * @param rideTime    Time of the ride
     * @throws IndexOutOfBoundsException    This exception is thrown, when time is less than 0.
     */
    public void setRideTime(long rideTime) {
        if (rideTime > 0)
            this.rideTime = rideTime;
        else
            throw new IndexOutOfBoundsException("Time should be above 0");
    }

    /**
     * Get the price of a ticket.
     * {@link Attraction#ticketPrice}
     *
     * @return  Price of a ticket
     */
    public int getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Set the price of a ticket.
     * {@link Attraction#ticketPrice}
     *
     * @param ticketPrice    Price of the ticket
     * @throws IndexOutOfBoundsException    This exception is thrown, when price is less than 0.
     */
    public void setTicketPrice(int ticketPrice) {
        if (ticketPrice >= 0)
            this.ticketPrice = ticketPrice;
        else
            throw new IndexOutOfBoundsException("Price should be above 0");
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (obj.getClass() == this.getClass())) {
            Attraction anotherAttraction = (Attraction)obj;
            if ((this.getName() == anotherAttraction.getName()) &&
                    (this.type == anotherAttraction.type))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 37*this.getName().hashCode() + this.type.hashCode();
    }

    @Override
    public String toString() {
        return "Attraction " + this.getName() + "\n   type: " + this.type +
                "\n   territory(x: " + this.getTerritory().getX() + "; y: " + this.getTerritory().getY() +
                "; width: " + this.getTerritory().getWidth() + "; height: " + this.getTerritory().getHeight() +
                ")\n   price of the ticket: " + this.ticketPrice +
                "\n   time of a one ride: " + this.rideTime +
                "\n   pride for the building: " + this.getBuildPrice() +
                "\n   time before the next repair: " + this.getTimeToRepair() +
                "\n   rank: " + this.visitorsLove;
    }

    @Override
    public int compareTo(Attraction anotherAttraction) {
        return this.getName().compareTo(anotherAttraction.getName());
    }
}
