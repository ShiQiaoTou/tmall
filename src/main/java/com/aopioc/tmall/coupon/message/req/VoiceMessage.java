package com.aopioc.tmall.coupon.message.req;

/**
 * @ClassName VoiceMessage
 * @Description 语音消息
 * @Author Q_先生
 * @Date 2018/7/6 16:15
 **/
public class VoiceMessage {

    /**
     *  媒体 ID
     */
    private String MediaId;

    /**
     *  语音格式
     */
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
