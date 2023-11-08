package com.juliar.tehnoskytask;


import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Transactional // чтобы тесты выполнялись и назад откатывались
public abstract class IntegrationTestBase {
}
