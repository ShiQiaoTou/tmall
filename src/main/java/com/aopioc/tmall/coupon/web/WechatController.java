package com.aopioc.tmall.coupon.web;

import com.aopioc.tmall.coupon.dispatcher.EventDispatcher;
import com.aopioc.tmall.coupon.dispatcher.MsgDispatcher;
import com.aopioc.tmall.coupon.util.MessageUtil;
import com.aopioc.tmall.coupon.util.WechatSignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @ClassName WXNoticeController
 * @Description TODO
 * @Author Q_先生
 * @Date 2018/7/6 14:37
 **/
@RestController
@RequestMapping("/wechat")
public class WechatController {

    private static Logger logger = LoggerFactory.getLogger(WechatController.class);

    /**
     * @return void
     * @Author Q_先生
     * @Description 验证消息的确来自微信服务器
     * @Date 15:17 2018/7/6
     * @Param [signature, timestamp, nonce, echostr]
     **/
    @GetMapping("/notice")
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response,
                      @RequestParam(value = "signature") String signature,
                      @RequestParam(value = "timestamp") String timestamp,
                      @RequestParam(value = "nonce") String nonce,
                      @RequestParam(value = "echostr") String echostr) {
        logger.info("这是 get 方法！");
        try {
            if (WechatSignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                logger.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * @return void
     * @Author Q_先生
     * @Description post 方法用于接收微信服务端消息
     * @Date 15:56 2018/7/6
     * @Param [request, response]
     **/
    @PostMapping("/notice")
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        logger.info("这是 post 方法！");
        try {
            Map<String, String> map = MessageUtil.parseXml(request);
            String msgtype = map.get("MsgType");
            if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)) {
                // 进入事件处理
                EventDispatcher.processEvent(map);
            } else {
                // 进入消息处理
                MsgDispatcher.processMessage(map);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
