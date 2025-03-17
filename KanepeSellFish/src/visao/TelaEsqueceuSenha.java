package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controle.UsuarioDAO;
import modelo.RoundButton;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class TelaEsqueceuSenha extends JFrame {

	@SuppressWarnings("unused")
	private JPanel contentPane;
	private JTextField txtEmail;
	private static UsuarioDAO uDAO = UsuarioDAO.getInstancia();
	private JPasswordField txtSenhaRepet;
	private JPasswordField txtNovaSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEsqueceuSenha frame = new TelaEsqueceuSenha();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaEsqueceuSenha() {
		setResizable(false);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(1176, 664));
		setMaximumSize(new Dimension(1920, 1080));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 768);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setLayout(new BorderLayout(0, 0));

		PicPanel panel = new PicPanel("src//IMG/TelaCadastro.png");
		panel.setOpaque(false);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panel.add(panelPrincipal);
		panelPrincipal.setLayout(new MigLayout("", "[grow]", "[100px][90px][50px][65px][65px][65px][20px][grow]"));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panelPrincipal.add(panel_2, "cell 0 0,grow");

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panelPrincipal.add(panel_4, "cell 0 1,grow");
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblTitulo = new JLabel("Esqueceu sua senha?");
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 27));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblTitulo);

		JPanel panelTexto = new JPanel();
		panelTexto.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelTexto.setOpaque(false);
		panelPrincipal.add(panelTexto, "cell 0 2,grow");
		panelTexto.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblTexto = new JLabel("Vamos criar uma nova!");
		lblTexto.setForeground(Color.BLACK); // Define a cor do texto principal
		lblTexto.setFont(new Font("Dialog", Font.ITALIC, 14)); // Define a fonte
		panelTexto.add(lblTexto, "cell 0 0,alignx center");

		JPanel panelEmail = new JPanel();
		panelEmail.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelEmail.setOpaque(false);
		panelPrincipal.add(panelEmail, "cell 0 3,grow");
		panelEmail.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelEmail.add(lblEmail, "cell 0 0");

		txtEmail = new JTextField();
		txtEmail.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtEmail.setOpaque(false);
		txtEmail.setColumns(10);
		panelEmail.add(txtEmail, "cell 0 1,grow");

		JPanel panelNovaSenha = new JPanel();
		panelNovaSenha.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelNovaSenha.setOpaque(false);
		panelPrincipal.add(panelNovaSenha, "cell 0 4,grow");
		panelNovaSenha.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblNovaSenha = new JLabel("Nova Senha");
		lblNovaSenha.setForeground(new Color(0, 0, 0));
		lblNovaSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelNovaSenha.add(lblNovaSenha, "cell 0 0");

		txtNovaSenha = new JPasswordField();
		txtNovaSenha.setColumns(10);
		txtNovaSenha.setBorder(new LineBorder(Color.BLACK, 2));
		txtNovaSenha.setOpaque(false);
		panelNovaSenha.add(txtNovaSenha, "cell 0 1,grow");

		JPanel panelSenha = new JPanel();
		panelSenha.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelSenha.setOpaque(false);
		panelPrincipal.add(panelSenha, "cell 0 5,grow");
		panelSenha.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblSenha = new JLabel("<html>Repita a senha<span style='color: red;'>*</span></html>");
		lblSenha.setForeground(new Color(0, 0, 0));
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelSenha.add(lblSenha, "cell 0 0");

		txtSenhaRepet = new JPasswordField();
		txtSenhaRepet.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtSenhaRepet.setOpaque(false);
		panelSenha.add(txtSenhaRepet, "cell 0 1,grow");

		JPanel panelConfirmacao = new JPanel();
		panelConfirmacao.setOpaque(false);
		panelConfirmacao.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelPrincipal.add(panelConfirmacao, "cell 0 7,grow");
		panelConfirmacao.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel PainelBTN = new JPanel();
		PainelBTN.setOpaque(false);
		panelConfirmacao.add(PainelBTN);
		SpringLayout sl_PainelBTN = new SpringLayout();
		PainelBTN.setLayout(sl_PainelBTN);

		JButton btnCadastrar = new RoundButton("Cadastrar");
		sl_PainelBTN.putConstraint(SpringLayout.NORTH, btnCadastrar, 0, SpringLayout.NORTH, PainelBTN);
		sl_PainelBTN.putConstraint(SpringLayout.WEST, btnCadastrar, 70, SpringLayout.WEST, PainelBTN);
		sl_PainelBTN.putConstraint(SpringLayout.EAST, btnCadastrar, -77, SpringLayout.EAST, PainelBTN);
		btnCadastrar.setText("Confirmar");
		btnCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrar.setFont(new Font("Dialog", Font.PLAIN, 22));
		btnCadastrar.setBackground(new Color(2, 73, 89));
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBorderPainted(false);
		btnCadastrar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String senha = String.valueOf(txtNovaSenha.getPassword());
				String email = txtEmail.getText();
				String senhaRepet = String.valueOf(txtSenhaRepet.getPassword());

				if (senha.equals(senhaRepet) && senhaRepet.equals(senha)) {
					if (uDAO.consultarUsuarioLoginSenha(email, senha) == null) {
						uDAO.alterarSenha(senha, email);
						PopupVoltarLogin aviso = new PopupVoltarLogin();
						aviso.setLabelText("Senha alterada com sucesso!");
						aviso.setVisible(true);
						aviso.setLocationRelativeTo(null);
						dispose();
					} else {
						TelaError erro = new TelaError();
						erro.setLabelText("Não utilize a mesma senha antes cadastrada!");
						erro.setVisible(true);
						erro.setLocationRelativeTo(null);
					}
				} else {
					TelaError erro = new TelaError();
					erro.setLabelText("As duas senhas não coincidem!");
					erro.setVisible(true);
					erro.setLocationRelativeTo(null);
				}
			}
		});
		JLabel lblCriarConta = new JLabel("Criar nova conta");
		sl_PainelBTN.putConstraint(SpringLayout.SOUTH, btnCadastrar, -6, SpringLayout.NORTH, lblCriarConta);
		sl_PainelBTN.putConstraint(SpringLayout.NORTH, lblCriarConta, 54, SpringLayout.NORTH, PainelBTN);
		sl_PainelBTN.putConstraint(SpringLayout.EAST, lblCriarConta, -112, SpringLayout.EAST, PainelBTN);
		sl_PainelBTN.putConstraint(SpringLayout.WEST, lblCriarConta, 112, SpringLayout.WEST, PainelBTN);
		lblCriarConta.setHorizontalAlignment(SwingConstants.LEFT);
		lblCriarConta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCriarConta.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblCriarConta.setForeground(new Color(0, 92, 214));
		lblCriarConta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaCadastro frame = new TelaCadastro();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				dispose();

			}

		});
		PainelBTN.setLayout(sl_PainelBTN);
		PainelBTN.add(btnCadastrar);
		PainelBTN.add(lblCriarConta);

		JLabel lblIrLogin = new JLabel("Logar-se");
		lblIrLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIrLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaLogin frame = new TelaLogin();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				dispose();
			}
		});
		sl_PainelBTN.putConstraint(SpringLayout.NORTH, lblIrLogin, 97, SpringLayout.NORTH, PainelBTN);
		sl_PainelBTN.putConstraint(SpringLayout.WEST, lblIrLogin, 133, SpringLayout.WEST, PainelBTN);
		sl_PainelBTN.putConstraint(SpringLayout.EAST, lblIrLogin, -138, SpringLayout.EAST, PainelBTN);
		lblIrLogin.setForeground(Color.BLUE);
		lblIrLogin.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblIrLogin.setForeground(new Color(0, 92, 214));
		PainelBTN.add(lblIrLogin);

		JLabel lblOu = new JLabel("ou");
		sl_PainelBTN.putConstraint(SpringLayout.NORTH, lblOu, 5, SpringLayout.SOUTH, lblCriarConta);
		sl_PainelBTN.putConstraint(SpringLayout.WEST, lblOu, 140, SpringLayout.WEST, PainelBTN);
		sl_PainelBTN.putConstraint(SpringLayout.SOUTH, lblOu, -6, SpringLayout.NORTH, lblIrLogin);
		sl_PainelBTN.putConstraint(SpringLayout.EAST, lblOu, -150, SpringLayout.EAST, PainelBTN);
		lblOu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOu.setHorizontalAlignment(SwingConstants.CENTER);
		PainelBTN.add(lblOu);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel.add(panel_3);
	}
}
