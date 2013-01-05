import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Window
{
	
	private JFrame frmNumericalConverter;
	private JTextField textField_Decimal;
	private JTextField textField_Binary;
	private JTextField textField_Hex;
	/**
	 * @wbp.nonvisual location=191,21
	 */

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try 
				{
					Window window = new Window();
					window.frmNumericalConverter.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public Window()
	{
		initialize();
	}
	
	
	/**
	 * 
	 */
	private void initialize()
	{
		frmNumericalConverter = new JFrame();
		frmNumericalConverter.setTitle("Numerical Converter");
		frmNumericalConverter.setBounds(100, 100, 450, 170);
		frmNumericalConverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNumericalConverter.getContentPane().setLayout(null);
		
		textField_Decimal = new JTextField();
		
		// allows only for inputs ranging from 0 to 9 and the sign "-"
		// waits for the input of correct keys and then processes input
		textField_Decimal.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				char c = e.getKeyChar(); 
				String extra = "";
				
				if (!Decimal.isDecimal(c) && c != KeyEvent.VK_BACK_SPACE)
					e.consume();  // ignore event if inputs are not 0-9 or '-' 
			    
				if(!textField_Decimal.getText().isEmpty() && c == '-')
			    	e.consume(); // ignore event if '-' is present in the string
			    
				if (!textField_Decimal.getText().isEmpty() && textField_Decimal.getText().charAt(0) != '-' && c == '-')
			    	textField_Decimal.setText("-"+textField_Decimal.getText()); // if string is positive, inserts a "-" into the beginning
			    
				if(Character.isDigit(c))
			    	extra = Character.toString(c);	
			    
				if(Decimal.isNumeric(textField_Decimal.getText()+extra) && Decimal.notZero(textField_Decimal.getText()+extra))
			    {
			    	String input = Decimal.vertifyDecimal(textField_Decimal.getText()+extra);
			    	textField_Binary.setText(Decimal.toBinary(input));
			    	textField_Hex.setText(Decimal.toHex(input));
			    }
			    else if(!textField_Decimal.getText().isEmpty() || Decimal.isNumeric(textField_Decimal.getText()+extra))
			    {
			    	textField_Hex.setText("0");
			    	textField_Binary.setText("0");
			    } 
			    else
			    {
			    	textField_Hex.setText("");
			    	textField_Binary.setText("");
			    }
			}
		});
		
		
		textField_Decimal.setBounds(58, 21, 386, 28);
		frmNumericalConverter.getContentPane().add(textField_Decimal);
		textField_Decimal.setColumns(10);
		
		JLabel lblBinary = new JLabel("Binary");
		lblBinary.setBounds(6, 67, 43, 16);
		frmNumericalConverter.getContentPane().add(lblBinary);
		
		textField_Binary = new JTextField();
		
		// allows only for inputs 0 or 1
		textField_Binary.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				char c = e.getKeyChar();
				String extra = "";
				
				if (!Binary.isBinary(c))
					e.consume();  // ignore event
			    if(Character.isDigit(c))
			    	extra = Character.toString(c);
		    	
				if(!textField_Binary.getText().isEmpty() || Binary.isBinary(c) || c == KeyEvent.VK_BACK_SPACE)
				{
					String input = Binary.vertifyBinary(textField_Binary.getText()+extra);
					textField_Decimal.setText(Binary.toDecimal(input));
					textField_Hex.setText(Binary.toHex(input));
				}
				else if(!textField_Binary.getText().isEmpty())
				{
					textField_Hex.setText("0");
					textField_Decimal.setText("0");
				}     
				else
				{
					textField_Hex.setText("");
					textField_Decimal.setText("");
				}
			}
		});
	
		textField_Binary.setBounds(58, 61, 386, 28);
		frmNumericalConverter.getContentPane().add(textField_Binary);
		textField_Binary.setColumns(10);
		
		JLabel lblHex = new JLabel("Hex");
		lblHex.setBounds(6, 104, 43, 16);
		frmNumericalConverter.getContentPane().add(lblHex);
		
		textField_Hex = new JTextField();
		
		// allows only for inputs ranging from 0 to 9, a to f, and A to F to be entered into JTextField
		textField_Hex.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent e)
			{
				char c = e.getKeyChar(); 
				String extra = "";
				
				if ((!Hex.isHex(c)) && (c != KeyEvent.VK_BACK_SPACE))
					e.consume();  // ignore event
				if (Hex.isHex(c))
					extra = Character.toString(c);	
				
				if(!textField_Hex.getText().isEmpty() || (Hex.isHex(c) || c == KeyEvent.VK_BACK_SPACE))
				{
					if(textField_Hex.getText().isEmpty())
					{
						textField_Binary.setText(Hex.toBinary(extra));
						textField_Decimal.setText(Hex.toDecimal(extra));
					}
					else
					{
						String refresh = Hex.vertifyHex(textField_Hex.getText());
						String input = refresh+extra;
			    		textField_Hex.setText(refresh);
			    		textField_Binary.setText(Hex.toBinary(input));
						textField_Decimal.setText(Hex.toDecimal(input));
					}
				}
				else if (!textField_Hex.getText().isEmpty())
				{
					textField_Binary.setText("0");
					textField_Decimal.setText("0");
				}
				else
				{
					textField_Binary.setText("");
					textField_Decimal.setText("");
				}     
			}
		});
	
		textField_Hex.setBounds(58, 101, 386, 28);
		frmNumericalConverter.getContentPane().add(textField_Hex);
		textField_Hex.setColumns(10);
		
		JLabel lblDecimal = new JLabel("Decimal");
		lblDecimal.setBounds(6, 27, 51, 16);
		frmNumericalConverter.getContentPane().add(lblDecimal);
	}
}
