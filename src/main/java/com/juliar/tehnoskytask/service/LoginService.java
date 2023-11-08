package com.juliar.tehnoskytask.service;

import com.juliar.tehnoskytask.dto.CustomUserDetails;
import com.juliar.tehnoskytask.dto.LoginDto;
import com.juliar.tehnoskytask.dto.UserReadDto;
import com.juliar.tehnoskytask.entity.User;

import com.juliar.tehnoskytask.repository.UserRepository;
import com.juliar.tehnoskytask.service.api.ILoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@AllArgsConstructor
@Service
public class LoginService implements ILoginService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserReadDto findByMail(LoginDto dto) {
        User user = userRepository.findByMail(dto.getUsername())
                .map(this::getUserFromDb)
                .orElseThrow(() -> new NoSuchElementException("Incorrect mail!"));

        // var userDetails = loadUserByUsername(dto.getUsername());
        checkPassword(dto, user);
        return getUserReadDto(user);
    }

    private void checkPassword(LoginDto dto, User user) {
        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password!");
        }
    }

    private UserReadDto getUserReadDto(User userFromDb) {
        return UserReadDto.builder()
                .id(userFromDb.getId())
                .fullName(userFromDb.getFullName())
                .mail(userFromDb.getMail())
                .documentType(userFromDb.getDocumentType())
                .documentNumber(userFromDb.getDocumentNumber())
                .build();
    }

    private User getUserFromDb(User userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getFullName(),
                userEntity.getMail(),
                userEntity.getPassword(),
                userEntity.getDocumentType(),
                userEntity.getDocumentNumber()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userFromDb = userRepository.findByMail(username)
                .map(user -> new User(
                        user.getId(),
                        user.getMail(),
                        user.getPassword())
                )
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found!"));

        return CustomUserDetails.mapFromUserToCustomDetails(userFromDb);
    }

}
