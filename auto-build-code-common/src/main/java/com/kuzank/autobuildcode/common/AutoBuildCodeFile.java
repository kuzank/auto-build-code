package com.kuzank.autobuildcode.common;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/25
 */
public class AutoBuildCodeFile {

    private String fileName;
    private String fileContent;

    public String getFileName() {
        return fileName;
    }

    public AutoBuildCodeFile setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileContent() {
        return fileContent;
    }

    public AutoBuildCodeFile setFileContent(String fileContent) {
        this.fileContent = fileContent;
        return this;
    }

    @Override
    public String toString() {
        return "AutoBuildCodeFile{\n" +
                "fileName='" + fileName + '\'' +
                "\n, fileContent='" + fileContent + '\'' +
                '}';
    }
}
