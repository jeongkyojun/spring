package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoPrinter {
 
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			System.out.println("no data\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
}
