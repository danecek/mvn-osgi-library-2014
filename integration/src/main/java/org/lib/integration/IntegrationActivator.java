package org.lib.integration;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class IntegrationActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("integration start");
     //   ServiceReference sr = context.getServiceReference(AbstractDAOFactory.class);
        ServiceTracker st = new ServiceTracker(context, AbstractDAOFactory.class.getName(), null);
        AbstractDAOFactory.setSt(st);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("integration stop");
    }

}
