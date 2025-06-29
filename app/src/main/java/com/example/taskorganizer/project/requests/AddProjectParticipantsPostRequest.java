package com.example.taskorganizer.project.requests;

import java.util.List;

public class AddProjectParticipantsPostRequest {
    private List<String> participantUsernames;

    public List<String> getParticipantUsernames() {
        return participantUsernames;
    }
}
