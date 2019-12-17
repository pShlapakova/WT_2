
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import exception.*;
import service.*;

public class Main {
    private final static String xmlFile = "src/main/resources/data.xml";
    private final static String xsdFile = "src/main/resources/schema.xsd";

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws SAXException, IOException {

        logger.info("Start...");

        try {
            ValidatorXSD.validate(new File(xmlFile), new File(xsdFile));
        } catch (InvalidException e) {
            logger.error(e.getMessage());
            return;
        }

        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        ParserSAX saxParser = new ParserSAX();
        xmlReader.setContentHandler(saxParser);
        xmlReader.parse(new InputSource("src/main/resources/data.xml"));

        TransferService transferService = TransferService.getInstance();

        Connection connection = null;
        DBService dbManager = DBService.getInstance();
        try {
            connection = dbManager.getConnection();
            transferService.transferAttraction(connection, saxParser.getAttractionList());
            transferService.transferServiceBuilding(connection, saxParser.getServiceBuildingList());
            transferService.transferTerritory(connection, saxParser.getTerritoryList());
        } catch (DBException e) {
            logger.error(e.getMessage());
        }

    }
}
