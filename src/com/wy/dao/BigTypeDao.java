package com.wy.dao;

import com.wy.tool.JDBConnection;
import java.sql.*;
import java.util.*;
import com.wy.domain.BigTypeForm;

//对商品大类别信息的操作
public class BigTypeDao {
  private Connection connection = null; //定义连接的对象
  private PreparedStatement ps = null; //定义预准备的对象
  private JDBConnection jdbc = null; //定义数据库连接对象
  public BigTypeDao() {
    jdbc = new JDBConnection();
    connection = jdbc.connection; //利用构造方法取得数据库连接
  }

//以数据库流水号为条件查询大类别的名称
  public String selectName(Integer id) {
    String name = null;
    try {
      this.ps = connection.prepareStatement("select * from tb_bigType where id=?");
      ps.setString(1, id.toString());
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        name = rs.getString("bigName");
      }
    }
    catch (SQLException ex) {
    	ex.printStackTrace();
    }
    return name;
  }

//删除操作
  public boolean deleteBig(Integer id) {
    try {
      ps = connection.prepareStatement("delete from tb_bigType where id=?");
      ps.setString(1, id.toString());
      ps.executeUpdate();
      ps.close();
      return true;
    }
    catch (SQLException ex) {
    	ex.printStackTrace();
      return false;
    }
  }


  //添加操作
  public void insertBig(String name) {
    try {
      ps = connection.prepareStatement("insert into tb_bigType values (null,?,now())");
      ps.setString(1, name);
      ps.executeUpdate();
      ps.close();
    }
    catch (SQLException ex) {
    	ex.printStackTrace();
    }
  }

  //全部查询的操作
  public List selectBig() {
    List list = new ArrayList();
    BigTypeForm big = null;
    try {
      this.ps = connection.prepareStatement("select * from tb_bigType order by id DESC");
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        big = new BigTypeForm();
        big.setId(Integer.valueOf(rs.getString(1)));
        big.setBigName(rs.getString(2));
        big.setCreaTime(rs.getString(3));
        list.add(big);
      }
    }
    catch (SQLException ex) {
    	ex.printStackTrace();
    }
    return list;
  }

}
