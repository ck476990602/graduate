package com.se.user.info.web;

//import packages
import com.se.notice.domain.Notice;
import com.se.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.se.global.domain.User;
import com.se.global.service.ModelService;
import com.se.global.service.SessionService;
import com.se.user.info.domain.EditableInfo;
import com.se.user.info.service.InfoService;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Controller
public class InfoController {
    private InfoService infoService;
    private NoticeService noticeService;

    @Autowired
    public void setInfoService(InfoService infoService) { this.infoService = infoService; }

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    /**
     * 显示用户个人信息
     *
     * @param session 当前会话
     * @param model Mode对象
     * @return 个人信息界面逻辑视图名
     */
    @RequestMapping("/user/info")
    public String infoPage(HttpSession session, Model model) {
        User user = SessionService.getUser(session);

        ArrayList<Notice> notices = noticeService.getNotices(session);
        SessionService.setNotices(session, notices);
        ModelService.setNoticeTotalNum(model, session);

        if (user.getType() == User.STUDENT_TYPE) {
            return "user/info/student_info";
        } else {
            return "user/info/teacher_info";
        }
    }

    /**
     * 显示用户个人信息编辑界面
     *
     * @param session 当前会话
     * @param model Model对象
     * @return 个人信息编辑界面逻辑视图名
     */
    @RequestMapping("/user/info/edit")
    public String editInfoPage(HttpSession session, Model model) {
        User user = SessionService.getUser(session);

        ModelService.setNoticeTotalNum(model, session);

        if (user.getType() == User.STUDENT_TYPE) {
            return "user/info/student_info_edit";
        } else {
            return "user/info/teacher_info_edit";
        }
    }

    /**
     * 更新用户个人信息
     *
     * @param request 请求对象
     * @param image 上传的图片文件
     * @param session 当前会话
     * @param model Model对象
     * @return 带着操作结果重定向个人信息界面逻辑视图名
     */
    @RequestMapping("/user/info/update")
    public String update(HttpServletRequest request, @RequestParam(value = "image", required = false) MultipartFile image,  HttpSession session, Model model) {
        User user = SessionService.getUser(session);
        EditableInfo editableInfo = new EditableInfo();
        editableInfo.setSignature(request.getParameter("signature"));
        editableInfo.setProfile(request.getParameter("profile"));
        editableInfo.setImage(image);

        if (infoService.update(session, editableInfo)) {
            updateUser(user, editableInfo);
            ModelService.setInfo(model, "Success: 个人资料修改成功!");
        }
        else {
            ModelService.setInfo(model, "ERROR: 个人资料修改失败!");
        }

        return "redirect:/user/info";
    }

    private void updateUser(User user, EditableInfo editableInfo) {
        user.setImageLocation(editableInfo.getImageLocation());
        user.setSignature(editableInfo.getSignature());
        user.setProfile(editableInfo.getProfile());
    }
}
