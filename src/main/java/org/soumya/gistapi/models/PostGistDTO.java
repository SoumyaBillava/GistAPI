package org.soumya.gistapi.models;

import java.util.HashMap;

public class PostGistDTO {
    private String description;
    private boolean isPublic;
    private HashMap<String, FileDTO> files;

    public PostGistDTO(String description, Boolean isPublic, HashMap<String, FileDTO> files) {
        this.description = description;
        this.isPublic = isPublic;
        this.files = files;
    }

    public PostGistDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public HashMap<String, FileDTO> getFiles() {
        return files;
    }

    public void setFiles(HashMap<String, FileDTO> files) {
        this.files = files;
    }
}
