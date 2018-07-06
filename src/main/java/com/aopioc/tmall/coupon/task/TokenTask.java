package com.aopioc.tmall.coupon.task;

import com.aopioc.tmall.coupon.bean.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName TokenTask
 * @Description token 自动刷新
 * @Author Q_先生
 * @Date 2018/7/6 17:42
 **/
public class TokenTask {

    private static final Logger logger = LoggerFactory.getLogger(TokenTask.class);

    private static ScheduledExecutorService scheduledExecutorService;

    private static Map<String, String> tokenMap = new LinkedHashMap<String, String>();

    private static Map<String, ScheduledFuture<?>> futureMap = new HashMap<>();

    private static int poolSize = 2;

    private static boolean daemon = Boolean.TRUE;

    /**
     * 初始化 scheduledExecutorService
     */
    private static void initScheduledExecutorService() {
        logger.info("daemon:{},poolSize:{}", daemon, poolSize);
        scheduledExecutorService = Executors.newScheduledThreadPool(poolSize, new ThreadFactory() {

            @Override
            public Thread newThread(Runnable arg0) {
                Thread thread = Executors.defaultThreadFactory().newThread(arg0);
                //设置守护线程
                thread.setDaemon(daemon);
                return thread;
            }
        });
    }

    /**
     * 设置线程池
     *
     * @param poolSize poolSize
     */
    public static void setPoolSize(int poolSize) {
        TokenTask.poolSize = poolSize;
    }

    /**
     * 设置线程方式
     *
     * @param daemon daemon
     */
    public static void setDaemon(boolean daemon) {
        TokenTask.daemon = daemon;
    }

    /**
     * 初始化token 刷新，每118分钟刷新一次。
     *
     * @param appid  appid
     * @param secret secret
     */
    public static void init(final String appid, final String secret) {
        init(appid, secret, 0, 20);
    }

    /**
     * 初始化token 刷新，每118分钟刷新一次。
     *
     * @param appid        appid
     * @param secret       secret
     * @param initialDelay 首次执行延迟（秒）
     * @param delay        执行间隔（秒）
     */
    public static void init(final String appid, final String secret, int initialDelay, int delay) {
        if (scheduledExecutorService == null) {
            initScheduledExecutorService();
        }
        if (futureMap.containsKey(appid)) {
            futureMap.get(appid).cancel(true);
        }
        //立即执行一次
        if (initialDelay == 0) {
            doRun(appid, secret);
        }
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                doRun(appid, secret);
            }
        }, initialDelay == 0 ? delay : initialDelay, delay, TimeUnit.SECONDS);
        futureMap.put(appid, scheduledFuture);
        logger.info("appid:{}", appid);
    }

    private static void doRun(final String appid, final String secret) {
        try {

            logger.info("======================================获取TOken");

            Token token = new Token("aaa", 12345);
            //            Token token = TokenAPI.token(appid, secret);
            tokenMap.put(appid, token.getAccess_token());
            logger.info("ACCESS_TOKEN refurbish with appid:{}", appid);
        } catch (Exception e) {
            logger.error("ACCESS_TOKEN refurbish error with appid:{}", appid);
            e.printStackTrace();
        }
    }

    /**
     * 取消 token 刷新
     */
    public static void destroyed() {
        scheduledExecutorService.shutdownNow();
        logger.info("destroyed");
    }

    /**
     * 取消刷新
     *
     * @param appid appid
     */
    public static void destroyed(String appid) {
        if (futureMap.containsKey(appid)) {
            futureMap.get(appid).cancel(true);
            logger.info("destroyed appid:{}", appid);
        }
    }

    /**
     * 获取 access_token
     *
     * @param appid appid
     * @return token
     */
    public static String getToken(String appid) {
        return tokenMap.get(appid);
    }

    /**
     * 获取第一个appid 的 access_token
     * 适用于单一微信号
     *
     * @return token
     */
    public static String getDefaultToken() {
        Object[] objs = tokenMap.values().toArray();
        return objs.length > 0 ? objs[0].toString() : null;
    }
}
