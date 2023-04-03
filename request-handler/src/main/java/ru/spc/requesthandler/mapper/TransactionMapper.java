package ru.spc.requesthandler.mapper;

import org.mapstruct.Mapper;
import ru.spc.requesthandler.dto.RequestDto;
import ru.spc.requesthandler.dto.ResponseDto;
import ru.spc.requesthandler.model.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toEntity(RequestDto request);
    ResponseDto toDto(Transaction transaction);
}
