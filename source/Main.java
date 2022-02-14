import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		
		//파일을 읽어 데이터로딩하는 과제 1
		
		// BufferedReader를 통해 파일 읽어오기
		BufferedReader r = new BufferedReader(new FileReader("c:\\assignment.csv", Charset.forName("euc-kr")));

		ArrayList<AccidentInfo> accidentInfo = new ArrayList(); // 읽은 내용을 담을 arrayList

		String str = r.readLine(); // 맨 윗줄은 속성을 나타내는 줄

		// 읽어들인 파일 한줄씩 읽어 ArrayList에 담기
		while (true) {

			str = r.readLine();// 한줄읽기
			if (str == null)// 빈줄이라면 종료
				break; 

			String[] strArr = str.split(","); // 읽어들인 한줄 ,를 기준으로 분류

			// AccidentInfo클래스 생성하여 ArrayList에 담기
			accidentInfo.add(new AccidentInfo(strArr[0], strArr[1], strArr[2], Integer.parseInt(strArr[3]),
					Integer.parseInt(strArr[4]), Integer.parseInt(strArr[5]), Integer.parseInt(strArr[6]),
					Integer.parseInt(strArr[7])));
		}
		
		//과제1 출력!!
		for(AccidentInfo accident : accidentInfo) {
			System.out.println(accident.print());
		}
		
		//여기까지 과제 1
		System.out.println("---------------------------------------------------------");
		
		//DB에 접속해서 로딩한 데이터를 담을 테이블을 만드는 과제 2
		String id = "root";
		String pw = "1234";
		// MariaDB에 접속
		Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);

		// SQL을 사용하기 위함
		Statement stmt = connection.createStatement();

		// db라는 데이터베이스를 존재하더라도 새로만들고 db에 접속한다
		stmt.executeUpdate("CREATE OR REPLACE DATABASE db;");
		stmt.executeUpdate("USE db");

		// accidentinfo테이블을 생성한다.
		stmt.executeUpdate(
				"CREATE OR REPLACE TABLE accidentinfo (" + "accident_who VARCHAR(50), " + "accident_how VARCHAR(50), "
						+ "accident_type VARCHAR(50), " + "accident_count INT(11), " + "dead_count INT(11), "
						+ "critical_count INT(11), " + "injured_count INT(11), " + "hurt_count INT(11)" + ");");

		// DESCRIBE명령을 수행한 결과 출력
		ResultSet rs = stmt.executeQuery("DESCRIBE accidentinfo;");
		while (rs.next()) {
			String field = rs.getString("Field");
			String type = rs.getString("Type");
			String isNull = rs.getString("NULL");
			String key = rs.getString("Key");
			String defaultValue = rs.getString("Default");
			String extra = rs.getString("Extra");
			System.out.println(field + "\t" + type + "\t" + isNull + "\t" + key + "\t" + defaultValue + "\t" + extra);
		}
		
		//여기까지 과제 2
		System.out.println("-----------------------------------------------------------");
		
		//생성한 테이블에 로딩한 데이터들을 삽입하고 select문을 통해 출력하는 과제3 
		
		// 한글데이터 삽입을 인식하기위해 accidentinfo테이블 데이터셋을 UTF8로 바꿔준다.
		stmt.executeUpdate("ALTER TABLE accidentinfo CONVERT TO CHARACTER SET utf8;");

		// ArrayList<AccidentInfo> 순회하면서 테이블에 데이터 삽입하기
		for (AccidentInfo accident : accidentInfo) {

			stmt.executeUpdate(
					"INSERT INTO accidentinfo VALUES('" + accident.getAccidentWho() + "','" + accident.getAccidentHow()
							+ "','" + accident.getAccidentType() + "','" + accident.getAccidentCount() + "','"
							+ accident.getDeadCount() + "','" + accident.getCriticalCount() + "','"
							+ accident.getInjuredCount() + "','" + accident.getHurtCount() + "');");

		}
		
		//SELECT문을 통해 accidentinfo 테이블에 전체 데이터를 쿼리하고 출력
		rs = stmt.executeQuery("SELECT * FROM accidentinfo;");
		while (rs.next()) { //다음 줄이 비어있을 때 까지
			String who = rs.getString("accident_who");
			String how = rs.getString("accident_how");
			String type = rs.getString("accident_type");
			String accidentCount = rs.getString("accident_count");
			String deadCount = rs.getString("dead_count");
			String criticalCount = rs.getString("critical_count");
			String injuredCount = rs.getString("injured_count");
			String hurtCount = rs.getString("hurt_count");
			System.out.println(who + "\t" + how + "\t" + type + "\t" + accidentCount + "\t" + deadCount + "\t"
					+ criticalCount + "\t" + injuredCount + "\t" + hurtCount);
		}

		//SELECT문을 통해 사망자 수가 100명이상인 사고유형들의 개수를 출력
		rs = stmt.executeQuery("SELECT COUNT(*) FROM accidentinfo WHERE dead_count >= 100;");
		rs.next();
		System.out.println(rs.getInt("count(*)"));

		// DB연결 종료
		connection.close();

	}

}
