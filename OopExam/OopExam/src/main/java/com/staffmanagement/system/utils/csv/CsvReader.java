package com.staffmanagement.system.utils.csv;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.staffmanagement.system.entities.Employee;
import com.staffmanagement.system.utils.interfaces.Reader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvReader implements Reader {
        private final File file;
        public CsvReader(File file) {
        this.file = file;
    }

    @Override
    public List<Employee> readFile() {
        try (CSVReader csvReader = new CSVReader(new FileReader(file.getPath()))) {
            csvReader.skip(1);
            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(csvReader)
                    .withType(Employee.class)
                    .build();

            return csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public <T> List<T> readFile(Class<T> valueType) {
        return null;
    }
}
