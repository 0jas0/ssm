package com.jas.web.controller;

import com.alibaba.fastjson.JSON;
import com.jas.web.bean.model.StudentUploadModel;
import com.jas.web.service.IFileService;
import com.jas.web.service.IStudentUploadService;
import com.jas.web.utils.EUDataGridResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/paper")
public class PaperController {
    @Resource
    IFileService fileService;

    @Resource
    IStudentUploadService studentUploadService;

    @RequestMapping(value = "/paperStudentList")
    public String StudentList(){
        return "paper_list";
    }

    @RequestMapping(value = "/paperStdudentEdit")
    public String edit(){
        return "paper_add";
    }

    @RequestMapping(value = "/paperList")
    public Object getData(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        String[] split = userId.split(",");
        List<StudentUploadModel> studentUploadModelList = studentUploadService.getUploadFileByStudentId(split[0]);
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(studentUploadModelList);
        result.setTotal(studentUploadModelList.size());
        return result;
    }

    @RequestMapping(value="/file/upload", method= RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(MultipartHttpServletRequest request) throws Exception{
        Iterator<String> iterator = request.getFileNames();
        String json = null;
        while (iterator.hasNext()) {
            String fileName = iterator.next();
            MultipartFile multipartFile = request.getFile(fileName);
            Map<String,Object> result = fileService.uploadFile(multipartFile);
            json = JSON.toJSONString(result);
        }
        return json;
    }
}
    @RequestMapping(value="/file1/upload", method= RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload1(MultipartHttpServletRequest request) throws Exception{
        Iterator<String> iterator = request.getFileNames();
        String json = null;
        while (iterator.hasNext()) {
            String fileName = iterator.next();
            MultipartFile multipartFile = request.getFile(fileName);
            Map<String,Object> result = fileService.uploadFile(multipartFile);
            json = JSON.toJSONString(result);
        }
        return json;
    }
    @RequestMapping(value="/file2/upload", method= RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload2(MultipartHttpServletRequest request) throws Exception{
        Iterator<String> iterator = request.getFileNames();
        String json = null;
        while (iterator.hasNext()) {
            String fileName = iterator.next();
            MultipartFile multipartFile = request.getFile(fileName);
            Map<String,Object> result = fileService.uploadFile(multipartFile);
            json = JSON.toJSONString(result);
        }
        return json;
    }
    @RequestMapping(value="/file3/upload", method= RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload3(MultipartHttpServletRequest request) throws Exception{
        Iterator<String> iterator = request.getFileNames();
        String json = null;
        while (iterator.hasNext()) {
            String fileName = iterator.next();
            MultipartFile multipartFile = request.getFile(fileName);
            Map<String,Object> result = fileService.uploadFile(multipartFile);
            json = JSON.toJSONString(result);
        }
        return json;
    }
    @RequestMapping(value="/file4/upload", method= RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload4(MultipartHttpServletRequest request) throws Exception{
        Iterator<String> iterator = request.getFileNames();
        String json = null;
        while (iterator.hasNext()) {
            String fileName = iterator.next();
            MultipartFile multipartFile = request.getFile(fileName);
            Map<String,Object> result = fileService.uploadFile(multipartFile);
            json = JSON.toJSONString(result);
        }
        return json;
    }
