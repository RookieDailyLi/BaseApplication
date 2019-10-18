package com.credithc.baseapp.bean;

import java.io.Serializable;

/**
 * @author liyong
 * @date 2019/10/18
 * @des
 */
public class UserBean implements Serializable {

    public String uId;
    public String token;
    public String phoneNum;
    public String uNickname;

    @Override
    public String toString() {
        return "UserBean{" +
                "uId='" + uId + '\'' +
                ", token='" + token + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", uNickname='" + uNickname + '\'' +
                '}';
    }
}
