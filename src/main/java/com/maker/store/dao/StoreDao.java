package com.maker.store.dao;

import com.maker.store.model.Store;
import com.maker.store.util.DbUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StoreDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Store selectStoreByStoreId(@Param("storeId") String storeId){
        Store store=jdbcTemplate.queryForObject("SELECT * FROM store WHERE store_id=?",new Store(),new Object[]{storeId});
//        Store store = new Store();
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/maker","root","123456");
////            第一种
////            Statement stat=connection.createStatement();
////            ResultSet rs=stat.executeQuery("SELECT * FROM store WHERE store_id='"+storeId+"'");
////            第二种
////            PreparedStatement pstat=connection.prepareStatement("SELECT * FROM store WHERE store_id=?");
////            pstat.setString(1,storeId);
////            ResultSet rs=pstat.executeQuery();
////            第三种
//            CallableStatement cs=connection.prepareCall("{CALL sp_select_store_by_store_id(?)}");
//            cs.setString(1,storeId);
//            ResultSet rs=cs.executeQuery();
//            if (rs.next()){
//                store.setStoreId(rs.getInt("store_id"));
//                store.setStoreName(rs.getString("store_name"));
//                store.setStoreIntroduce(rs.getString("store_introduce"));
//                store.setBrowseTimes(rs.getInt("browse_times"));
//            }
//            rs.close();
////            stat.close();
////            pstat.close();
//            cs.close();
//            connection.close();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return store;

    }
    public int deleteStore (@Param("storeId") String storeId){
        return DbUtil.executeUpdate("DELETE FROM store WHERE store_id=?",new Object[]{storeId})==true?1:0;
    }
}