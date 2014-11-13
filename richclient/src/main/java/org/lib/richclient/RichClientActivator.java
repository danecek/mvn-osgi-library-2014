package org.lib.richclient;

import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class RichClientActivator implements BundleActivator {

    @Override
    public void start(final BundleContext context) throws Exception {
        Logger.getLogger(getClass().getSimpleName()).info("start");
        new JFXPanel();
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                new MainWindow(context);
            }
        });

    }

    @Override
    public void stop(BundleContext context) throws Exception {
        Logger.getLogger(getClass().getSimpleName()).info("stop");
    }

}
