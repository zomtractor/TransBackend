package com.xiaosuange.clipboardjava;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClipboardjavaApplicationTests {

    @Test
    void contextLoads() throws Exception {
        String queueName = "cpsignal.queue";
        String message = "hello world!";
    }

}
