package com.aopioc.tmall.coupon.dispatcher;

import com.aopioc.tmall.coupon.message.resp.Article;
import com.aopioc.tmall.coupon.message.resp.NewsMessage;
import com.aopioc.tmall.coupon.message.resp.TextMessage;
import com.aopioc.tmall.coupon.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MsgDispatcher
 * @Description 消息业务处理分发器
 * @Author Q_先生
 * @Date 2018/7/6 16:19
 **/
public class MsgDispatcher {

    private static Logger logger = LoggerFactory.getLogger(MsgDispatcher.class);

    public static String processMessage(Map<String, String> map) {

        String msgtype = map.get("MsgType");

        //用户 openid
        String openid = map.get("FromUserName");

        //公众号原始 ID
        String mpid = map.get("ToUserName");

        switch (msgtype) {
            case MessageUtil.REQ_MESSAGE_TYPE_TEXT:
                logger.info("==============这是文本消息！");
                TextMessage txtMsg = new TextMessage();
                txtMsg.setToUserName(openid);
                txtMsg.setFromUserName(mpid);
                txtMsg.setCreateTime(LocalDateTime.now().getSecond());
                txtMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                txtMsg.setContent("你好，这是Q_先生在测试哦！");

                return MessageUtil.textMessageToXml(txtMsg);
            case MessageUtil.REQ_MESSAGE_TYPE_IMAGE:
                logger.info("==============这是图片消息！");

                NewsMessage newmsg=new NewsMessage();
                newmsg.setToUserName(openid);
                newmsg.setFromUserName(mpid);
                newmsg.setCreateTime(LocalDateTime.now().getSecond());
                newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

                Article article=new Article();
                article.setDescription("这是图文消息 1");
                article.setPicUrl("http://www.pig66.com/uploadfile/2017/0508/20170508065351128.jpg");
                article.setTitle("图文消息 1");
                article.setUrl("http://www.aopioc.com");
                List<Article> list=new ArrayList<>();
                list.add(article);
                newmsg.setArticleCount(list.size());
                newmsg.setArticles(list);
                return MessageUtil.newsMessageToXml(newmsg);
            case MessageUtil.REQ_MESSAGE_TYPE_LINK:
                logger.info("==============这是链接消息！");
                break;
            case MessageUtil.REQ_MESSAGE_TYPE_LOCATION:
                logger.info("==============这是位置消息！");
                break;
            case MessageUtil.REQ_MESSAGE_TYPE_VIDEO:
                logger.info("==============这是视频消息！");
                break;
            case MessageUtil.REQ_MESSAGE_TYPE_VOICE:
                logger.info("==============这是语音消息！");
                break;
            default:
                logger.info("==============不知道是什么鬼！");
                break;
        }

        return null;
    }

}
