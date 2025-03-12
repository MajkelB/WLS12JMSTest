package eu.pp.mb.test.wls.util;

import weblogic.logging.NonCatalogLogger;

public class JMSLogger {

    private static NonCatalogLogger logger = null;

    public static NonCatalogLogger getLogger() {
        if( logger == null ) {
            logger = new NonCatalogLogger( "JMSLogger" );
        }
        return logger;
    }

}
