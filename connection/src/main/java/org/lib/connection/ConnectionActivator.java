package org.lib.connection;

import java.util.logging.Logger;
import javafx.application.Platform;
import org.lib.richclient.MainWindow;
import org.lib.richclient.controller.ActionState;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ConnectionActivator implements BundleActivator {

    static final Logger logger = Logger.getGlobal();

    public static BundleContext context;

    @Override
    public void start(BundleContext context) throws Exception {
        logger.info("");
        ConnectionActivator.context = context;
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                MainWindow.instance.getLibToolBar().getItems().addAll(ConnectAction.instance.createButton());
                MainWindow.instance.getLibToolBar().getItems().addAll(DisconnectAction.instance.createButton());
                MainWindow.instance.getLibMenuBar().getMenus().addAll(new ConnectionMenu());
                ActionState.instance.fire();
            }
        });

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        logger.info("");
        Connection.instance.disconnect();

    }

}
