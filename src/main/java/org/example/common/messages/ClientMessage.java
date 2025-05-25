package org.example.common.messages;

import org.example.common.Connection;
import org.example.common.Message;
import org.example.common.ServerContext;

public interface ClientMessage extends Message {
    void handle(ServerContext serverContext, Connection connection);
}
