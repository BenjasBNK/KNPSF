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

public class PopupVoltarLogin extends JFrame {

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
					PopupVoltarLogin frame = new PopupVoltarLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PopupVoltarLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 332, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(154, 205, 217));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[100px][50px,grow][100px]", "[grow][]"));
		
		btnNewButton = new JButton("Voltar ao Login");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBounds(100, 100, 50, 20);
		btnNewButton.setBackground(new Color(8, 127, 140));
		btnNewButton.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaLogin login = new TelaLogin();
				login.setLocationRelativeTo(null);
				login.setVisible(true);
				dispose();
				
			}
		});
		
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1, "cell 0 0 3 1,grow");
		
		lblNewLabel = new JLabel("Inserir Texto");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel);
		panel.add(btnNewButton, "cell 1 1");
	}
	public void setLabelText(String text) {
		lblNewLabel.setText(text);
    }
}
