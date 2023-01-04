package com.ezen.delivery.repository;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JDBCTests {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		
		try {
			Connection con =
			DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/deliverydb?allowpublickeyretrieval=true",
					"springUser",
					"mysql"
					);
			log.info(con.toString());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
}
