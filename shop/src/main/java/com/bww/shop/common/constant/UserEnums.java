package com.bww.shop.common.constant;

import sun.applet.Main;

public class UserEnums {

    public enum UserType{
        _0("0", "admin"),
        _1("1", "current_user");
        private String code;
        private String desc;


        private UserType(String code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public String geCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static void main(String[] args){
        System.out.println(UserType._0.geCode());
    }


}
