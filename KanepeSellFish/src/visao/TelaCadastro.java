package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import controle.UsuarioDAO;
import modelo.RoundButton;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class TelaCadastro extends JFrame {

	@SuppressWarnings("unused")
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtEmail;
	private static UsuarioDAO uDAO = UsuarioDAO.getInstancia();
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
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
		panelPrincipal.setLayout(new MigLayout("", "[grow]", "[100px][90px][65px][65px][65px][65px][20px][grow]"));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panelPrincipal.add(panel_2, "cell 0 0,grow");

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panelPrincipal.add(panel_4, "cell 0 1,grow");
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblTitulo = new JLabel("Crie uma conta");
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 27));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblTitulo);

		JPanel panelNome = new JPanel();
		panelNome.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelNome.setOpaque(false);
		panelPrincipal.add(panelNome, "cell 0 2,grow");
		panelNome.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblNome = new JLabel("<html>Nome<span style='color: red;'>*</span></html>");
		lblNome.setForeground(Color.BLACK); // Define a cor do texto principal
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12)); // Define a fonte
		panelNome.add(lblNome, "cell 0 0");

		txtNome = new JTextField();
		txtNome.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtNome.setOpaque(false);
		panelNome.add(txtNome, "cell 0 1,grow");
		txtNome.setColumns(10);

		JPanel panelCpf = new JPanel();
		panelCpf.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelCpf.setOpaque(false);
		panelPrincipal.add(panelCpf, "cell 0 3,grow");
		panelCpf.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblCPF = new JLabel("<html>CPF<span style='color: red;'>*</span></html>");
		lblCPF.setForeground(Color.BLACK);
		lblCPF.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelCpf.add(lblCPF, "cell 0 0");

		txtCPF = new JTextField();
		txtCPF.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtCPF.setOpaque(false);
		txtCPF.setColumns(10);
		panelCpf.add(txtCPF, "cell 0 1,grow");

		JPanel panelEmail = new JPanel();
		panelEmail.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelEmail.setOpaque(false);
		panelPrincipal.add(panelEmail, "cell 0 4,grow");
		panelEmail.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblEmail = new JLabel("<html>Email<span style='color: red;'>*</span></html>");
		lblEmail.setForeground(new Color(0, 0, 0));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelEmail.add(lblEmail, "cell 0 0");

		txtEmail = new JTextField();
		txtEmail.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtEmail.setOpaque(false);
		txtEmail.setColumns(10);
		panelEmail.add(txtEmail, "cell 0 1,grow");

		JPanel panelSenha = new JPanel();
		panelSenha.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelSenha.setOpaque(false);
		panelPrincipal.add(panelSenha, "cell 0 5,grow");
		panelSenha.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblSenha = new JLabel("<html>Senha<span style='color: red;'>*</span></html>");
		lblSenha.setForeground(new Color(0, 0, 0));
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelSenha.add(lblSenha, "cell 0 0");

		txtSenha = new JPasswordField();
		txtSenha.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtSenha.setOpaque(false);
		panelSenha.add(txtSenha, "cell 0 1,grow");

		JPanel panelCheck = new JPanel();
		panelCheck.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) panelCheck.getLayout();
		flowLayout.setVgap(-5);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelCheck.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelPrincipal.add(panelCheck, "cell 0 6,grow");

		JCheckBox chckboxVendedor = new JCheckBox("Criar conta como vendedor");
		chckboxVendedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckboxVendedor.setForeground(new Color(0, 0, 0));
		chckboxVendedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckboxVendedor.setOpaque(false);
		chckboxVendedor.setHorizontalAlignment(SwingConstants.LEFT);
		panelCheck.add(chckboxVendedor);
		chckboxVendedor.setIcon(new CustomCheckBoxIcon(16)); // Definir o ícone personalizado
		chckboxVendedor.setSelectedIcon(new CustomCheckBoxIcon(16));

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
		sl_PainelBTN.putConstraint(SpringLayout.SOUTH, btnCadastrar, 48, SpringLayout.NORTH, PainelBTN);
		btnCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrar.setFont(new Font("Dialog", Font.PLAIN, 22));
		btnCadastrar.setBackground(new Color(2, 73, 89));
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBorderPainted(false);
		btnCadastrar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = txtCPF.getText();
				String email = txtEmail.getText();
				String senha = new String(txtSenha.getPassword());
				String nome = txtNome.getText();

				// Consulta o usuário
				Usuario u = uDAO.consultaUsuarioCadastrado(cpf, email);

				if (u != null) {
					System.out.println("CPF ou Email já cadastrado");
					TelaError erro = new TelaError();
					erro.setLabelText("CPF ou Email já cadastrado");
					erro.setLocationRelativeTo(null);
					erro.setVisible(true);

				} else {
					Usuario novoUsuario = new Usuario();
					if (email.isEmpty() || senha.isEmpty() || cpf.isEmpty() || nome.isEmpty()) {

						TelaError erro = new TelaError();
						erro.setLabelText("Informações inválidas");
						erro.setLocationRelativeTo(null);
						erro.setVisible(true);
					} else {
						if(uDAO.validarCPF(cpf)) {
							
						
						novoUsuario.setNome(nome);
						novoUsuario.setCpf(cpf);
						novoUsuario.setEmail(email);
						novoUsuario.setSenha(senha);
						if (!chckboxVendedor.isSelected()) {
							uDAO.inserirUsuario(novoUsuario);
							TelaLogin tela = new TelaLogin();
							tela.setLocationRelativeTo(null);
							tela.setVisible(true);

							dispose();
						} else {

							TelaCadastroComercio tela = new TelaCadastroComercio(novoUsuario);
							tela.setLocationRelativeTo(null);
							tela.setVisible(true);

							dispose();

						}
					}else {
						TelaError erro = new TelaError();
						erro.setLabelText("CPF Invalido	");
						erro.setLocationRelativeTo(null);
						erro.setVisible(true);
					}
					}
				}
			}
		});
		JLabel lblClique = new JLabel("Acesse Aqui.");
		sl_PainelBTN.putConstraint(SpringLayout.EAST, btnCadastrar, -9, SpringLayout.EAST, lblClique);
		lblClique.setHorizontalAlignment(SwingConstants.LEFT);
		lblClique.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClique.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblClique.setForeground(new Color(0, 92, 214));
		lblClique.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaLogin frame = new TelaLogin();

				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				dispose();

			}

		});
		PainelBTN.setLayout(sl_PainelBTN);
		PainelBTN.add(btnCadastrar);
		PainelBTN.add(lblClique);

		JLabel lblJaTemConta = new JLabel("Já tem uma conta?");
		sl_PainelBTN.putConstraint(SpringLayout.NORTH, lblJaTemConta, 6, SpringLayout.SOUTH, btnCadastrar);
		sl_PainelBTN.putConstraint(SpringLayout.WEST, btnCadastrar, 9, SpringLayout.WEST, lblJaTemConta);
		sl_PainelBTN.putConstraint(SpringLayout.WEST, lblJaTemConta, 62, SpringLayout.WEST, PainelBTN);
		sl_PainelBTN.putConstraint(SpringLayout.NORTH, lblClique, 0, SpringLayout.NORTH, lblJaTemConta);
		sl_PainelBTN.putConstraint(SpringLayout.WEST, lblClique, 1, SpringLayout.EAST, lblJaTemConta);
		lblJaTemConta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJaTemConta.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblJaTemConta.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblJaTemConta.setForeground(new Color(0, 0, 0));
		PainelBTN.add(lblJaTemConta);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel.add(panel_3);

		RestrictedTextField validarcpf = new RestrictedTextField(txtCPF);
		validarcpf.setOnlyNums(true);
		validarcpf.setLimit(11);
	}
}
