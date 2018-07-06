package com.aopioc.tmall.coupon.message.resp;

/**
 * @ClassName TextMessage
 * @Description 文本消息
 * @Author Q_先生
 * @Date 2018/7/6 16:13
 **/
public class TextMessage extends BaseMessage {

    /**
     * 消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
