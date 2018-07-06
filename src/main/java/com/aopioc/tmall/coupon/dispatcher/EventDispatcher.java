package com.aopioc.tmall.coupon.dispatcher;

import com.aopioc.tmall.coupon.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName EventDispatcher
 * @Description TODO 事件消息业务分发器 (暂时不处理)
 * @Author Q_先生
 * @Date 2018/7/6 16:26
 **/
public class EventDispatcher {

    private static Logger logger = LoggerFactory.getLogger(EventDispatcher.class);

    public static String processEvent(Map<String, String> map) {

        logger.info("事件消息业务分发器================进来了啊");

//        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { //关注事件
//            System.out.println("==============这是关注事件！");
//        }
//
//        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { //取消关注事件
//            System.out.println("==============这是取消关注事件！");
//        }
//
//        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { //扫描二维码事件
//            System.out.println("==============这是扫描二维码事件！");
//        }
//
//        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { //位置上报事件
//            System.out.println("==============这是位置上报事件！");
//        }
//
//        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { //自定义菜单点击事件
//            System.out.println("==============这是自定义菜单点击事件！");
//        }
//
//        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { //自定义菜单 View 事件
//            System.out.println("==============这是自定义菜单 View 事件！");
//        }

        return null;
    }
}
