package com.aopioc.tmall.coupon.message.req;

/**
 * @ClassName ImageMessage
 * @Description 图片消息
 * @Author Q_先生
 * @Date 2018/7/6 16:05
 **/
public class ImageMessage extends BaseMessage {

    /**
     * 图片链接
     */
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
