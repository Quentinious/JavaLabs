package org.example.common.messages;

import org.example.common.modelobjects.Session;

public interface NonLoginRequest extends Request {
    Session getSession();
}
