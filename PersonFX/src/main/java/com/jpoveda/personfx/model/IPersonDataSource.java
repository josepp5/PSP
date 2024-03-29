package com.jpoveda.personfx.model;

import com.jpoveda.personfx.model.person.Person;

import java.util.List;

public interface IPersonDataSource {

    List<Person> getList() throws DataSourceException;

    void save(List<Person> list) throws DataSourceException;

    void delete(int position) throws DataSourceException;
}
