package org.example.common.messages;

import org.example.common.ClientContext;
import org.example.common.Message;

public interface ServerMessage extends Message {
    void handle(ClientContext clientContext, ServerMessagesListener serverMessagesListener);
}
