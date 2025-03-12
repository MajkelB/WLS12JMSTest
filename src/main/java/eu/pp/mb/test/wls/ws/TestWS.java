package eu.pp.mb.test.wls.ws;

import eu.pp.mb.test.wls.util.JMSConfig;
import eu.pp.mb.test.wls.util.JMSLogger;
import weblogic.logging.NonCatalogLogger;

import javax.ejb.Stateless;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;

@Stateless
@Path("test")
public class TestWS {

    private final NonCatalogLogger log = JMSLogger.getLogger();

    @GET
    @Produces("text/plain")
    @Path("/version")
    public String getVersion() {
        String str;
        try {
            str = JMSConfig.getVersion();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        log.info( str = "Version: " + JMSConfig.VERSION );
        return str;
    }

    @GET
    @Produces("text/plain")
    @Path("/send/{mess}")
    public String send( @PathParam("mess") String mess ) {
        String str;
        try {
            InitialContext ctx = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/MyConnectionFactory");
            Queue queue = (Queue) ctx.lookup("jms/MyQueue");

            try (Connection connection = connectionFactory.createConnection();
                 Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                 MessageProducer producer = session.createProducer(queue)) {
                TextMessage message = session.createTextMessage(mess);
                producer.send(message);
                log.info("TestWS: Wyslano wiadomosc: " + mess);
            }
        } catch (NamingException | JMSException e) {
            log.error( "TestWS Exception", e );
            return "Error sending message: " + mess;
        }
        return "Message sent. No: " + JMSConfig.messageCounter;
    }

}
