package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controle.CarrinhoDAO;
import modelo.CarrinhoCompras;
import modelo.ItemCarrinho;
import modelo.Produto;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class CardProduto extends JPanel {

	int quantidade = 1;
	Float preco;

	public CardProduto(Produto p, Usuario u) {
		setLayout(new MigLayout("", "[20px][100px][20px]", "[][200px][][][][][]"));

		JPanel panel = new JPanel();
		add(panel, "flowy,cell 1 1,grow");

		JLabel imgPeixe = new JLabel("");
		panel.add(imgPeixe);
		// Verifica se a imagem do produto não é nula
		if (p.getFoto() != null) {
		    // Cria um ImageIcon a partir da imagem
		    ImageIcon iconFoto = new ImageIcon(p.getFoto());
		    
		    // Usa o JLabel para pegar o tamanho da área disponível
		    imgPeixe.setSize(160, 160); // Definindo um tamanho fixo para o exemplo
		    
		    // Redimensiona a imagem de acordo com o tamanho do JLabel
		    Image foto = iconFoto.getImage().getScaledInstance(imgPeixe.getWidth(), imgPeixe.getHeight(), Image.SCALE_SMOOTH);
		    
		    // Define a imagem redimensionada no JLabel
		    imgPeixe.setIcon(new ImageIcon(foto));
		} else {
		    // Se não houver foto, define uma imagem padrão
		    imgPeixe.setIcon(new ImageIcon(getClass().getResource("/img/DefaultImage.png")));
		}

		

		JLabel lblNome = new JLabel("Nome");
		add(lblNome, "flowx,cell 1 2,alignx left");
		lblNome.setText(p.getNome());

		JLabel lblQuantidade = new JLabel("1");

		JLabel lblQuantidadeEstoque = new JLabel("");
		lblQuantidadeEstoque.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblQuantidadeEstoque.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblQuantidadeEstoque, "cell 1 2,alignx right");
		lblQuantidadeEstoque.setText(Integer.toString(p.getQuantidadeEstoque()));

		JLabel lblCifra = new JLabel("R$");
		add(lblCifra, "flowx,cell 1 4");

		JLabel lblPreco = new JLabel("");
		add(lblPreco, "cell 1 4");
		lblPreco.setText(String.valueOf(p.getPreco()));

		JLabel imgMenos = new JLabel("");
		imgMenos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				quantidade = Integer.parseInt(lblQuantidade.getText());
				if (quantidade > 0) {
					quantidade--;
					if (quantidade != 0) {

						lblQuantidade.setText(Integer.toString(quantidade));
					} else {
						lblQuantidade.setText("1");
					}
				} else if (quantidade == 0) {
					lblQuantidade.setText("1");
				}
			}
		});
		imgMenos.setIcon(new ImageIcon(CardProduto.class.getResource("/img/Menos.png")));
		add(imgMenos, "flowx,cell 1 6");
		ImageIcon imgMe = new ImageIcon(CardProduto.class.getResource("/img/Menos.png"));
		Image me = imgMe.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imgMenos.setIcon(new ImageIcon(me));

		add(lblQuantidade, "cell 1 6");

		JLabel imgMais = new JLabel("");
		imgMais.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				quantidade = Integer.parseInt(lblQuantidade.getText());
				if (quantidade >= p.getQuantidadeEstoque()) {
					quantidade = p.getQuantidadeEstoque();
				} else {
					quantidade++;
				}
				lblQuantidade.setText(String.valueOf(quantidade));
			}
		});
		imgMais.setIcon(new ImageIcon(CardProduto.class.getResource("/img/More.png")));
		add(imgMais, "cell 1 6");
		ImageIcon imgM = new ImageIcon(CardProduto.class.getResource("/img/More.png"));
		Image m = imgM.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imgMais.setIcon(new ImageIcon(m));

		JButton btAdicionar = new JButton("Adicionar");
		btAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarrinhoDAO cdao = new CarrinhoDAO();
				CarrinhoCompras c;

				// Verificar se já existe um carrinho do usuário para o produtor do produto a
				// ser adicionado
				c = cdao.verificarSeExisteCarrinho(u, p.getIdProdutor(), false);

				// Se já existe um carrinho para o produtor do produto, então tenta adicionar o
				// produto
				if (c != null) {
					// Verifica se o produtor do produto a ser adicionado é o mesmo dos itens no
					// carrinho
					boolean produtorDiferente = false;
					ArrayList<ItemCarrinho> itensCarrinho = cdao.addProdCarrinho(c);

					for (ItemCarrinho item : itensCarrinho) {
						if (item.getProduto().getIdProdutor() != p.getIdProdutor()) {
							produtorDiferente = true;
							break;
						}
					}

					if (produtorDiferente) {
						// Se os produtores são diferentes, exibe a mensagem de erro
						TelaError erro = new TelaError();
						erro.setLabelText("Não é possível adicionar itens de produtores diferentes ao carrinho!");
						erro.setVisible(true);
						erro.setLocationRelativeTo(null);
					} else {
						// Caso contrário, o produto pode ser adicionado ao carrinho
						if (cdao.verificarProdutoNoCarrinho(c, p)) {
							TelaError erro = new TelaError();
							erro.setLabelText("Este produto já está no seu carrinho!");
							erro.setVisible(true);
							erro.setLocationRelativeTo(null);
						} else {
							preco = p.getPreco();
							preco = preco * quantidade;

							cdao.inserirProduto(p, quantidade, preco, c);
							TelaError erro = new TelaError();
							erro.setLabelText("Produto adicionado ao carrinho");
							erro.setLocationRelativeTo(null);
							erro.setVisible(true);
						}
					}
				} else {
					// Se não houver carrinho para o produtor, cria um novo carrinho com o produtor
					preco = p.getPreco();
					preco = preco * quantidade;

					cdao.inserirProduto(p, quantidade, preco, cdao.criarCarrinho(u, p.getIdProdutor()));
					TelaError erro = new TelaError();
					erro.setLabelText("Produto adicionado ao carrinho");
					erro.setLocationRelativeTo(null);
					erro.setVisible(true);
				}
			}
		});

		btAdicionar.setForeground(Color.WHITE);
		btAdicionar.setBackground(new Color(2, 73, 89));
		add(btAdicionar, "cell 1 6");

		JLabel lblUnidades = new JLabel("un");
		lblUnidades.setFont(new Font("Tahoma", Font.PLAIN, 9));
		add(lblUnidades, "cell 1 2");

	}
}
