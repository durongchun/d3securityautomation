package com.d3security.tools.elementmanagement;

import org.openqa.selenium.By;

import java.util.Set;

/**
 * Created by Lucy on 9/29/2022.
 */
public interface ElementDefinitionManager {

    public Set<By> findElements(String key);

    public Set<By> findElements(String key, String... additionalValues);

}
