package com.graduation.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 类目相关枚举
 * @Author fuxiaoxiang2
 * @Create 2019/1/10 11:47
 */
public class CategoryEnum {
    enum TYPE {
        MOTORCYCLE_CAR(0,"摩托车"),
        COMMON_CAR(1,"小轿车"),
        VAN_CAR(2,"面包车"),
        RUN_CAR(3,"跑车"),
        SMALL_TRUCK_CAR(4,"小货车"),
        TRUCK_CAR(5,"大货车");
        int type;
        String typeName;
        private static final Map<Integer,TYPE> map = new HashMap<Integer, TYPE>(){{
           put(0,MOTORCYCLE_CAR);
           put(1,COMMON_CAR);
           put(2,VAN_CAR);
           put(3,RUN_CAR);
           put(4,SMALL_TRUCK_CAR);
           put(5,TRUCK_CAR);
        }};
        TYPE(int type, String name) {
            this.type = type;
            this.typeName = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
        public TYPE getType(int code){
            return map.get(code);
        }
    }
}
