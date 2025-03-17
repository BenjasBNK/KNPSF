package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class TelaError extends JFrame {

	private JPanel contentPane;
	JButton btnNewButton;
	private JPanel panel_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaError frame = new TelaError();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TelaError() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 381, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(154, 205, 217));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[100px,grow][50px,grow][100px,grow]", "[grow][grow]"));
		
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1, "flowy,cell 0 0 3 1,growx");
		panel_1.setLayout(new MigLayout("", "[grow][grow][grow]", "[]"));
		
		lblNewLabel = new JLabel("Informações Inválidas");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel, "cell 1 0,growx,aligny center");
		
		btnNewButton = new JButton("Ok");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(100, 100, 50, 20);
		btnNewButton.setBackground(new Color(8, 127, 140));
		btnNewButton.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		panel.add(btnNewButton, "cell 1 1");
	}
	public void setLabelText(String text) {
		lblNewLabel.setText(text);
    }

}
