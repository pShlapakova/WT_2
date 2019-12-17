package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class AmusementPark
        implements Serializable {
    private ArrayList<Attraction> attractions;
    private ArrayList<ServiceBuilding> serviceBuildings;

    public AmusementPark() {
        attractions = new ArrayList<Attraction>();
        serviceBuildings = new ArrayList<ServiceBuilding>();
    }

    public ArrayList<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(ArrayList<Attraction> attractions) {
        this.attractions = attractions;
    }

    public int getNewAttractionID(){
        if (attractions.isEmpty()){
            return 1;
        }else{
            Collections.sort(attractions);
            return attractions.get(attractions.size()-1).getId() + 1;
        }
    }

    public ArrayList<ServiceBuilding> getServiceBuildings() {
        return serviceBuildings;
    }

    public void setServiceBuildings(ArrayList<ServiceBuilding> serviceBuildings) {
        this.serviceBuildings = serviceBuildings;
    }

    public int getNewServiceBuildingID(){
        if (serviceBuildings.isEmpty()){
            return 1;
        }else{
            Collections.sort(serviceBuildings);
            return serviceBuildings.get(serviceBuildings.size()-1).getId() + 1;
        }
    }
}
