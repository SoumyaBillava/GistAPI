package org.soumya.gistapi.flatmodels;

public class GistFileDTO {

    private String fileName;
    private String fileContent;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public GistFileDTO(String fileName, String fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }
}
