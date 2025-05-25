package org.example.common;

import org.example.common.modelobjects.ChatUser;
import org.example.common.modelobjects.Session;

import java.util.List;

public interface ClientContext {
    Connection getConnection();

    void setSession(Session session);

    void addChatUser(ChatUser chatUser);

    void removeChatUser(ChatUser chatUser);

    void updateChatUsersList(List<ChatUser> chatUsersList);

    List<ChatUser> getChatUsersList();
}
