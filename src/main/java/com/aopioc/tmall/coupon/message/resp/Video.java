package com.aopioc.tmall.coupon.message.resp;

/**
 * @ClassName Video
 * @Description TODO
 * @Author Q_先生
 * @Date 2018/7/6 16:45
 **/
public class Video {

    private String MediaId;
    private String Title;
    private String Description;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
