package epam.chernova.finalproject.dao;

import java.sql.ResultSet;
import java.sql.Statement;

public interface AbstractDao  {
    void close(ResultSet resultSet, Statement statement);

}
