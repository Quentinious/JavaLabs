package org.example.common.messages;

import org.example.common.ClientContext;
import org.example.common.modelobjects.ChatUser;

public record LoginEvent(ChatUser chatUser) implements Event {
    @Override
    public void handle(ClientContext clientContext, ServerMessagesListener serverMessagesListener) {
        clientContext.addChatUser(chatUser);
        serverMessagesListener.onLogin(chatUser);
    }

    @Override
    public String toXML() {
        return String.format(
                """
                        <event name="userlogin">
                            <name> %s </name>
                        </event>
                        """,
                chatUser.name()
        );
    }
}
