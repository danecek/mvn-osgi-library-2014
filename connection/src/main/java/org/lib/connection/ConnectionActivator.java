package org.lib.connection;

import java.util.logging.Logger;
import javafx.application.Platform;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ConnectionActivator implements BundleActivator {

    static final Logger logger = Logger.getGlobal();

    public static BundleContext context;

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
        ConnectionActivator.context = context;
        System.out.println("@@@@@@@@@@@@@@@@@@" + context.getProperty("port"));
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                Connection.instance.disconnect();
            }
        });
    }
}
