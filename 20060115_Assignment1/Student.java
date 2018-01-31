// Val Kobilaski
//20060115


public class Student{
	
	int studentNumber;
	boolean status;
	int assignment1Mark;
	int assignment2Mark;
	int finalExamMark;
	
	
	Student(int Number){																			//Constructor method for student object
		
		studentNumber=Number;
		if (studentNumber>10000000 && studentNumber<100000000){										//Verifies the student number is valid (REDUNDANT)
			status=true;
		}
		else {
			status=false;
		}

		
	}
	public int getStudentNumber() {																	//returns student number
		return studentNumber;
	}
	public void setStudentNumber(int newNumber) {	//Done											//Allows student number to be changed
		studentNumber=newNumber;
		if (studentNumber>10000000 && studentNumber<10000000){										//Verifies it's still valid
			status=true;
		}
		else {
			status=false;
		}
	}
	
	public void updateMark(String markType,int mark) {	//Done										//allows for changes to be made to marks

		if (markType.equals("A1")) {
			assignment1Mark=mark;
		}
		else if (markType.equals("A2")) {															//Grade verification is handled in viewUpdateStudentMarks() method
			assignment2Mark=mark;
		}
		else if (markType.equals("FE")) {
			finalExamMark=mark;
		}
		else {
			System.out.println("Invalid mark type enterd");											//ensures mark type is valid
		}
	}
	
	public double getMark(String markType) {	//Done												//returns marks for assignments and final exam
		if (markType.equals("A1")) {
			return assignment1Mark;
		}
		else if (markType.equals("A2")) {
			return assignment2Mark;
		}
		else if (markType.equals("FE")) {
			return finalExamMark;
		}
		else {
			System.out.println("Invalid mark type entered");
			return -1;
		}
	}
	
	public boolean getStatus() { //Done																//returns status of student
		return status;
	}
	
	public void setStatus(boolean newStatus) { //Done												//allows status to be changed
		status= newStatus;
	}
}