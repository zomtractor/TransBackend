package com.xiaosuange.clipboardjava.web;

import com.xiaosuange.clipboardjava.dao.HistDao;
import com.xiaosuange.clipboardjava.pojo.Hist;
import com.xiaosuange.clipboardjava.pojo.Resp;
import com.xiaosuange.clipboardjava.utils.FormatUtil;
import lombok.Data;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ClipboardController {

    @Autowired
    private HistDao histDao;

    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    @GetMapping("/fromwindow")
    public Resp toPhone() throws Exception{
        Transferable contents = clipboard.getContents(null);
        if(contents==null) return Resp.fail();
        if(contents.isDataFlavorSupported(DataFlavor.imageFlavor)){
            BufferedImage bf = (BufferedImage) contents.getTransferData(DataFlavor.imageFlavor);
            System.out.println(bf);
            FileOutputStream fos = new FileOutputStream("clip.jpg");
            ImageIO.write(bf,"jpeg",fos);
            fos.close();
            return Resp.ok(null,2);
        } else {
            String str = (String) contents.getTransferData(DataFlavor.stringFlavor);
            System.out.println(str);
            System.out.println(histDao);
            histDao.insert(new Hist(null,"服务器", FormatUtil.getTime(),str));
            return Resp.ok(str,1);
        }
    }
    @GetMapping("/pic")
    public void pic(HttpServletResponse resp) throws Exception{
        ServletOutputStream sos = resp.getOutputStream();
        FileInputStream fis = new FileInputStream("clip.jpg");
        IOUtils.copy(fis,sos);
    }
    @PostMapping("/fromandroid")
    public void toPCText(String text) throws Exception{
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection,null);
        histDao.insert(new Hist(null,StaticArea.form.getPhoneName(), FormatUtil.getTime(),text));
    }
}

@RestControllerAdvice
class ClipExceptionHandlder {
    @ExceptionHandler(Exception.class)
    public Resp doException(Exception ex){
        ex.printStackTrace();
        return Resp.fail();
    }
}
