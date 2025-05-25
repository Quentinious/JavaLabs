package org.example.common.connections;

import org.example.common.Connection;
import org.example.common.Message;

public interface ConnectionListener {
    void onConnectionReady(Connection connection);

    void onDisconnect(Connection connection);

    void onException(Connection connection, Exception exception);

    void onMessageReceived(Connection connection, Message message);
}
