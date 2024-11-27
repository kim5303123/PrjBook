/**
 * 
 */
package prj.com.book.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author : 202-12
 * @Date   : 2024. 11. 27.
 */
public class Connections {
	// 연결 객체 정의
		private static Connection conn;
		
		
//		본인 계정 넣기
//		static final String dburl = "jdbc:mysql://localhost:3306/test_db";
//		static final String dbuser = "test_user";
//		static final String dbpass = "test";
//		
		
		// 초기화 블럭(static 블럭) : 가장 먼저 메모리에 로딩 → 실행
		static {
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "hanul";
			String password = "0000";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("DB Connection Exception!!!");
			}
		}

		//getConn() 정의
		public static Connection getConn() {
			return conn;
		}
}
