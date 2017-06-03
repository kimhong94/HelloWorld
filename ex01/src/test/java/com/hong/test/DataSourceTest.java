package com.hong.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DataSourceTest {
	
	@Inject
	private DataSource ds;
	
	@Test
	public void testConnection() throws SQLException {
		Connection con = ds.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from tbl_member");
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		System.out.println(rs.getString("userid"));
	}

}
