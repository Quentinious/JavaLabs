package org.example.client.model;

import org.example.common.messages.ServerMessagesListener;

public interface ClientListener extends ServerMessagesListener {
    void onClientDisconnect();

    void onException(Exception exception);
}
