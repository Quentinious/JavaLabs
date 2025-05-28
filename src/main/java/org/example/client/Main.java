package org.example.client;

import org.example.client.controller.Controller;
import org.example.client.model.DefaultClient;
import org.example.client.view.SwingView;
import org.example.common.connections.factories.ConnectionsFactory;
import org.example.common.connections.factories.DefaultConnectionsFactory;

public class Main {
    public static void main(String[] args) {
        final Controller controller = new Controller();
        final SwingView view = new SwingView(controller, controller);
        final ConnectionsFactory connectionsFactory = new DefaultConnectionsFactory();
        final DefaultClient defaultClient = new DefaultClient(view.getMessageDocument(), view, connectionsFactory);
        view.setClientContext(defaultClient);
        controller.setView(view);
        controller.setClient(defaultClient);
        controller.start();
    }
}
