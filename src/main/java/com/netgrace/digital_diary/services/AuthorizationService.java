package com.netgrace.digital_diary.services;

import com.netgrace.digital_diary.domain.PersonalDiaryEntity;
import com.netgrace.digital_diary.exceptions.EntityNotFoundException;
import com.netgrace.digital_diary.exceptions.UnauthorizedException;
import com.netgrace.digital_diary.security.User;
import com.netgrace.digital_diary.security.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User verifyUserOwnership(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
        if (!user.getUsername().equals(authenticatedUsername)) {
            throw new UnauthorizedException("You are not authorized to access this resource");
        }
        return user;
    }


}


