package org.lib.integeration;

import org.lib.model.BookId;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        BookDAO bdao = AbstractDAOFactory.getInstance().getBooksDAO();
        bdao.create(new BookId(2), "RUR", "Capek");
        System.out.println(bdao.getAll());
        // TODO add activation code here
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
