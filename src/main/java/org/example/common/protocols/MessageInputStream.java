package org.example.common.protocols;

import org.example.common.Message;
import org.example.common.messages.InvalidMessageException;

public interface MessageInputStream extends MessageStream {
    Message readMessage() throws MessageStreamException, InvalidMessageException;
}

