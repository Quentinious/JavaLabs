package org.example.common.protocols.object;

import org.example.common.Message;
import org.example.common.protocols.MessageOutputStream;
import org.example.common.protocols.MessageStreamException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class ObjectMessageOutputStream implements MessageOutputStream {
    private final ObjectOutputStream objectOutputStream;

    public ObjectMessageOutputStream(OutputStream outputStream) throws MessageStreamException {
        try {
            this.objectOutputStream = new ObjectOutputStream(outputStream);
        } catch (IOException exception) {
            throw new MessageStreamException(exception);
        }
    }

    @Override
    public void writeMessage(Message message) throws MessageStreamException {
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException exception) {
            throw new MessageStreamException(exception);
        }
    }

    @Override
    public void flush() throws MessageStreamException {
        try {
            objectOutputStream.flush();
        } catch (IOException exception) {
            throw new MessageStreamException(exception);
        }
    }

    @Override
    public void close() throws MessageStreamException {
        try {
            objectOutputStream.close();
        } catch (IOException exception) {
            throw new MessageStreamException(exception);
        }
    }
}
