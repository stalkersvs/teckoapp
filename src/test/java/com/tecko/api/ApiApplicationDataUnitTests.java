package com.tecko.api;

import com.tecko.api.data.DataProviderService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApiApplicationDataUnitTests {

    @Inject
    private DataProviderService dataProviderService;


    @BeforeEach
    public void setup() {

    }


    private String generateValue (){
        String str = dataProviderService.getValue();

        System.out.println(str);
        return str;
    }


    @Test
    public void generated() {
        String str = this.generateValue();

        assertFalse(str.isEmpty());
        assertTrue(str.matches("([a-z]{20})"));

    }

    @Test
    public void generatedSchedule() throws InterruptedException {
        String a = this.generateValue();
        Thread.sleep(5000);
        String b = this.generateValue();

        assertNotEquals(a, b);
    }

    @Test
    public void generatedScheduleBad() throws InterruptedException {
        String a = generateValue();
        Thread.sleep(2500);
        String b = generateValue();

        assertEquals(a, b);
    }

}
