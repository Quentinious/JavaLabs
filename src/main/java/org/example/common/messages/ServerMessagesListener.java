package org.example.common.messages;

import org.example.common.modelobjects.ChatMessage;
import org.example.common.modelobjects.ChatUser;

import java.util.List;

public interface ServerMessagesListener {
    void onLogin(ChatUser chatUser);

    void onLogout(ChatUser chatUser);

    void onErrorResponse(String message);

    void onChatMessage(ChatMessage chatMessage);

    void onUsersListUpdate(List<ChatUser> chatUsersList);
}
