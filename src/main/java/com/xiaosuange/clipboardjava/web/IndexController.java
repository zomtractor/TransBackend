package com.xiaosuange.clipboardjava.web;

import com.xiaosuange.clipboardjava.dao.FormDao;
import com.xiaosuange.clipboardjava.dao.HistDao;
import com.xiaosuange.clipboardjava.pojo.Form;
import com.xiaosuange.clipboardjava.pojo.Hist;
import com.xiaosuange.clipboardjava.pojo.Resp;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.List;


@RestController
public class IndexController {

    @Autowired
    private FormDao formDao;
    @Autowired
    private HistDao histDao;

    @GetMapping("host")
    public String host() throws Exception{
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        System.out.println(hostAddress);
        return hostAddress;
    }
    @PostMapping("setting")
    public Resp setting(@RequestBody Form form){
        System.out.println(form);
        int res = formDao.updateById(form);
        StaticArea.form=form;
        return res>0?Resp.ok(null,0):Resp.fail();
    }
    @GetMapping("setting")
    public Resp setting(){
        Form form = formDao.selectList(null).get(0);
        StaticArea.form=form;
        return Resp.ok(form,0);
    }
    @GetMapping("history")
    public Resp getHistory(){
        List<Hist> hists = histDao.selectList(null);
        return Resp.ok(hists,0);
    }
}
