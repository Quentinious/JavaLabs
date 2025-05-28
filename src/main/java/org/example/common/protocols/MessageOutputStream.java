package org.example.common.protocols;

import org.example.common.Message;

public interface MessageOutputStream extends MessageStream {
    void writeMessage(Message message) throws MessageStreamException;

    void flush() throws MessageStreamException;
}
