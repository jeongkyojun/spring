package spring;

public class MemberPrinter {

	public void print(Member member) {
		System.out.printf(
				"member info: Id=%d, email=%s, name=%s, register date=%tF\n",
				member.getId(),member.getEmail(), member.getName(),member.getRegisterDateTime());
	}
	
}
