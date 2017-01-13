package lprime;
import java.util.*;
import java.lang.*;

public class funcs {
			
	static List<Integer>[] primes = (ArrayList<Integer>[])new ArrayList[9];
	static int cnt = 5, cnt0 = 10;

	funcs(int n){ cnt0 = n;}
	
	static boolean isprime(int n){
		if ( n <  2 )	return false;
		if ( n == 2	)	return true;
		if ( n == 3 )	return true;
		
		if ( n % 3 == 0) return false;
		
		int i = 5;
		int w = 2;
		while( i * i < n ){
			if (n % i == 0)	return false;
			i += w;
			w = 6 - w;
		}
	    return true;
	}
	
	static int power(int m, int n){
		int k = 1;
		for(int i = 0 ; i < n ; i++) k *= m;
		return k;
	}

	static Integer f1(){
		Integer[] prevPrimes = new Integer[]{3,7};
		for (int j = 1 ; j < 9 ; j++){ // j = number of digits 1..9
			int powOfTen = power(10, j);
			for(int i = 1 ; i <= 9 ; i++){	// i = added front digit {1..9}
				primes[i-1] = new ArrayList<Integer>();
				for(int k = 0 ; k < prevPrimes.length ; k++){
					int candidate = prevPrimes[k] +  i * powOfTen; // add i00..0 
					if( isprime(candidate)) primes[i-1].add(candidate);
				}				
				if( (cnt += primes[i-1].size()) >= cnt0){
					// find it
					Integer[] lprimes = new Integer[]{0};
					lprimes = primes[i-1].toArray(lprimes);
					int idx = primes[i-1].size() + cnt0 - cnt;					
					return lprimes[idx].intValue();
				}
			}
			// concatenate all primes[j][0..8] and delete primes[j][0..8]
			for(int i = 1 ; i < 9 ; i++ ) primes[0].addAll(primes[i]);
			prevPrimes = primes[0].toArray(prevPrimes);
			for(int i = 0 ; i < 9 ; i++ ) if(primes[i] != null) primes[i] = null;
		}
		return -1;
	}
}
