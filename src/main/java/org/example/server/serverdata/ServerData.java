package org.example.server.serverdata;

import org.example.common.Connection;
import org.example.common.messages.Event;
import org.example.common.modelobjects.ChatUser;
import org.example.common.modelobjects.Session;

import java.util.List;

public interface ServerData {
    void addConnection(Connection connection);

    List<Connection> getConnectionsList();

    void removeConnectionBySession(Session session);

    void addChatUser(ChatUser chatUser, Session session);

    void removeChatUserBySession(Session session);

    ChatUser getChatUserBySession(Session session);

    List<ChatUser> getChatUsers();

    void addChatEvent(Event event);

    List<Event> getRecentChatEvents(int count);

    boolean containsUser(ChatUser chatUser);

    boolean containsUserBySession(Session session);
}
