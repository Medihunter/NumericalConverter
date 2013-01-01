import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;


public class Decimal 
{
	public static String toBinary(String input)
	{
		boolean negative = false;
		String temp = "";
		int i = 0;
		BigInteger binum, bi0, bi2;
		BigDecimal bdnum, bd1, bd2;
		
		if(input == "-0")
			return "0";
		
		// determines whether the number is negative or positive
		if(input.charAt(0) == '-')
			negative = true;
		
		
		// BigDecimal and BigInteger used to convert any size Decimal input into binary
		bdnum = new BigDecimal(input).abs();
		binum = new BigInteger(input).abs();
		bi0 = new BigInteger("0");
		bi2 = new BigInteger("2");
		bd1 = new BigDecimal("1");
		bd2 = new BigDecimal("2");
		
		if(bdnum.equals(bi0))
			negative = false;
		
		// if negative inverts the binary to prepare for two's compliment form
		if(negative)
		{	
			input = "1";
			while(bdnum.compareTo(bd1) >= 0)
			{	
				binum = bdnum.toBigInteger();
				if((binum.mod(bi2).equals(bi0)))
					temp += "1";
				else
					temp += "0";
			
				bdnum = bdnum.divide(bd2, RoundingMode.FLOOR);
			}
		}
		
		// if positive, obtains the normal binary starting with a 0 to show that it is positive
		else
		{
			input = "0";
			while(bdnum.compareTo(bd1) >= 0)
			{	
				binum = bdnum.toBigInteger();
				if((binum.mod(bi2).equals(bi0)))
					temp += "0";
				else
					temp += "1";
			
				bdnum = bdnum.divide(bd2, RoundingMode.FLOOR);
			}
		}

		// if negative adds 1 to the inverted binary number to complete two's compliment form
		if(negative)
		{
			boolean addOne = true;
			int end = 0;
			while(addOne)
			{
				if(temp.charAt(end) == '1')
				{	
					char[] aux = temp.toCharArray();
					aux[end] = '0';
					temp = String.valueOf(aux);
				}
				else if(temp.charAt(end) == '0')
				{
					char[] aux = temp.toCharArray();
					aux[end] = '1';
					temp = String.valueOf(aux);
					addOne = false;
				}
				end++;
			}
		}
		
		// temp is reversed because of the algorithms output order
		for(i = temp.length()-1; i >= 0; i--)
			input += temp.charAt(i);  // output is temp reversed

		return input;
	}


	// converts decimal input to hexadecimal
	public static String toHex(String input)
	{
		boolean negative = false;
		input = toBinary(input);
		
		// if the binary starts with 1, it is a two's complement value and therefore should be buffered by 1's not 0's
		if(input.charAt(0) == '1')
			negative = true;
	
		return binaryToHex(input, negative);
	}
	
	// helper function that assumes the input is perfectly formatted and simply converts
	private static String binaryToHex(String input, boolean negative)
	{
		String temp = "";
		
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


	public static String vertifyDecimal(String input) 
	{
		boolean negative = false;
		String vert = "";
		
		// if first char of input is '-' means the number is negative
		if(input.charAt(0) == '-')
		{
			vert = "-";
			negative = true;
		}
		
		// if the user forgets to enter "0" before the "." it is automatically inserted
		if(!Character.isDigit(input.charAt(0)) && !negative)
			vert = "0.";
		else
			vert = String.valueOf(input.charAt(0));
		// if the user tried to type "-."
			
		// copies values only if they are digits, if they somehow input another "-" or "." it gets skipped
		for(int i = 1; i < input.length(); i++)
				if(Character.isDigit(input.charAt(i)))
					vert += input.charAt(i);
		return vert;
	}
	
	// checks if a character qualifies to be part of a decimal number
	public static boolean isDecimal(char c) 
	{
		if((c >= '0') && (c <= '9') && (c != KeyEvent.VK_BACK_SPACE))
			return true;
		else if( c == '-')
			return true;
		else
			return false;		
	}
	
	public static boolean isNumeric(String str)
	{
		if(str.length() > 5)
			return true;
		else
		{
			try
			{
				Integer.parseInt(str);   
				return true;
			}
			catch (Exception e) {}
			return false;
		}
	}
	
	// checks if the string is empty
	public static boolean hasSign(String input) 
	{
		if(input.isEmpty())
			return false;
		else if(input.contains("-"))
			return true;
		 
		return true;
	}


	public static boolean notZero(String input)
	{
		String temp = "";
		BigInteger zero = new BigInteger("0");
		BigInteger num;
		
		if(input.charAt(0) == '-')
			for(int i = 1; i < input.length(); i++)
				temp += Character.toString(input.charAt(i));
		else
			for(int i = 0; i < input.length(); i++)
				temp += Character.toString(input.charAt(i));
			
		num = new BigInteger(temp);
		if(num.compareTo(zero) == 0)
			return false;
		else return true;
		
	}
}
