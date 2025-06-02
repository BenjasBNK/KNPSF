package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controle.Imagem;
import controle.ProdutoDAO;
import modelo.Produto;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;

public class TelaAlterarItens extends JFrame {
	private JTextField txtValidade;
	private JTextField txtPreco;
	private JTextField txtQuantidade;
	private JLabel lblImagem;
	private JComboBox cbProdutos;
	private ProdutoDAO pDAO = ProdutoDAO.getInstancia();
	private FileInputStream fis;
	private static Imagem img = Imagem.getInstancia();
	Produto prod = new Produto();
	public static ArrayList<Produto> listaProdutos;
		
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaAlterarProduto frame = new TelaAlterarProduto();
//					frame.setLocationRelativeTo(null);
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
	public TelaAlterarItens(Produto oprod, TelaEstoque estaJanela, Usuario u) {

		setTitle("Cadastro de produto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroComercio.class.getResource("/img/logo.png")));
		setResizable(false);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(0, 0));
		setMaximumSize(new Dimension(1920, 1080));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelBackground = new JPanel();
		panelBackground.setBackground(new Color(255, 255, 255));
		getContentPane().add(panelBackground, BorderLayout.CENTER);
		panelBackground.setLayout(new MigLayout("", "[grow]", "[100px][300px,grow][100px]"));

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(154, 205, 217));
		panelBackground.add(panelTitulo, "cell 0 0,grow");
		panelTitulo.setLayout(new MigLayout("", "[grow]", "[grow]"));

