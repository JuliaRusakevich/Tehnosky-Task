package com.juliar.tehnoskytask.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juliar.tehnoskytask.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Integer id;
    private String mail;
    @JsonIgnore
    private String password;

    public static CustomUserDetails mapFromUserToCustomDetails(User e) {
        CustomUserDetails c = new CustomUserDetails();
        c.id = e.getId();
        c.mail = e.getMail();
        c.password = e.getPassword();
        return c;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
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

