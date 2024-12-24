package com.project.chat_application.chat;

public class ChatMessage {

    private String content;
    private String sender;
    private MessageType type;

    // Constructor
    public ChatMessage(String content, String sender, MessageType type) {
        this.content = content;
        this.sender = sender;
        this.type = type;
    }

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    // Static Builder class
    public static class Builder {
        private String content;
        private String sender;
        private MessageType type;

        // Builder methods to set fields
        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder sender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder type(MessageType type) {
            this.type = type;
            return this;
        }

        // Build method to return a ChatMessage instance
        public ChatMessage build() {
            return new ChatMessage(content, sender, type);
        }
    }

    // Builder method to return a new Builder instance
    public static Builder builder() {
        return new Builder();
    }
}
