package org.soumya.gistapi.models;

public class FileDTO {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FileDTO(String content) {
        this.content = content;
    }
}
