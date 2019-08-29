package com.jigexio.store.dao.imp;

import com.jigexio.db.core.JdbcTemplate;
import com.jigexio.store.dao.GoodsDao;
import com.jigexio.store.domain.Goods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpJdbc implements GoodsDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public Goods findByPk(long pk) {

        List<Goods> list = new ArrayList<Goods>();

        String sql = "select id,name,price,description,brand,style,car_type,displacement,mileage,image from Goods where id=?";
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, pk);
            return ps;
        }, rs -> {
            populate(list, rs);

        });

        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Goods> findAll() {

        List<Goods> list = new ArrayList<Goods>();

        String sql = "select id,name,price,description,brand,style,car_type,displacement,mileage,image from Goods";
        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps;
        }, rs -> {
            populate(list, rs);
        });

        return list;
    }

    @Override
    public List<Goods> findStartEnd(int start, int end) {

        List<Goods> list = new ArrayList<Goods>();

        StringBuffer sql = new StringBuffer("select id,name,price,description,brand,style,car_type,displacement,mileage,image from Goods");
        sql.append(" LIMIT ").append(end - start);
        sql.append(" OFFSET ").append(start);

        jdbcTemplate.query(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            return ps;
        }, rs -> {
            populate(list, rs);

        });

        return list;
    }

    private void populate(List<Goods> list, ResultSet rs) throws SQLException {
        Goods goods = new Goods();
        goods.setId(rs.getLong("id"));
        goods.setName(rs.getString("name"));
        goods.setPrice(rs.getDouble("price"));
        goods.setDescription(rs.getString("description"));
        goods.setBrand(rs.getString("brand"));
        goods.setStyle(rs.getString("style "));
        goods.setCar_type(rs.getString("car_type"));
        goods.setDisplacement(rs.getString("displacement"));
        goods.setMileage(rs.getString("mileage"));
        goods.setImage(rs.getString("image"));

        list.add(goods);
    }

    @Override
    public void create(Goods goods) {

        String sql = "insert into Goods (id,name,price,description,brand,style,car_type,displacement,mileage,image)" +
                " values (?,?,?,?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(conn -> {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, goods.getId());
            ps.setString(2, goods.getName());
            ps.setDouble(3, goods.getPrice());
            ps.setString(4, goods.getDescription());
            ps.setString(5, goods.getBrand());
            ps.setString(6, goods.getStyle());
            ps.setString(7, goods.getCar_type());
            ps.setString(8, goods.getDisplacement());
            ps.setString(9, goods.getMileage());
            ps.setString(10, goods.getImage());

            return ps;
        });

    }

    @Override
    public void modify(Goods goods) {
        String sql = "update Goods set name=?,price=?,description=?,brand=?,style=?,car_type=?,displacement=?,mileage=?=?,image=? where id=?";

        jdbcTemplate.update(conn -> {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, goods.getName());
            ps.setDouble(2, goods.getPrice());
            ps.setString(3, goods.getDescription());
            ps.setString(4, goods.getBrand());
            ps.setString(5, goods.getStyle());
            ps.setString(6, goods.getCar_type());
            ps.setString(7, goods.getDisplacement());
            ps.setString(8, goods.getMileage());
            ps.setString(9, goods.getImage());
            ps.setLong(10, goods.getId());

            return ps;
        });
    }

    @Override
    public void remove(long pk) {
        String sql = "delete from Goods where id=?";

        jdbcTemplate.update(conn -> {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, pk);

            return ps;
        });
    }
}
