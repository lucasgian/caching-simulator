package cache;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.List;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.io.*;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
public class view {

	private JFrame frmIdiesoftware;
	private JTextField txtBomDia;
	private JTextField txtHit;
	private JTextField txtAccess;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view window = new view();
					window.frmIdiesoftware.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the application.
	 */
	public view() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIdiesoftware = new JFrame();
		frmIdiesoftware.setFont(null);
		frmIdiesoftware.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		frmIdiesoftware.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\es\\Desktop\\image\\IdieSofte.png"));
		frmIdiesoftware.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmIdiesoftware.getContentPane().setBackground(SystemColor.activeCaption);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(95, 158, 160));
		frmIdiesoftware.getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("SIMULAR CACHE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					
					new Cache();
					
					EndRead rd = new EndRead("value.data");
					String hit = rd.read();
					String miss = rd.read();
					String access = rd.read();
					txtHit.setText(((Integer.parseInt(hit)) / 100) + " %");
					txtBomDia.setText(miss);
					txtAccess.setText(access);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		txtBomDia = new JTextField();
		
	
		txtBomDia.setColumns(10);
		
		JLabel lblNumeroDeMiss = new JLabel("MISS");
		lblNumeroDeMiss.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblHit = new JLabel("Taxa de Hit");
		lblHit.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtHit = new JTextField();
		
		txtHit.setColumns(10);
		
		JLabel lblAccess = new JLabel("ACCESS");
		lblAccess.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtAccess = new JTextField();
		
		txtAccess.setColumns(10);
		
		JList list = new JList();
		list.setValueIsAdjusting(true);
		list.addMouseWheelListener(new MouseWheelListener() 
		{
			public void mouseWheelMoved(MouseWheelEvent arg0) 
			{
			}
		});
		list.setToolTipText("");
		list.setVisibleRowCount(1000);
		list.setModel(new AbstractListModel() 
		{
			String[] values = new String[] {};
			public int getSize() 
			{
				return values.length;
			}
			public Object getElementAt(int index) 
			{
				return values[index];
			}
		});
		
		JButton btnListarMissE = new JButton("LISTAR MISS E HIT");
		btnListarMissE.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				try {
					Cache ch = new Cache();
					int size = ch.size;
					
					EndRead rd = new EndRead("list.data");
					String[] line = new String[size];
					for(int i=0; i < size; i++)
						line[i] = rd.read() + "\r\n";
					list.setListData(line);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Gerar Novo Trace");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new WriteTrace(textField.getText(), textField_1.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		textField = new JTextField();
		textField.setText("4");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("1");
		textField_1.setColumns(10);
		
		JLabel lblAssociedade = new JLabel("Associedade");
		lblAssociedade.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblWordPorBloco = new JLabel("Word Por Bloco");
		lblWordPorBloco.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblObsO = new JLabel("OBS -> ");
		lblObsO.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblOperacoes = new JLabel("9000 operacoes N Trace");
		lblOperacoes.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblAccess, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton)
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNumeroDeMiss, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblHit))
								.addGap(44)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(txtHit, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtBomDia, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtAccess, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))))
						.addComponent(btnListarMissE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(168)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_1)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblWordPorBloco)
										.addComponent(lblAssociedade))
									.addGap(50)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textField_1, 0, 0, Short.MAX_VALUE)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(88)
							.addComponent(lblObsO, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblOperacoes, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(16)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumeroDeMiss, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtBomDia, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHit)
						.addComponent(txtHit, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAccess, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAccess, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addComponent(btnListarMissE)
							.addGap(27)
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAssociedade, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblWordPorBloco, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(61)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblObsO, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOperacoes, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))))
					.addGap(11))
		);
		panel.setLayout(gl_panel);
		frmIdiesoftware.setTitle("                              \t\t\t\t\t\tMemoria Cache by www.idiesoftware.com.br \r\n");
		frmIdiesoftware.setBackground(Color.LIGHT_GRAY);
		frmIdiesoftware.setForeground(Color.LIGHT_GRAY);
		frmIdiesoftware.setBounds(100, 100, 600, 493);
		frmIdiesoftware.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIdiesoftware.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmIdiesoftware.getContentPane(), list}));
	}
}
