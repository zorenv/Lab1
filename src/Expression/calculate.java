package Expression;

import java.util.*;

/**
 * Copyright	2016    Only2333's Studio
 *
 * @author panwei1995&zorenv
 *
 * @github https://github.com/zorenv
 * 		   https://github.com/panwei1995
 *
 * @email  hit_lmf@163.com
 *         492358046@qq.com
 *
 * All right reserved.
 *
 */
public class calculate {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String str_origin = "", com, der, test = "";    //接收原始的输入
		String[] str_split_plus;                        //用+分割
		String[][] str_split_multi = new String[10][10];//用*分割
		calculate A = new calculate();
		int i=0;
		
		/*接受到end时，结束程序*/
		System.out.println("输入end结束程序");
		while (!test.equals("end")) {
			test = in.nextLine();
			if (test.equals(""))
				System.out.println("Error, no variable");
			else if (test.charAt(0) == '+' || test.charAt(0) == '-' || test.charAt(0) == '*'|| test.charAt(0) == '/') {
				System.out.println("Error, no variable");
				continue;
			}else if (test.charAt(0) != '!' && !test.equals("end")) {
				for( i=0;i<test.length();i++){
					if(test.charAt(i)=='-'||test.charAt(i)=='/'||test.charAt(i)=='^'){
						System.out.println("Error, no variable");
						break;
					}
				}
				if(i == test.length()){
				str_origin = test;
				str_origin = str_origin.replaceAll(" ", "");   //去除空格
				str_origin = str_origin.replaceAll("	", "");//去除Tab
				System.out.println(str_origin);                //第一个要求，输出接收的表达式
				continue;
				}
			} else if (test.charAt(0) == '!') {
				if (test.subSequence(1, 4).equals("d/d")) {
					str_split_plus = str_origin.split("\\+");       //用+分割
					str_split_multi = A.expression(str_split_plus); //用*分割
					der = test;            
					A.derivative(der, str_split_plus, str_split_multi);//求导
					continue;
				} else if (test.subSequence(1, 9).equals("simplify")) {
					com = test;
					A.simplify(com, str_origin);
					continue;
				} 

			} else
				continue;

		}
		in.close();

	}
	
	/*******************赋值函数*******************/
	public void simplify(String com, String str) {

		String[] cc, sumfen;
		int i, j, k, mul, sum = 0, l;
		String[] ee = new String[10];
		String[][] summ = new String[10][10];
		try {
			cc = com.split(" ");
			if (cc.length == 1)
				System.out.println(str);
			else {
				ee[0] = str;
				for (i = 1; i < cc.length; i++)
					ee[i] = ee[i - 1].replace(cc[i].charAt(0), cc[i].charAt(cc[i].length() - 1));
				sumfen = ee[i - 1].split("\\+");
				summ = expression(sumfen);
				for (k = 0; k < sumfen.length; k++) {
					mul = 1;
					l = 0;
					for (j = 0; j < summ[k].length; j++) {
						if (88 <= (int) summ[k][j].charAt(0) && (int) summ[k][j].charAt(0) <= 90
								|| 120 <= (int) summ[k][j].charAt(0) && (int) summ[k][j].charAt(0) <= 122) {
							System.out.println(ee[i - 1]);
							break;

						} else {
							l++;
						}

					}
					if (l == summ[k].length) {
						for (j = 0; j < summ[k].length; j++)
							mul = mul * Integer.valueOf(summ[k][j]);
					} else
						break;
					sum = sum + mul;
				}
				if (k == sumfen.length)
					System.out.println(sum);
			}
		} catch (Exception e) {
			System.out.println("Error, no variable");
		}

	}

	/*******************求导函数*******************/
	public void derivative(String der, String[] str_split_plus, String[][] str_split_multi) {
		char x;
		int i, j, k, num = 0;
		x = der.charAt(der.length() - 1);
		k = 0;
		for (i = 0; i < str_split_plus.length; i++) {
			for (j = 0; j < str_split_plus[i].length(); j++) {
				if (str_split_plus[i].charAt(j) == x) {
					num++;
				}
			}
			if (num == 0) {
				k++;
				if (k == str_split_plus.length)
					System.out.println("Error, no variable");
				continue;
			} else {
				if (num == 1) {
					System.out.print(num);
				} else {
					System.out.print(x + "*" + num);
				}
				for (j = 0; j < str_split_multi[i].length; j++) {
					if (str_split_multi[i][j].charAt(0) != x) {
						System.out.print("*" + str_split_multi[i][j].charAt(0));

					}
					// System.out.print(j+" ");
				}
			}
			if (i < str_split_plus.length - 1)
				System.out.print("+");
			num = 0;
		}
		System.out.println("\n");

	}

	/*******************将用+分割过的字符串再用*进行分割*******************/
	private String[][] expression(String[] str_split_plus) {
		String[][] str_split_multi = new String[10][10];
		int i, k;
		for (i = 0; i < str_split_plus.length; i++) {
			k = i;
			str_split_multi[k] = str_split_plus[i].split("\\*");
		}

		return str_split_multi;
	}


}
/* 
 * 测试1 给全部变量赋值，并得到最终计算结果
 * 30*2+6*8*x*x+9*x*y 
 * !simplify x=2 y=6 
 * !d/dz
 * 
 * 测试 2 接收空格與Tab
 * 30 *2+ 6*8*x*x +9 *x*y 
 * !simplify x=2 
 * !d/dx  
 * 
 */
