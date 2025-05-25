package org.example.common.messages;

import org.example.common.Connection;
import org.example.common.ServerContext;

import java.util.logging.Logger;

public record LoggingRequestWrapper(Request otherRequest, Logger logger) implements Request {
    @Override
    public void handle(ServerContext serverContext, Connection connection) {
        final String logMessage = String.format("Started handling %s from %s", otherRequest, connection);
        logger.info(logMessage);
        otherRequest.handle(serverContext, connection);
    }

    @Override
    public String toXML() {
        return otherRequest.toXML();
    }
}
