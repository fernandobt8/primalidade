package MillerRabin;

import java.math.BigInteger;

/**
 * @author Felipe Nedel
 * 
 */
public class MathUtils {

	private static final BigInteger ZERO = BigInteger.ZERO;
	private static final BigInteger UM = BigInteger.ONE;
	private static final BigInteger DOIS = BigInteger.valueOf(2);
	private static final BigInteger DEZ = BigInteger.TEN;

	/**
	 * Produto modular
	 * 
	 * @param a
	 * @param b
	 * @param n
	 */
	private static BigInteger modProd(BigInteger a, BigInteger b, BigInteger n) {
		if (b == ZERO) {
			return ZERO;
		}
		if (b == UM) {
			return a.mod(n);
		}

		BigInteger sub = b.subtract(b.mod(DEZ)).divide(DEZ);
		BigInteger number = modProd(a, sub, n);
		BigInteger mult = number.multiply(DEZ);
		BigInteger module = b.mod(DEZ);
		return mult.add(module.multiply(a)).mod(n);
	}

	/**
	 * Exponenciação modular: a^b mod n
	 * 
	 * @param a
	 * @param b
	 * @param n
	 */
	private static BigInteger modPow(BigInteger a, BigInteger b, BigInteger n) {
		if (b == ZERO) {
			return UM;
		}
		if (b == UM) {
			return a.mod(n);
		}
		if (b.mod(DOIS) == ZERO) {
			BigInteger modpow = modPow(a, b.divide(DOIS), n);
			return modProd(modpow, modpow, n);
		}
		return modProd(a, modPow(a, b.subtract(UM), n), n);
	}

	/**
	 * Verifica se o número dado é primo
	 * 
	 * @param n o número a ser testado
	 * @return true se o número for primo. Senão, false.
	 */
	public static boolean isPrime(BigInteger n) {

		// Execução do algoritmo;
		BigInteger d = n.subtract(UM);
		BigInteger s = ZERO;

		// Fatoração de potências de 2 para n-1;
		while (d.mod(DOIS).compareTo(ZERO) == 0) {
			s = s.add(UM);
			d = d.divide(DOIS);
		}

		// Array de primos base;
		int[] primeValues = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41 };
		boolean continueFlag = false;
		BigInteger x;

		for (int index = 0; index < primeValues.length; index++) {
			BigInteger a = BigInteger.valueOf(primeValues[index]);
			x = modPow(a, d, n);

			// Se x = 1 ou x = n-1, chama o próximo valor do array base de primos;
			if (x.equals(UM) || x.equals(n.subtract(UM))) {
				continue;
			}
			for (int counter = 1; counter <= s.intValue(); counter++) {
				x = modProd(x, x, n);
				if (x.equals(UM)) {
					// O número não é primo;
					return false;
				}
				if (x.equals(n.subtract(UM))) {
					continueFlag = true;
					break;
				}
			}
			if (continueFlag) {
				continue;
			}
			// O número não é primo;
			return false;
		}

		// O número é primo;
		return true;
	}
}
