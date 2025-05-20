package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controle.ProdutoDAO;
import modelo.Produto;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;
import java.awt.Cursor;

public class TelaInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel panelLeft;
	List<Produto> produtos;
	JButton btEstoque;
	JLabel lblAddProduto;
	JLabel lblAdicionarProduto;
	ProdutoDAO pDAO = new ProdutoDAO();

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaInicio frame = new TelaInicio(null, false);
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public TelaInicio(Usuario u, boolean isVendedor) {
		setResizable(false);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(1176, 664));
		setMaximumSize(new Dimension(1920, 1080));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 768);
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
		panel_1.setLayout(new MigLayout("", "[10px][300px][grow][grow]", "[160px,grow][150px,grow]"));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel panelProd = new JPanel();
		panelProd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelProd.setForeground(Color.WHITE);
		panelProd.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Peixes Dispon\u00EDveis", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setViewportView(panelProd);
		panel_1.add(scrollPane, "cell 1 0 3 2,grow");
		panelProd.setLayout(new MigLayout("", "[][][][]", "[][][][]"));

		ArrayList<Produto> lista = pDAO.addTodosProd();

		int linha = 0;
		int coluna = -1;
		if (lista == null) {
			System.out.println("lista ta Vazia");
		} else {

			for (Produto p : lista) {

				coluna++;
				if (coluna > 3) {
					coluna = 0;
					linha++;
				}
				CardProduto panel_8 = new CardProduto(p, u);
				panelProd.add(panel_8, "cell " + coluna + " " + linha + "");

			}
		}
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, "cell 0 0 1 2,grow");
		panel_2.setLayout(new MigLayout("", "[][][]", "[60px][100px][60px][50px][][][][190px,grow][][]"));

		ButtonGroup grupo = new ButtonGroup();

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
		panelLeft.setLayout(new MigLayout("", "[100px]", "[50px][50px][50px][50px][50px]"));

		JButton btInicio = new JButton("Inicio");
		btInicio.setForeground(new Color(0, 0, 0));
		btInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btInicio.setBackground(new Color(96, 154, 168));
		btInicio.setBorder(null);
		panelLeft.add(btInicio, "cell 0 0,grow");

		JButton btCarrinho = new JButton("Carrinho");
		btCarrinho.setForeground(new Color(0, 0, 0));
		btCarrinho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCarrinho carrinho = new TelaCarrinho(u, produtos, isVendedor);
				carrinho.setLocationRelativeTo(null);
				carrinho.setVisible(true);
				dispose();
			}
		});
		btCarrinho.setBackground(new Color(154, 205, 217));
		btCarrinho.setBorder(null);
		btCarrinho.setOpaque(false);
		panelLeft.add(btCarrinho, "cell 0 1,grow");

		JButton btPerfil = new JButton("Perfil");
		btPerfil.setForeground(new Color(0, 0, 0));
		btPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (isVendedor) {
					TelaPerfilVendedor v = new TelaPerfilVendedor(u, isVendedor, null);
					v.setLocationRelativeTo(null);
					v.setVisible(true);
					dispose();
				} else {
					TelaPerfilCliente telaPerfil = new TelaPerfilCliente( u, isVendedor, null);
					telaPerfil.setLocationRelativeTo(null);
					telaPerfil.setVisible(true);
					dispose();
				}
			}
		});

		btPerfil.setBackground(new Color(154, 205, 217));
		btPerfil.setBorder(null);
		btPerfil.setOpaque(false);
		panelLeft.add(btPerfil, "cell 0 2,grow");

		JLabel imgConta = new JLabel("");
		imgConta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgConta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isVendedor) {
					TelaPerfilVendedor v = new TelaPerfilVendedor(u, isVendedor, null);
					v.setLocationRelativeTo(null);
					v.setVisible(true);
					dispose();
				} else {
					TelaPerfilCliente telaPerfil = new TelaPerfilCliente(u, isVendedor, null);
					telaPerfil.setLocationRelativeTo(null);
					telaPerfil.setVisible(true);
					dispose();
				}
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
		imgConta.setIcon(new ImageIcon(TelaInicio.class.getResource("/IMG/do-utilizador.png")));
		panel.add(imgConta, "cell 4 0");
		ImageIcon conta = new ImageIcon(TelaInicio.class.getResource("/IMG/do-utilizador.png"));
		Image iconConta = conta.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imgConta.setIcon(new ImageIcon(iconConta));
				
				JButton btProdutos = new JButton("Produtos");
				btProdutos.setForeground(new Color(0, 0, 0));
				btProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btProdutos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TelaProdutos TelaProd = new TelaProdutos(u);
						TelaProd.setLocationRelativeTo(null);
						TelaProd.setVisible(true);
						dispose();
						
					}
				});
				btProdutos.setOpaque(false);
				btProdutos.setForeground(Color.BLACK);
				btProdutos.setBorder(null);
				btProdutos.setBackground(new Color(154, 205, 217));
				panelLeft.add(btProdutos, "flowy,cell 0 3,grow");
				
						btEstoque = new JButton("Estoque");
						btEstoque.setForeground(new Color(0, 0, 0));
						btEstoque.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						btEstoque.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								TelaEstoque frame = new TelaEstoque(u);
								frame.setLocationRelativeTo(null);
								frame.setVisible(true);
								dispose();

							}
						});
						btEstoque.setOpaque(false);
						btEstoque.setBorder(null);
						btEstoque.setBackground(new Color(154, 205, 217));
						btEstoque.setVisible(isVendedor);
						panelLeft.add(btEstoque, "cell 0 4,grow");

		lblAddProduto = new JLabel("");
		lblAddProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaEstoque estoque = new TelaEstoque(u);
				estoque.setLocationRelativeTo(null);
				estoque.setVisible(true);
				TelaCadastroProduto cadastro = new TelaCadastroProduto(null, u);
				cadastro.setLocationRelativeTo(null);
				cadastro.setVisible(true);
				dispose();
			}
		});
		lblAddProduto.setIcon(new ImageIcon(TelaInicio.class.getResource("/img/More.png")));
		ImageIcon imgM = new ImageIcon(TelaInicio.class.getResource("/img/More.png"));
		Image m = imgM.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		lblAddProduto.setIcon(new ImageIcon(m));
		lblAddProduto.setVisible(isVendedor);
		

		panel_2.add(lblAddProduto, "cell 0 8,alignx center,aligny center");

		lblAdicionarProduto = new JLabel("Adicionar Produto");
		panel_2.add(lblAdicionarProduto, "cell 0 9,alignx center,aligny center");
		lblAdicionarProduto.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 12));
		
		lblAdicionarProduto.setVisible(isVendedor);
//		if (isVendedor) {
//
//			JLabel imgMore = new JLabel("");
//			imgMore.addMouseListener(new MouseAdapter() {
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					TelaEstoque estoque = new TelaEstoque(u);
//
//					TelaCadastroProduto cadastrar = new TelaCadastroProduto(estoque, u);
//					cadastrar.setVisible(true);
//					cadastrar.setLocationRelativeTo(null);
//				}
//			});
//
//			imgMore.setIcon(new ImageIcon(TelaInicio.class.getResource("/IMG/iconMore.png")));
//			panel_2.add(imgMore, "cell 0 8,alignx center");
//			imgMore.setIcon(new ImageIcon(more));
//		} else {
//			btEstoque.setVisible(isVendedor);
//			lblAddProduto.setVisible(isVendedor);
//			lblAdicionarProduto.setVisible(isVendedor);
//		}

		String[] colunas = { "Nome", "Quantidade", "Preço" };
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}