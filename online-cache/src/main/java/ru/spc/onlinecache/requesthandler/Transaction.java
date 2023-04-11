package ru.spc.onlinecache.requesthandler;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class Transaction implements Serializable {
    Long requestId;
    Integer userId;
    Integer amount;
}
