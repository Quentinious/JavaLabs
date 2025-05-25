package org.example.common.messages;

import org.example.common.ClientContext;

public record SuccessResponse() implements Response {
    @Override
    public void handle(ClientContext clientContext, ServerMessagesListener serverMessagesListener) {
    }

    @Override
    public String toXML() {
        return """
                <success>
                </success>
                """;
    }
}
