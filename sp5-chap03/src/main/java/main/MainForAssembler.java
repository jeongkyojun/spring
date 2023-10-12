package main;

import java.io.*;

import assembler.Assembler;

import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

public class MainForAssembler {

	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.println("input command");
			String command = reader.readLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("exit program");
				break;
			}
			
			if(command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			}else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			printHelp();
		}
	}
	
	private static Assembler assembler = new Assembler();
	
	private static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		MemberRegisterService regSvc = assembler.getMemberRegisterService();
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("password not correct\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("registed\n");
		} catch(DuplicateMemberException e) {
			System.out.println("duplicated email.\n");
		}
	}
	
	private static void processChangeCommand(String[] arg) {
		if(arg.length !=4) {
			printHelp();
			return;
		}
		ChangePasswordService changePwdSvc = 
				assembler.getChangePasswordService();
		
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("change password success.\n");
		}catch(MemberNotFoundException e) {
			System.out.println("email not found\n");
		}catch(WrongIdPasswordException e) {
			System.out.println("email and password are not correct\n");
		}
	}
	private static void printHelp() {
		System.out.println();
		System.out.println("command error, look documentation below");
		System.out.println("command manual");
		System.out.println("new [email] [name] [password] [password again]");
		System.out.println("change [email] [now password] [change password]");
		System.out.println();
	}
}
