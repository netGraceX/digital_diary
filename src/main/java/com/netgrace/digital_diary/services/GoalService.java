package com.netgrace.digital_diary.services;

import com.netgrace.digital_diary.domain.GoalDTO;
import com.netgrace.digital_diary.domain.GoalEntity;
import com.netgrace.digital_diary.domain.GoalMapper;
import com.netgrace.digital_diary.repositories.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private GoalMapper goalMapper;

    public GoalDTO getGoalById(Long goalId) {
        return goalRepository.findById(goalId).map(goalMapper::goalEntityToGoalDTO).get();
    }

    public void deleteGoal(Long id) {
        if(goalRepository.existsById(id)) {
            goalRepository.deleteById(id);
        }else {
            throw new IllegalStateException("Goal with id " + id + " not found");
        }
    }

    public GoalDTO updateGoal(Long id, GoalDTO goalDTO) {
        GoalEntity goal = goalRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Goal with id " + id + " not found"));
        GoalEntity updatedGoal = goalMapper.goalDTOtoGoalEntity(goalDTO);
        updatedGoal.setId(goal.getId());
        updatedGoal.setDiary(goal.getDiary());
        GoalEntity savedGoal = goalRepository.save(updatedGoal);
        return goalMapper.goalEntityToGoalDTO(savedGoal);
    }

    public GoalDTO patchGoal(Long id, GoalDTO patchDetails) {
        GoalEntity goal = goalRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("Goal with id" + id + "not found"));
        goalMapper.updateGoalFromDTO(patchDetails, goal);
        return goalMapper.goalEntityToGoalDTO(goalRepository.save(goal));
    }
}
