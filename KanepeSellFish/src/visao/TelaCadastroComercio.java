package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import controle.EnderecoDAO;
import controle.ProdutorDAO;
import controle.UsuarioDAO;
import modelo.Endereco;
import modelo.Produtor;
import modelo.RoundButton;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

public class TelaCadastroComercio extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeComercio;
	private JTextField txtCNPJ;
	private JTextField txtCEP;
	private JTextField txtBairro;
	private static UsuarioDAO uDAO = UsuarioDAO.getInstancia();
	private static EnderecoDAO eDAO = EnderecoDAO.getInstancia();
	private static ProdutorDAO pDAO = ProdutorDAO.getInstancia();
	private JTextField txtLogradouro;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JTextField txtChavePix;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @param novoUsuario
	 */
	public TelaCadastroComercio(Usuario novoUsuario) {
		setTitle("Cadastro do comercio");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroComercio.class.getResource("/img/logo.png")));
		setResizable(false);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(1176, 664));
		setMaximumSize(new Dimension(1920, 1080));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		panelPrincipal
				.setLayout(new MigLayout("", "[grow]", "[100px][60px][65px][65px][65px][65px][65px][65px][grow]"));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panelPrincipal.add(panel_2, "cell 0 0,grow");

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panelPrincipal.add(panel_4, "cell 0 1,grow");
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblTitulo = new JLabel("Quase lá...");
		lblTitulo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 27));
		panel_4.add(lblTitulo);

		JPanel panelNome = new JPanel();
		panelNome.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelNome.setOpaque(false);
		panelPrincipal.add(panelNome, "cell 0 2,grow");
		panelNome.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblNomeComercio = new JLabel("<html>Nome do Comercio<span style='color: red;'>*</span></html>");
		lblNomeComercio.setForeground(Color.BLACK); // Define a cor do texto principal
		lblNomeComercio.setFont(new Font("Tahoma", Font.BOLD, 12)); // Define a fonte
		panelNome.add(lblNomeComercio, "cell 0 0");

		txtNomeComercio = new JTextField();
		txtNomeComercio.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtNomeComercio.setOpaque(false);
		panelNome.add(txtNomeComercio, "cell 0 1,grow");
		txtNomeComercio.setColumns(10);

		JPanel panelCpf = new JPanel();
		panelCpf.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelCpf.setOpaque(false);
		panelPrincipal.add(panelCpf, "cell 0 3,grow");
		panelCpf.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblCNPJ = new JLabel("<html>CNPJ<span style='color: red;'>*</span></html>");
		lblCNPJ.setForeground(Color.BLACK);
		lblCNPJ.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelCpf.add(lblCNPJ, "cell 0 0");

		txtCNPJ = new JTextField();
		txtCNPJ.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtCNPJ.setOpaque(false);
		txtCNPJ.setColumns(10);
		panelCpf.add(txtCNPJ, "cell 0 1,grow");

		JPanel panelCpf_1 = new JPanel();
		panelCpf_1.setOpaque(false);
		panelCpf_1.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelPrincipal.add(panelCpf_1, "cell 0 4,grow");
		panelCpf_1.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblChavePix = new JLabel("Chave Pix(Opcional)");
		lblChavePix.setForeground(Color.BLACK);
		lblChavePix.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelCpf_1.add(lblChavePix, "cell 0 0");

		txtChavePix = new JTextField();
		txtChavePix.setOpaque(false);
		txtChavePix.setColumns(10);
		txtChavePix.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelCpf_1.add(txtChavePix, "cell 0 1,grow");

		JPanel panelEmail = new JPanel();
		panelEmail.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelEmail.setOpaque(false);
		panelPrincipal.add(panelEmail, "cell 0 5,grow");
		panelEmail.setLayout(new MigLayout("", "[grow][][190px]", "[10px][30px]"));

		JLabel lblCEP = new JLabel("<html>CEP<span style='color: red;'>*</span></html>");
		lblCEP.setForeground(new Color(0, 0, 0));
		lblCEP.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelEmail.add(lblCEP, "cell 0 0");

		JLabel lblCidade = new JLabel("<html>Cidade<span style='color: red;'>*</span></html>");
		lblCidade.setForeground(Color.BLACK);
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelEmail.add(lblCidade, "cell 2 0");

		txtCEP = new JTextField();
		txtCEP.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtCEP.setOpaque(false);
		txtCEP.setColumns(10);
		panelEmail.add(txtCEP, "cell 0 1,grow");

		JLabel lblImagem = new JLabel("");
		lblImagem.setToolTipText("Procurar CEP");
		lblImagem.setName("");
		lblImagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Endereco endereco = eDAO.buscaCEP(txtCEP.getText());
				txtLogradouro.setText(endereco.getLogradouro());
				txtBairro.setText(endereco.getBairro());
				txtCidade.setText(endereco.getCidade());

			}
		});
		lblImagem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImagem.setIcon(new ImageIcon(TelaCadastroComercio.class.getResource("/img/procurar.png")));
		panelEmail.add(lblImagem, "cell 1 1,alignx trailing");
		ImageIcon iconProcurar = new ImageIcon(TelaCadastroComercio.class.getResource("/IMG/procurar.png"));
		Image iconP = iconProcurar.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		lblImagem.setIcon(new ImageIcon(iconP));

		txtCidade = new JTextField();
		txtCidade.setOpaque(false);
		txtCidade.setColumns(10);
		txtCidade.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelEmail.add(txtCidade, "cell 2 1,grow");

		JPanel panelSenha = new JPanel();
		panelSenha.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelSenha.setOpaque(false);
		panelPrincipal.add(panelSenha, "cell 0 6,grow");
		panelSenha.setLayout(new MigLayout("", "[grow][80px]", "[10px][30px]"));

		JLabel lblBairro = new JLabel("<html>Bairro<span style='color: red;'>*</span></html>");
		lblBairro.setForeground(new Color(0, 0, 0));
		lblBairro.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelSenha.add(lblBairro, "cell 0 0");

		JLabel lblNumero = new JLabel("<html>Numero<span style='color: red;'>*</span></html>");
		lblNumero.setForeground(Color.BLACK);
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelSenha.add(lblNumero, "cell 1 0");

		txtBairro = new JTextField();
		txtBairro.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtBairro.setOpaque(false);
		txtBairro.setColumns(10);
		panelSenha.add(txtBairro, "cell 0 1,grow");

		txtNumero = new JTextField();
		txtNumero.setOpaque(false);
		txtNumero.setColumns(10);
		txtNumero.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelSenha.add(txtNumero, "cell 1 1,grow");

		JPanel panelCheck = new JPanel();
		panelCheck.setOpaque(false);
		panelCheck.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelPrincipal.add(panelCheck, "cell 0 7,grow");
		panelCheck.setLayout(new MigLayout("", "[grow]", "[10px][30px]"));

		JLabel lblLogradouro = new JLabel("<html>Logradouro<span style='color: red;'>*</span></html>");
		lblLogradouro.setForeground(new Color(0, 0, 0));
		lblLogradouro.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelCheck.add(lblLogradouro, "cell 0 0");

		txtLogradouro = new JTextField();
		txtLogradouro.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		txtLogradouro.setOpaque(false);
		panelCheck.add(txtLogradouro, "cell 0 1,grow");
		txtLogradouro.setColumns(10);

		JPanel panelConfirmacao = new JPanel();
		panelConfirmacao.setOpaque(false);
		panelConfirmacao.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelPrincipal.add(panelConfirmacao, "cell 0 8,grow");
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
				// verificacao de dados vazios

				// verificacao de cep incorreto

				Endereco endereco = new Endereco();
				Produtor produtor = new Produtor();

				String nomeComercio = txtNomeComercio.getText();
				String cnpj = txtCNPJ.getText();
				String cep = txtCEP.getText();
				String cidade = txtCidade.getText();
				String bairro = txtBairro.getText();
				String numero = txtNumero.getText();
				String logradouro = txtLogradouro.getText();
				String chavePix = txtChavePix.getText();

				if (nomeComercio.isEmpty() || cnpj.isEmpty() || cep.isEmpty() || logradouro.isEmpty()
						|| bairro.isEmpty() || cidade.isEmpty() || numero.isEmpty()) {
					TelaError erro = new TelaError();
					erro.setLabelText("Informações inválidas");
					erro.setLocationRelativeTo(null);
					erro.setVisible(true);
				} else {
					endereco.setCep(cep);
					endereco.setCidade(cidade);
					endereco.setBairro(bairro);
					endereco.setNumero(Integer.parseInt(numero));
					endereco.setLogradouro(logradouro);

					int idEndereco = eDAO.inserirEnderecoDoComercio(endereco);
					int idUsuario = uDAO.inserirUsuario(novoUsuario);

					if (idEndereco != -1 || idUsuario != -1) {

						produtor.setNomeComercio(nomeComercio);
						produtor.setCnpj(cnpj);
						produtor.setEndereco(idEndereco);
						produtor.setIdUsuario(idUsuario);
						if (chavePix.isEmpty() || chavePix == null) {
							
						} else {
							produtor.setChavePix(chavePix);
						}

						System.out.println(idUsuario);

						pDAO.inserirProdutor(produtor);
						TelaLogin tela = new TelaLogin();
						tela.setLocationRelativeTo(null);
						tela.setVisible(true);

						dispose();
					} else {
						TelaError erro = new TelaError();
						erro.setLabelText("Erro ao inserir dados");
						erro.setLocationRelativeTo(null);
						erro.setVisible(true);
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

		RestrictedTextField validarCep = new RestrictedTextField(txtCEP);
		validarCep.setOnlyNums(true);
		validarCep.setLimit(8);

		RestrictedTextField validarCnpj = new RestrictedTextField(txtCNPJ);
		validarCnpj.setOnlyNums(true);
		validarCnpj.setLimit(14);
	}
}