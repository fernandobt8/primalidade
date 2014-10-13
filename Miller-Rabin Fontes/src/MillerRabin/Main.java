package MillerRabin;

import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {

		// Contorle da quantidade de argumentos;
		if (args.length > 1) {
			System.out.println("Por favor utilize apenas um argumento.");
			System.exit(0);
		}
		if (args.length < 1) {
			System.out.println("Por favor insira um argumento.");
			System.exit(0);
		}

		// Tamanho do primo a ser gerado;
		int size = 0;
		try {
			size = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.out.println("Argumento invalido: " + args[0]);
			System.exit(0);
		}

		if ( size > 80) {
			System.out.println("Numeros primos muito grandes podem demorar a ser gerados.");
		}

		String number = "";
		BigInteger probablyPrime = BigInteger.ZERO;
		boolean isProbablyPrime = false;

		// Enquanto o número não for provavelmente primo;
		while (!isProbablyPrime) {
			number = "";
			int random = (int) (Math.random() * 9) + 1;
			number += random;
			for (int counter = 1; counter < size; counter++) {
				random = (int) (Math.random() * 9);
				number += random;
			}

			probablyPrime = new BigInteger(number);
			isProbablyPrime = MathUtils.isPrime(probablyPrime);
		}

		System.out.println("\nO numero " + probablyPrime + " eh um provavel primo (" + size + " digitos).\n");

	}
}
