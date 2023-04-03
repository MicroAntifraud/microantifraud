package ru.spc.requesthandler.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Transaction implements Serializable {
    private String requestId;
    private String userId;
    private Long amount;
}
