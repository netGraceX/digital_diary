package com.netgrace.digital_diary.services;

import com.netgrace.digital_diary.domain.ToDoListDTO;
import com.netgrace.digital_diary.domain.ToDoListEntity;
import com.netgrace.digital_diary.domain.ToDoListMapper;
import com.netgrace.digital_diary.repositories.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private ToDoListMapper toDoListMapper;

    public ToDoListDTO getToDoListById(Long id) {
        return toDoListRepository.findById(id).map(toDoListMapper::toDoListEntityToToDoListDTO).get();
    }

    public void deleteToDoList(Long id) {
        if(toDoListRepository.existsById(id)) {
            toDoListRepository.deleteById(id);
        }else {
            throw new IllegalStateException("ToDo list with id " + id + " not found");
        }
    }

    public ToDoListDTO updateToDoList(Long id, ToDoListDTO toDoListDTO) {
        ToDoListEntity existingToDo = toDoListRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("ToDo list with id " + id + " not found"));
        ToDoListEntity updatedToDo = toDoListMapper.toDoListDTOtoToDoListEntity(toDoListDTO);
        updatedToDo.setId(existingToDo.getId());
        updatedToDo.setDiary(existingToDo.getDiary());
        ToDoListEntity savedToDo = toDoListRepository.save(updatedToDo);
        return toDoListMapper.toDoListEntityToToDoListDTO(updatedToDo);
    }

    public ToDoListDTO patchToDoList(Long id, ToDoListDTO patchDetails) {
        ToDoListEntity existingToDO = toDoListRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("ToDo with id" + id + "not found"));
        toDoListMapper.updateToDoListFromDTO(patchDetails, existingToDO);
        return toDoListMapper.toDoListEntityToToDoListDTO(toDoListRepository.save(existingToDO));
    }

}
