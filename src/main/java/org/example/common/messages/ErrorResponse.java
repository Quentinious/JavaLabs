package org.example.common.messages;

import org.example.common.ClientContext;

public record ErrorResponse(String message) implements Response {
    @Override
    public void handle(ClientContext clientContext, ServerMessagesListener serverMessagesListener) {
        serverMessagesListener.onErrorResponse(message);
    }

    @Override
    public String toXML() {
        return String.format(
                """
                        <error>
                            <message> %s </message>
                        </error>
                        """,
                message
        );
    }
}
