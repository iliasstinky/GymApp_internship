package com.example.demo.Services;

import com.example.demo.Repositories.MembersInterface;
import com.example.demo.Repositories.SessionInterface;
import com.example.demo.entities.Members;
import com.example.demo.entities.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SessionService {

    @Autowired
    SessionInterface si;

    @Autowired
    MembersInterface mi;





    public UserSession createsession(UserSession NewSession) {
        return si.save(NewSession);
    }



}