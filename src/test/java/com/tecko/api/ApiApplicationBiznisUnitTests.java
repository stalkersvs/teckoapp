package com.tecko.api;

import com.tecko.api.business.BiznisAnalystService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApiApplicationBiznisUnitTests {

    @BeforeEach
    public void setup() {
    }

    @Mock
    private BiznisAnalystService biznisAnalystService;


    @Test
    public void call()  {
        when(biznisAnalystService.getData()).thenReturn(3L);

        Long length = biznisAnalystService.getData();

        assertEquals(3, length);
    }

}
