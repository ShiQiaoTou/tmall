package com.aopioc.tmall.coupon.bean;

/**
 * @ClassName Token
 * @Description TODO
 * @Author Q_先生
 * @Date 2018/7/6 17:44
 **/
public class Token {

    private String access_token;

    private int expires_in;

    public Token(String access_token, int expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String accessToken) {
        access_token = accessToken;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expiresIn) {
        expires_in = expiresIn;
    }
}
