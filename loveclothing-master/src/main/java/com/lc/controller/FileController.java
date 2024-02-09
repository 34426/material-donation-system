package com.lc.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * 文件上传控制层
 */
@Controller
@RequestMapping("/file")
public class FileController {

    /**
     * 前缀路径（本地测试环境）
     */
    public static String filePrePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static\\";
//    public static String filePrePath = System.getProperty("user.dir")+ "\\src\\main\\resources\\static\\";


    /**
     * 论坛图片上传
     * @param file
     * @return
     */
    @RequestMapping("/imagesUpload")
    @ResponseBody
    public String imagesUpload(@RequestParam("file") MultipartFile file){
        Map<String, Object> resultMap = new HashMap<>(4);
        try{
            //修改文件名，防止重复
            String filename = file.getOriginalFilename();
            String extName = FileNameUtil.getSuffix(filename);
            String newFileName = IdUtil.simpleUUID() + "." +extName;
            String pathString = filePrePath + "forumImg\\" + newFileName;
            //文件上传
            File f = new File(pathString);
            file.transferTo(f);
            //转base64
            String base64 = "data:image/"+extName+";base64," + Base64.encode(f);
            //回调信息
            resultMap.put("code",0);
            resultMap.put("data", newFileName);
            resultMap.put("baseData", base64);
            return JSON.toJSONString(resultMap);
        }catch(Exception e){
            e.printStackTrace();
            resultMap.put("code",1);
            return JSON.toJSONString(resultMap);
        }
    }

    /**
     * 随机获取一个默认头像
     */
    public static String randomGetDefaultUserImg(){
        List<String> userImgList = new ArrayList<>();
        userImgList.add("defaultOne.jpg");
        userImgList.add("defaultTwo.jpg");
        userImgList.add("defaultThree.jpg");
        userImgList.add("defaultFour.jpg");
        userImgList.add("defaultFive.jpg");
        Random random = new Random();
        return userImgList.get(random.nextInt(userImgList.size()));
    }



}
