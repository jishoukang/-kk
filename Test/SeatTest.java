package com.lib.mangement.Test;

import org.junit.Test;

import com.lib.mangement.Common.dao.CommonDao;

public class SeatTest {
	
  //座位数据
  @Test
  public void testdata2() {
	  String sql2="insert into seat(status) values(?)";
	  try {
		 for (int i = 0; i < 10000; i++) {
			CommonDao.executeUpdate(sql2, new Object[] {"空"});
		}
	
	} catch (Exception e) {
		// TODO: handle exception
	}  
	 
	  
  }
	
}
