package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import beans.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import exception.*;

public class TransferService {
    private static final Logger logger = LogManager.getLogger();
    private static TransferService instance = null;

    private TransferService() {
    }

    public static TransferService getInstance() {
        if (instance == null) {
            instance = new TransferService();
            logger.info("Get transfer service");
        }
        return instance;
    }

    private boolean isIdExist(Connection connection, String tableName, int id) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        statement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        return resultSet.first();
    }


    public void transferAttraction(Connection connection, ArrayList<Attraction> attractionList) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        for (Attraction attraction : attractionList) {
            try {
                if (isIdExist(connection, "attractions", attraction.getId()))
                    throw new ExistedEntityException("Entity with id = " + attraction.getId() + " is already exist at the table \"attractions\".");
                statement = connection.prepareStatement(
                        "INSERT INTO attractions (id, name, build_price, time_to_repair,  type, visitors_love, ride_time, ticket_price, territory_id) VALUES (?,?,?,?,?,?,?,?,?);");
                statement.setInt(1, attraction.getId());
                statement.setString(2, attraction.getName());
                statement.setInt(3, attraction.getBuildPrice());
                statement.setLong(4, attraction.getTimeToRepair());
                statement.setString(5, attraction.getType().toString());
                statement.setByte(6, attraction.getVisitorsLove());
                statement.setLong(7, attraction.getRideTime());
                statement.setInt(8, attraction.getTicketPrice());
                statement.setInt(9, attraction.getTerritoryId());
                statement.executeUpdate();

            } catch (SQLException | ExistedEntityException e) {
                logger.error(e.getMessage());
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                    if (statement != null)
                        statement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    public void transferServiceBuilding(Connection connection, ArrayList<ServiceBuilding> serviceBuildingList) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        for (ServiceBuilding serviceBuilding : serviceBuildingList) {
            try {
                if (isIdExist(connection, "serviceBuildings", serviceBuilding.getId()))
                    throw new ExistedEntityException("Entity with id = " + serviceBuilding.getId() + " is already exist at table \"serviceBuildings\".");
                statement = connection.prepareStatement(
                        "INSERT INTO serviceBuildings (id, name, build_price, time_to_repair,  service, price, territory_id) VALUES (?,?,?,?,?,?,?);");
                statement.setInt(1, serviceBuilding.getId());
                statement.setString(2, serviceBuilding.getName());
                statement.setInt(3, serviceBuilding.getBuildPrice());
                statement.setLong(4, serviceBuilding.getTimeToRepair());
                statement.setString(5, serviceBuilding.getService());
                statement.setInt(6, serviceBuilding.getPrice());
                statement.setInt(7, serviceBuilding.getTerritoryId());
                statement.executeUpdate();

            } catch (SQLException | ExistedEntityException e) {
                logger.error(e.getMessage());
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                    if (statement != null)
                        statement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    public void transferTerritory(Connection connection, ArrayList<Territory> territoryList) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        for (Territory territory : territoryList) {
            try {
                if (isIdExist(connection, "territory", territory.getId()))
                    throw new ExistedEntityException("Entity with id=" + territory.getId() + " is already exists at table \"territory\".");
                statement = connection.prepareStatement(
                        "INSERT INTO territory (id, x, y, width, height) VALUES (?,?,?,?,?);");
                statement.setInt(1, territory.getId());
                statement.setInt(2, territory.getX());
                statement.setInt(3, territory.getY());
                statement.setInt(4, territory.getWidth());
                statement.setInt(5, territory.getHeight());
                statement.executeUpdate();

            } catch (SQLException | ExistedEntityException e) {
                logger.error(e.getMessage());
            } finally {
                try {
                    if (resultSet != null)
                        resultSet.close();
                    if (statement != null)
                        statement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
