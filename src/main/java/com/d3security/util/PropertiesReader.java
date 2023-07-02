package com.d3security.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bsh.This;

/**
 * Read properties 
 *
 * @author lucy
 * @version 1.0.0
 * @date 2020/10/22
 */
@Slf4j
public class PropertiesReader {
    /**
     * Config file
     */
    private static final Properties PROPERTIES = new Properties();

    /**
     * Private constructor, outer constructs are not allowed
     */
    private PropertiesReader() {}

    /**
     * Read properties 
     *
     * @param propertiesPath 
     * @return PROPERTIES
     * @throws IOException IOException
     */
    public static Properties readProperties(String propertiesPath) throws IOException {
        log.info("Read config file");
        InputStream inputStream = new FileInputStream(propertiesPath);
        // Read config file via utf-8 
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        PROPERTIES.load(bufferedReader);
        return PROPERTIES;
    }

    /**
     * Get value according to key
     *
     * @param key 
     * @return 
     */
    public static String getKey(String key) {
        return PROPERTIES.getProperty(key);
    }
}
