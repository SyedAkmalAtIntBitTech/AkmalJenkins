package com.divtohtml;

public class DivHTMLModel {

    private String divContent;
    private String modelId;
    private String htmlFileName;
    private String htmlFileContent;

    /**
     * @return the divContent
     */
    public String getDivContent() {
        return divContent;
    }

    /**
     * @param divContent the divContent to set
     */
    public void setDivContent(String divContent) {
        this.divContent = divContent;
    }

    /**
     * @return the modelId
     */
    public String getModelId() {
        return modelId;
    }

    /**
     * @param modelId the modelId to set
     */
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getHtmlFileName() {
        return htmlFileName;
    }

    public void setHtmlFileName(String htmlFileName) {
        this.htmlFileName = htmlFileName;
    }

    public String getHtmlFileContent() {
        return htmlFileContent;
    }

    public void setHtmlFileContent(String htmlFileContent) {
        this.htmlFileContent = htmlFileContent;
    }
}
