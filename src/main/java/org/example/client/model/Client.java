package org.example.client.model;

import org.example.common.modelobjects.ChatUser;
import org.example.common.protocols.Protocol;

public interface Client {
    void join(int port, String serverAddress, ChatUser chatUser, Protocol protocol);

    void leave();

    void shutdown();

    void sendChatMessage();

    void insertLineBreak();
}
