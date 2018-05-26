package epam.chernova.finalproject.command.impl;


import epam.chernova.finalproject.command.ICloseDBCommand;
import epam.chernova.finalproject.exception.ServiceException;
import epam.chernova.finalproject.service.ICloseDB;
import epam.chernova.finalproject.service.impl.CloseDB;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class CloseDBCommand implements ICloseDBCommand {
    private static final Logger LOGGER = Logger.getLogger(CloseDBCommand.class);

    @Override
    public void closeDB() {
        try {
            ICloseDB closeDB = new CloseDB();
            closeDB.closeConnections();
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, "Problem while closing database:" + e.getMessage());
        }
    }
}
