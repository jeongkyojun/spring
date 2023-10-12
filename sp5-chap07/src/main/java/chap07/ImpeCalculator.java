package chap07;

public class ImpeCalculator implements Calculator {

	@Override
	public long factorial(long num) {
		long start = System.currentTimeMillis();
		long result = 1;
		for(long i=2; i<=num;i++) {
			result *= i;
		}
		long end = System.currentTimeMillis();
		//System.out.printf("ImpeCalculator.factorial(%d) times = %d\n",num, (end-start));
		return result;
	}

}
