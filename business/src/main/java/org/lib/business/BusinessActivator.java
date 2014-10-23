package org.lib.business;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class BusinessActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("business start");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("business stop");
    }

}
