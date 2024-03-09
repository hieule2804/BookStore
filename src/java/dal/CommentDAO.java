/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comment;

/**
 *
 * @author ADMIN
 */
public class CommentDAO extends DBContext {

    public int addcmt(int bid, String username, String comment) {
        connection = getConnection();
        String sql = "INSERT INTO [dbo].[comment]\n"
                + "           ([bid]\n"
                + "           ,[cmtuser]\n"
                + "           ,[cmtdetail])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, bid);
            statement.setString(2, username);
            statement.setString(3, comment);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public List<Comment> getlistComment() {
        List<Comment> list = new ArrayList<>();
        connection = getConnection();
        String sql = "SELECT [cmtid]\n"
                + "      ,[bid]\n"
                + "      ,[cmtuser]\n"
                + "      ,[cmtdetail]\n"
                + "  FROM [dbo].[comment]";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comment c = new Comment();
                c.setCmtid(resultSet.getInt("cmtid"));
                c.setBid(resultSet.getInt("bid"));
                c.setCmtuser(resultSet.getString("cmtuser"));
                c.setCmtdetail(resultSet.getString("cmtdetail"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Comment> getlistCommentbyId(int id) {
        List<Comment> list = new ArrayList<>();
        for (Comment comment : getlistComment()) {
            if (comment.getBid() == id) {
                list.add(comment);
            }
        }
        return list;
    }

    public List<Comment> getListComment() {
        List<Comment> list = new ArrayList<>();
        connection = getConnection();
        String sql = "SELECT [cmtid]\n"
                + "      ,[bid]\n"
                + "      ,[cmtuser]\n"
                + "      ,[cmtdetail]\n"
                + "  FROM [dbo].[comment]";
        try {
            statement = connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while (resultSet.next()) {
             Comment c = new Comment();
             c.setCmtid(resultSet.getInt("cmtid"));
             c.setBid(resultSet.getInt("bid"));
             c.setCmtuser(resultSet.getString("cmtuser"));
             c.setCmtdetail(resultSet.getString("cmtdetail"));
             list.add(c);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
return list;
    }

    public void deleteByid(int id) {
         connection =getConnection();
         String sql ="DELETE FROM [dbo].[comment]\n" +
"      WHERE cmtid = ?";
        try {
            statement=connection.prepareStatement(sql);
         statement.setInt(1, id);
         statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
