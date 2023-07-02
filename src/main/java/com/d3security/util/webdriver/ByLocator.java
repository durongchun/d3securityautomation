package com.d3security.util.webdriver;

import org.openqa.selenium.By;

public enum ByLocator {
    ID("id"), NAME("name"), CLASS_NAME("className"), CSS_SELECTOR("cssSelector"), XPATH(
            "xpath"), LINK_TEXT("linkText"), TAG("tag");
    private String by;

    private ByLocator(String by) {
        this.by = by;
    }

    public static ByLocator parse(String by) {
        for (final ByLocator byLocator : values()) {
            if (byLocator.toString().equalsIgnoreCase(by.trim())) {
                return byLocator;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return by;
    }

    public By toBy(final String elementDefinition) {
        switch (this) {
            case TAG:
                return By.tagName(elementDefinition);
            case ID:
                return By.id(elementDefinition);
            case NAME:
                return By.name(elementDefinition);
            case CLASS_NAME:
                return By.className(elementDefinition);
            case CSS_SELECTOR:
                return By.cssSelector(elementDefinition);
            case XPATH:
                return By.xpath(elementDefinition);
            case LINK_TEXT:
                return By.linkText(elementDefinition);
            default:
                return By.cssSelector(elementDefinition);
        }
    }

}
