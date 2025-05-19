package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import modelo.Produto;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

public class TelaPerfilCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	TelaPerfilCliente estajanela = this;
	JPanel panelLeft;
	List<Produto> produtos;
	Usuario usuarioNovo = new Usuario();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaPerfilCliente frame = new TelaPerfilCliente();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaPerfilCliente(Usuario u, boolean isVendedor, Image foto) {
		usuarioNovo = u;
		setResizable(false);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(1176, 664));
		setMaximumSize(new Dimension(1920, 1080));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 205, 217));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[][2000px][][][]", "[grow]"));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new MigLayout("", "[][30px][10px][][10px][][][][][][][][]", "[50px][][][50px][][20px][][20px][][][][][][][]"));

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setBackground(new Color(192, 192, 192));
		panel_2.add(panel_3, "cell 0 0 1 12");
		panel_3.setLayout(new MigLayout("", "[grow]", "[280px][grow]"));

		JLabel lblImagemCliente = new JLabel("");
		lblImagemCliente.setOpaque(true);
		
		panel_3.add(lblImagemCliente, "flowy,cell 0 0");

		lblImagemCliente.setMinimumSize(new Dimension(100, 100)); // Garantindo tamanho mínimo para o JLabel
		lblImagemCliente.setPreferredSize(new Dimension(200, 200)); // Tamanho preferido

		JLabel lblFoto = new JLabel("Foto de Perfil");
		lblFoto.setForeground(new Color(128, 128, 128));
		lblFoto.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		panel_3.add(lblFoto, "cell 0 1,alignx center,aligny bottom");

		try {
			if (foto != null) {
				
				panel_3.revalidate();
				panel_3.repaint();

				// Verifica se o tamanho do JLabel é válido
				SwingUtilities.invokeLater(() -> {
				    int labelWidth = lblImagemCliente.getWidth();
				    int labelHeight = lblImagemCliente.getHeight();

				    if (labelWidth > 0 && labelHeight > 0) {
				        Image scaledImage = foto.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
				        lblImagemCliente.setIcon(new ImageIcon(scaledImage));
				    } else {
				        System.out.println("Tamanho inválido do JLabel. Definindo tamanho padrão.");
				        Image scaledImage = foto.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				        lblImagemCliente.setIcon(new ImageIcon(scaledImage));
				    }
				});


				
				System.out.println("Deu bom!");
			}else {
				lblImagemCliente.setIcon(new ImageIcon(TelaPerfilCliente.class.getResource("/img/Avatar.png")));
				System.out.println("Deu ruim!");
			}
		} catch (Exception e) {
			System.out.println(e);

		}
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_2.add(lblNewLabel, "cell 3 1");
		JLabel lblNomeCliente = new JLabel(u.getNome());
		panel_2.add(lblNomeCliente, "cell 3 2 8 1,growx,aligny center");
		lblNomeCliente.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 30));
												
														JLabel lblCPF = new JLabel("Cpf");
														lblCPF.setForeground(new Color(128, 128, 128));
														lblCPF.setHorizontalAlignment(SwingConstants.RIGHT);
														lblCPF.setFont(new Font("Dialog", Font.BOLD, 15));
														panel_2.add(lblCPF, "cell 3 5,alignx left");
										
												JLabel lblEmail = new JLabel("Email");
												lblEmail.setForeground(new Color(128, 128, 128));
												lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
												lblEmail.setFont(new Font("Dialog", Font.BOLD, 15));
												panel_2.add(lblEmail, "cell 8 5,alignx left");
								
										JLabel lblDynamicCPF = new JLabel(u.getCpf());
										lblDynamicCPF.setFont(new Font("Dialog", Font.PLAIN, 20));
										panel_2.add(lblDynamicCPF, "cell 3 6,alignx left,aligny center");
												
												JLabel lblNewLabel_1 = new JLabel("                        ");
												panel_2.add(lblNewLabel_1, "cell 5 6 2 1");
										
												JLabel lblDynamicEmail = new JLabel(u.getEmail());
												lblDynamicEmail.setFont(new Font("Dialog", Font.PLAIN, 20));
												panel_2.add(lblDynamicEmail, "cell 8 6,alignx left,aligny center");
								
										JLabel lblTelefone = new JLabel("Telefone");
										lblTelefone.setForeground(new Color(128, 128, 128));
										lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
										lblTelefone.setFont(new Font("Dialog", Font.BOLD, 15));
										panel_2.add(lblTelefone, "cell 3 8,alignx left");
										
										JLabel lblDynamicTel = new JLabel("");
										lblDynamicTel.setFont(new Font("Dialog", Font.PLAIN, 20));
										panel_2.add(lblDynamicTel, "cell 3 9");
		if (u.getTel() == null) {
			lblDynamicTel.setText("Nenhum telefone cadastrado.");
		} else {
			lblDynamicTel.setText(u.getTel());
		}

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new MigLayout("", "[grow]", "[grow]"));
		panel_1.add(panel_4, "cell 0 1,grow");

		JButton btnNewButton_4 = new JButton("Alterar informações");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaEditarPerfilCliente editar = new TelaEditarPerfilCliente(u, isVendedor);
				editar.setLocationRelativeTo(null);
				editar.setVisible(true);
				dispose();

			}
		});

		JButton btEditarSenha = new JButton("Alterar senha");
		btEditarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRedefinicaoSenha redefinir = new TelaRedefinicaoSenha(u, isVendedor);
				redefinir.setVisible(true);
				redefinir.setLocationRelativeTo(null);
			}
		});

		btEditarSenha.setForeground(Color.BLACK);
		btEditarSenha.setFont(new Font("Dialog", Font.PLAIN, 13));
		btEditarSenha.setBackground(new Color(154, 205, 217));
		panel_4.add(btEditarSenha, "flowx,cell 0 0,alignx right,aligny bottom");
		panel_4.add(btnNewButton_4, "cell 0 0,alignx right,aligny bottom");

		btnNewButton_4.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnNewButton_4.setBackground(new Color(154, 205, 217));
		btnNewButton_4.setForeground(new Color(0, 0, 0));

		JLabel imgMenu = new JLabel("");
		imgMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (panelLeft.isVisible()) {
					panelLeft.setVisible(false);
				} else {
					panelLeft.setVisible(true);
				}
			}
		});
		imgMenu.setIcon(new ImageIcon(TelaInicio.class.getResource("/IMG/menu-hamburguer.png")));
		panel.add(imgMenu, "cell 0 0");
		ImageIcon menu = new ImageIcon(TelaInicio.class.getResource("/IMG/menu-hamburguer.png"));
		Image iconMenu = menu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imgMenu.setIcon(new ImageIcon(iconMenu));
		ImageIcon carrinho = new ImageIcon(TelaInicio.class.getResource("/IMG/carrinho-de-compras.png"));
		Image imgC = carrinho.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

		panelLeft = new JPanel();
		panelLeft.setBackground(new Color(154, 208, 217));
		contentPane.add(panelLeft, BorderLayout.WEST);
		panelLeft.setLayout(new MigLayout("", "[100px]", "[50px][50px][50px][50px]"));

		JButton btnInicio = new JButton("Inicio");
		btnInicio.setForeground(new Color(0, 0, 0));
		btnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaInicio tela = new TelaInicio(u, isVendedor);
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);

				dispose();
			}
		});
		btnInicio.setBackground(new Color(154, 205, 217));
		btnInicio.setBorder(null);
		panelLeft.add(btnInicio, "cell 0 0,grow");

		JButton btnCarrinho = new JButton("Carrinho");
		btnCarrinho.setForeground(new Color(0, 0, 0));
		btnCarrinho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCarrinho carrinho = new TelaCarrinho(u, null, isVendedor);
				carrinho.setLocationRelativeTo(null);
				carrinho.setVisible(true);
				dispose();
			}
		});
		btnCarrinho.setBackground(new Color(154, 205, 217));
		btnCarrinho.setBorder(null);
		btnCarrinho.setOpaque(false);
		panelLeft.add(btnCarrinho, "cell 0 1,grow");

		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.setForeground(new Color(0, 0, 0));
		btnPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnPerfil.setBackground(new Color(96, 154, 168));
		btnPerfil.setBorder(null);
		panelLeft.add(btnPerfil, "cell 0 2,grow");

		if (isVendedor) {
			JButton btnNewButton_3 = new JButton("Estoque");
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
			btnNewButton_3.setOpaque(false);
			panelLeft.add(btnNewButton_3, "cell 0 3,grow");
		}
		JLabel imgSair = new JLabel("");
		imgSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeLogOff telaLogOff = new TelaDeLogOff(estajanela, u, isVendedor);
				telaLogOff.setLocationRelativeTo(null);
				telaLogOff.setVisible(true);
			}
		});

		JLabel imgCarrinho = new JLabel("");
		imgCarrinho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCarrinho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TelaCarrinho carrinho = new TelaCarrinho(u, produtos, isVendedor);
				carrinho.setLocationRelativeTo(null);
				carrinho.setVisible(true);
				dispose();

			}
		});
		imgCarrinho.setIcon(new ImageIcon(TelaInicio.class.getResource("/IMG/carrinho-de-compras.png")));
		panel.add(imgCarrinho, "cell 3 0");
		imgCarrinho.setIcon(new ImageIcon(imgC));
		imgSair.setIcon(new ImageIcon(TelaPerfilCliente.class.getResource("/img/saida.png")));
		panel.add(imgSair, "cell 4 0");
		ImageIcon conta = new ImageIcon(TelaInicio.class.getResource("/img/saida.png"));
		Image iconConta = conta.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imgSair.setIcon(new ImageIcon(iconConta));

	}

}
