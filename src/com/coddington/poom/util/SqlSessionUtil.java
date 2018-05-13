package com.coddington.poom.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtil {
	
	private static SqlSessionFactory factory;
	
	//인스턴스 멤버필드의 초기화 : 생성자
	
	//static 멤버필드의 초기화 : static 영역
	
	//main이 작동되기 전에 해야할 일 있을 때
	//static영역으로 빼서 먼저 작동하게 한다.
	
	static{ 
		
		Reader reader = null;
	
	try {
		
		reader = 
			Resources.getResourceAsReader("com/coddington/poom/config/mybatis-config.xml");
		
	}catch(Exception e) {
		
		e.printStackTrace();
		
	}//try~catch end
	
	SqlSessionFactoryBuilder builder = 
			new SqlSessionFactoryBuilder();
	
	factory = builder.build(reader);
	
	}//static end
	

	public static SqlSession getSession() {
		
		return factory.openSession(true);
		
	}//getSession() end
	
}//SqlSessionUtil
