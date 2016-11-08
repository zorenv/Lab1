package Expression;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class calculateTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("��ʼ����");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("���Խ���");
	}

	@Test
	public void testExpression() {
		calculate mycalculate = new calculate();
		String actualResult;
		String expectedResult;
		
		System.out.println("���Ե�һ������");
		actualResult = mycalculate.expression("30*2+6*8*x*x+9*x*y");  
		expectedResult = "30*2+6*8*x*x+9*x*y";
		assertEquals(expectedResult, actualResult);
		
		System.out.println("���Եڶ�������");
		actualResult = mycalculate.expression("30 * 2 + 6 * 8 * x * x + 9 * x * y");  
		expectedResult = "30*2+6*8*x*x+9*x*y";
		assertEquals(expectedResult, actualResult);
		
		System.out.println("���Ե���������");
		actualResult = mycalculate.expression("30.11*2.22+6*8*x*x+9*x*y");  
		expectedResult = "Error, wrong input!";
		assertEquals(expectedResult, actualResult);
		
		System.out.println("���Ե��ĸ�����");
		actualResult = mycalculate.expression("30*2+6*8*x*x+9*x*y@#$%^");  
		expectedResult = "Error, wrong input!";
		assertEquals(expectedResult, actualResult);
		
	}

}
