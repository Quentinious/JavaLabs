package org.example.common.messages;

import org.example.common.ClientContext;
import org.example.common.modelobjects.ChatUser;

public record LogoutEvent(ChatUser chatUser) implements Event {
    @Override
    public void handle(ClientContext clientContext, ServerMessagesListener serverMessagesListener) {
        clientContext.removeChatUser(chatUser);
        serverMessagesListener.onLogout(chatUser);
    }

    @Override
    public String toXML() {
        return String.format(
                """
                        <event name="userlogout">
                            <name> %s </name>
                        </event>
                        """,
                chatUser.name()
        );
    }
}
