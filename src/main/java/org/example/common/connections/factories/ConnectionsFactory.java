package org.example.common.connections.factories;

import org.example.common.Connection;
import org.example.common.connections.ConnectionListener;
import org.example.common.protocols.Protocol;

import java.io.IOException;
import java.net.Socket;

public interface ConnectionsFactory {
    Connection createConnection(Socket socket, ConnectionListener connectionListener, Protocol protocol) throws IOException;
}
