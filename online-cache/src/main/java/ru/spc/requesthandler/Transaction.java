package ru.spc.requesthandler;

import lombok.Data;

import java.io.Serializable;

@Data
public class Transaction implements Serializable {
    Long requestId;
    Integer userId;
    Integer amount;
}
