package main;

import java.io.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;

import spring.DuplicateMemberException;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.WrongIdPasswordException;

import spring.ChangePasswordService;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.VersionPrinter;

public class MainForSpring {

	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException{
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
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
			}else if(command.equals("list")) {
				processListCommand();
				continue;
			}else if(command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			}else if(command.equals("version")) {
				processVersionCommand();
				continue;
			}
			printHelp();
		}
	}
	
	private static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		MemberRegisterService regSvc = 
				ctx.getBean(MemberRegisterService.class);
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
				ctx.getBean(ChangePasswordService.class);
		
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
	
	private static void processListCommand() {
		MemberListPrinter listPrinter =
				ctx.getBean("listPrinter",MemberListPrinter.class);
		listPrinter.printAll();
	}
	
	private static void processInfoCommand(String[] arg) {
		if(arg.length!=2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter =
				ctx.getBean("infoPrinter",MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}
	
	private static void processVersionCommand() {
		VersionPrinter versionPrinter = 
				ctx.getBean("versionPrinter",VersionPrinter.class);
		versionPrinter.print();
	}
	
}
