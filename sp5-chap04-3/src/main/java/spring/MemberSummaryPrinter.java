package spring;

public class MemberSummaryPrinter extends MemberPrinter{
	
	@Override
	public void print(Member member) {
		System.out.printf(
				"member info : email = %s, name = %s",
				member.getEmail(), member.getName());
	}
}
