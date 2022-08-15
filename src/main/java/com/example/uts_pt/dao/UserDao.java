package com.example.uts_pt.dao;

import com.example.uts_pt.model.UserEntity;
import com.example.uts_pt.util.HiberUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDao implements DaoService<UserEntity> {
    @Override
    public List<UserEntity> getdata() {
        List<UserEntity>Ulist;

        Session s = HiberUtility.getSessionFactory();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery(UserEntity.class);
        query.from(UserEntity.class);

        Ulist = s.createQuery(query).getResultList();
        return Ulist;
    }

    @Override
    public void addData(UserEntity data) {
        Session s = HiberUtility.getSessionFactory();
        Transaction t = s.beginTransaction();
        try{
            s.save(data);
            t.commit();
        }catch (Exception exception){
            t.rollback();
        }
        s.close();
    }

    @Override
    public void deleteData(UserEntity data) {
        Session s = HiberUtility.getSessionFactory();
        Transaction t = s.beginTransaction();
        try{
            s.delete(data);
            t.commit();
        }catch (Exception exception){
            System.out.println(exception);
            t.rollback();
        }
        s.close();
    }

    @Override
    public void updateData(UserEntity data) {

    }

//    @Override
//    public ObservableList<User> getData() {
//        ObservableList<User> users;
//        users = FXCollections.observableArrayList();
//
//        Connection conn = MyConnection.getConnection();
//        String query = "Select * FROM User";
//        PreparedStatement ps;
//        try {
//            ps = conn.prepareStatement(query);
//            ResultSet result = ps.executeQuery();
//            while (result.next()){
//                String username = result.getString("UserName");
//                String pw = result.getString("UserPassword");
//                User user = new User(username,pw);
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return users;
//    }
//
//    @Override
//    public void addData(User data) {
//        Connection conn = MyConnection.getConnection();
//        String query = "INSERT INTO user (UserName, UserPassword) VALUES (?,?)";
//        try {
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setString(1,data.getUsername());
//            ps.setString(2,data.getPassword());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public boolean delData(User data) {
//        Connection conn = MyConnection.getConnection();
//        String query = "DELETE FROM user WHERE idUser = ?";
//        PreparedStatement ps;
//        try {
//            ps = conn.prepareStatement(query);
//            ps.setInt(1,data.getIdUser());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return true;
//    }
//
//    @Override
//    public int updateData(User data) {
//        return 0;
//    }
//

}
