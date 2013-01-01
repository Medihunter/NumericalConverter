import java.awt.event.KeyEvent;
import java.math.BigInteger;


public class Binary
{
	
	public static String toDecimal(String input)
	{
		boolean negative = false;
		BigInteger total = new BigInteger("0");
		BigInteger bi2 = new BigInteger("2");
		String output = "";
		int j = 0;
		
		// if the binary starts with 1, it is a two's complement value and therefore should be buffered by 1's not 0's
		if(input.charAt(0) == '1')
			negative = true;
		
		// converts input from two's compliment to unsigned if input is negative
		if(negative)
		{
			boolean addOne = true;
			char[] aux = new char[input.length()];
			int i;
			
			for(i = 0; i < input.length(); i++)
			{
				if(input.charAt(i) == '0')
					aux[i] = '1';
				else
					aux[i] = '0';
			}
			
			i = input.length()-1;
			while(addOne)
			{
				if(aux[i] == '0')
				{
					aux[i] = '1';
					addOne = false;
				}
				else
					aux[i] = '0';
				
				i -= 1;
			}
			
			input = "";
			for(i = 0; i < aux.length; i++)
				input += Character.toString(aux[i]);
		}
		
		// from binary to decimal
		for(int i = input.length()-1; i >= 0; i--)
		{
			if(input.charAt(i) == '1')
				total = total.add(bi2.pow(j));
			j++;
		}
		
		output = total.toString();
		if(negative)
			return "-"+output;
		else
			return output;
	}
	
	public static String toHex(String input)
	{
		boolean negative = false;
		String temp = "";
		
		// if the binary starts with 1, it is a two's complement value and therefore should be buffered by 1's not 0's
		if(input.charAt(0) == '1')
			negative = true;
	
		
		// adds extra 0's to buffer the binary value to fit perfectly into hexadecimal
		if(negative)
		{
			if(4-(input.length()%4) != 4)
			{
				for(int i = 0; i < 4-(input.length()%4); i++)
					temp += "1";		
				temp += input;
		}
			else
				temp = input;
			input = "";
		}
		else
		{
			if(4-(input.length()%4) != 4)
			{
				for(int i = 0; i < 4-(input.length()%4); i++)
					temp += "0";		
				temp += input;
		}
			else
				temp = input;
			input = "";
		}
		
		// converts the buffered binary into parts of 4 bits, each representing one hex value
		for(int i = 0; i < temp.length()/4; i++)
		{
			int a = 0, b = 0, c = 0, d = 0;
					
			if(Character.getNumericValue(temp.charAt(i*4)) == 1)
				a = 8;
			if(Character.getNumericValue(temp.charAt((i*4)+1)) == 1)
				b = 4;
			if(Character.getNumericValue(temp.charAt((i*4)+2)) == 1)
				c = 2;
			if(Character.getNumericValue(temp.charAt((i*4)+3)) == 1)
				d = 1;
					
			a = a + b + c + d;
					
			if(a > 9)
			{
				switch(a)
				{
					case 10:
					{
						input += "a";
						break;
					}
					case 11:
					{
						input += "b";
						break;
					}
					case 12:
					{
						input += "c";
						break;
					}
					case 13:
					{
						input += "d";
						break;
					}
					case 14:
					{
						input += "e";
						break;
					}
					case 15:
					{
						input += "f";
						break;
					}	
				}
			}
			else
				input += String.valueOf(a);
		}
		return input;
	}
	
	protected static String vertifyBinary(String input) 
	{
		String vert = "";
		
		if(!Character.isDigit(input.charAt(0)))
			vert += "0";
		
		for(int i = 0; i < input.length(); i++)
				if(Character.isDigit(input.charAt(i)))
					vert += input.charAt(i);
			
		return vert;
	}
	
	protected static boolean isBinary(char c) 
	{
		if((c == '0') || (c == '1') && (c != KeyEvent.VK_BACK_SPACE))
			return true;
		else
			return false;	
	}
}
