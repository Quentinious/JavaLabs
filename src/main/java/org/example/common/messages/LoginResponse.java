package org.example.common.messages;

import org.example.common.ClientContext;
import org.example.common.modelobjects.Session;

public record LoginResponse(Session session) implements Response {
    @Override
    public void handle(ClientContext clientContext, ServerMessagesListener serverMessagesListener) {
        clientContext.setSession(session);
        clientContext.getConnection().send(new UsersListRequest(session));
    }

    @Override
    public String toXML() {
        return String.format(
                """
                        <success>
                            <session> %s </session>
                        </success>
                        """,
                session
        );
    }
}
