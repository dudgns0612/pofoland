/*
 * @(#) FileVo.java
 *
 * v1.0.0 / 2017. 8. 13. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.file.vo;

/**
 * 
 * 시스템명 : 
 * $com.hst.pofoland.biz.file.vo.FileVo.java
 * 클래스 설명 : 
 *
 * @author 이현규
 * @since 2017. 8. 13.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 8. 13.   이현규  최초생성
 * </pre>
 */
public class FileVO {

    private int boardFileSeq;
    private String boardFilename;
    private String boardFiletype;
    private double boardFilesize;
    private String boardFilepath;
    private int boardSeq;
    
    // 디렉토리명 제외 파일명
    private String filenameExcludeDirectory;
    
    public FileVO() {
    }
    
    /**
     * @param boardFileSeq
     * @param boardFilename
     * @param boardFiletype
     * @param boardFilesize
     * @param boardFilepath
     * @param boardSeq
     */
    public FileVO(int boardFileSeq, String boardFilename, String boardFiletype, double boardFilesize,
            String boardFilepath, int boardSeq) {
        super();
        this.boardFileSeq = boardFileSeq;
        this.boardFilename = boardFilename;
        this.boardFiletype = boardFiletype;
        this.boardFilesize = boardFilesize;
        this.boardFilepath = boardFilepath;
        this.boardSeq = boardSeq;
    }

    /**
     * @return the boardFileSeq
     */
    public int getBoardFileSeq() {
        return boardFileSeq;
    }
    /**
     * @param boardFileSeq the boardFileSeq to set
     */
    public void setBoardFileSeq(int boardFileSeq) {
        this.boardFileSeq = boardFileSeq;
    }
    /**
     * @return the boardFilename
     */
    public String getBoardFilename() {
        return boardFilename;
    }
    /**
     * @param boardFilename the boardFilename to set
     */
    public void setBoardFilename(String boardFilename) {
        this.boardFilename = boardFilename;
    }
    /**
     * @return the boardFiletype
     */
    public String getBoardFiletype() {
        return boardFiletype;
    }
    /**
     * @param boardFiletype the boardFiletype to set
     */
    public void setBoardFiletype(String boardFiletype) {
        this.boardFiletype = boardFiletype;
    }
    /**
     * @return the boardFilesize
     */
    public double getBoardFilesize() {
        return boardFilesize;
    }
    /**
     * @param boardFilesize the boardFilesize to set
     */
    public void setBoardFilesize(double boardFilesize) {
        this.boardFilesize = boardFilesize;
    }
    /**
     * @return the boardFilepath
     */
    public String getBoardFilepath() {
        return boardFilepath;
    }
    /**
     * @param boardFilepath the boardFilepath to set
     */
    public void setBoardFilepath(String boardFilepath) {
        this.boardFilepath = boardFilepath;
    }
    /**
     * @return the boardSeq
     */
    public int getBoardSeq() {
        return boardSeq;
    }
    /**
     * @param boardSeq the boardSeq to set
     */
    public void setBoardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
    }
    /**
     * @return filenameExcludeDirectory 반환
     */
    public String getFilenameExcludeDirectory() {
        return filenameExcludeDirectory;
    }

    /**
     * @param filenameExcludeDirectory 설정할 filenameExcludeDirectory
     */
    public void setFilenameExcludeDirectory(String filenameExcludeDirectory) {
        this.filenameExcludeDirectory = filenameExcludeDirectory;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FileVO [boardFileSeq=");
        builder.append(boardFileSeq);
        builder.append(", boardFilename=");
        builder.append(boardFilename);
        builder.append(", boardFiletype=");
        builder.append(boardFiletype);
        builder.append(", boardFilesize=");
        builder.append(boardFilesize);
        builder.append(", boardFilepath=");
        builder.append(boardFilepath);
        builder.append(", boardSeq=");
        builder.append(boardSeq);
        builder.append(", filenameExcludeDirectory=");
        builder.append(filenameExcludeDirectory);
        builder.append("]");
        return builder.toString();
    }
    
}
