package org.example.common.messages;

import org.example.common.ClientContext;
import org.example.common.modelobjects.ChatMessage;

public record ChatMessageEvent(ChatMessage chatMessage) implements Event {
    @Override
    public void handle(ClientContext clientContext, ServerMessagesListener serverMessagesListener) {
        serverMessagesListener.onChatMessage(chatMessage);
    }

    @Override
    public String toXML() {
        return String.format(
                """
                        <event name="message">
                            <message> %s </message>
                            <name> %s </name>
                        </event>
                        """,
                chatMessage.content(),
                chatMessage.sender().name()
        );
    }
}
