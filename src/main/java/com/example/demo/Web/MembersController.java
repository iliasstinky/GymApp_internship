package com.example.demo.Web;

import com.example.demo.Repositories.MembersInterface;
import com.example.demo.Services.MembersService;
import com.example.demo.entities.Members;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin

@RequestMapping("/api/users")
public class MembersController {

         @Autowired
         MembersInterface mb ;
         @Autowired
         MembersService ms ;


    @GetMapping
    public ResponseEntity<List<Members>>  showallmembers(){
             List<Members> MembersList = mb.findAll() ;
             return ResponseEntity.ok(MembersList);
         }

        @PutMapping("/{id}")
        public ResponseEntity<Members> editMemberById(@PathVariable int id, @RequestBody Members member) {
            Members updatedMember = ms.updateMember(id, member );
            if (updatedMember != null) {

                return ResponseEntity.ok(updatedMember);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Members> deleteMembersById(@PathVariable int id  ) {

            Members memberdeleted = ms.deleteMembersbyid(id);

            if (memberdeleted != null) {
                return ResponseEntity.ok(memberdeleted);
            } else {
                return ResponseEntity.notFound().build();
            }

        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Members> CreateMember( @RequestBody Members member){

                member.setRole("USER");
                Members Createdmember = ms.Creatememeber(member);
                return ResponseEntity.ok(Createdmember);
        }



}
