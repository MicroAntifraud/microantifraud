package ru.spc.requesthandler.mapper;

import org.mapstruct.Mapper;
import ru.spc.requesthandler.dto.ResponseDto;
import ru.spc.requesthandler.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    ResponseDto toDto(Transaction transaction);
}
