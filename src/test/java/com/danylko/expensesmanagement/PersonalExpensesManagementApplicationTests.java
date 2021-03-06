package com.danylko.expensesmanagement;

import com.danylko.expensesmanagement.controller.MainController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PersonalExpensesManagementApplicationTests {

    @Autowired
    private MainController mainController;

    @Test
    void contextLoads() throws Exception {
        assertThat(mainController).isNotNull();
    }

}
