//package org.dfpl.lecture.database.assignment.assignment17013238;

public class AccidentInfo {
	private String accidentWho; // 사고유형 대분류
	private String accidentHow; // 사고유형 중분류
	private String accidentType; // 사고유형
	private int accidentCount; // 사고건수
	private int deadCount; // 사망자수
	private int criticalCount; // 중상자수
	private int injuredCount; // 경상자수
	private int hurtCount; // 부상신고자수

	public AccidentInfo(String accidentWho, String accidentHow, String accidentType, int accidentCount, int deadCount,
			int criticalCount, int injuredCount, int hurtCount) {
		this.accidentWho = accidentWho;
		this.accidentHow = accidentHow;
		this.accidentType = accidentType;
		this.accidentCount = accidentCount;
		this.deadCount = deadCount;
		this.criticalCount = criticalCount;
		this.injuredCount = injuredCount;
		this.hurtCount = hurtCount;
	}

	public String print() {
		return accidentWho + "," + accidentHow + "," + accidentType + "," + accidentCount + "," + deadCount + ","
				+ criticalCount + "," + injuredCount + "," + hurtCount;
	}

	public String getAccidentWho() {
		return accidentWho;
	}

	public void setAccidentWho(String accidentWho) {
		this.accidentWho = accidentWho;
	}

	public String getAccidentHow() {
		return accidentHow;
	}

	public void setAccidentHow(String accidentHow) {
		this.accidentHow = accidentHow;
	}

	public String getAccidentType() {
		return accidentType;
	}

	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}

	public int getAccidentCount() {
		return accidentCount;
	}

	public void setAccidentCount(int accidentCount) {
		this.accidentCount = accidentCount;
	}

	public int getDeadCount() {
		return deadCount;
	}

	public void setDeadCount(int deadCount) {
		this.deadCount = deadCount;
	}

	public int getCriticalCount() {
		return criticalCount;
	}

	public void setCriticalCount(int criticalCount) {
		this.criticalCount = criticalCount;
	}

	public int getInjuredCount() {
		return injuredCount;
	}

	public void setInjuredCount(int injuredCount) {
		this.injuredCount = injuredCount;
	}

	public int getHurtCount() {
		return hurtCount;
	}

	public void setHurtCount(int hurtCount) {
		this.hurtCount = hurtCount;
	}
}
