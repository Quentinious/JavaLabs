package org.example.common;

import org.example.common.modelobjects.Session;

public interface Connection {
    Session getSession();

    void setSession(Session session);

    void send(Message message);

    boolean isClosed();

    void close();
}
