package com.zeda.javatest;

public class C5_Bit {

	public static int putntom(int N, int M, byte i, byte j) {
		if (i>j) return 0;
		if (j>31) return 0;
		
		int temp = 0;
		for (int k=0; k<32; k++) {
			System.out.println("N " + (k>=i && k<j) + " res: " + ((M<<i) & (1 << k)) + "  " + (N & (1 << k)) );
			temp = temp + (k>=i && k<j ?  ((M<<i) & (1 << k)) : (N & (1 << k)));
		}
		
		return temp;
	}
	
	public static int putntom_test() {
		int M = 0x05;
		int N = 0x10;
		byte i = 1;
		byte j = 4;
		
		int t = putntom(N,M,i,j);
		System.out.println("N " + N + "  M " + (M << 1) + "   t " + t);
		
		int max = ~0;
		int right = ((1<<i) -1);
		int left = max - ((1<<j) -1);
		int mask = left | right;
		
		System.out.println("right " + ((N & mask) | (M << i)));
		return t;
	}
	
	
	public static int decimals(String decstr) {
		int intpart = Integer.parseInt(decstr.substring(0, decstr.indexOf('.')));
		double decpart = 
				Double.parseDouble(decstr.substring(decstr.indexOf('.'), decstr.length()));
		
		StringBuffer sb = new StringBuffer();
		
		while (intpart > 0) {
			int r = intpart % 2;
			intpart = (intpart >> 1);
			sb.append(r);
		}
		
		sb.append(".");
		
		String decs = "";
		double decd = 0;
		while (decpart - decd > 0) {
			if (decs.length() > 32) {
				System.out.println("ERROR");
				return -1;
			}
			
			String tmps = decs + "1";
			double tmpd = decd + Math.pow(2, -decs.length() - 1);
			System.out.println("DECD " + decd + "   TMPD " + tmpd + "   DECPART " + decpart);
			if (tmpd > decpart) {
				tmps = decs + "0";
				tmpd = decd;
			}
			
			decs = tmps;
			decd = tmpd;
		}
		
		sb.append(decs);
		System.out.println(sb.toString());
		return 0;
	}
	
	public static int bitdifference(int N, int M) {
		int  c = 0;
		for (int b = N ^ M; b > 0; b = b>>1) {
			c += (b & 1);
		}
		
		System.out.println(String.format("Bit difference between %#x and %#x is %d", N, M, c));
		
		return 0;
	}
	
	public static int minimalswap(int N) {
		int mN = (1 << 1) + (1 << 3) + (1 << 5) + (1 << 7) + (1 << 9) + (1 << 11) +  
				(1 << 13) + (1 << 15) + (1 << 17) + (1 << 19) + (1 << 21) + (1 << 23) +
				(1 << 25) + (1 << 27) + (1 << 29) + (1 << 31);
		int mM = 1 + (1 << 2) + (1 << 4) + (1 << 6) + (1 << 8) + (1 << 10) + (1 << 12) +
				(1 << 14) + (1 << 16) + (1 << 18) + (1 << 20) + (1 << 22) + (1 << 24) + 
				(1 << 26) + (1 << 28) + (1 << 30);
		System.out.println(String.format("Masks: %#x %#x", mN, mM));
		
		int r = ((N & mN) >> 1) | ((N & mM) << 1);

		System.out.println(String.format("Swapped: %d", r));
		return 0;
	}
	
	public static int test() {
		//return putntom_test();
		//return decimals("3.15");
		//return bitdifference(005, 003);
		return minimalswap(10);  // 1010
	}
}
