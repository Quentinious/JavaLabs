package org.example.common.protocols;

public interface MessageStream extends AutoCloseable {
    @Override
    void close() throws MessageStreamException;
}
