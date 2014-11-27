package org.lib.protocol;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ProtocolActivator implements BundleActivator {

    static Logger logger = Logger.getLogger(ProtocolActivator.class.getSimpleName());

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("protocol starts");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("protocol stops");
    }

}
