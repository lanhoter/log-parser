package com.mengyu.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerLog {
    private String id;
    private State state;
    private String type;
    private String host;
    private Long timestamp;
    public enum State {
        STARTED,
        FINISHED
    }
}