package org.lib.model;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        System.out.println("model start");
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}