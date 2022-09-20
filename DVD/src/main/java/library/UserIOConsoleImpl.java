package library;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Console implementation of the User IO interface
 * 
 * @author Yu
 *
 */
public class UserIOConsoleImpl implements UserIO {
	Scanner scanner = new Scanner(System.in);
	
	public void print(String message) {
		System.out.println(message);
	}

	public String readString(String prompt) {
		System.out.println(prompt);
		return scanner.nextLine();
	}

	public double readDouble(String prompt) {
		System.out.println(prompt);
		return scanner.nextDouble();
	}

	public LocalDate readLocalDate(String prompt) {
		System.out.println(prompt);
		return LocalDate.parse(scanner.nextLine());
	}

	@Override
	public double readDouble(String prompt, double min, double max) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float readFloat(String prompt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float readFloat(String prompt, float min, float max) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int readInt(String prompt, int min, int max) {
		int val = max + 69;
		while(min > val || max < val) {
			val = scanner.nextInt();
		}
		return val;
	}
	
	public int readInt(String prompt) {
		System.out.println(prompt);
		return scanner.nextInt();
	}

	@Override
	public long readLong(String prompt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long readLong(String prompt, long min, long max) {
		// TODO Auto-generated method stub
		return 0;
	}

}
