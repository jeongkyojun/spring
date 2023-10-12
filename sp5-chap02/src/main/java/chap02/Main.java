package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	public static void main(String[] args){
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppContext.class); // 객체를 구현 및 초기화시킴
		Greeter g = ctx.getBean("greeter",Greeter.class);
		String msg = g.greet("spring");
		System.out.println(msg);
		ctx.close();		
	}
}
