package com.d3security.constant;

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
    public static final int THREE_THOUSANG = 6000;

    /**
     * int 10000
     */
    public static final int TEN_THOUSANG = 10000;    
    
    /**
     * file path
     */
    public static final String FILEPATH = resourceDirectory() + "\\testData\\demo.xlsx" ;
    public static final String IRPATH = resourceDirectory() + "\\testData\\IR.xlsx" ;
    public static final String DISPATCHPATH = resourceDirectory() + "\\testData\\Dispatch.xlsx" ;
    
    /**
     * get resource directory 
     */
    public static String resourceDirectory() {
    	File resourcesDirectory = new File("src/test/resources");
        resourcesDirectory.getAbsolutePath();
        return resourcesDirectory.getAbsolutePath();
    }
    
    
    
    
}
