//Val Kobilaski
// 20060115

import java.util.Random;
import java.util.Scanner;

public class GradeCalculator{																		//Main Class; contains functionality for an applcation to generate,modify and analyse a simulated class with grades
	
	static Random generator = new Random(System.currentTimeMillis());								//New seed for all future random numbers
	static Scanner myScanner= new Scanner(System.in);												// System input for user input
	static String UInput;
	
	static int classSize;
	static Student[] classList;																		// All students are kept in classList as student objects
	static int assignment1Weight;
	static int assignment2Weight;
	static int finalExamWeight;
	
	public static void main(String[] args) {
		
		while (true) {
			
			System.out.println("Grade Calculator(Version 0.1).Author: Val Kobilaski\n");			//Main menu selection
			System.out.println("1-Simulate Course Marks");
			System.out.println("2-View/Update Student Marks");
			System.out.println("3-Run Mark Statistics\n");
			System.out.println("Select Option [1,2,3] (9 to Quit)");
			
			UInput=myScanner.next();
			int n =Integer.parseInt(UInput);
		
			switch(n) {																				// Cases determine which methods to run
			case 1: System.out.println("You selected option 1");
			simulateCourseMarks();																	//Option 1 generates a new class
			break;
			case 2: System.out.println("You selected option 2");
			viewUpdateStudentMarks();																//Option 2 views and modifies existing grades
			break;
			case 3: System.out.println("You selected option 3");
			runMarkStatistics();																	//Option 3 generates a table to view statistics
			break;
			case 9: System.out.println("You selected option 9, closing program");
			System.exit(0);																			//Option 9 closes program
			break;
			}
			System.out.println("==================================================================================== \n");
		}// end of loop

		

	
		
	}	//end of main
	
	public static void simulateCourseMarks() {	//Done
		
		if (!(classList==null)){																	//If a class has already been generated
			for(int i=0; i<classList.length;i++) {
				classList[i].setStatus(false);																//Set status of previous students to false
			}
		}
		System.out.println("Enter course enrollement size");										//User inputs class Size, assignment weight and final exam weight
		String UInput=myScanner.next();
		int classSize= Integer.parseInt(UInput);
		
		do {
			System.out.println("Enter weight assignment 1 (20-30)");
			UInput=myScanner.next();
			assignment1Weight=Integer.parseInt(UInput);
		} while(assignment1Weight<20 || assignment1Weight>30);
		
		do {
			System.out.println("Enter weight assignment 2 (20-30)");
			UInput=myScanner.next();
			assignment2Weight=Integer.parseInt(UInput);
		} while(assignment2Weight<20 || assignment2Weight>30);
		
		do {
			System.out.println("Enter weight final exam (40-60)");
			UInput=myScanner.next();
			finalExamWeight=Integer.parseInt(UInput);
		} while(finalExamWeight<40 || finalExamWeight>60);
		
		if (!(assignment1Weight+assignment2Weight+finalExamWeight==100)) {							//Verifies that the given weights are valid
			System.out.println("<<Error : weights do not add up to 100%>>");								//Error handling
			return;
		}
		
		classList=genNewClass(classSize);															// Generates the new class based on class Size
		for (int i=0;i<classSize;i++) {
			generateStudentMarks(classList[i]);
		}
	}
	
	public static void viewUpdateStudentMarks() {
		int index= -1;
		if (classList==null) {																	// Checks that the class is already initialized/exists
			System.out.println("<<Error: empty class list >> Run option 1 first!");
			return;
		}
		System.out.println("Enter Student Number:");
		int studentNum =Integer.parseInt(myScanner.next());										//Request student number from user
		for ( int i=0; i<classList.length;i++) {
			if (classList[i].getStudentNumber()==studentNum) {									//Checks if the status if false
				index=i;
				if(classList[i].getStatus()==false) {
					System.out.println(studentNum +" is invalid");								//Error handling
					return;
				}
			}
		}
		System.out.println("View or Update? (V/U):");
		UInput=myScanner.next();
		while(true) {
			if( UInput.equals("V")){															//If the user just wants to view the grades
				System.out.println("Student "+ classList[index].getStudentNumber()+ " marks\n");
					
				System.out.println("Assignment 1: " + classList[index].getMark("A1"));					//Collects and displays grades from student class
				System.out.println("Assignment 2: " + classList[index].getMark("A2"));
				System.out.println("Final Exam  : "+ classList[index].getMark("FE")+"\n");		
				return;
			}
			else if(UInput.equals("U")) {														// If the user wants to update the grades
				System.out.println("Mark Type? (A1, A2 ,FE):");
				UInput=myScanner.next();
				
				while(true) {
					if (UInput.equals("A1")) {
						System.out.println("Assignment 1 is "+classList[index].getMark("A1"));
						System.out.println("Updated Mark (0-100):");									
						int newMark=Integer.parseInt(myScanner.next());
						if (newMark<0 || newMark>100) {													//Verifies the new A1 grade is valid
							System.out.println(newMark + " is an invalid mark");
							return;
						}
						classList[index].updateMark("A1",newMark);												//Updates the A1 grade
						return;
					}
					else if (UInput.equals("A2")) {
						System.out.println("Assignment 2 is "+classList[index].getMark("A2"));
						System.out.println("Updated Mark (0-100):");
						int newMark=Integer.parseInt(myScanner.next());
						if (newMark<0 || newMark>100) {													//Verifies the new A2 grade is valid
							System.out.println(newMark + " is an invalid mark");
							return;
						}
						classList[index].updateMark("A2",newMark);												//Updates the A2 grade
						return;
					}
					else if  (UInput.equals("FE")) {
						System.out.println("final exam is "+classList[index].getMark("FE"));
						System.out.println("Updated Mark (0-100):");
						int newMark=Integer.parseInt(myScanner.next());
						if (newMark<0 || newMark>100) {													//Verifies the new FE grade is valid
							System.out.println(newMark + " is an invalid mark");
							return;
						}
						classList[index].updateMark("FE",newMark);												//Updates FE grade
						return;
					}
				}
			}
			
		}
		
	}
	
