package com.jpoveda.personfx.model;

import com.jpoveda.personfx.model.person.Person;
import com.jpoveda.personfx.model.person.PersonType;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PersonFileDataSource implements IPersonDataSource {
    private String fileName = "";

    public PersonFileDataSource(String fileName){
        this.fileName = fileName;
    }

    @Override
    public List<Person> getList() throws DataSourceException {
        try {
            return Files
                    // lines gets the file rows one by one, doing the code below for each line of the file
                    .lines(Paths.get(this.fileName))
                    // map takes one element and returns another one
                    .map(line -> new Person(
                            line.split(";")[0],
                            line.split(";")[1],
                            Integer.parseInt(line.split(";")[2]),
                            PersonType.valueOf(line.split(";")[3])
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public void save(List<Person> list) throws DataSourceException {
        try (PrintWriter pw = new PrintWriter(this.fileName)) {
            // two ways to write the list in the file with for
            //list.stream().forEach(f -> pw.println(f.toString()));
            // or
            for (Person person: list) {
                pw.println(person.toString());
            }
        } catch (Exception e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public void delete(int position) throws DataSourceException {
        List<Person> l = this.getList();
        l.remove(position);
        this.save(l);
    }
}
