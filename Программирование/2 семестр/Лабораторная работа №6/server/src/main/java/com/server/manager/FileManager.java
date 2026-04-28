package com.server.manager;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.common.model.Route;
import com.common.util.CollectionWrapper;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;


/**
 * Мененджер файлов - управляет чтением/записью данных из файлов
 * @author Ivan Kirillov
 */
public final class FileManager {
    private final XmlMapper mapper;
    private final String fileName;

    /**
     * Конструктор мененджера файлов
     */
    public FileManager(String fileName) {
        this.fileName = fileName;
        this.mapper = new XmlMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Записывает коллекцию в файл
     */
    public void saveCollection(Collection<Route> collection) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            CollectionWrapper wrapper = new CollectionWrapper();
            wrapper.setRoutes(new HashSet<>(collection));

            mapper.writeValue(writer, wrapper);
        }
    }


    /**
     * Считывает коллекцию из файла
     */
    public Collection<Route> readCollection() throws IOException {
        if (fileName == null || fileName.isEmpty()) return new HashSet<>();

        File file = new File(fileName);
        if (!file.exists()) return new HashSet<>();

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(fis)) {

            CollectionWrapper wrapper = mapper.readValue(reader, CollectionWrapper.class);
            return wrapper.getRoutes();
        }
    }
}