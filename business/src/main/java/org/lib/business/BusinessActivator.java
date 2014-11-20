package org.lib.business;

import java.security.Provider;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class BusinessActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        LibraryFacade.st =  new ServiceTracker(context, LibraryFacade.class.getName(), null);
        LibraryFacade.st.open();
        Logger.getLogger(getClass().getSimpleName()).info("start");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getSimpleName()).info("stop");
    }

}
