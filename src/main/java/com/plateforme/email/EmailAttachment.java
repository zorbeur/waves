package com.plateforme.email;

public class EmailAttachment {
    private String fileName;
    private byte[] content;

    public EmailAttachment() {}
    public EmailAttachment(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public byte[] getContent() { return content; }
    public void setContent(byte[] content) { this.content = content; }
}
