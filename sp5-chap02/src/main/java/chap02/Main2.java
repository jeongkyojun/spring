package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppContext.class);
		Greeter g1 = ctx.getBean("greeter",Greeter.class);
		Greeter g2 = ctx.getBean("greeter",Greeter.class);
		System.out.println("(g1==g2)="+(g1==g2)); // true가 된다 = getBean 메서드는 같은 객체를 리턴한다.
		// 스프링은 별도의 설정을 하지 않을경우 싱글톤 범위를 갖는다.
		ctx.close();
	}

}
