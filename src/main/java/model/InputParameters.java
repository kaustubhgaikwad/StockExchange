package model;

/**
 * InputParameters entity class for storing all type parameters
 * Currently contains only fileName
 */
public class InputParameters {
    private String fileName;

    public InputParameters(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
