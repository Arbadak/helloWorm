package com.replilab.worm;

/**
 * Cell class, is element of worm, contains every cell coordinate and heading
 */
@Deprecated
public class Cells {
    public Integer id;
    public int posX, posY;
    public String cellColor;
    public Boolean isHead;
    public String headLook;

    public Cells() {
    }

    public Cells(Integer id, int posX, int posY, String cellColor, Boolean isHead, String headLook) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.cellColor = cellColor;
        this.isHead = isHead;
        this.headLook = headLook;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getCellColor() {
        return cellColor;
    }

    public void setCellColor(String cellColor) {
        this.cellColor = cellColor;
    }

    public Boolean getHead() {
        return isHead;
    }

    public void setHead(Boolean head) {
        isHead = head;
    }

    public String getHeadLook() {
        return headLook;
    }

    public void setHeadLook(String headLook) {
        this.headLook = headLook;
    }
}