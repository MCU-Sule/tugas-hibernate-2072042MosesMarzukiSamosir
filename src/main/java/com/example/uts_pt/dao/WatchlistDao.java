package com.example.uts_pt.dao;

import com.example.uts_pt.model.WatchlistEntity;
import com.example.uts_pt.util.HiberUtility;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class WatchlistDao implements DaoService<WatchlistEntity>{
    @Override
    public List<WatchlistEntity> getdata() {
        List<WatchlistEntity>Wlist;

        Session s = HiberUtility.getSessionFactory();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery(WatchlistEntity.class);
        query.from(WatchlistEntity.class);

        Wlist = s.createQuery(query).getResultList();
        return Wlist;
    }

    @Override
    public void addData(WatchlistEntity data) {

    }

    @Override
    public void deleteData(WatchlistEntity data) {

    }

    @Override
    public void updateData(WatchlistEntity data) {

    }

//    @Override
//    public ObservableList<Watchlist> getData() {
//        ObservableList<Watchlist> watchlists;
//        watchlists = FXCollections.observableArrayList();
//
//        Connection conn = MyConnection.getConnection();
//        String query = "select movie.Title,watchlist.LastWatch, watchlist.Favorite,watchlist.Movie_idMovie, watchlist.User_idUser,movie.Title, movie.Genre, movie.Durasi, user.UserName, user.UserPassword  FROM watchlist \n" +
//                "Join user on watchlist.User_idUser  = user.idUser\n" +
//                "join movie on watchlist.Movie_idMovie = movie.idMovie";
//        PreparedStatement ps;
//        try {
//            ps = conn.prepareStatement(query);
//            ResultSet result = ps.executeQuery();
//            while (result.next()){
//                int lastwatch = result.getInt("LastWatch");
//                boolean fav = result.getBoolean("Favorite");
//                int idm = result.getInt("Movie_idMovie");
//                String title = result.getString("Title");
//                String genre = result.getString("Genre");
//                int durasi = result.getInt("Durasi");
//                String username = result.getString("UserName");
//                String pass = result.getString("UserPassword");
//                Movie idmov = new Movie(idm,title,genre,durasi);
//                User iduser = new User(username,pass);
//                Watchlist wl = new Watchlist(lastwatch,fav,idmov,iduser);
//                watchlists.add(wl);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return watchlists;
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
