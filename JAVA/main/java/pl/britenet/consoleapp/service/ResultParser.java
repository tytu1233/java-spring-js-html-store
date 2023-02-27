package pl.britenet.consoleapp.service;

import java.sql.ResultSet;

public interface ResultParser<T> {
    T parse(ResultSet resultSet);
}