		JLabel lblTitulo = new JLabel("Alterar Itens");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 38));
		panelTitulo.add(lblTitulo, "cell 0 0,alignx center,aligny center");

		JPanel panelInformacoes = new JPanel();
		panelInformacoes.setOpaque(false);
		panelBackground.add(panelInformacoes, "cell 0 1,grow");
		panelInformacoes.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panelEsquerda = new JPanel();
		panelEsquerda.setOpaque(false);
		panelEsquerda.setBorder(new EmptyBorder(0, 60, 0, 40));
		panelInformacoes.add(panelEsquerda);
		panelEsquerda
				.setLayout(new MigLayout("", "[grow]", "[50px][30px,grow][50px][30px,grow][50px][30px,grow][50px]"));
		 
		listaProdutos = ProdutoDAO.getInstancia().addTodosProd();

		cbProdutos = new JComboBox<>();

		// Supondo que listaProdutos já esteja carregada em algum lugar
		for (Produto p : listaProdutos) {
		    cbProdutos.addItem(p);
		}
		

		// Preenche com os objetos Produto

		
		cbProdutos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEsquerda.add(cbProdutos, "cell 0 0,growx,aligny center");

		txtValidade = new JTextField();
		txtValidade.setOpaque(false);
		txtValidade.setToolTipText("");
		txtValidade.setColumns(10);
		txtValidade.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"<html>Validade<span style='color: red;'>*</span></html>", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		txtValidade.setBackground(SystemColor.menu);
		panelEsquerda.add(txtValidade, "cell 0 2,grow");

		txtQuantidade = new JTextField();
		txtQuantidade.setToolTipText("");
		txtQuantidade.setOpaque(false);
		txtQuantidade.setColumns(10);
		txtQuantidade.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"<html>Quantidade<span style='color: red;'>*</span></html>", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		txtQuantidade.setBackground(SystemColor.menu);
		panelEsquerda.add(txtQuantidade, "cell 0 4,grow");

		txtPreco = new JTextField();
		txtPreco.setToolTipText("");
		txtPreco.setOpaque(false);
		txtPreco.setColumns(10);
		txtPreco.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"<html>Preco<span style='color: red;'>*</span></html>", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		txtPreco.setBackground(SystemColor.menu);
		panelEsquerda.add(txtPreco, "cell 0 6,grow");

		JPanel panelDireita = new JPanel();
		panelDireita.setOpaque(false);
		panelDireita.setBorder(new EmptyBorder(5, 40, 0, 60));
		panelInformacoes.add(panelDireita);
		panelDireita.setLayout(new MigLayout("", "[grow]", "[200px][25px][40px]"));

		JPanel panelImage = new JPanel();
		panelImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelImage.setOpaque(false);
		panelDireita.add(panelImage, "cell 0 0,grow");
		panelImage.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		lblImagem = new JLabel();
		lblImagem.setMinimumSize(new Dimension(100, 100)); // Garantindo tamanho mínimo para o JLabel
		lblImagem.setPreferredSize(new Dimension(200, 200));
		panelImage.add(lblImagem);

		JLabel lblAdcImagem = new JLabel("Adicionar Imagem");
		lblAdcImagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					// Obtém o InputStream da imagem
					fis = img.Imagem();

					// Verifica se nenhuma imagem foi selecionada
					if (fis == null) {
						throw new IOException("Nenhuma imagem foi selecionada.");
					}

					BufferedImage bufferedImage = ImageIO.read(fis);
					prod.setFotoC(fis);

					// Redimensiona a imagem dentro de invokeLater
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							// Força o layout a ser recalculado
							panelImage.revalidate();
							panelImage.repaint();

							int labelWidth = lblImagem.getWidth();
							int labelHeight = lblImagem.getHeight();

							// Verifica se o tamanho do JLabel é válido
							if (labelWidth > 0 && labelHeight > 0) {
								// Redimensiona a imagem para o tamanho do JLabel, mantendo a proporção
								Image scaledImage = bufferedImage.getScaledInstance(labelWidth, labelHeight,
										Image.SCALE_SMOOTH);

								// Converte a imagem redimensionada para ImageIcon
								ImageIcon icon = new ImageIcon(scaledImage);

								// Define o ícone do JLabel
								lblImagem.setIcon(icon);
							} else {
								// Caso o tamanho ainda seja inválido, talvez um tamanho mínimo seja necessário
								System.out.println("Tamanho inválido do JLabel.");
							}
						}
					});

				} catch (IOException ex) {
					ex.printStackTrace();

					// Exibir erro ao usuário
					TelaError erro = new TelaError();
					erro.setLabelText("Erro ao carregar imagem. Nenhuma imagem foi selecionada.");
					erro.setLocationRelativeTo(null);
					erro.setVisible(true);
				}
			}
		});
		lblAdcImagem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdcImagem.setBorder(null);
		lblAdcImagem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdcImagem.setForeground(new Color(0, 128, 255));
		panelDireita.add(lblAdcImagem, "cell 0 1,alignx right");

		ButtonGroup g = new ButtonGroup();

		JPanel panelBotoes = new JPanel();
		panelBotoes.setOpaque(false);
		panelBackground.add(panelBotoes, "cell 0 2,grow");
		panelBotoes.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panelAdicionar = new JPanel();
		panelAdicionar.setOpaque(false);
		panelBotoes.add(panelAdicionar);
		panelAdicionar.setLayout(new MigLayout("", "[230px][130px]", "[5px][30px,grow][5px]"));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setMargin(new Insets(4, 14, 4, 14));
		btnCancelar.setFont(new Font("Dialog", Font.PLAIN, 18));
		panelAdicionar.add(btnCancelar, "flowx,cell 1 1,growx,aligny center");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setBackground(new Color(205, 92, 92));
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBorderPainted(false);

		JPanel panelCancelar = new JPanel();
		panelCancelar.setOpaque(false);
		panelBotoes.add(panelCancelar);
		panelCancelar.setLayout(new MigLayout("", "[130px][grow]", "[5px][30px,grow][5px]"));

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setMargin(new Insets(4, 14, 4, 14));
		btnAlterar.setFont(new Font("Dialog", Font.PLAIN, 18));
		panelCancelar.add(btnAlterar, "cell 0 1,growx,aligny center");
		btnAlterar.setText("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Produto oriProd = new Produto();

				oriProd = oprod;

	
				String validadeStr = txtValidade.getText();
				String precoStr = txtPreco.getText();
				String quantidadeStr = txtQuantidade.getText();
	
				// Verificação de campos vazios
				if (validadeStr.isEmpty() || precoStr.isEmpty() || quantidadeStr.isEmpty()) {
					TelaError erro = new TelaError();
					erro.setLabelText("Campos inseridos incorretamente");
					erro.setLocationRelativeTo(null);
					erro.setVisible(true);
					return;
				}

				Float preco;
				int quantidade;
				LocalDate validade;
				try {
					preco = Float.parseFloat(precoStr.replace(",","."));
					quantidade = Integer.parseInt(quantidadeStr);
					// Converter validadeStr para LocalDate
					validade = LocalDate.parse(validadeStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				} catch (NumberFormatException | DateTimeParseException ex) {
					TelaError erro = new TelaError();
					erro.setLabelText("Preço, quantidade ou validade inválidos");
					erro.setLocationRelativeTo(null);
					erro.setVisible(true);
					return;
				}

				// Verificação de valores negativos
				if (preco < 0 || quantidade < 0) {
					TelaError erro = new TelaError();
					erro.setLabelText("Preço e quantidade devem ser positivos");
					erro.setLocationRelativeTo(null);
					erro.setVisible(true);
					return;
				}

				// Preenchendo os atributos do produto

				prod.setQuantidadeEstoque(quantidade);
				prod.setPreco(preco);
				prod.setValidade(validade); // Descomente se necessário
	

				if (pDAO.atualizarProduto(oriProd, prod, u)) {
					estaJanela.atualizarTabela(u, null);
					dispose();
					TelaError erro = new TelaError();
					erro.setLabelText("Alterado com sucesso");
					erro.setLocationRelativeTo(null);
					erro.setVisible(true);
				} else {
					TelaError erro = new TelaError();
					erro.setLabelText("Erro ao alterar");
					erro.setLocationRelativeTo(null);
					erro.setVisible(true);
				}
			}
		});

		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setBackground(new Color(2, 73, 89));
		btnAlterar.setForeground(new Color(255, 255, 255));
		btnAlterar.setBorderPainted(false);
	}

	public void mostrarDados(Produto produtoSelecionado) {
	    LocalDate validade = produtoSelecionado.getValidade();
	    DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    String formattedDate = validade.format(desiredFormatter);

	/*    if (produtoSelecionado.getSalinidade() != null && produtoSelecionado.getSalinidade()) {
	        rdbtnDoce.setSelected(true);
	    } else {
	        rdbtnSalgada.setSelected(true);
	    }
		
	    cbProdutos.setText(produtoSelecionado.getNome());*/
	    txtQuantidade.setText(String.valueOf(produtoSelecionado.getQuantidadeEstoque()));
	    txtValidade.setText(formattedDate);
	    txtPreco.setText(String.valueOf(produtoSelecionado.getPreco()));

	    // Obtendo a foto diretamente do Produto
	    Image foto = produtoSelecionado.getFoto(); 

	    if (foto != null) {
	        // Redimensiona a imagem para o tamanho do JLabel
	        Image scaledImage = foto.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	        lblImagem.setIcon(new ImageIcon(scaledImage)); // Define a imagem no JLabel
	        System.out.println("Foto encontrada e carregada.");
	    } else {
	        lblImagem.setIcon(null); // Limpa a imagem se não existir
	        System.out.println("Foto não encontrada.");
	    }
	}

}
