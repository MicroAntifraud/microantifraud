package ru.spc.requesthandler;

import java.io.Serializable;

public class Transaction implements Serializable {
    Integer requestId;
    Integer userId;
    Integer amount;
}
