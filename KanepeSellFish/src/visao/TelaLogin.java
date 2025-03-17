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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
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
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		
		
		
		setResizable(false);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(1176, 664));
		setMaximumSize(new Dimension(1920, 1080));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 768);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setLayout(new BorderLayout(0, 0));

		PicPanel panel = new PicPanel("src//IMG/TelaLogin.png");
		panel.setOpaque(false);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panel.add(panelPrincipal);
		panelPrincipal.setLayout(new MigLayout("", "[grow]", "[150px][95px][70px][88px][65px][grow]"));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panelPrincipal.add(panel_2, "cell 0 0,grow");

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panelPrincipal.add(panel_4, "cell 0 1,grow");
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblTitulo = new JLabel("Bem vindo de volta!");
		lblTitulo.setVerticalAlignment(SwingConstants.TOP);
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.BOLD, 27));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblTitulo);

		JPanel panelNome = new JPanel();
		panelNome.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelNome.setOpaque(false);
		panelPrincipal.add(panelNome, "cell 0 2,grow");
		panelNome.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblEmail = new JLabel("<html>Email<span style='color: red;'>*</span></html>");
		lblEmail.setForeground(Color.BLACK); // Define a cor do texto principal
		lblEmail.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.BOLD, 12)); // Define a fonte
		panelNome.add(lblEmail, "cell 0 0");

		txtEmail = new JTextField();
		txtEmail.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtEmail.setOpaque(false);
		panelNome.add(txtEmail, "cell 0 1,grow");
		txtEmail.setColumns(10);

		JPanel panelCpf = new JPanel();
		panelCpf.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelCpf.setOpaque(false);
		panelPrincipal.add(panelCpf, "cell 0 3,grow");
		panelCpf.setLayout(new MigLayout("", "[grow]", "[10px][30px][10px,top]"));

		JLabel lblSenha = new JLabel("<html>Senha<span style='color: red;'>*</span></html>");
		lblSenha.setForeground(Color.BLACK);
		lblSenha.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.BOLD, 12));
		panelCpf.add(lblSenha, "cell 0 0");

		JLabel lblEsqueceu = new JLabel("Esqueceu a senha? ");
		lblEsqueceu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEsqueceu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaEsqueceuSenha esqueceu = new TelaEsqueceuSenha();
				esqueceu.setLocationRelativeTo(null);
				esqueceu.setVisible(true);
				dispose();

			}
		});

		txtSenha = new JPasswordField();
		txtSenha.setOpaque(false);
		txtSenha.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelCpf.add(txtSenha, "cell 0 1,grow");
		lblEsqueceu.setForeground(new Color(0, 92, 214));
		lblEsqueceu.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.ITALIC, 12));
		lblEsqueceu.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblEsqueceu.setVerticalAlignment(SwingConstants.TOP);
		lblEsqueceu.setHorizontalAlignment(SwingConstants.RIGHT);
		panelCpf.add(lblEsqueceu, "cell 0 2,alignx right");

		JPanel panelConfirmacao = new JPanel();
		panelConfirmacao.setOpaque(false);
		panelConfirmacao.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelPrincipal.add(panelConfirmacao, "cell 0 5,grow");
		panelConfirmacao.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel PainelBTN = new JPanel();
		PainelBTN.setOpaque(false);
		panelConfirmacao.add(PainelBTN);
		SpringLayout sl_PainelBTN = new SpringLayout();
		PainelBTN.setLayout(sl_PainelBTN);
		
		txtSenha.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    realizarLogin();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

		JButton btnEntrar = new RoundButton("Entrar");
		btnEntrar.setText("Entrar");
		sl_PainelBTN.putConstraint(SpringLayout.NORTH, btnEntrar, 0, SpringLayout.NORTH, PainelBTN);
		sl_PainelBTN.putConstraint(SpringLayout.SOUTH, btnEntrar, 48, SpringLayout.NORTH, PainelBTN);
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrar.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 22));
		btnEntrar.setBackground(new Color(2, 73, 89));
		btnEntrar.setForeground(new Color(255, 255, 255));
		btnEntrar.setBorderPainted(false);
		btnEntrar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				realizarLogin();
			}
		});
		JLabel lblClique = new JLabel("Crie Aqui.");
		sl_PainelBTN.putConstraint(SpringLayout.EAST, btnEntrar, -9, SpringLayout.EAST, lblClique);
		lblClique.setHorizontalAlignment(SwingConstants.LEFT);
		lblClique.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClique.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 14));
		lblClique.setForeground(new Color(0, 92, 214));
		lblClique.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaCadastro frame = new TelaCadastro();

				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				dispose();

			}

		});
		PainelBTN.setLayout(sl_PainelBTN);
		PainelBTN.add(btnEntrar);
		PainelBTN.add(lblClique);

		JLabel lblNaoTemUmaConta = new JLabel("Não tem uma conta?");
		sl_PainelBTN.putConstraint(SpringLayout.NORTH, lblNaoTemUmaConta, 6, SpringLayout.SOUTH, btnEntrar);
		sl_PainelBTN.putConstraint(SpringLayout.WEST, btnEntrar, 9, SpringLayout.WEST, lblNaoTemUmaConta);
		sl_PainelBTN.putConstraint(SpringLayout.WEST, lblNaoTemUmaConta, 62, SpringLayout.WEST, PainelBTN);
		sl_PainelBTN.putConstraint(SpringLayout.NORTH, lblClique, 0, SpringLayout.NORTH, lblNaoTemUmaConta);
		sl_PainelBTN.putConstraint(SpringLayout.WEST, lblClique, 1, SpringLayout.EAST, lblNaoTemUmaConta);
		lblNaoTemUmaConta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNaoTemUmaConta.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblNaoTemUmaConta.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 14));
		lblNaoTemUmaConta.setForeground(new Color(0, 0, 0));
		PainelBTN.add(lblNaoTemUmaConta);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel.add(panel_3);
	}
	
	private void realizarLogin() {
		Usuario u = new Usuario();

		String email = txtEmail.getText();
		String senha = String.valueOf(txtSenha.getPassword());
		u = uDAO.consultarUsuarioLoginSenha(email, senha);

		if (u != null) {
			if (uDAO.consultarUsuarioVendedor(u)) {
				TelaInicio tela = new TelaInicio(u, true);
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);

				dispose();
			} else {
				TelaInicio tela = new TelaInicio(u, false);
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);

				dispose();
			}
		} else {
			TelaError tela = new TelaError();
			tela.setLabelText("Usuario não encontrado");
			tela.setLocationRelativeTo(null);
			tela.setVisible(true);

			System.out.println("Nao achou");
		}
    }
	
}
