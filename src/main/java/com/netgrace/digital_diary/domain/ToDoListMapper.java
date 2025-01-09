package com.netgrace.digital_diary.domain;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface ToDoListMapper {
    ToDoListDTO toDoListEntityToToDoListDTO(ToDoListEntity todo);
    ToDoListEntity toDoListDTOtoToDoListEntity(ToDoListDTO todoDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateToDoListFromDTO(ToDoListDTO todoDTO, @MappingTarget ToDoListEntity todo);
}
