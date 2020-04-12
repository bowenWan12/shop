package com.bww.shop.common.constant;

public class GoodsEnums {
    public enum GoodssState{
        _0("0", "正常"),
        _1("1", "已删除"),
        _2("2", "缺货");
        private String code;
        private String desc;


        private GoodssState(String code, String desc){
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
