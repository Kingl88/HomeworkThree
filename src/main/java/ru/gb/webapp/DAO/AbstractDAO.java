package ru.gb.webapp.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AbstractDAO<T> { //интерфейс для работы с базой данных.

    T findById(Long id) throws SQLException; //поиск по Id

    List<T> findAll() throws SQLException; //получение списка

    void deleteById(Long id) throws SQLException; //удаление

    T saveOrUpdate(T t) throws SQLException; //сохранить или обновить данные


}
