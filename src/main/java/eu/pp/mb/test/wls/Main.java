package eu.pp.mb.test.wls;

import eu.pp.mb.test.wls.util.JMSLogger;
import weblogic.logging.NonCatalogLogger;

public class Main {

    private static final NonCatalogLogger log = JMSLogger.getLogger();

    public static void main(String[] args) {

//        System.out.println("Hello world!");
        log.info ("Hello world!");
    }
}