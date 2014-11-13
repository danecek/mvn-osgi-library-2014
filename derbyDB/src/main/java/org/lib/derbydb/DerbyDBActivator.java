package org.lib.derbydb;

import org.lib.derbydb.impl.DerbyDAOFactory;
import org.lib.integration.AbstractDAOFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class DerbyDBActivator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        System.out.println("derbyDB start");
        context.registerService(AbstractDAOFactory.class.getName(), new DerbyDAOFactory(), null);
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("derbyDB stop");
    }

}
