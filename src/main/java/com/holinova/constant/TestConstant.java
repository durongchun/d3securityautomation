package com.holinova.constant;

import java.io.File;

/**
 * Test constant
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20
 */
public class TestConstant {
    /**
     * int 3000
     */
    public static final int THREE_THOUSANG = 3000;

    /**
     * int 10000
     */
    public static final int TEN_THOUSANG = 10000;    
    
    /**
     * file path
     */
    public static final String FILEPATH = resourceDirectory() + "\\testData\\demo.xlsx" ;
    
    /**
     * get resource directory 
     */
    public static String resourceDirectory() {
    	File resourcesDirectory = new File("src/test/resources");
        resourcesDirectory.getAbsolutePath();
        return resourcesDirectory.getAbsolutePath();
    }
    
    
    
    
}
