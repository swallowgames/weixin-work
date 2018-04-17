package com.swallow.weixin.work.entity;

public class AccessToken extends BaseResult{

    private String access_token;

    private Integer expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public boolean isValid(){
        if (super.isValid()){
            return expires_in != null && expires_in > 0;
        }else{
            return false;
        }
    }
}
