package com.se.courses.announcement.dao;

//import packages
import com.se.global.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.se.courses.announcement.domain.Announcement;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
@Repository
public class AnnouncementDAO {
    private JdbcTemplate jdbcTemplate;
    private final String UPLOAD_ANNOUNCEMENT_SQL = "INSERT INTO announcement(" + SqlService.ANNOUNCEMENT_TITLE + "," +
            SqlService.ANNOUNCEMENT_CONTENT + "," + SqlService.ANNOUNCEMENT_DATE + "," + SqlService.ANNOUNCEMENT_COURSE_ID + ") VALUES(?,?,?,?)";
    private final String GET_LATEST_ANNOUNCEMENT_SQL = "SELECT * FROM announcement WHERE " + SqlService.ANNOUNCEMENT_COURSE_ID + " = ? " +
            " ORDER BY " + SqlService.ANNOUNCEMENT_DATE + " DESC";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    /**
     * 上传公告
     *
     * @param courseId 课程id
     * @param title 公告标题
     * @param content 公告内容
     * @throws DataAccessException 数据库访问出错
     */
    public void upload(int courseId, String title, String content) throws DataAccessException {
        Object[] args = new Object[] {title, content, new Date(), courseId};
        jdbcTemplate.update(UPLOAD_ANNOUNCEMENT_SQL, args);
    }

    /**
     * 获取最新公告
     *
     * @param courseId 课程id
     * @return 最新的公告对象
     * @throws SQLException SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public Announcement getLatestAnnouncement(final int courseId) throws SQLException, DataAccessException {
        return jdbcTemplate.query(GET_LATEST_ANNOUNCEMENT_SQL, new Object[] {courseId}, new ResultSetExtractor<Announcement>() {
            @Override
            public Announcement extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Announcement announcement = new Announcement();

                if (resultSet.next()) {
                    announcement.setTitle(resultSet.getString(SqlService.ANNOUNCEMENT_TITLE));
                    announcement.setContent(resultSet.getString(SqlService.ANNOUNCEMENT_CONTENT));
                    announcement.setDate(resultSet.getDate(SqlService.ANNOUNCEMENT_DATE));
                    announcement.setCourseId(courseId);
                }

                return announcement;
            }
        });
    }

    /**
     * 获取公告列表
     *
     * @param courseId 课程id
     * @return 公告列表
     * @throws SQLException SQL查询出错
     * @throws DataAccessException 数据库访问出错
     */
    public ArrayList<Announcement> getAnnouncements(final int courseId) throws SQLException, DataAccessException {
        return jdbcTemplate.query(GET_LATEST_ANNOUNCEMENT_SQL, new Object[] {courseId}, new ResultSetExtractor<ArrayList<Announcement>>() {
            @Override
            public ArrayList<Announcement> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                ArrayList<Announcement> announcementList = new ArrayList<Announcement>();

                while (resultSet.next()) {
                    Announcement announcement = new Announcement();
                    announcement.setTitle((resultSet.getString(SqlService.ANNOUNCEMENT_TITLE)));
                    announcement.setContent(resultSet.getString(SqlService.ANNOUNCEMENT_CONTENT));
                    announcement.setDate(resultSet.getDate(SqlService.ANNOUNCEMENT_DATE));
                    announcement.setCourseId(courseId);
                    announcementList.add(announcement);
                }

                return announcementList;
            }
        });
    }
}
