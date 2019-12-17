package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import exception.InvalidException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorXSD {
    private static final Logger logger = LogManager.getLogger();

    public static void validate(File XMLFile, File XSDFile) throws InvalidException {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(XSDFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(XMLFile));
            logger.info("Is valid");
        } catch (IOException e){
            logger.error("Have an exception during the verification" + e.getMessage());
            throw new InvalidException("Have an exception during the verification" + e.getMessage());
        } catch (SAXException e) {
            logger.error("InvalidException: XML-file " + XMLFile.getName() + " is invalid: " + e.getMessage());
            throw new InvalidException("XML-file " + XMLFile.getName() + " is invalid: " + e.getMessage());
        }
    }
}
