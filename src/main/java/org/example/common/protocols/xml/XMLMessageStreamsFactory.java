package org.example.common.protocols.xml;

import org.example.common.protocols.MessageInputStream;
import org.example.common.protocols.MessageOutputStream;
import org.example.common.protocols.MessageStreamsFactory;

import java.io.InputStream;
import java.io.OutputStream;

public class XMLMessageStreamsFactory implements MessageStreamsFactory {
    @Override
    public MessageInputStream createMessageInputStream(InputStream inputStream) {
        return new XMLMessageInputStream(inputStream);
    }

    @Override
    public MessageOutputStream createMessageOutputStream(OutputStream outputStream) {
        return new XMLMessageOutputStream(outputStream);
    }
}
