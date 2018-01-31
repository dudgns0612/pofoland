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

    private int fileSeq;
    private String filename;
    private String filetype;
    private double filesize;
    private String filepath;
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
    public FileVO(int fileSeq, String filename, String filetype, double filesize,
            String filepath, int boardSeq) {
        super();
        this.fileSeq = fileSeq;
        this.filename = filename;
        this.filetype = filetype;
        this.filesize = filesize;
        this.filepath = filepath;
        this.boardSeq = boardSeq;
    }

    /**
	 * @return the fileSeq
	 */
	public int getFileSeq() {
		return fileSeq;
	}

	/**
	 * @param fileSeq the fileSeq to set
	 */
	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the filetype
	 */
	public String getFiletype() {
		return filetype;
	}

	/**
	 * @param filetype the filetype to set
	 */
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	/**
	 * @return the filesize
	 */
	public double getFilesize() {
		return filesize;
	}

	/**
	 * @param filesize the filesize to set
	 */
	public void setFilesize(double filesize) {
		this.filesize = filesize;
	}

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
        builder.append("FileVO [fileSeq=");
        builder.append(fileSeq);
        builder.append(", filename=");
        builder.append(filename);
        builder.append(", filetype=");
        builder.append(filetype);
        builder.append(", filesize=");
        builder.append(filesize);
        builder.append(", filepath=");
        builder.append(filepath);
        builder.append(", boardSeq=");
        builder.append(boardSeq);
        builder.append(", filenameExcludeDirectory=");
        builder.append(filenameExcludeDirectory);
        builder.append("]");
        return builder.toString();
    }
    
}
