package eu.pp.mb.test.wls.util;

import java.io.IOException;
import java.util.Properties;

public class JMSConfig {

    public final static String VERSION = JMSConfig.class.getPackage().getImplementationVersion();   //    get version from manifest (earlier received from pom)

    public static int messageCounter = 1;

    public static String getVersion() throws IOException {
        try {
            Properties properties = new Properties();

            properties.load(JMSConfig.class.getResourceAsStream("/META-INF/maven/eu.pp.mb.test.wls/JMSTest/pom.properties"));
            return properties.getProperty("version");
        } catch (Exception e) {
            return null;
        }


    }

}
