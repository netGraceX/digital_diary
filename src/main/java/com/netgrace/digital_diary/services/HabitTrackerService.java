package com.netgrace.digital_diary.services;

import com.netgrace.digital_diary.domain.HabitTrackerDTO;
import com.netgrace.digital_diary.domain.HabitTrackerEntity;
import com.netgrace.digital_diary.domain.HabitTrackerMapper;
import com.netgrace.digital_diary.domain.PersonalDiaryEntity;
import com.netgrace.digital_diary.repositories.HabitTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitTrackerService {

    @Autowired
    private HabitTrackerRepository habitTrackerRepository;

     @Autowired
     private HabitTrackerMapper habitTrackerMapper;

    public HabitTrackerDTO getHabitTrackerById(Long id) {
        HabitTrackerEntity tracker = habitTrackerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Habit Tracker with id" + id + "not found"));
        return habitTrackerMapper.habitTrackerToHabitTrackerDTO(tracker);
    }

    public HabitTrackerDTO updateHabitTracker(Long id, HabitTrackerDTO trackerDTO) {
        HabitTrackerEntity existingTracker = habitTrackerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Habit Tracker with id" + id + "not found"));
        HabitTrackerEntity updatedTracker = habitTrackerMapper.habitTrackerDTOEntityToHabitTracker(trackerDTO);
        updatedTracker.setId(existingTracker.getId());
        updatedTracker.setDiary(existingTracker.getDiary());
        return habitTrackerMapper.habitTrackerToHabitTrackerDTO(updatedTracker);
    }

    public HabitTrackerDTO patchHabitTracker(Long id, HabitTrackerDTO patchDetails) {
        HabitTrackerEntity existingTracker = habitTrackerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Habit Tracker with id" + id + "not found"));
        habitTrackerMapper.updateHabitTrackerFromDTO(patchDetails, existingTracker);
        return habitTrackerMapper.habitTrackerToHabitTrackerDTO(habitTrackerRepository.save(existingTracker));
    }

    public void deleteHabitTracker(Long id) {
        if(habitTrackerRepository.existsById(id)) {
            habitTrackerRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Habit Tracker with id " + id + " not found");

        }
    }

}
