package org.lib.server;

import java.util.Properties;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ServerActivator implements BundleActivator {
    
    static Logger logger = Logger.getLogger(ServerActivator.class.getSimpleName());

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("server starts");
        Properties p = new Properties();
        p.load(getClass().getResourceAsStream("server.properties"));
        String port = p.getProperty("port", "3333");
        new Thread(new ServerThread(Integer.parseInt(port))).start();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("server stops");
    }

}
