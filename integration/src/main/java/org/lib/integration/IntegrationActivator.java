package org.lib.integration;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class IntegrationActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("integration start");
        BookDAO bdao = AbstractDAOFactory.getInstance().getBooksDAO();
        bdao.create("RUR", "Capek");
        System.out.println(bdao.getAll());
        // TODO add activation code here
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
