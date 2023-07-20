package com.example.webapp.repository.account;

import com.example.webapp.entity.account.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void test() {
        for (UserDO user : this.userRepository.findAll()) {
            System.out.println(user.toString());
        }
    }
}
