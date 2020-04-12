package com.bww.shop.common.constant;

public class OrderEnums {
    public enum OrderState{
        _0("0", "已下单"),
        _1("1", "待确认"),
        _2("2", "待发货"),
        _3("3", "已发货"),
        _4("4", "已完成");
        private String code;
        private String desc;


        private OrderState(String code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
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
}
