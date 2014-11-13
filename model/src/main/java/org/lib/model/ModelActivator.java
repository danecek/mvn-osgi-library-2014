package org.lib.model;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ModelActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getSimpleName()).info("start");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getSimpleName()).info("stop");
    }

}
