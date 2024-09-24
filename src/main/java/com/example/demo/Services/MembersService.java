package com.example.demo.Services;

import com.example.demo.Repositories.MembersInterface;
import com.example.demo.entities.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class MembersService {

    @Autowired
    MembersInterface mb ;

    public Members updateMember(int id, Members member) {

        Optional<Members> existingMember = mb.findById(id);
        if (existingMember.isPresent()) {
            Members updatedMember = existingMember.get();
            updatedMember.setUsername(member.getUsername());
            updatedMember.setAge(member.getAge());
            updatedMember.setEmail(member.getEmail());
            updatedMember.setPhone(member.getPhone());
            updatedMember.setRole(member.getRole());
            return mb.save(updatedMember);

        }
        return  null;
    }

    public Members deleteMembersbyid( int id) {
                Optional<Members> membersexist = mb.findById(id) ;
                if (membersexist.isPresent()){
                    Members mem = membersexist.get();
                    mb.deleteById(id);

                    return mem;
                }
                 else {
                    return  null;
                }
    }



    public Members Creatememeber(Members member){
            Members members = mb.save(member);
            return members ;
    }

    public Members findById(int memberId) {
        return mb.findById(memberId).orElse(null);
    }

}