	public static void runMarkStatistics() {
		printClassReport(classList);
		System.out.println("\nPress Enter to Continue...");											//Allows user to exit
		UInput=myScanner.nextLine();
		UInput=myScanner.nextLine();
		return;
		}

	
	
	
	
	public static void generateStudentMarks(Student student) {	//Done								// Randomly generates possible assignment and final exam marks
		student.assignment1Mark=(generator.nextInt(100));
		student.assignment2Mark=(generator.nextInt(100));
		student.finalExamMark=(generator.nextInt(100));
	}
	public static double computeStudentGrade(Student student) {	//Done								//Calculates final grade

		double finalMark;
		double weightedA1=assignment1Weight*student.getMark("A1")/100;
		double weightedA2=assignment2Weight*student.getMark("A2")/100;
		double weightedFE=finalExamWeight*student.getMark("FE")/100;
		
		finalMark=weightedA1+weightedA2+weightedFE;
		
		return finalMark;
	}
	public static void printClassReport(Student[] classList) {	//Done								//Creates table for option 3
		double averageA1;
		double averageA2;
		double averageFE;
		double averageFinal;
		double maxFinal;
		double minFinal;
		
		
		int activeStudents=0;
																									//Counts the students with active status'
		
		if (classList==null) {
			System.out.println("Student Number    A1("+assignment1Weight+"%)    A2("+assignment2Weight+"%)    FE("+finalExamWeight+"%)    Final Mark\n");
			System.out.println("**************    *******    *******    *******    *********\n");
			System.out.println("************************************************************\n");
			return;

		}
		for (int i=0;i<classList.length;i++) {
			if(classList[i].getStatus()) {
				activeStudents++;
			}
		}
		Student[] activeClass= new Student[activeStudents];											//Creates a new array of only active students
		int j=0;
		for(int i=0;i<classList.length;i++) {
			if (classList[i].getStatus()) {
				activeClass[j]=classList[i];
				j++;
			}
		}
		
		maxFinal=computeStudentGrade(activeClass[0]);												//Calculates the highest final mark
		for (int i=1;i<activeClass.length;i++) {
			if(computeStudentGrade(activeClass[i])>maxFinal){
				maxFinal=computeStudentGrade(activeClass[i]);
			}
		}
		
		minFinal=computeStudentGrade(activeClass[0]);												//Calculates the lowest final mark
		for (int i=1;i<activeClass.length;i++) {
			if(computeStudentGrade(activeClass[i])<minFinal){
				minFinal=computeStudentGrade(activeClass[i]);
			}
		}
		
		double marksA1=0;																			//Calculates average for A1
		for(int i=0;i<activeClass.length;i++) {
			marksA1+=activeClass[i].getMark("A1");
		}
		averageA1=marksA1/activeStudents;
		
		double marksA2=0;																			//Calculates average for A2
		for(int i=0;i<activeClass.length;i++) {
			marksA2+=activeClass[i].getMark("A2");
		}
		averageA2=marksA2/activeStudents;
		
		double marksFE=0;																			//Calculates average for FE
		for(int i=0;i<activeClass.length;i++) {
			marksFE+=activeClass[i].getMark("FE");
		}
		averageFE=marksFE/activeStudents;
		
		double marksFinal=0;																		//Calculates average for final grade
		for(int i=0;i<activeClass.length;i++) {
			marksFinal+=computeStudentGrade(activeClass[i]);
		}
		averageFinal=marksFinal/activeStudents;
																									//Constructing the table(WITH A LOT OF FORMATTING)
		System.out.println("Student Number    A1("+assignment1Weight+"%)    A2("+assignment2Weight+"%)    FE("+finalExamWeight+"%)    Final Mark\n");
		System.out.println("**************    *******    *******    *******    *********\n");
		for (int i=0;i<activeClass.length;i++) {
			System.out.println(activeClass[i].getStudentNumber()+"            "+ String.format("%02d",Math.round(activeClass[i].getMark("A1")))+"         "+ String.format("%02d",Math.round(activeClass[i].getMark("A2")))+"         "+String.format("%02d",Math.round(activeClass[i].getMark("FE")))+"          "+Math.round(computeStudentGrade(activeClass[i])*100.00)/100.00+"    ");
		}
		System.out.println("************************************************************\n");
		System.out.println("AVERAGES            "+String.format("%-2s",Math.round(averageA1*100.00)/100.00).replace(' ', '0')+"      "+String.format("%-2s",Math.round(averageA2*100.00)/100.00).replace(' ', '0')+"      "+String.format("%-2s",Math.round(averageFE*100.00)/100.00).replace(' ', '0')+"        "+String.format("%-2s",Math.round(averageFinal*100.00)/100.00).replace(' ', '0')+"    ");
		System.out.println("\nHighest final mark is "+ maxFinal);
		System.out.println("Lowest final mark is "+ minFinal);
	}
	public static Student[] genNewClass(int classSize) {	//Done									//Generates a new class
		Student[] classList=new Student[classSize];
		for (int i=0;i<classSize;i++) {
			classList[i]=new Student(10000000+generator.nextInt(89999999));							// Randomly generates a valid student number
		}
		return classList;
	}
}