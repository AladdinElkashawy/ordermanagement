package com.example.ordermanagement.error.model;


import lombok.Value;

import java.sql.Timestamp;

@Value
public class Response {
int code;
String message;
String detail;
Timestamp timestamp;
}
