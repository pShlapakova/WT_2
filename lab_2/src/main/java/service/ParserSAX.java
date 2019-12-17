package service;

import beans.*;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import tags.*;

public class ParserSAX
        extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();

    private ArrayList<Attraction> attractionList = new ArrayList<Attraction>();
    private Attraction attraction;
    private ArrayList<ServiceBuilding> serviceBuildingList = new ArrayList<ServiceBuilding>();
    private ServiceBuilding serviceBuilding;
    private ArrayList<Territory> territoryList = new ArrayList<Territory>();;
    private Territory territory;

    private StringBuilder text;
    private EntityTag entityTag = EntityTag.NONE;

    public ArrayList<Attraction> getAttractionList() {
        return attractionList;
    }

    public ArrayList<ServiceBuilding> getServiceBuildingList() {
        return serviceBuildingList;
    }

    public ArrayList<Territory> getTerritoryList() {
        return territoryList;
    }

    @Override
    public void startDocument() throws SAXException {
        logger.info("Start parsing...");
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("Finish parsing");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        text = new StringBuilder();
        EntityTag rootTag = EntityTag.NONE;
        try {
            rootTag = EntityTag.valueOf(qName.toUpperCase());
            entityTag = rootTag;
        } catch (IllegalArgumentException e) {}

        switch (rootTag) {
            case ATTRACTION:
                attraction = new Attraction();
                attraction.setId(Integer.parseInt(attributes.getValue("id")));
                break;
            case SERVICEBUILDING:
                serviceBuilding = new ServiceBuilding();
                serviceBuilding.setId(Integer.parseInt(attributes.getValue("id")));
                break;
            case TERRITORY:
                territory = new Territory();
                territory.setId(Integer.parseInt(attributes.getValue("id")));
                break;
        }
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String qNameUpperCase = qName.toUpperCase();
        switch (entityTag) {
            case ATTRACTION: {
                AttractionTag attractionTag = AttractionTag.NONE;
                try {
                    attractionTag = AttractionTag.valueOf(qNameUpperCase);
                } catch (IllegalArgumentException e) { }

                switch (attractionTag) {
                    case NAME:
                        attraction.setName(text.toString());
                        break;
                    case BUILDPRICE:
                        attraction.setBuildPrice(Integer.parseInt(text.toString()));
                        break;
                    case TIMETOREPAIR:
                        attraction.setTimeToRepair(Long.parseLong(text.toString()));
                        break;
                    case TICKETPRICE:
                        attraction.setTicketPrice(Integer.parseInt(text.toString()));
                        break;
                    case RIDETIME:
                        attraction.setRideTime(Long.parseLong(text.toString()));
                        break;
                    case VISITORSLOVE:
                        attraction.setVisitorsLove(Byte.parseByte(text.toString()));
                        break;
                    case TYPE:
                        attraction.setType(AttractionType.valueOf(text.toString()));
                        break;
                    case TERRITORYID:
                        attraction.setTerritoryId(Integer.parseInt(text.toString()));
                        break;
                    default:
                        attractionList.add(attraction);
                        attraction = null;
                        entityTag = EntityTag.NONE;
                        break;
                }
                break;
            }
            case SERVICEBUILDING: {
                ServiceBuildingTag serviceBuildingTag = ServiceBuildingTag.NONE;
                try {
                    serviceBuildingTag = ServiceBuildingTag.valueOf(qNameUpperCase);
                } catch (IllegalArgumentException e) { }

                switch (serviceBuildingTag) {
                    case NAME:
                        serviceBuilding.setName(text.toString());
                        break;
                    case BUILDPRICE:
                        serviceBuilding.setBuildPrice(Integer.parseInt(text.toString()));
                        break;
                    case TIMETOREPAIR:
                        serviceBuilding.setTimeToRepair(Long.parseLong(text.toString()));
                        break;
                    case TERRITORYID:
                        serviceBuilding.setTerritoryId(Integer.parseInt(text.toString()));
                        break;
                    case SERVICE:
                        serviceBuilding.setService(text.toString());
                        break;
                    case PRICE:
                        serviceBuilding.setPrice(Integer.parseInt(text.toString()));
                        break;
                    default:
                        serviceBuildingList.add(serviceBuilding);
                        serviceBuilding = null;
                        entityTag = EntityTag.NONE;
                        break;
                }
                break;
            }
            case TERRITORY: {
                TerritoryTag territoryTag = TerritoryTag.NONE;
                try {
                    territoryTag = TerritoryTag.valueOf(qNameUpperCase);
                } catch (IllegalArgumentException e) { }

                switch (territoryTag) {
                    case X:
                        territory.setX(Integer.parseInt(text.toString()));
                        break;
                    case Y:
                        territory.setY(Integer.parseInt(text.toString()));
                        break;
                    case WIDTH:
                        territory.setWidth(Integer.parseInt(text.toString()));
                        break;
                    case HEIGHT:
                        territory.setHeight(Integer.parseInt(text.toString()));
                        break;
                    default:
                        territoryList.add(territory);
                        territory = null;
                        entityTag = EntityTag.NONE;
                        break;
                }
            }
        }
    }
}
