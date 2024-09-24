package com.example.demo.security;

import com.example.demo.entities.Members;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@NoArgsConstructor
@Data
@Getter
@Setter

public class CustomUserDetails implements UserDetails {
    private Members user;

    public CustomUserDetails(Members user ) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Assuming role is stored directly as "ADMIN", "USER", etc.
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // Ensure user is not null
    }

    @Override
    public String getUsername() {
        return user.getUsername(); // Ensure user is not null
    }
    public void setPassword(String password) {
        this.user.setPassword(password);
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
