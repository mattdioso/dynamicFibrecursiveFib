import java.time.Instant;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.math.BigInteger;

public class fibonacciTesting {
	public BigInteger recursiveFib(int n) {
		if (n==0)
			return BigInteger.ZERO;
		if (n==1)
			return BigInteger.ONE;
		return recursiveFib(n-1).add(recursiveFib(n-2));// + recursiveFib(n-2);
	}

	public BigInteger memoizeFib(int n) {
		BigInteger [] memo = new BigInteger[n+1];
		return memoizeHelper(n, memo);

	}

	public BigInteger memoizeHelper(int n, BigInteger [] memo) {
		if (n==0)
			return BigInteger.ZERO;
		if (n==1)
			return BigInteger.ONE;
		if (memo[n]==BigInteger.ZERO) {
			memo[n]=memoizeHelper(n-1, memo).add(memoizeHelper(n-2, memo));// + memoizeHelper(n-2, memo);
		}
		return memo[n];
	}

	public BigInteger bottomUpFib(int n) {
		BigInteger [] fibs = new BigInteger[n+1];
		fibs[0] = BigInteger.ZERO;
		fibs[1] = BigInteger.ONE;
		for (int i=2; i<n; i++) {
			fibs[i] = fibs[i-1].add(fibs[i-2]);// +fibs[i-2];
		}
		return fibs[n-1].add(fibs[n-2]);// + fibs[n-2];
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Not enough arguments");
			return;
		}
		if (args.length > 2) {
			System.out.println("Too many arguments");
			return;
		}

		fibonacciTesting f = new fibonacciTesting();
		System.out.println(args[0] + " " + args[1]);
		if (args[0].equals("recursive")) {
			System.out.println("Performing Recursive Fibonacci...");
			Instant start = Instant.now();
			BigInteger fib = f.recursiveFib(Integer.parseInt(args[1]));
			System.out.println(args[1] + "th Fibonacci Number: " + fib);
			Instant end = Instant.now();
			Duration timeDiff = Duration.between(start,end);
			System.out.println("Time it took to find " + args[1] + "th Fibonacci number recursively: " + timeDiff.toMillis() + "ms");
			return;
		}

		if (args[0].equals("dynamic")) {
			System.out.println("Performaing Memoization to find " + args[1] + "th Fibonacci Number");
			Instant start = Instant.now();
			BigInteger fib = f.memoizeFib(Integer.parseInt(args[1]));
			System.out.println(args[1] + "th Fibonacci Number: " + fib);
			Instant end = Instant.now();
			Duration timeDiff = Duration.between(start, end);
			System.out.println("Time it took to find " + args[1] + "th Fibonacci number through memoization: " + timeDiff.toMillis() + "ms");
			return;
		}

		if (args[0].equals("memo")) {
			System.out.println("Performing Bottom Up to find " + args[1] + "th Fibonacci Number");
			Instant start = Instant.now();
			BigInteger fib = f.bottomUpFib(Integer.parseInt(args[1]));
			System.out.println(args[1] + "th Fibonacci Number: " + fib);
			Instant end = Instant.now();
			Duration timeDiff = Duration.between(start, end);
			System.out.println("Time it took to find " + args[1] + "th Fibonacci number through memoization: " + timeDiff.toMillis() + "ms");
			return;
		}
	}
}