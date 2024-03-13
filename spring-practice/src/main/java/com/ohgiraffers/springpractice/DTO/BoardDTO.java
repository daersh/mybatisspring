package com.ohgiraffers.springpractice.DTO;

public class BoardDTO {
    private int boardId;
    private String boardTitle;
    private String boardContent;
    private String bdWriteDate;
    private int hits;
    private int bdReportCount;
    private boolean beOnOff;
    private int memberId;
    private String imgId;

    public BoardDTO(int boardId, String boardTitle, String boardContent, String bdWriteDate, int hits, int bdReportCount, boolean beOnOff, int memberId, String imgId) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.bdWriteDate = bdWriteDate;
        this.hits = hits;
        this.bdReportCount = bdReportCount;
        this.beOnOff = beOnOff;
        this.memberId = memberId;
        this.imgId = imgId;
    }

    public BoardDTO(int boardId, String boardTitle, String boardContent, String bdWriteDate, int hits, int bdReportCount, boolean beOnOff, int memberId) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.bdWriteDate = bdWriteDate;
        this.hits = hits;
        this.bdReportCount = bdReportCount;
        this.beOnOff = beOnOff;
        this.memberId = memberId;
    }

    public int getBoardId() {
        return boardId;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public String getBdWriteDate() {
        return bdWriteDate;
    }

    public int getHits() {
        return hits;
    }

    public int getBdReportCount() {
        return bdReportCount;
    }

    public boolean isBeOnOff() {
        return beOnOff;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getImgId() {
        return imgId;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "boardId=" + boardId +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                ", bdWriteDate='" + bdWriteDate + '\'' +
                ", hits=" + hits +
                ", bdReportCount=" + bdReportCount +
                ", beOnOff=" + beOnOff +
                ", memberId=" + memberId +
                ", imgId='" + imgId + '\'' +
                '}';
    }
}
