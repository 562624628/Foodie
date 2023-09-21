package com.imooc.controller.impl;

import com.imooc.controller.IUserCenterApiService;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.CenterUsersBO;
import com.imooc.resource.FileUpload;
import com.imooc.service.MyOrdersService;
import com.imooc.service.UsersCenterService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.PagedGridResult;
import com.imooc.utils.ResultBase;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@RestController
public class UserCenterApiServiceImpl extends BaseController implements IUserCenterApiService {
    @Autowired
    private UsersCenterService usersCenterService;
    @Autowired
    private FileUpload fileUpload;
    @Autowired
    private MyOrdersService myOrdersService;

    @Override
    public ResultBase userInfo(String userId) {
        return ResultBase.ok(usersCenterService.queryUserInfo(userId));
    }

    @Override
    public ResultBase update(String userId, CenterUsersBO centerUsersBO, HttpServletRequest request, HttpServletResponse response) {
        Users users = usersCenterService.updateUserInfo(userId, centerUsersBO);
        users.setPassword(null);
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(users),true);

        return ResultBase.ok();
    }

    @Override
    public ResultBase update(String userId, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String imgUrl = fileUpload.getImgeUserFaceLocaltion();
        String uploadPathPrefix = File.separator + userId;
        if(file == null){
            return ResultBase.errorMsg("文件不能为空");
        }
        String fileName = file.getOriginalFilename();
        if(StringUtils.isBlank(fileName)){
            return ResultBase.errorMsg("文件名字不能为空");
        }
        //文件重命名 doge-face.png ->["doge-face","png"]
        String[] fileNameArr = fileName.split("\\.");
        String suffix = fileNameArr[fileNameArr.length - 1];
        if(Arrays.asList("png","jgp","jpeg").contains(suffix)){
            return ResultBase.errorMsg("图片格式不对");
        }
        String newFileName = "face-"+userId+ "."+suffix;
        //上传头像最终保存的位置
        String fileUrl = imgUrl + uploadPathPrefix + File.separator + newFileName;

        File outFile = new File(fileUrl);
        if(outFile.getParentFile() !=null){
            outFile.getParentFile().mkdirs();
        }
        //文件输出 保存到目录
        try (FileOutputStream fileOutputStream = new FileOutputStream(outFile)) {
            InputStream inputStream = file.getInputStream();
            IOUtils.copy(inputStream, fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageServerUrl = fileUpload.getImageServerUrl();
        String resourceUrl = imageServerUrl + uploadPathPrefix + "/" + newFileName;
        Users users = usersCenterService.updateUserInfo(userId, resourceUrl);
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(users),true);

        return ResultBase.ok();
    }

    @Override
    public ResultBase query(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        if(StringUtils.isBlank(userId)){
            return ResultBase.errorMsg(null);
        }
        if(page == null){
            page = 1;
        }
        if(pageSize == null){
            pageSize = PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = myOrdersService.queryMyOrders(userId, orderStatus, page, pageSize);
        return ResultBase.ok(pagedGridResult);
    }
}
