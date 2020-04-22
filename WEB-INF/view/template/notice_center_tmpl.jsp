<%--@elvariable id="course" type="com.se.global.domain.Course"--%>
<%--@elvariable id="courses" type="java.util.ArrayList<com.se.global.domain.Course>"--%>
<%--@elvariable id="noticeCourseNum" type="java.util.ArrayList<Integer>"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="content1">
    <div id="main">
        <div class="wrapper">
            <aside id="left-sidebar-nav">
                <ul id="slide-out" class="side-nav fixed leftside-navigation">
                    <c:forEach var="course" items="${courses}" varStatus="status">
                        <li>
                            <a href="/notice/${course.id}">
                                <i class="material-icons left">
                                    notifications
                                    <small class="notification-badge">
                                            ${noticeCourseNum.get(status.index)}
                                    </small>
                                </i>
                                    ${course.name}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
                <a href="#" data-activates="slide-out" class="sidebar-collapse btn-floating waves-effect waves-light hide-on-large-only">
                    <i class="material-icons">menu</i>
                </a>
            </aside>
            <tmpl:block name="content"/>
        </div>
    </div>
    <footer class="page-footer">
        <div class="container">
            <div class="row">
                <div class="col l6 s12">
                    <h5 class="white-text">反馈</h5>
                    <p class="grey-text text-lighten-4">
                        您可以通过发送邮件到daodao8888@qq.com
                    </p>
                    <p class="grey-text text-lighten-4">
                        给我们提供宝贵的建议和意见，谢谢！
                    </p>
                    <a href="https://mail.qq.com" class="btn waves-effect waves-light cyan lighten-3">联系我们</a>
                </div>
                <div class="col l4 offset-l2 s12">
                    <h5 class="white-text">友情链接</h5>
                    <ul>
                        li><a class="grey-text text-lighten-3" href="https://wandbox.org/">C语言在线编译器</a></li>
                        <li><a class="grey-text text-lighten-3" href="http://c.biancheng.net/c/">C语言中文网</a></li>
                        <li><a class="grey-text text-lighten-3" href="https://gitee.com/">基于Git的代码托管和研发协作平台</a></li>
                        <li><a class="grey-text text-lighten-3" href="http://www.nxist.com//">宁夏理工学院官网</a></li>
                        <li><a class="grey-text text-lighten-3" href="https://fanyi.baidu.com/">百度翻译</a></li>>
                    </ul>
                </div>
            </div>
        </div>
        <div class="footer-copyright">
            <div class="container">
                © 2020 Copyright xiangchenkai
                <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
            </div>
        </div>
    </footer>
</tmpl:overwrite>
<jsp:include page="top_bar_tmpl.jsp"/>