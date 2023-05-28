package com.xiaosuange.clipboardjava;

import com.xiaosuange.clipboardjava.dao.FormDao;
import com.xiaosuange.clipboardjava.web.StaticArea;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClipboardjavaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ClipboardjavaApplication.class, args);
        FormDao dao = context.getBean(FormDao.class);
        StaticArea.form=dao.selectList(null).get(0);
    }

}
