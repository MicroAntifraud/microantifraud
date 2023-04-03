package ru.spc.requesthandler.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.spc.requesthandler.enumeration.Verdict;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDto {
    private String requestId;
    private String userId;
    private Verdict verdict;
}
