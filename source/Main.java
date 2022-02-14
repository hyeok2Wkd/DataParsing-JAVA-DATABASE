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
		
		//������ �о� �����ͷε��ϴ� ���� 1
		
		// BufferedReader�� ���� ���� �о����
		BufferedReader r = new BufferedReader(new FileReader("c:\\assignment.csv", Charset.forName("euc-kr")));

		ArrayList<AccidentInfo> accidentInfo = new ArrayList(); // ���� ������ ���� arrayList

		String str = r.readLine(); // �� ������ �Ӽ��� ��Ÿ���� ��

		// �о���� ���� ���پ� �о� ArrayList�� ���
		while (true) {

			str = r.readLine();// �����б�
			if (str == null)// �����̶�� ����
				break; 

			String[] strArr = str.split(","); // �о���� ���� ,�� �������� �з�

			// AccidentInfoŬ���� �����Ͽ� ArrayList�� ���
			accidentInfo.add(new AccidentInfo(strArr[0], strArr[1], strArr[2], Integer.parseInt(strArr[3]),
					Integer.parseInt(strArr[4]), Integer.parseInt(strArr[5]), Integer.parseInt(strArr[6]),
					Integer.parseInt(strArr[7])));
		}
		
		//����1 ���!!
		for(AccidentInfo accident : accidentInfo) {
			System.out.println(accident.print());
		}
		
		//������� ���� 1
		System.out.println("---------------------------------------------------------");
		
		//DB�� �����ؼ� �ε��� �����͸� ���� ���̺��� ����� ���� 2
		String id = "root";
		String pw = "1234";
		// MariaDB�� ����
		Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", id, pw);

		// SQL�� ����ϱ� ����
		Statement stmt = connection.createStatement();

		// db��� �����ͺ��̽��� �����ϴ��� ���θ���� db�� �����Ѵ�
		stmt.executeUpdate("CREATE OR REPLACE DATABASE db;");
		stmt.executeUpdate("USE db");

		// accidentinfo���̺��� �����Ѵ�.
		stmt.executeUpdate(
				"CREATE OR REPLACE TABLE accidentinfo (" + "accident_who VARCHAR(50), " + "accident_how VARCHAR(50), "
						+ "accident_type VARCHAR(50), " + "accident_count INT(11), " + "dead_count INT(11), "
						+ "critical_count INT(11), " + "injured_count INT(11), " + "hurt_count INT(11)" + ");");

		// DESCRIBE����� ������ ��� ���
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
		
		//������� ���� 2
		System.out.println("-----------------------------------------------------------");
		
		//������ ���̺� �ε��� �����͵��� �����ϰ� select���� ���� ����ϴ� ����3 
		
		// �ѱ۵����� ������ �ν��ϱ����� accidentinfo���̺� �����ͼ��� UTF8�� �ٲ��ش�.
		stmt.executeUpdate("ALTER TABLE accidentinfo CONVERT TO CHARACTER SET utf8;");

		// ArrayList<AccidentInfo> ��ȸ�ϸ鼭 ���̺� ������ �����ϱ�
		for (AccidentInfo accident : accidentInfo) {

			stmt.executeUpdate(
					"INSERT INTO accidentinfo VALUES('" + accident.getAccidentWho() + "','" + accident.getAccidentHow()
							+ "','" + accident.getAccidentType() + "','" + accident.getAccidentCount() + "','"
							+ accident.getDeadCount() + "','" + accident.getCriticalCount() + "','"
							+ accident.getInjuredCount() + "','" + accident.getHurtCount() + "');");

		}
		
		//SELECT���� ���� accidentinfo ���̺� ��ü �����͸� �����ϰ� ���
		rs = stmt.executeQuery("SELECT * FROM accidentinfo;");
		while (rs.next()) { //���� ���� ������� �� ����
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

		//SELECT���� ���� ����� ���� 100���̻��� ����������� ������ ���
		rs = stmt.executeQuery("SELECT COUNT(*) FROM accidentinfo WHERE dead_count >= 100;");
		rs.next();
		System.out.println(rs.getInt("count(*)"));

		// DB���� ����
		connection.close();

	}

}
