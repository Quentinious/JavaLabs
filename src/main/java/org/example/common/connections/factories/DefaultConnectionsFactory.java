package org.example.common.connections.factories;

import org.example.common.Connection;
import org.example.common.connections.ConnectionListener;
import org.example.common.protocols.MessageStreamsFactory;
import org.example.common.protocols.Protocol;
import org.example.common.protocols.object.ObjectMessageStreamsFactory;
import org.example.common.protocols.xml.XMLMessageStreamsFactory;

import java.io.IOException;
import java.net.Socket;

public class DefaultConnectionsFactory implements ConnectionsFactory {
    @Override
    public Connection createConnection(Socket socket, ConnectionListener connectionListener, Protocol protocol) throws IOException {
        final MessageStreamsFactory messageStreamsFactory = protocol == Protocol.OBJECT
                ? new ObjectMessageStreamsFactory()
                : new XMLMessageStreamsFactory();

        return new DefaultConnection(socket, connectionListener, messageStreamsFactory);
    }
}
