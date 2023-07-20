package com.example.webapp.repository.account;

import com.example.webapp.entity.account.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
//@EnableTransactionManagement
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    //@Transactional("accountTransactionManager")
    public void test() {
        for (UserDO user : this.userRepository.findAll()) {
            System.out.println(user.toString());
        }
    }
}
