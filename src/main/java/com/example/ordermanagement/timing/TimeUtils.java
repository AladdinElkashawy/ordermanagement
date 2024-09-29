package com.example.ordermanagement.timing;

import java.sql.Timestamp;

public class TimeUtils {
    public static Timestamp getCurentTimeStamp(){
        return new Timestamp(System.currentTimeMillis());
    }
}
