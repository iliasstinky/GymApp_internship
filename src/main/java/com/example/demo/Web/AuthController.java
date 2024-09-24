 package com.example.demo.Web;
import com.example.demo.LoginRequestDTO;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin

@RequestMapping("/api")
public class AuthController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            // Attempt to load user by username

            CustomUserDetails customUserDetails =  customUserDetailsService.loadUserByUsername(loginRequest.getUsername());


            // If user is not found, it will throw UsernameNotFoundException
            if (customUserDetails == null) {
                return ResponseEntity.status(404).body("User not found");
            }


            // Here you can add additional logic to handle password verification, etc.
            // However, the password should not be handled here but by the authentication manager
            if (!loginRequest.getPassword().equals(customUserDetails.getPassword())   ) {
                        return  ResponseEntity.status(404).body("password is not correct ! ");

                }



            return ResponseEntity.ok().body(customUserDetails);

        } catch (UsernameNotFoundException e) {
            // User not found
            return ResponseEntity.status(404).body("User not found");
        } catch (BadCredentialsException e) {
            // Handle other exceptions
            return ResponseEntity.status(500).body("BAD CREDENTIALS");
        }
    }
}
 //this what about sending a userdetail throught a rest api inlcuding checking the user and checking the password manually



/*
 @RestController
 @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
 @RequestMapping("/api")
 public class AuthController {

     @Autowired
     private AuthenticationManager authenticationManager;

     @Autowired
     private CustomUserDetailsService customUserDetailsService;

     @PostMapping("/login")
     public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
         try {
             // Use AuthenticationManager to authenticate
             Authentication authentication = authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
             );

             // If authentication is successful, the session will be created automatically
             CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
             return ResponseEntity.ok().body(customUserDetails);

         } catch (AuthenticationException e) {
             // Handle authentication failure
             return ResponseEntity.status(401).body("Invalid username or password");
         }
     }
 }
*/  //this is using the bean for authenticationManager it worked with session practically but the login wouldnt work for some reason lets try session in Jwt NEXT APP
