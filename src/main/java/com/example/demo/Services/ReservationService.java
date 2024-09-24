package com.example.demo.Services;

import com.example.demo.Repositories.MembersInterface;
import com.example.demo.Repositories.SessionInterface;
import com.example.demo.entities.Members;
import com.example.demo.entities.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    MembersInterface mi;


    @Autowired
    SessionInterface si;

    public String reservesession(int sessionId, int memberId) {
        Members members = mi.findById(memberId).get();

        UserSession session = si.findById(sessionId).get();

        // Check if the member has already reserved this session
        if (members.getUserSessions().contains(session)) {
            return "You have already reserved this session.";
        }

        if (session.getPlacesLeft() > 0) {
            members.getUserSessions().add(session);
            session.getMember().add(members);
            session.setPlacesLeft(session.getPlacesLeft() - 1);

            mi.save(members);
            si.save(session);
            return "Reservation Succesful";
        }
        return "no Spots left";
    }

    public  List<UserSession> reservedSessions(int memberId) {
        Members members = mi.findById(memberId).get();
        List<UserSession> sessions = si.findAll();
        List<UserSession> filteredSessions = new ArrayList<>();

        for (UserSession session : sessions) {

            if (session.getMember().contains(members)) {

                filteredSessions.add(session);

            }
        }
        return filteredSessions;

    }



    public List<UserSession> reservedSessions(int memberId, String date) {
        Members member = mi.findById(memberId).orElse(null);
        List<UserSession> allSessions = si.findAll();
        List<UserSession> filteredSessions = new ArrayList<>();

        if (member != null) {
            for (UserSession session : allSessions) {
                // Filter by member and optionally by date
                boolean isMemberMatch = session.getMember().contains(member);
                boolean isDateMatch = (date == null || session.getDate().toString().equals(date));

                if (isMemberMatch && isDateMatch) {
                    filteredSessions.add(session);
                }
            }
        }
        return filteredSessions;
    }
    
}
