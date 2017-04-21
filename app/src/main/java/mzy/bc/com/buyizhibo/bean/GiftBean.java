package mzy.bc.com.buyizhibo.bean;

/**
 * Created by Administrator on 2017/4/17.
 */

public class GiftBean {
    private Object giftImgDa;
    private Integer giftMoney;
    private Object giftHuang;
    private String giftJingYan;
    private boolean isDui;

    public GiftBean(Object giftImgDa, Integer giftMoney, Object giftHuang, String giftJingYan, boolean isDui) {
        this.giftImgDa = giftImgDa;
        this.giftMoney = giftMoney;
        this.giftHuang = giftHuang;
        this.giftJingYan = giftJingYan;
        this.isDui = isDui;
    }

    public GiftBean() {
        super();
    }

    public Integer getGiftMoney() {
        return giftMoney;
    }

    public void setGiftMoney(Integer giftMoney) {
        this.giftMoney = giftMoney;
    }

    public Object getGiftImgDa() {
        return giftImgDa;
    }

    public void setGiftImgDa(Object giftImgDa) {
        this.giftImgDa = giftImgDa;
    }

    public Object getGiftHuang() {
        return giftHuang;
    }

    public void setGiftHuang(Object giftHuang) {
        this.giftHuang = giftHuang;
    }

    public String getGiftJingYan() {
        return giftJingYan;
    }

    public void setGiftJingYan(String giftJingYan) {
        this.giftJingYan = giftJingYan;
    }

    public boolean isDui() {
        return isDui;
    }

    public void setDui(boolean dui) {
        isDui = dui;
    }
}
