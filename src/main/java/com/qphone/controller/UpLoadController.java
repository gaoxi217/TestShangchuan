package com.qphone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * Created by 高玺 on 2019/6/25.
 * 上传图片
 */
@Controller
public class UpLoadController {

    @RequestMapping(value="/upload.do", method= RequestMethod.POST)
    public String upload(@RequestParam("file")MultipartFile file,HttpServletRequest request){

        //取得随机字符串
        UUID uuid = UUID.randomUUID();

        //获得上传文件名称,拼接随机字符串,避免文件名重复
        String filename = uuid + file.getOriginalFilename();

        //创建上传目录
        String path = request.getRealPath("/static");

        //创建目录文件对象
        File file1 = new File(path);

        //判断文件是否存在,不存在就创建一个文件
        if(!file1.exists()){
            file1.mkdirs();

        }
        File file2 = new File(file1 + "/" + filename);
        try {
            file2.createNewFile();//创建文件
            file.transferTo(file2);//上传处理
        }catch (Exception e){
            System.out.println("创建文件异常");
            System.out.println("test2");
            e.printStackTrace();
        }
        return "apply-1";
    }


}
