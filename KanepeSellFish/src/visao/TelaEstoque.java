package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controle.ProdutoDAO;
import controle.UsuarioDAO;
import modelo.Produto;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

public class TelaEstoque extends JFrame {

	private static final long serialVersionUID = 3710277884173399876L;
	private JPanel contentPane;
	TelaEstoque estaJanela = this;
	public static ArrayList<Produto> listaProdutos;
	private static ProdutoDAO pDAO = ProdutoDAO.getInstancia();
	private static UsuarioDAO uDAO = UsuarioDAO.getInstancia();
	private JPanel panelTop;
	private JLabel imgMenu;
	private JPanel panelTabelaProdutos;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JPanel panel_3;
	private JButton btnAdicionar;
	private JButton btnNewButton;
	private JButton btnEditar;
	private JPanel panelMenu;
	private JLabel lblNewLabel;
	List<Produto> produtos;
	private JPanel panel;
	private JLabel lblFiltroNome;
	private JTextField txtFiltro;
	private JLabel lblImagem;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaEstoque frame = new TelaEstoque();
//					frame.setLocationRelativeTo(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public TelaEstoque(Usuario u) {
		setResizable(false);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(1176, 664));
		setMaximumSize(new Dimension(1920, 1080));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
//		panelTabelaProdutos.add(scrollPane, "cell 0 0,alignx center");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Nome", "QuantidadeEstoque:", "Validade", "Salinidade", "Pre\u00E7o" }));
		table.setBorder(null);
		scrollPane.setViewportView(table);

		panelTop = new JPanel();
		panelTop.setBackground(new Color(154, 205, 217));
		contentPane.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new MigLayout("", "[][2000px][][][]", "[grow]"));

		imgMenu = new JLabel("");
		imgMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (panelMenu.isVisible()) {
					panelMenu.setVisible(false);
				} else {
					panelMenu.setVisible(true);
				}
			}
		});
		imgMenu.setIcon(new ImageIcon(TelaEstoque.class.getResource("/img/menu-hamburguer.png")));
		panelTop.add(imgMenu, "cell 0 0,alignx trailing");
		ImageIcon menu = new ImageIcon(TelaInicio.class.getResource("/IMG/menu-hamburguer.png"));
		Image iconMenu = menu.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imgMenu.setIcon(new ImageIcon(iconMenu));
		ImageIcon carrinho = new ImageIcon(TelaInicio.class.getResource("/IMG/carrinho-de-compras.png"));
		Image imgC = carrinho.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		
		JLabel imgConta = new JLabel("");
		imgConta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgConta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaPerfilVendedor v = new TelaPerfilVendedor(u, true, null);
				v.setLocationRelativeTo(null);
				v.setVisible(true);
				dispose();

			}
		});

		JLabel imgCarrinho = new JLabel("");
		imgCarrinho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCarrinho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TelaCarrinho carrinho = new TelaCarrinho(u, produtos, true);
				carrinho.setLocationRelativeTo(null);
				carrinho.setVisible(true);
				dispose();

			}
		});
		imgCarrinho.setIcon(new ImageIcon(TelaInicio.class.getResource("/IMG/carrinho-de-compras.png")));
		panelTop.add(imgCarrinho, "cell 3 0");
		imgCarrinho.setIcon(new ImageIcon(imgC));
		imgConta.setIcon(new ImageIcon(TelaInicio.class.getResource("/IMG/do-utilizador.png")));
		panelTop.add(imgConta, "cell 4 0");
		ImageIcon conta = new ImageIcon(TelaInicio.class.getResource("/IMG/do-utilizador.png"));
		Image iconConta = conta.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imgConta.setIcon(new ImageIcon(iconConta));

		panelTabelaProdutos = new JPanel();
		panelTabelaProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelTabelaProdutos.setOpaque(false);
		contentPane.add(panelTabelaProdutos, BorderLayout.CENTER);
		panelTabelaProdutos.setLayout(new MigLayout("", "[grow]", "[center][50px][grow][]"));

		lblNewLabel = new JLabel("ESTOQUE");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 27));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelTabelaProdutos.add(lblNewLabel, "cell 0 0,alignx center,aligny center");
		
		panel = new JPanel();
		panel.setOpaque(false);
		panelTabelaProdutos.add(panel, "cell 0 1,grow");
		panel.setLayout(new MigLayout("", "[][400px][]", "[]"));
		
		lblFiltroNome = new JLabel("Nome:");
		lblFiltroNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel.add(lblFiltroNome, "cell 0 0,alignx left,aligny center");
		
		txtFiltro = new JTextField();
		txtFiltro.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(txtFiltro, "flowx,cell 1 0,grow");
		txtFiltro.setColumns(100);
		
		ImageIcon iconProcurar = new ImageIcon(TelaCadastroComercio.class.getResource("/IMG/procurar.png"));
		Image iconP = iconProcurar.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		
		lblImagem = new JLabel("");
		panel.add(lblImagem, "cell 2 0,alignx left,aligny center");
		lblImagem.setToolTipText("Filtrar");
		lblImagem.setName("");
		lblImagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String filtro = txtFiltro.getText();
				if(filtro.isEmpty()||filtro==null) {
					atualizarTabela(u, null);
				}else {
					atualizarTabela(u, filtro);
				}
					
			}
		});
		txtFiltro.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            String filtro = txtFiltro.getText();
		            if (filtro.isEmpty() || filtro == null) {
		                atualizarTabela(u, null);
		            } else {
		                atualizarTabela(u, filtro);
		            }
		        }
		    }
		});
		lblImagem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImagem.setIcon(new ImageIcon(TelaCadastroComercio.class.getResource("/img/procurar.png")));
		lblImagem.setIcon(new ImageIcon(iconP));

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setOpaque(false);
		panelTabelaProdutos.add(scrollPane_1, "cell 0 2,grow");

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(new Color(2, 73, 89));
		table.setBackground(new Color(255, 255, 255));
		table.setOpaque(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Nome", "Quantidade", "Validade", "Salinidade", "Pre\u00E7o" }));
		table.getTableHeader().setBackground(Color.BLACK);
		scrollPane_1.setViewportView(table);

		panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panelTabelaProdutos.add(panel_3, "cell 0 3,alignx right,growy");

		btnEditar = new JButton(" Editar Produtos");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicaoSelecionada = -1;
				posicaoSelecionada = table.getSelectedRow();
				if (posicaoSelecionada != -1) {

					Produto produtoSelecionado = ProdutoDAO.listaProdutos.get(posicaoSelecionada);
					TelaAlterarProduto novaJanela = new TelaAlterarProduto(produtoSelecionado, estaJanela, u);
					novaJanela.mostrarDados(produtoSelecionado);
					novaJanela.setLocationRelativeTo(null);
					novaJanela.setVisible(true);
				} else {
					TelaError erro = new TelaError();
					erro.setLabelText("Selecione um produto");
					erro.setLocationRelativeTo(null);
					erro.setVisible(true);
				}

			}
		});

		btnEditar.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnEditar.setBackground(new Color(154, 205, 217));
		btnEditar.setForeground(new Color(0, 0, 0));

		btnNewButton = new JButton("Excluir Produto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicaoSelecionada = -1;
				posicaoSelecionada = table.getSelectedRow();
				if (posicaoSelecionada != -1) {
					Produto produtoSelecionado = ProdutoDAO.listaProdutos.get(posicaoSelecionada);

					PopupExcluir erro = new PopupExcluir(produtoSelecionado, estaJanela, u);
					erro.setLocationRelativeTo(null);
					erro.setVisible(true);
				} else {
					TelaError erro = new TelaError();
					erro.setLabelText("Selecione um produto");
					erro.setLocationRelativeTo(null);
					erro.setVisible(true);
				}

				atualizarTabela(u, null);
			}
		});
		panel_3.add(btnNewButton, "flowx,cell 0 8,alignx right");
		btnNewButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnNewButton.setBackground(new Color(205, 92, 92));
		btnNewButton.setForeground(new Color(255, 255, 255));

		panel_3.add(btnEditar, "cell 0 8,alignx right");

		btnAdicionar = new JButton("Adicionar Produto");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaCadastroProduto addProd = new TelaCadastroProduto(estaJanela, u);
				addProd.setLocationRelativeTo(null);
				addProd.setVisible(true);

			}
		});
		panel_3.add(btnAdicionar, "cell 0 8,alignx trailing");

		btnAdicionar.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnAdicionar.setBackground(new Color(154, 205, 217));
		btnAdicionar.setForeground(new Color(0, 0, 0));

		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(154, 208, 217));
		contentPane.add(panelMenu, BorderLayout.WEST);
		panelMenu.setLayout(new MigLayout("", "[100px]", "[50px][50px][50px][50px]"));

		JButton btnNewButton = new JButton("Inicio");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		btnNewButton.setBackground(new Color(154, 205, 217));
		btnNewButton.setBorder(null);
		btnNewButton.setOpaque(false);
		panelMenu.add(btnNewButton, "cell 0 0,grow");

		JButton btnNewButton_1 = new JButton("Carrinho");
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCarrinho carrinho = new TelaCarrinho(u, null, true);
				carrinho.setLocationRelativeTo(null);
				carrinho.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(154, 205, 217));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setOpaque(false);
		panelMenu.add(btnNewButton_1, "cell 0 1,grow");

		JButton btnNewButton_2 = new JButton("Perfil");
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaPerfilVendedor frame = new TelaPerfilVendedor(u, true, null);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				dispose();

			}
		});
		btnNewButton_2.setBackground(new Color(154, 205, 217));
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setOpaque(false);
		panelMenu.add(btnNewButton_2, "cell 0 2,grow");

		JButton btnNewButton_3 = new JButton("Estoque");
		btnNewButton_3.setForeground(new Color(0, 0, 0));
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewButton_3.setBackground(new Color(96, 154, 168));
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setOpaque(true);
		panelMenu.add(btnNewButton_3, "cell 0 3,grow");

		
		
		try {
			
			String filtro = txtFiltro.getText();
			if(filtro.isEmpty()||filtro==null) {
				atualizarTabela(u, null);
			}else {
				atualizarTabela(u, filtro);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void atualizarTabela(Usuario u, String filtro) {

		
		if(filtro==null) {
			listaProdutos = pDAO.addListaProd(u);
		}else {
			listaProdutos = pDAO.addListaProdFiltro(u, filtro);
		}

		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Nome", "QuantidadeEstoque", "Validade", "Salinidade", "Pre\u00E7o" });

		for (Produto produto : listaProdutos) {
			LocalDate validade = produto.getValidade();
			DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formattedDate = validade.format(desiredFormatter);
			String salinidade;

			if (produto.getSalinidade() != null && produto.getSalinidade() == true) {
				salinidade = "Doce";
			} else {
				salinidade = "Salgada";
			}

			tableModel.addRow(new Object[] { produto.getNome(), produto.getQuantidadeEstoque(), formattedDate,
					salinidade, produto.getPreco() });
		}

		table.setModel(tableModel);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, renderer);

		// Alterando a cor do título da coluna
		table.getTableHeader().setBackground(new Color(2, 73, 89)); // Cor #024959
		table.getTableHeader().setForeground(Color.WHITE); // Cor do texto do cabeçalho

		// Alterando a cor do item selecionado
		table.setSelectionBackground(new Color(96, 154, 168)); // Cor #609AA8

		// Alterando a cor de fundo das células não selecionadas
		table.setBackground(new Color(240, 240, 240)); // Cor suave para o fundo das células

		// Cor da grade da tabela
		table.setGridColor(new Color(200, 200, 200)); // Cor da grade (borda das células)

	}

}
