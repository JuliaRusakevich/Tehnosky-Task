package com.juliar.tehnoskytask.service.api;

import com.juliar.tehnoskytask.dto.LoginDto;
import com.juliar.tehnoskytask.dto.UserReadDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ILoginService {

   UserReadDto findByMail(LoginDto dto);
}
