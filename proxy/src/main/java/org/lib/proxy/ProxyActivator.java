package org.lib.proxy;

import java.util.logging.Logger;
import org.lib.business.LibraryFacade;
import org.lib.proxy.impl.LibraryFacadeProxy;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ProxyActivator implements BundleActivator {

    static final Logger logger = Logger.getGlobal();

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
        context.registerService(LibraryFacade.class.getName(), new LibraryFacadeProxy(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
    }

}
