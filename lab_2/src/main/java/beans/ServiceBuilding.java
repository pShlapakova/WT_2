package beans;

import java.io.Serializable;

public final class ServiceBuilding
        extends Infrastructure
        implements Comparable<ServiceBuilding>, Serializable {
    private String service;
    private int price;

    public ServiceBuilding() {
        super();
        service = "";
        price = 0;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        if (service == null)
            this.service = "";
        else
            this.service = service;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price >= 0)
            this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (obj.getClass() == this.getClass())) {
            ServiceBuilding anotherBuilding = (ServiceBuilding)obj;
            if ((this.getName() == anotherBuilding.getName()) &&
                    (this.service == anotherBuilding.service) &&
                    (this.price == anotherBuilding.price))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 37*(37*this.getName().hashCode() + this.service.hashCode()) + this.price;
    }

    @Override
    public String toString() {
        return this.getName() + "\n   service: " + this.service + "\n   price: " + this.price +
                "\n   territory(x: " + this.getTerritory().getX() + "; y: " + this.getTerritory().getY() +
                "; width: " + this.getTerritory().getWidth() + "; height: " + this.getTerritory().getHeight() +
                "\n   pride for the building: " + this.getBuildPrice() +
                "\n   time before the next repair: " + this.getTimeToRepair();
    }

    @Override
    public int compareTo(ServiceBuilding anotherBuilding) {
        return this.getName().compareTo(anotherBuilding.getName());
    }
}
