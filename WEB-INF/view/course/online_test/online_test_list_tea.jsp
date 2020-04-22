<%--@elvariable id="announcement" type="com.se.courses.announcement.domain.Announcement"--%>
<%--@elvariable id="courseId" type="int"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tmpl" uri="/WEB-INF/mytag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tmpl:overwrite name="script">

</tmpl:overwrite>
<tmpl:overwrite name="content">
    <div class="container">
        <iframe id="mainContent" width="100%" height="1200px"></iframe>

        <script>

            var mainContent = document.getElementById('mainContent');
            <!-- 指定所展示url -->
            mainContent.src = "https://wandbox.org/"

        </script>

    <%--<div class="row">--%>
            <%--<div class="col s0 m1 l1"></div>--%>
            <%--<div class="col s12 m10 l10">--%>
                <%--<div class="section"></div>--%>
                <%--<h3>测试列表</h3>--%>
                <%--<h4>Test List</h4>--%>
                <%--<div class="divider"></div>--%>
                <%--<div class="section"></div>--%>
                <%--<div class="row">--%>
                    <%--<div class="col s0"></div>--%>
                    <%--<div class="col s12">--%>
                        <%--<a href="https://wandbox.org/" class="btn waves-effect waves-light">--%>
                            <%--<i class="material-icons right">--%>
                                <%--send--%>
                            <%--</i>--%>
                            <%--发布试题--%>
                        <%--</a>--%>
                    <%--</div>--%>
                    <%--<div class="col s0"></div>--%>
                <%--</div>--%>
                <%--<table class="striped">--%>
                    <%--<thead>--%>
                    <%--<tr>--%>
                        <%--<th>测试名称</th>--%>
                        <%--<th>截止时间</th>--%>
                    <%--</tr>--%>
                    <%--</thead>--%>
                    <%--<tbody>--%>
                    <%--<c:forEach var="onlineTest" items="${onlineTests}">--%>
                        <%--<tr>--%>
                            <%--<td><a href="/course/${onlineTest.course_id}/onlineTest/${onlineTest.id}">${onlineTest.title}</a></td>--%>
                            <%--<td>${onlineTest.ddl_date}</td>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>
                    <%--</tbody>--%>
                <%--</table>--%>
                <%--<div class="col s0 m1 l1"></div>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>
</tmpl:overwrite>
<jsp:include page="../../template/teacher_course_tmpl.jsp"/>