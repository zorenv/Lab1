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
	public final static int NUMBER=10;//申请空间大小
	public static void main(String[]  args) {
		Scanner in = new Scanner(System.in,"utf-8");
		String str_origin = "";
		String com;
		String der;
		String test = "";    //接收原始的输入
		String[] str_split_plus;                        //用+分割
		String[][] str_split_multi = new String[NUMBER][NUMBER]; //用*分割
		calculate myCalculate = new calculate();
		int i = 0;
		
		/*接受到end时，结束程序*/
		System.out.println("输入end结束程序");
		while (!"end".equals(test)) {
			test = in.nextLine();
			if (test.equals("")) {
				System.out.println("Error, no variable");
			} else if("end".equals(test)){
				break;
			}else if (test.charAt(0) == '!') {
				if (test.subSequence(1, 4).equals("d/d")) {
					str_split_plus = str_origin.split("\\+");       //用+分割
					str_split_multi = myCalculate.expression(str_split_plus); //用*分割
					der = test;            
					myCalculate.derivative(der, str_split_plus, str_split_multi); //求导
					continue;
				} else if (test.subSequence(1, 9).equals("simplify")) {
					com = test;
					myCalculate.simplify(com, str_origin);
					continue;
				} else {
					System.out.println("Error, no variable");
				}
					

			} else if (((test.charAt(0) <= '9' && test.charAt(0) >= '1') 
					|| (test.charAt(0) <= 'z' && test.charAt(0) >= 'a') 
					|| (test.charAt(0) <= 'Z' && test.charAt(0) >= 'A') 
					|| test.charAt(0) == ' ' )) {
				for( i=0;i<test.length();i++){
					if(!((test.charAt(i) <= '9' && test.charAt(i) >= '0') 
							|| (test.charAt(i) <= 'z' && test.charAt(i) >= 'a') 
							|| (test.charAt(i) <= 'Z' && test.charAt(i) >= 'A') 
							|| test.charAt(i) == ' ' 
							|| test.charAt(i) == '*'
							|| test.charAt(i) == '+')) {
						System.out.println("Error, no variable");
						break;
					}
				}
				if (i == test.length()) {
				str_origin = test;
				str_origin = str_origin.replaceAll(" ", "");   //去除空格
				str_origin = str_origin.replaceAll("	", ""); //去除Tab
				System.out.println(str_origin);                //第一个要求，输出接收的表达式
				continue;
				}
			} else {
				System.out.println("Error, no variable");
				continue;
			}
				
				

		}
		in.close();

	}
	
	/*******************赋值函数.*******************/
	public void simplify(String  com, String str) {/*参数：com 为赋值命令，str为原表达式*/

		String[] command_split, sumfen;
		int i = 0;
		int j = 0;
		int k = 0;
		int mul = 1;
		int sum = 0;
		int l = 0;
		String[] ee = new String[NUMBER];
		String[][] summ = new String[NUMBER][NUMBER];
		try {
			command_split = com.split(" ");
			if (command_split.length == 1) {
				System.out.println(str);
			} else {
				ee[0] = str;
				for (i = 1; i < command_split.length; i++) {
					ee[i] = ee[i - 1].replace(command_split[i].charAt(0), 
							command_split[i].charAt(command_split[i].length() - 1));
				}
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
						for (j = 0; j < summ[k].length; j++){
							mul = mul * Integer.valueOf(summ[k][j]).intValue();
						}
					} else {
						break;
					}
					sum = sum + mul;
				}
				if (k == sumfen.length){
					System.out.println(sum);
				}
			}
		} catch (Exception e) {
			System.out.println("Error, no variable");
		}

	}

	/*******************求导函数*******************/
	public void derivative(final String der, final String[] str_split_plus, final String[][]  str_split_multi) {
		char x;
		int plusIndex = 0;
		int charIndex = 0;
		int len = 0;
		int num = 0;
		x = der.charAt(der.length() - 1);
		for (plusIndex = 0; plusIndex < str_split_plus.length; plusIndex++) {
			for (charIndex = 0; charIndex < str_split_plus[plusIndex].length(); charIndex++) {
				if (str_split_plus[plusIndex].charAt(charIndex) == x) {
					num++;
				}
			}
			if (num == 0) {
				len++;
				if (len == str_split_plus.length) {
					System.out.println("Error, no variable");
				}
				continue;
			} else {
				if (num == 1) {
					System.out.print(num);
				} else {
					System.out.print(x + "*" + num);
				}
				for (charIndex = 0; charIndex < str_split_multi[plusIndex].length; charIndex++) {
					if (str_split_multi[plusIndex][charIndex].charAt(0) != x) {
						System.out.print("*" + str_split_multi[plusIndex][charIndex].charAt(0));

					}
					// System.out.print(j+" ");
				}
			}
			if (plusIndex < str_split_plus.length - 1) {
				System.out.print("+");
			}
			num = 0;
		}
		System.out.println("\n");

	}

	/*******************将用+分割过的字符串再用*进行分割*******************/
	private String[][] expression(String[] str_split_plus) {
		String[][] str_split_multi = new String[NUMBER][NUMBER];
		int i = 0;
		int k = 0;
		for (i = 0; i < str_split_plus.length; i++) {
			k = i;
			str_split_multi[k] = str_split_plus[i].split("\\*");
		}

		return str_split_multi;
	}


	public String expression(String test) {
		String result = new String();
		int i = 0;
		if (test.equals("")) {
			result = "Error, no variable";
			return "Error, no variable";
		}
		else if (test.charAt(0) == '!') {
			result = "Error, no variable";
			return "Error, wrong input!";
		} else if (!test.equals("end") || (test.charAt(0) >= '1' && test.charAt(0) <= '9') || (test.charAt(0) >= 'a' && test.charAt(0) <= 'z') || (test.charAt(0) >= 'A' && test.charAt(0) <= 'Z')|| test.charAt(0) == ' ') {
			for (i = 0; i < test.length(); i++) {
				if (!((test.charAt(i) >= '0' && test.charAt(i) <= '9')|| (test.charAt(i) >= 'a' && test.charAt(i) <= 'z')|| (test.charAt(i) >= 'A' && test.charAt(i) <= 'Z') || test.charAt(i) == ' '|| test.charAt(i) == '+' || test.charAt(i) == '*')) {
					result = "Error, no variable";
					return "Error, wrong input!";
				}
			}
			if (i == test.length()) {
				test = test.replaceAll(" ", ""); // 去除空格
				test = test.replaceAll("	", "");// 去除Tab
				result = test;
				return test;
			}
		}
		else {
			result = "Error, no variable";
			return "Error, wrong input!";
		}
		result = test;
		return test;
	}




}
//these are examples
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