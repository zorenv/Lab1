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
public class Calculate {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in,"utf-8");
		String str_origin="";//����ԭʼ������
		String		com;
		String der;
		String test = "";    
		String[] str_split_plus;                       
		String[][] str_split_multi;
		Calculate A = new Calculate();
		int i = 0;
		/*���ܵ�endʱ����������*/
		System.out.println("����end��������");
		while (!test.equals("end"))
		{
			test = in.nextLine();
			if (test.equals(""))
				System.out.println("Error, no variable");
			else if (test.charAt(0) =='!') {
				if (test.subSequence(1, 4).equals("d/d")) {
					str_split_plus = str_origin.split("\\+");       //��+�ָ�
					str_split_multi = A.expression(str_split_plus); //��*�ָ�
					der = test;            
					A.derivative(der, str_split_plus, str_split_multi);//��
					continue;
				} else if (test.subSequence(1, 9).equals("simplify")) {
					com = test;
					A.simplify(com, str_origin);
					continue;
				}
				else{
					System.out.println("Error, no variable");
				}
				continue;
			}else if ( !test.equals("end")  || (test.charAt(0)>='1'&& test.charAt(0)<='9') ||  ( test.charAt(0)>='a'&& test.charAt(0)<='z') ||( test.charAt(0)>='A'&& test.charAt(0)<='Z') || test.charAt(0)==' ') {
				for( i=0;i<test.length();i++){
					if(!((test.charAt(i)>='0'&& test.charAt(i)<='9') ||  ( test.charAt(i)>='a'&& test.charAt(i)<='z') ||( test.charAt(i)>='A'&& test.charAt(i)<='Z') || test.charAt(i)==' '||test.charAt(i)=='+'||test.charAt(i)=='*')){
						System.out.println("Error, no variable");
						break;
					}
				}
				if(i == test.length()){
				str_origin = test;
				str_origin = str_origin.replaceAll(" ", "");   //ȥ���ո�
				str_origin = str_origin.replaceAll("	", "");//ȥ��Tab
				System.out.println(str_origin);                //��һ��Ҫ��������յı��ʽ
				continue;
				}
			} 
				

			else { 
				System.out.println("Error, no variable");
			}
				continue;

		}
		in.close();

	}
	
	/*******************��ֵ����*******************/
	public void simplify(String com, String str) {
		final int f=10;
		String[] cc, sumfen;
		int i, j, k, mul, sum = 0, l;
		String[] ee = new String[f];
		String[][] summ = new String[f][f];
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

	/*******************�󵼺���*******************/
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

	/*******************����+�ָ�����ַ�������*���зָ�*******************/
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
 * ����1 ��ȫ��������ֵ�����õ����ռ�����
 * 30*2+6*8*x*x+9*x*y 
 * !simplify x=2 y=6 
 * !d/dz
 * 
 * ���� 2 ���տո��cTab
 * 30 *2+ 6*8*x*x +9 *x*y 
 * !simplify x=2 
 * !d/dx  
 * 
 */
