package com.example.uts_pt.dao;

import com.example.uts_pt.model.MovieEntity;
import com.example.uts_pt.util.HiberUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MovieDao implements DaoService<MovieEntity> {
    @Override
    public List<MovieEntity> getdata() {
        List<MovieEntity>Mlist;

        Session s = HiberUtility.getSessionFactory();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery(MovieEntity.class);
        query.from(MovieEntity.class);

        Mlist = s.createQuery(query).getResultList();
        return Mlist;
    }

    @Override
    public void addData(MovieEntity data) {
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
    public void deleteData(MovieEntity data) {
        Session s = HiberUtility.getSessionFactory();

        Transaction t = s.beginTransaction();
        try{
            s.delete(data);
            t.commit();
        }catch (Exception exception){
            t.rollback();
        }
        s.close();
    }

    @Override
    public void updateData(MovieEntity data) {
        Session s = HiberUtility.getSessionFactory();
        Transaction t = s.beginTransaction();
        try{
            s.update(data);
            t.commit();
        }catch (Exception exception){
            t.rollback();
        }
        s.close();
    }


//    @Override
//    public ObservableList<Movie> getData() {
//        ObservableList<Movie> movies;
//        movies = FXCollections.observableArrayList();
//
//        Connection conn = MyConnection.getConnection();
//        String query = "SELECT * FROM Movie";
//        PreparedStatement ps;
//        try {
//            ps = conn.prepareStatement(query);
//            ResultSet result = ps.executeQuery();
//            while (result.next()){
//                int id = result.getInt("idMovie");
//                String title = result.getString("Title");
//                String genre = result.getString("Genre");
//                int durasi = result.getInt("Durasi");
//                Movie movie = new Movie(id,title,genre,durasi);
//                movies.add(movie);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return movies;
//    }
//
//    @Override
//    public void addData(Object data) {
//
//    }
//
//    @Override
//    public boolean delData(Object data) {
//        return false;
//    }
//
//    @Override
//    public int updateData(Object data) {
//        return 0;
//    }
}
