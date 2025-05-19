package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controle.CarrinhoDAO;
import controle.ProdutoDAO;
import controle.UsuarioDAO;
import modelo.CarrinhoCompras;
import modelo.ItemCarrinho;
import modelo.Produto;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

public class TelaCarrinho extends JFrame {

	private JPanel contentPane;
	ProdutoDAO pDAO = new ProdutoDAO();
//	CartaoDAO cDAO = new CartaoDAO();
	JPanel panelLeft;
	JButton btnEstoque;
	CarrinhoDAO cDAO = new CarrinhoDAO();
	TelaCarrinho estaJanela = this;
	JPanel panelProd;
	Boolean isVendedor;
	int carrinhoProdutor;
	CarrinhoCompras carrinho = new CarrinhoCompras();

	public static UsuarioDAO uDAO = UsuarioDAO.getInstancia();

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCarrinho frame = new TelaCarrinho(null, null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public TelaCarrinho(Usuario u, List<Produto> produtos, boolean isVendedor) {
		this.isVendedor = isVendedor;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 205, 220));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[30px][grow][]", "[30px]"));

		panelLeft = new JPanel();
		panelLeft.setBackground(new Color(154, 208, 217));
		contentPane.add(panelLeft, BorderLayout.WEST);
		panelLeft.setLayout(new MigLayout("", "[100px]", "[50px][50px][50px][50px][50px]"));

		JButton btnInicio = new JButton("Inicio");
		btnInicio.setForeground(new Color(0, 0, 0));
		btnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaInicio tiv = new TelaInicio(u, isVendedor);
				tiv.setLocationRelativeTo(null);
				tiv.setVisible(true);
				dispose();
			}
		});
		btnInicio.setBackground(new Color(154, 205, 217));
		btnInicio.setBorder(null);
		btnInicio.setOpaque(false);
		panelLeft.add(btnInicio, "cell 0 0,grow");

		JButton btnCarrinho = new JButton("Carrinho");
		btnCarrinho.setForeground(new Color(0, 0, 0));
		btnCarrinho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCarrinho.setBackground(new Color(96, 154, 168));
		btnCarrinho.setBorder(null);
		panelLeft.add(btnCarrinho, "cell 0 1,grow");

		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.setForeground(new Color(0, 0, 0));
		btnPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
		btnPerfil.setBackground(new Color(154, 205, 217));
		btnPerfil.setBorder(null);
		btnPerfil.setOpaque(true);
		panelLeft.add(btnPerfil, "cell 0 2,grow");

		btnEstoque = new JButton("Estoque");
		btnEstoque.setForeground(new Color(0, 0, 0));
		btnEstoque.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaEstoque frame = new TelaEstoque(u);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				dispose();

			}
		});
		btnEstoque.setBackground(new Color(154, 205, 217));
		btnEstoque.setBorder(null);
		btnEstoque.setVisible(isVendedor);
		panelLeft.add(btnEstoque, "cell 0 3,grow");
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaProdutos TelaProd = new TelaProdutos(u);
				TelaProd.setLocationRelativeTo(null);
				TelaProd.setVisible(true);
				dispose();
			}
		});
		btnProdutos.setOpaque(true);
		btnProdutos.setForeground(Color.BLACK);
		btnProdutos.setBorder(null);
		btnProdutos.setBackground(new Color(154, 205, 217));
		panelLeft.add(btnProdutos, "cell 0 4,grow");

		JLabel imgMenu = new JLabel("");
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
		imgMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(imgMenu, "cell 0 0,alignx left,aligny top");
		imgMenu.setIcon(new ImageIcon(TelaCarrinho.class.getResource("/IMG/menu-hamburguer.png")));
		ImageIcon menu = new ImageIcon(TelaCarrinho.class.getResource("/IMG/menu-hamburguer.png"));
		Image iconMenu = menu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imgMenu.setIcon(new ImageIcon(iconMenu));
		ImageIcon perfil = new ImageIcon(TelaCarrinho.class.getResource("/img/do-utilizador.png"));
		Image imgp = perfil.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

		JLabel imgPerfil = new JLabel("");
		imgPerfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgPerfil.addMouseListener(new MouseAdapter() {
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
		imgPerfil.setIcon(new ImageIcon(TelaCarrinho.class.getResource("/img/do-utilizador.png")));
		panel.add(imgPerfil, "cell 2 0,alignx right");
		imgPerfil.setIcon(new ImageIcon(imgp));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][]"));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelProd = new JPanel();
		panelProd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelProd.setForeground(Color.WHITE);
		panelProd.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Peixes Dispon\u00EDveis", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane.setViewportView(panelProd);
		panel_1.add(scrollPane, "cell 0 0,grow");
		panelProd.setLayout(new MigLayout("", "[][][][]", "[][][][]"));

		atualizarTela(u);
//		TelaListaCartao listaC = new TelaListaCartao(u, produtos, isVendedor, null);
//		listaC.setLocationRelativeTo(null);
//		listaC.setVisible(true);
//		dispose();

		JPanel panelBotoes = new JPanel();
		panel_1.add(panelBotoes, "cell 0 1,grow");
		panelBotoes.setLayout(new MigLayout("", "[][]", "[]"));

		JButton btnVoltaTela = new JButton("Continuar comprando");
		btnVoltaTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelBotoes.add(btnVoltaTela, "cell 0 0");
		btnVoltaTela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaInicio tela = new TelaInicio(u, isVendedor);
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);
				dispose();
			}
		});
		btnVoltaTela.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnVoltaTela.setBackground(new Color(154, 205, 217));
		btnVoltaTela.setForeground(new Color(0, 0, 0));

		JButton btnPagamento = new JButton("Concluir compra");
		panelBotoes.add(btnPagamento, "cell 1 0");
		btnPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CarrinhoCompras carrinho = cDAO.verificarSeExisteCarrinho(u, carrinhoProdutor, false);
				ArrayList<ItemCarrinho> lista = cDAO.addProdCarrinho(carrinho);
				
				TelaPagamento pagamento = new TelaPagamento(u, isVendedor, lista, carrinho);
				pagamento.setVisible(true);
				pagamento.setLocationRelativeTo(null);
			}
		});
		btnPagamento.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnPagamento.setBackground(new Color(154, 205, 217));
		btnPagamento.setForeground(new Color(0, 0, 0));
	}

	public void atualizarTela(Usuario u) {

		if (cDAO.verificarSeExisteCarrinho(u, carrinhoProdutor, false) == null) {
			System.out.println("Carrinho nao existe");
		} else {
			CarrinhoCompras carrinho = cDAO.verificarSeExisteCarrinho(u, carrinhoProdutor, false);
			ArrayList<ItemCarrinho> lista = cDAO.addProdCarrinho(carrinho);
//		ArrayList<Produto> lista = pDAO.addTodosProd();

			int linha = 0;
			int coluna = -1;
			if (lista == null) {
				System.out.println("lista ta Vazia");
			} else {

				for (ItemCarrinho p : lista) {

					coluna++;
					if (coluna > 3) {
						coluna = 0;
						linha++;
					}
					CardProdutoCarrinho panel_8 = new CardProdutoCarrinho(u, p, estaJanela, isVendedor);
					panelProd.add(panel_8, "cell " + coluna + " " + linha + "");

				}
			}
		}

	}
}
