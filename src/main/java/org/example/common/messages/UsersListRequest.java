package org.example.common.messages;

import org.example.common.Connection;
import org.example.common.ServerContext;
import org.example.common.modelobjects.ChatUser;
import org.example.common.modelobjects.Session;

import java.util.List;

public record UsersListRequest(Session session) implements NonLoginRequest {
    @Override
    public void handle(ServerContext serverContext, Connection connection) {
        final List<ChatUser> chatUsers = serverContext.getChatUsers();
        connection.send(new UsersListResponse(chatUsers));
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public String toXML() {
        return String.format(
                """
                        <command name="list">
                            <session> %s </session>
                        </command>
                        """,
                session
        );
    }
}
