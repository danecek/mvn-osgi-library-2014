package org.lib.business;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class BusinessActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("start business");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
