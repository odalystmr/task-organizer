package com.example.taskorganizer.project.requests;

import java.util.List;

public class AddProjectParticipantsPostRequest {
    private List<Long> participantIds;

    public List<Long> getParticipantIds() {
        return participantIds;
    }
}
