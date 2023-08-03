package com.example.demo1;

import com.codeborne.selenide.Configuration;

public class DriverFactory {

    public static void setDriver() {
        String browser = System.getProperty("browser", "chrome");
        switch (browser) {
            default :
                throw new RuntimeException("Не удалось идентифицировать указанный тип браузера");
            case "chrome" :
                Configuration.browser = browser;
                break;
            case "firefox" :
                Configuration.browser = browser;
                break;
        }
    }
}
