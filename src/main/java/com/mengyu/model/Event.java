package com.mengyu.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private String id;
    private Long duration;
    private String type;
    private String host;
    private boolean alert;
}