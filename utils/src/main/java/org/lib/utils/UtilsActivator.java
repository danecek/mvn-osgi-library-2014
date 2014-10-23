package org.lib.utils;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class UtilsActivator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        System.out.println("utils start");
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("utils stop");
    }

}
