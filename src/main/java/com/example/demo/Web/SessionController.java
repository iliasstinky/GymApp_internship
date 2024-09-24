package com.example.demo.Web;
import com.example.demo.Repositories.SessionInterface;
import com.example.demo.Services.ReservationService;
import com.example.demo.Services.SessionService;
import com.example.demo.entities.UserSession;
import com.example.demo.entities.UserSession;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/session")
public class SessionController {

         @Autowired
        SessionInterface si;

         @Autowired
        SessionService ss;



         @Autowired
        ReservationService rs;


         @PostMapping()
        public ResponseEntity<UserSession> CreateSession (@RequestBody UserSession NewSession){
             UserSession newSession = ss.createsession(NewSession);

            return ResponseEntity.ok(NewSession);
         }

         @GetMapping
        public  ResponseEntity<List <UserSession>> getallreservations(){
             List <UserSession> session = si.findAll();
             return ResponseEntity.ok(session);

         }


         @PostMapping("/{sessionId}/reserve")
        public ResponseEntity<UserSession> ReserveSessions ( @PathVariable int sessionId  , @RequestParam int memberId  ){

             String results =  rs.reservesession(sessionId ,memberId );
             if (results.equals("Reservation Succesful")){
                UserSession reservesUsersession = si.findById(sessionId).get();
                 return  ResponseEntity.ok(reservesUsersession);
             }
             else  return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
         }


         @GetMapping("/{memberId}/reservations")
        public ResponseEntity<List<UserSession>> getUserReservations (@PathVariable int memberId  ) {

                    List<UserSession>  userSession=  rs.reservedSessions(memberId );
                if (userSession != null && !userSession.isEmpty()) {
                    return ResponseEntity.ok(userSession);
                }
             return ResponseEntity.noContent().build(); // 204 No Content
         }


        @GetMapping("/{memberId}/reservationBydate")
        public ResponseEntity<List<UserSession>> getUserReservations(
                @PathVariable int memberId,
                @RequestParam(required = false) String date // date in 'yyyy-MM-dd' format
        ) {
            List<UserSession> userSessions = rs.reservedSessions(memberId, date);
            if (userSessions != null && !userSessions.isEmpty()) {
                return ResponseEntity.ok(userSessions);
            }
            return ResponseEntity.noContent().build(); // 204 No Content
        }


}
