package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controle.ProdutorDAO;
import modelo.Produtor;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;
import modelo.RoundButton;

public class TelaPerfilVendedor extends JFrame {

	private JPanel contentPane;
	TelaPerfilVendedor estajanela = this;
	private ArrayList<Usuario> listaUsuarios;
	JPanel panelLeft;
	Produtor produtor;
	private ProdutorDAO pDAO = ProdutorDAO.getInstancia();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaPerfilVendedor frame = new TelaPerfilVendedor(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * 
	 * @param u
	 * 
	 * @param tela
	 */

	public static BufferedImage arredondar(BufferedImage imagemRetangular) {
		int largura = imagemRetangular.getWidth();
		int altura = imagemRetangular.getHeight();
		int raio = largura / (double) altura > 0 ? altura : largura;
		BufferedImage imagemRedonda = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = imagemRedonda.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setClip(new Area(new Ellipse2D.Double(0, 0, raio, raio)));
		graphics.drawImage(imagemRetangular, 0, 0, null);
		graphics.dispose();
		return imagemRedonda;
	}

	public TelaPerfilVendedor(Usuario u, boolean isVendedor, Image foto) {

		produtor = pDAO.consultaProdutor(u);

		setResizable(false);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(1176, 664));
		setMaximumSize(new Dimension(1920, 1080));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 208, 217));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[10px][1120px][10px][10px][][10px]", "[30px]"));

		JButton btnMenu = new JButton("");
		btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelLeft.isVisible()) {
					panelLeft.setVisible(false);
				} else {
					panelLeft.setVisible(true);
				}
			}
		});
		btnMenu.setBorder(null);
		btnMenu.setBackground(SystemColor.control);
		btnMenu.setOpaque(false);
		panel.add(btnMenu, "cell 0 0,grow");
		ImageIcon iconMenu = new ImageIcon(TelaPerfilVendedor.class.getResource("/IMG/menu-hamburguer.png"));
		Image iconM = iconMenu.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		btnMenu.setIcon(new ImageIcon(iconM));
		ImageIcon iconCarrinho = new ImageIcon(TelaPerfilVendedor.class.getResource("/IMG/carrinho-de-compras.png"));
		Image iconC = iconCarrinho.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		JButton btnFlecha = new JButton("");
		btnFlecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaDeLogOff telaLogOff = new TelaDeLogOff(estajanela, u, isVendedor);
				telaLogOff.setLocationRelativeTo(null);
				telaLogOff.setVisible(true);
			}
		});

		JButton btnCarrinho = new JButton("");
		btnCarrinho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCarrinho.setOpaque(false);
		btnCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaCarrinho carrinho = new TelaCarrinho(u, null, isVendedor);
				carrinho.setLocationRelativeTo(null);
				carrinho.setVisible(true);
				dispose();
			}
		});
		btnCarrinho.setBorder(null);
		btnCarrinho.setBackground(SystemColor.control);
		panel.add(btnCarrinho, "cell 4 0,grow");
		btnCarrinho.setIcon(new ImageIcon(iconC));
		btnFlecha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFlecha.setOpaque(false);
		btnFlecha.setBorder(null);
		btnFlecha.setBackground(SystemColor.control);
		panel.add(btnFlecha, "cell 5 0,grow");
		ImageIcon iconFlecha = new ImageIcon(TelaPerfilVendedor.class.getResource("/IMG/saida.png"));
		Image iconF = iconFlecha.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		btnFlecha.setIcon(new ImageIcon(iconF));

		panelLeft = new JPanel();
		panelLeft.setBackground(new Color(154, 208, 217));
		contentPane.add(panelLeft, BorderLayout.WEST);
		panelLeft.setLayout(new MigLayout("", "[100px]", "[50px][50px][50px][50px]"));

		JButton btnNewButton = new JButton("Inicio");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaInicio tiv = new TelaInicio(u, isVendedor);
				tiv.setLocationRelativeTo(null);
				tiv.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(154, 205, 217));
		btnNewButton.setBorder(null);
		btnNewButton.setOpaque(false);
		panelLeft.add(btnNewButton, "cell 0 0,grow");

		JButton btnNewButton_1 = new JButton("Carrinho");
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaCarrinho carrinho = new TelaCarrinho(u, null, isVendedor);
				carrinho.setLocationRelativeTo(null);
				carrinho.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(154, 205, 217));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setOpaque(false);
		panelLeft.add(btnNewButton_1, "cell 0 1,grow");

		JButton btnNewButton_2 = new JButton("Perfil");
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBackground(new Color(96, 154, 168));
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setOpaque(true);
		panelLeft.add(btnNewButton_2, "cell 0 2,grow");

		JButton btnNewButton_3 = new JButton("Estoque");
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaEstoque frame = new TelaEstoque(u);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				dispose();

			}
		});
		btnNewButton_3.setBackground(new Color(154, 205, 217));
		btnNewButton_3.setBorder(null);
		panelLeft.add(btnNewButton_3, "cell 0 3,grow");

		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(63, 23));
		panel_1.setMinimumSize(new Dimension(63, 23));
		contentPane.add(panel_1, BorderLayout.CENTER);

		panel_1.setLayout(new MigLayout("", "[grow][grow][grow][grow][]", "[][][][][][][][][250px][40px][]"));
		
				JLabel lblNomeUsuario = new JLabel();
				lblNomeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
				lblNomeUsuario.setFont(new Font("Dialog", Font.PLAIN, 30));
				panel_1.add(lblNomeUsuario, "cell 1 1,alignx left,aligny center");
				lblNomeUsuario.setText(u.getNome());
		
				JLabel lblNomeComercio = new JLabel("");
				lblNomeComercio.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 18));
				panel_1.add(lblNomeComercio, "cell 1 2,alignx left,aligny center");
				lblNomeComercio.setText(pDAO.consultaProdutor(u).getNomeComercio());

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, "cell 0 1 1 7,grow");
		panel_2.setLayout(new MigLayout("", "[grow]", "[grow][]"));

		JLabel lblFoto = new JLabel("Foto de Perfil");
		panel_2.add(lblFoto, "cell 0 0,alignx center,aligny center");
		lblFoto.setFont(new Font("Dialog", Font.ITALIC, 13));
		lblFoto.setForeground(new Color(0, 0, 0));
		
				JLabel imgUsuario = new JLabel("");
				panel_2.add(imgUsuario, "cell 0 1,alignx center,aligny center");
				imgUsuario.setIcon(new ImageIcon(TelaEditarPerfilCliente.class.getResource("/img/Avatar.png")));
		
		try {
			if (foto != null) {
				
				panel_2.revalidate();
				panel_2.repaint();

				// Verifica se o tamanho do JLabel é válido
				SwingUtilities.invokeLater(() -> {
				    int labelWidth = imgUsuario.getWidth();
				    int labelHeight = imgUsuario.getHeight();

				    if (labelWidth > 0 && labelHeight > 0) {
				        Image scaledImage = foto.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
				        imgUsuario.setIcon(new ImageIcon(scaledImage));
				    } else {
				        System.out.println("Tamanho inválido do JLabel. Definindo tamanho padrão.");
				        Image scaledImage = foto.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				        imgUsuario.setIcon(new ImageIcon(scaledImage));
				    }
				});


				
				System.out.println("Deu bom!");
			}else {
				imgUsuario.setIcon(new ImageIcon(TelaPerfilCliente.class.getResource("/img/Avatar.png")));
				System.out.println("Deu ruim!");
			}
		} catch (Exception e) {
			System.out.println(e);

		}

		JLabel lblInfoComercio = new JLabel("Informações pessoais:");
		lblInfoComercio.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.BOLD | Font.ITALIC, 17));
		panel_1.add(lblInfoComercio, "cell 1 4,alignx left,aligny bottom");

		JLabel lblInfoC = new JLabel("Informações sobre  o comércio:");
		lblInfoC.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.BOLD | Font.ITALIC, 17));
		panel_1.add(lblInfoC, "cell 2 4,alignx left,aligny bottom");

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(lblEmail, "flowx,cell 1 5,alignx left,aligny bottom");

		JLabel txtEmail = new JLabel("");
		txtEmail.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(txtEmail, "cell 1 5,alignx center,aligny bottom");
		txtEmail.setText(u.getEmail());

		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(lblEndereco, "flowx,cell 3 5,alignx left,aligny bottom");

		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(lblCpf, "flowx,cell 1 6");

		JLabel txtCpf = new JLabel("");
		txtCpf.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(txtCpf, "cell 1 6");
		txtCpf.setText(u.getCpf());

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(lblTelefone, "flowx,cell 1 7");

		JLabel txtTelefone = new JLabel("");
		txtTelefone.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(txtTelefone, "cell 1 7");
		if (u.getTel() == null) {
			txtTelefone.setText("Nenhum telefone cadastrado.");
		} else {
			txtTelefone.setText(u.getTel());
		}

		JLabel lblNomeC = new JLabel("Nome do Comércio:");
		lblNomeC.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(lblNomeC, "flowx,cell 2 5,aligny bottom");

		JLabel lblCnpj = new JLabel("Cnpj:");
		lblCnpj.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(lblCnpj, "flowx,cell 2 6,aligny bottom");

		JLabel txtCnpj = new JLabel("");
		txtCnpj.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(txtCnpj, "cell 2 6,aligny bottom");
		txtCnpj.setText(pDAO.consultaProdutor(u).getCnpj());

		JLabel txtNomeC = new JLabel("");
		txtNomeC.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(txtNomeC, "cell 2 5,aligny bottom");
		txtNomeC.setText(pDAO.consultaProdutor(u).getNomeComercio());

		JLabel lblnada = new JLabel("    ");
		panel_1.add(lblnada, "flowx,cell 3 3,aligny bottom");

		JLabel txtCidade = new JLabel("cidade");
		txtCidade.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(txtCidade, "flowx,cell 3 7");
		txtCidade.setText(produtor.getEnd().getCidade());

		JButton rbInfo = new JButton("");
		rbInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEditarPerfilVendedor editar = new TelaEditarPerfilVendedor(u);
				editar.setLocationRelativeTo(null);
				editar.setVisible(true);
				dispose();
			}
		});

		JButton rbSenha = new JButton("Alterar senha");
		rbSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRedefinicaoSenha redefinir = new TelaRedefinicaoSenha(u, isVendedor);
				redefinir.setVisible(true);
				redefinir.setLocationRelativeTo(null);
			}
		});
		rbSenha.setForeground(Color.BLACK);
		rbSenha.setFont(new Font("Dialog", Font.PLAIN, 11));
		rbSenha.setBackground(new Color(154, 205, 217));
		panel_1.add(rbSenha, "flowx,cell 4 10,alignx center,aligny center");
		rbInfo.setText("Alterar Informações");
		rbInfo.setFont(new Font("Dialog", Font.PLAIN, 11));
		rbInfo.setBackground(new Color(154, 205, 217));
		rbInfo.setForeground(new Color(0, 0, 0));
		panel_1.add(rbInfo, "cell 3 8,alignx center,aligny center");

		JLabel lblChavePix = new JLabel("ChavePix:");
		lblChavePix.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_1.add(lblChavePix, "flowx,cell 1 8,aligny top");
		rbInfo.setText("Alterar Informações");
		rbInfo.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 11));
		rbInfo.setBackground(new Color(154, 205, 217));
		rbInfo.setForeground(new Color(0, 0, 0));
		panel_1.add(rbInfo, "cell 4 9,alignx center,aligny center");

		JLabel lblnada3 = new JLabel(",     ");
		panel_1.add(lblnada3, "cell 3 7,alignx left");

		JLabel txtCep = new JLabel("cep");
		txtCep.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(txtCep, "cell 3 7,alignx right,aligny bottom");
		txtCep.setText(produtor.getEnd().getCep());

		JLabel lblNumero = new JLabel("Número: ");
		lblNumero.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(lblNumero, "flowx,cell 3 6,alignx left");

		JLabel txtNum = new JLabel("num");
		txtNum.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(txtNum, "cell 3 6,alignx left");
		txtNum.setText(String.valueOf(produtor.getEnd().getNumero()));

		JLabel lblnada2 = new JLabel(",     ");
		panel_1.add(lblnada2, "cell 3 6");

		JLabel txtBairro = new JLabel("bairro");
		txtBairro.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(txtBairro, "cell 3 6,alignx left");
		txtBairro.setText(produtor.getEnd().getBairro());

		JLabel txtLogradouro = new JLabel("logra");
		txtLogradouro.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		panel_1.add(txtLogradouro, "cell 3 5,alignx right,aligny bottom");
		txtLogradouro.setText(produtor.getEnd().getLogradouro());

		JLabel lblponto = new JLabel(".");
		panel_1.add(lblponto, "cell 3 7");

		JLabel lblvirgula = new JLabel(",");
		panel_1.add(lblvirgula, "cell 3 5,aligny bottom");
		
		JLabel lblPixValor = new JLabel("");
		panel_1.add(lblPixValor, "cell 1 8,aligny top");
		lblPixValor.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 15));
		ImageIcon img = new ImageIcon(u.getImg());
		Produtor p = pDAO.consultaProdutor(u);
		lblPixValor.setText(p.getChavePix());
	}

}
