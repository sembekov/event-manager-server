package org.example.eventmanagementsystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.eventmanagementsystem.dto.request.RegistrationRequest;
import org.example.eventmanagementsystem.dto.response.ParticipantResponse;
import org.example.eventmanagementsystem.entities.Participant;
import org.example.eventmanagementsystem.repositories.ParticipantRepository;
import org.example.eventmanagementsystem.services.RegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final ParticipantRepository participantRepository;
    private final PasswordEncoder passwordEncoder;  // используем для хеширования паролей

    @Override
    public ParticipantResponse register(RegistrationRequest registrationRequest) {
        Participant participant = new Participant();
        participant.setFirstName(registrationRequest.getFirstName());
        participant.setLastName(registrationRequest.getLastName());
        participant.setEmail(registrationRequest.getEmail());
        participant.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));  // хешируем пароль

        Participant savedParticipant = participantRepository.save(participant);

        ParticipantResponse response = new ParticipantResponse();
        response.setParticipantId(savedParticipant.getId());
        response.setFirstName(savedParticipant.getFirstName());
        response.setLastName(savedParticipant.getLastName());
        response.setEmail(savedParticipant.getEmail());

        return response;
    }
}
