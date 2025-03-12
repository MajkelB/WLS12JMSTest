package eu.pp.mb.test.wls;

import java.util.Set;

@javax.ws.rs.ApplicationPath("rest")
public class JMSApplicationConfig extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(eu.pp.mb.test.wls.ws.TestWS.class);
    }


}
