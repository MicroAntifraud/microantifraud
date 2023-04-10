package ru.spc.requesthandler.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Transaction implements Serializable {
    Long requestId;
    Integer userId;
    Integer amount;
}
