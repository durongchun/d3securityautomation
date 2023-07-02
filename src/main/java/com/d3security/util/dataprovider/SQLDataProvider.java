package com.d3security.util.dataprovider;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.d3security.util.MybatisUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SQLDataProvider {		
      
    public static List<Map<String, Object>> executeSql(String sql) {   
    	MybatisUtil mybatisUtil=new MybatisUtil();
    	List<Map<String, Object>> userMap;
    	SqlSession sqlSession=null;
		try {
			sqlSession = mybatisUtil.sqlSessionFactory.openSession();
			userMap = sqlSession.selectList("user.executeSql", sql);
		} finally {
			sqlSession.close();
		}		
		return userMap;
    	
    }
    
   

}
