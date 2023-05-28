package com.xiaosuange.clipboardjava.web;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@RestController
@RequestMapping("/files")
public class FileController {
    private String path = "D:/from_android/";

    @PostMapping
    public void doPost(@RequestParam("file") MultipartFile file,String specific) throws Exception {
        if(specific.indexOf(specific.length()-1)!='/') specific+="/";
        System.out.println("spec:"+specific);
        System.out.println("dopost: "+file.getResource().getFilename());
        File dir = new File(path+specific);
        if (!dir.exists()) dir.mkdirs();
        FileOutputStream fos = new FileOutputStream(path +specific + file.getResource().getFilename());
        InputStream is = file.getInputStream();
        IOUtils.copy(is, fos);
        fos.flush();
        fos.close();
    }

    @GetMapping("/connect")
    public void doGet() {
        System.out.println("doget");
    }

    @PutMapping
    public void modifyPath(String path) {
        this.path=path;
        System.out.println(path);
    }

    @GetMapping("/update")
    public void getUpdateApk(HttpServletResponse response) throws Exception {
        File file = new File("D:/My_project_temps/Android_from_cradle_to_tomb/MyFileTrans/app/build/outputs/apk/debug/app-debug.apk");
        String fileName = file.getName();
        System.out.println(fileName);
        FileInputStream fis = new FileInputStream(file);
        response.setContentType("application/vnd.android.package-archive");
        //response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(fis, outputStream);
        outputStream.flush();
        outputStream.close();
        fis.close();
        System.out.println(file.exists());
    }

    @GetMapping("/checkupdate")
    public void checkUpdate(String version, HttpServletResponse response) throws IOException {
        if (version.equals("1.2")) {
            response.getWriter().write("ok");
        } else {
            response.getWriter().write("err");
        }
    }

    @GetMapping("/getfile")
    public Feedback getFile(Integer checkcode, String path) {
        System.out.println("getFile: "+path);
        try {
            if (checkcode != 128) return null;
            System.out.println(path);
            File file = new File(path);
            if (!file.exists()) {
                return Feedback.builder()
                        .file_list(null)
                        .msg("null")
                        .build();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                boolean[] isDir = new boolean[files.length];
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) isDir[i] = true;
                }
                FileInfo[] fileInfos = new FileInfo[files.length];
                for (int i = 0; i < fileInfos.length; i++) {
                    fileInfos[i] = new FileInfo(files[i].getName(), files[i].length(), isDir[i]);
                }
                return Feedback.builder()
                        .msg("dir")
                        .file_list(fileInfos)
                        .build();
            } else {
                return Feedback.builder()
                        .msg("file")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Feedback.builder()
                    .msg("err")
                    .build();
        }
    }
    @GetMapping("/download")
    public void downloadFile(String filepath,HttpServletResponse response) throws Exception {
        System.out.println("downloading: "+filepath);
        File file = new File(filepath);
        FileInputStream fis = new FileInputStream(file);
        response.setContentType("'application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+file.getName());
        ServletOutputStream outputStream = response.getOutputStream();
        //System.in.read();
        IOUtils.copy(fis, outputStream);
        outputStream.flush();
        outputStream.close();
        fis.close();
    }
    @PostMapping("/stop")
    public void stop(){
        System.exit(0);
    }
}
