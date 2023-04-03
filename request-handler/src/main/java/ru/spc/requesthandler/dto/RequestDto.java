package ru.spc.requesthandler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RequestDto {
    private String requestId;
    private String userId;
    private Long amount;
}
