package org.soumya.gistapi.flatmodels;

public class GistBasicDTO {

    private String description;
    private boolean isPublic;

    public GistBasicDTO(String description, boolean isPublic) {
        this.description = description;
        this.isPublic = isPublic;
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
}
