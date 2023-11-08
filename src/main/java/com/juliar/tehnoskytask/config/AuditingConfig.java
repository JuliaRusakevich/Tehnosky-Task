package com.juliar.tehnoskytask.config;

import com.juliar.tehnoskytask.service.UserHolder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@AllArgsConstructor
@EnableJpaAuditing
@Configuration
public class AuditingConfig {

    private final UserHolder userHolder;

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(userHolder.getUser().getUsername());
    }

}
