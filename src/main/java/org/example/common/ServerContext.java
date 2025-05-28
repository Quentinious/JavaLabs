package org.example.common;

import org.example.common.messages.Event;
import org.example.common.modelobjects.ChatUser;
import org.example.common.modelobjects.Session;

import java.util.List;

public interface ServerContext {
    void removeConnectionBySession(Session session);

    void addChatUser(ChatUser chatUser, Session session);

    ChatUser getChatUserBySession(Session session);

    void removeChatUserBySession(Session session);

    List<ChatUser> getChatUsers();

    boolean containsChatUser(ChatUser chatUser);

    void addChatEvent(Event event);

    List<Event> getRecentChatEvents();

    Session createSession();

    List<Connection> getOpenConnections();
}
