package org.example.server.config;

public record ServerConfiguration(int port, int backlog, String bindAddress, int recentMessagesCount) {
}
