import java.math.BigInteger;


public final class Hex
{
    private Hex() {}
	
	public static String toDecimal(String input)
	{
		boolean negative = false;
		BigInteger total = new BigInteger("0");
		BigInteger bi2 = new BigInteger("2");
		String output = "";
		int j = 0;
		
		// converts from Hex to Binary
		input = Hex.toBinary(input);
		
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

	public static String toBinary(String input)
	{
		int aux;
		String output = "";
		// an array of 4bit values in decimal form
		for(int i = 0; i < input.length(); i++)
		{
			aux = Integer.parseInt(Character.toString(input.charAt(i)), 16);
			
			if(i > 0)
			{
				if(aux <= 1)
					output += "000";
				else if(aux < 4)
					output += "00";
				else if(aux < 8)
					output += "0";
			}
			else if(aux < 8 && aux != 0)
				output += "0";	
			
			output += Integer.toBinaryString(aux);
		}
		return output;
	}
	
	
	protected static String vertifyHex(String input) 
	{
		String vert = "";
		
		if(!Character.isLetterOrDigit(input.charAt(0)))
			vert += "0";
		
		for(int i = 0; i < input.length(); i++)
				if(Character.isLetterOrDigit(input.charAt(i)))
					vert += Character.toLowerCase(input.charAt(i));
	
		return vert;
	}
	
	protected static boolean isHex(char c)
	{
		if((c <= '9') && (c >= '0'))
			return true;
		else if((c <= 'f') && (c >= 'a'))
			return true;
		else if((c <= 'F') && (c >= 'A'))
			return true;
		else
			return false;
	}
}
