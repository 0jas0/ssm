package com.jas.web.controller;

import com.jas.web.model.ExcelModel;
import com.jas.web.model.UserModel;
import com.jas.web.param.UserParam;
import com.jas.web.service.IExcelFileService;
import com.jas.web.service.IUserService;
import com.jas.web.utils.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService userService;

    @Resource
    IExcelFileService excelFileService;

    @RequestMapping("ajax/list_user")
    @ResponseBody
    public Object listUsers(){
        //List<UserModel> userModelList = userService.listUsers(new UserParam());
        List<UserModel> userModelList = new LinkedList<UserModel>();
        userModelList.add(new UserModel(1,"aaaa"));
        userModelList.add(new UserModel(2,"bbbbb"));
        userModelList.add(new UserModel(3,"ccccc"));
        return userModelList;
    }
    @RequestMapping(value = "ajax/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFile(@RequestParam(value = "uploadFile", required = false) CommonsMultipartFile file){
        String filename = file.getOriginalFilename();
        if (filename.endsWith(".xlsx") || filename.endsWith(".xls")) {
            Workbook workbook = null;
            try {
                FileInputStream inputStream = (FileInputStream) file.getInputStream();
                workbook = ExcelUtil.getWorkbook(inputStream, filename);
                List<ExcelModel> dataList = excelFileService.getDataByExcel(workbook);
                String fileName = UUID.randomUUID().toString().replaceAll("-","")+".xls";
                FileOutputStream tmp = new FileOutputStream("/var/tmp/"+fileName);
                workbook = excelFileService.getWorkbookByData(dataList);
                workbook.write(tmp);
                MailSenderInfo mailInfo = new MailSenderInfo();
                mailInfo.setMailServerHost("smtp.163.com");
                mailInfo.setMailServerPort("25");
                mailInfo.setValidate(true);
                mailInfo.setUserName("13166956676@163.com"); // 实际发送者
                mailInfo.setPassword("juan0911081052");// 您的邮箱密码
                mailInfo.setFromAddress("13166956676@163.com"); // 设置发送人邮箱地址
                mailInfo.setToAddress("848420945@qq.com"); // 设置接受者邮箱地址
                mailInfo.setSubject("导出数据");
                mailInfo.setContent("导出数据成功");
                // 这个类主要来发送邮件
                SimpleMailSender sms = new SimpleMailSender();
                sms.sendHtmlMail(mailInfo, "/var/tmp/"+fileName, fileName); // 发送html格式
            } catch (IOException e) {
                return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "上传文件失败", null);
            }
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "文件上传成功", null);
        } else {
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "文件格式不正确", null);
        }
    }
}
