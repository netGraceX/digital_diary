package com.netgrace.digital_diary.controllers;

import com.netgrace.digital_diary.domain.ToDoListDTO;
import com.netgrace.digital_diary.services.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/diaries")
public class ToDoListController {

    private final ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping("/todos/{id}")
    public ToDoListDTO getToDoListById(@PathVariable Long id) {
        return toDoListService.getToDoListById(id);
    }


    @PutMapping("/todos/{id}")
    public ResponseEntity<ToDoListDTO> updateToDoList(@PathVariable Long id, @RequestBody ToDoListDTO toDoListDTO) {
        ToDoListDTO updatedToDo = toDoListService.updateToDoList(id, toDoListDTO);
        return ResponseEntity.ok(updatedToDo);
    }

    @PatchMapping("/todos/{id}")
    public ResponseEntity<ToDoListDTO> patchToDoList(@PathVariable Long id, @RequestBody ToDoListDTO toDoListDTO) {
        ToDoListDTO updatedToDo = toDoListService.patchToDoList(id, toDoListDTO);
        return  ResponseEntity.ok(updatedToDo);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteToDoList(@PathVariable Long id) {
        toDoListService.deleteToDoList(id);
        return ResponseEntity.noContent().build();
    }


}
