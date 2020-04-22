package com.se.courses.comment.web;

//import packages
import com.se.global.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import com.se.courses.comment.domain.Comment;
import com.se.courses.comment.service.CommentService;
import com.se.global.service.ModelService;
import com.se.global.service.SessionService;
import com.se.notice.service.NoticeService;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Controller
public class CommentController {
    private CommentService commentService;
    private NoticeService noticeService;

    @Autowired
    public void setCommentService(CommentService commentService) { this.commentService = commentService; }

    @Autowired
    public void setNoticeService(NoticeService noticeService) { this.noticeService = noticeService; }

    /**
     * 显示留言板界面
     *
     * @param session 当前会话
     * @param request 当前请求
     * @param courseId 课程id
     * @param model Model对象
     * @return 留言板界面逻辑视图名
     */
    @RequestMapping("/course/{courseId}/comment")
    public String CommentPage(HttpSession session, HttpServletRequest request, @PathVariable int courseId, Model model) {
        noticeService.removeNotice(session, request);

        ArrayList<Comment> comments = commentService.getComments(courseId);
        SessionService.setComments(session, comments);
        ModelService.setComments(model, comments);
        ModelService.setNoticeTotalNum(model, session);

        User user = SessionService.getUser(session);

        if (user.getType() == User.STUDENT_TYPE) {
            return "/courses/comment/student_comment";
        } else {
            return "/courses/comment/teacher_comment";
        }
    }

    /**
     * 提交留言
     *
     * @param session 当前会话
     * @param courseId 课程id
     * @param content 留言内容
     * @param model Model对象
     * @return 重定向至留言板界面
     */
    @RequestMapping("/course/{courseId}/comment/submit")
    public String submit(HttpSession session, @PathVariable int courseId, @RequestParam("content") String content, Model model) {
        if (!commentService.submit(session, courseId, content)) {
            ModelService.setError(model, "留言失败!");
        }

        return "redirect:/course/" + courseId +"/comment";
    }

    /**
     * 删除留言
     *
     * @param session 当前会话
     * @param courseId 课程id
     * @param commentId 留言id
     * @param model Model对象
     * @return 重定向至留言板界面
     */
    @RequestMapping("/course/{courseId}/comment/remove")
    public String remove(HttpSession session, @PathVariable int courseId, @RequestParam("comment_id") int commentId, Model model) {
        if (!commentService.remove(session, commentId)) {
            ModelService.setError(model, "删除留言失败!");
        }

        return "redirect:/course/" + courseId + "/comment";
    }
}
