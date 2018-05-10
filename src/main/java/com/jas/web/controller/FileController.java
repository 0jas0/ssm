package com.jas.web.controller;

import com.jas.web.bean.FileDO;
import com.jas.web.model.StudentModel;
import com.jas.web.service.IFileService;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {

    @Resource
    IFileService fileService;

    @RequestMapping("ajax/upload-file")
    public Object ajaxUploadFile(@RequestParam(value = "image", required = false) MultipartFile image, Integer type, HttpSession session){
        try {
            String userName = (String) session.getAttribute("username");
            Integer userRole = (Integer) session.getAttribute("type");
            if (userRole == 0) {
                return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "无权限", null);
            }
            fileService.uploadFile(image, type, userName);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"上传图片成功" , null);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"系统异常请稍后重试" , null);
        }
    }

    @RequestMapping("ajax/get-image-by-studentId")
    public Object ajaxGetImageByStudentId(@RequestParam("studentId") String studentId){
        try {
            List<FileDO> fileDOList = fileService.getFilesByStudentId(studentId);
            Map<Integer,List<FileDO>> map = new HashMap<>();
            for (FileDO fileDO : fileDOList){
                Integer fileType = fileDO.getFileType();
                List<FileDO> fileDOS = map.get(fileType);
                if (fileDOS == null){
                    List<FileDO> fileDOLinkedList = new LinkedList<>();
                    fileDOLinkedList.add(fileDO);
                    map.put(fileType, fileDOLinkedList);
                }else {
                    fileDOS.add(fileDO);
                }
            }
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"获取图片信息成功" , map);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"系统异常请稍后重试" , null);
        }

    }
    @RequestMapping("ajax/delete-image-by-id")
    public Object ajaxDelImage(@RequestParam("id") Integer id){
        try {
            fileService.deleteImage(id);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"删除图片成功" , null);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"系统异常请稍后重试" , null);
        }
    }
}
