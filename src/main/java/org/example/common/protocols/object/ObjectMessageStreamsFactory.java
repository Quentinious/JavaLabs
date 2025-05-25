package org.example.common.protocols.object;

import org.example.common.protocols.MessageInputStream;
import org.example.common.protocols.MessageOutputStream;
import org.example.common.protocols.MessageStreamException;
import org.example.common.protocols.MessageStreamsFactory;

import java.io.InputStream;
import java.io.OutputStream;

public class ObjectMessageStreamsFactory implements MessageStreamsFactory {
    @Override
    public MessageInputStream createMessageInputStream(InputStream inputStream) throws MessageStreamException {
        return new ObjectMessageInputStream(inputStream);
    }

    @Override
    public MessageOutputStream createMessageOutputStream(OutputStream outputStream) throws MessageStreamException {
        return new ObjectMessageOutputStream(outputStream);
    }
}
