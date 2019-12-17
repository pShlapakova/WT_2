package beans;

import java.io.Serializable;

abstract class Infrastructure
        implements Serializable {
    private int id;
    private String name;
    private int buildPrice;
    private long timeToRepair;
    private Territory territory;
    private int territoryId;

    public Infrastructure() {
        name = "";
        buildPrice = 0;
        timeToRepair = Integer.MAX_VALUE;
        territoryId = 0;
        territory = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            this.name = "";
        else
            this.name = name;
    }

    public int getBuildPrice() {
        return buildPrice;
    }

    public void setBuildPrice(int priceBuild) {
        if (priceBuild > 0)
            this.buildPrice = priceBuild;
    }

    public long getTimeToRepair() {
        return timeToRepair;
    }

    public void setTimeToRepair(long timeToRepair) {
        this.timeToRepair = timeToRepair;
    }

    public int getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(int territoryId) {
        this.territoryId = territoryId;
    }


    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public void build(Territory territory) {
        this.territory = territory;
        System.out.println("Build " + name);
    }

    public void destroy() {
        this.territory = null;
        System.out.println("Destroy " + name);
    }
}
