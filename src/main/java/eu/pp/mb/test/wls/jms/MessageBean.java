package eu.pp.mb.test.wls.jms;

import eu.pp.mb.test.wls.util.JMSConfig;
import eu.pp.mb.test.wls.util.JMSLogger;
import weblogic.logging.NonCatalogLogger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty( propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty( propertyName = "destinationLookup", propertyValue = "jms/MyQueue"),
                @ActivationConfigProperty( propertyName = "connectionFactoryLookup", propertyValue = "jms/MyConnectionFactory")
        }
)
public class MessageBean implements MessageListener {

    private final NonCatalogLogger log = JMSLogger.getLogger();

    @Override
    public void onMessage(Message message) {
        try {
            if( message instanceof TextMessage ) {
                String text = ((TextMessage) message).getText();
                log.info( "Text message no: " + JMSConfig.messageCounter++ + "Received: " + text );
            } else {
                log.info( "Unknown message no: " + JMSConfig.messageCounter++ );
            }
        } catch (JMSException e) {
            log.error( "JMSException: ", e );
        }
    }
}
