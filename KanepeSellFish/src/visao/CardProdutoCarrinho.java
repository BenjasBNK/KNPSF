package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import controle.CarrinhoDAO;
import modelo.ItemCarrinho;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class CardProdutoCarrinho extends JPanel {
	private static CarrinhoDAO cDAO = CarrinhoDAO.getInstancia();
	JLabel lblQuantidade = null;
	

	public CardProdutoCarrinho(Usuario u, ItemCarrinho p, TelaCarrinho telaCarrinho, Boolean isVendedor) {
		setBorder(new LineBorder(new Color(0, 0, 0)));

		ImageIcon imgMe = new ImageIcon(CardProduto.class.getResource("/img/Menos.png"));
		Image me = imgMe.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon imgM = new ImageIcon(CardProduto.class.getResource("/img/More.png"));
		Image m = imgM.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		setLayout(new MigLayout("", "[180px][20px,grow]", "[160px]"));
		
		JPanel panel_2 = new JPanel();
		add(panel_2, "flowx,cell 0 0,grow");
		
		JLabel imgPeixe = new JLabel("");
		panel_2.add(imgPeixe);

		imgPeixe.setPreferredSize(new java.awt.Dimension(160, 160));

		System.out.println("Ajustando tamanho do JLabel para 160x160");

		// Verifica se o produto tem uma foto associada
		if (p.getProduto().getFoto() != null) {
		    System.out.println("Produto tem foto. Carregando imagem...");

		    // Usamos invokeLater para garantir que o layout seja calculado antes de redimensionar a imagem
		    SwingUtilities.invokeLater(() -> {
		        // Cria um ImageIcon a partir da foto do produto
		        ImageIcon iconFoto = new ImageIcon(p.getProduto().getFoto());
		        System.out.println("Imagem do produto carregada com sucesso.");

		        // Redimensiona a imagem para caber nas dimensões do JLabel
		        Image foto = iconFoto.getImage().getScaledInstance(imgPeixe.getWidth(), imgPeixe.getHeight(), Image.SCALE_SMOOTH);
		        System.out.println("Imagem redimensionada para " + imgPeixe.getWidth() + "x" + imgPeixe.getHeight());

		        // Define a imagem redimensionada como ícone do JLabel
		        imgPeixe.setIcon(new ImageIcon(foto));
		        System.out.println("Imagem definida no JLabel.");
		    });
		} 
		// Caso o produto não tenha uma foto associada
		else {
		    System.out.println("Produto não tem foto. Usando imagem padrão.");

		    // Usamos invokeLater para garantir que o layout seja calculado antes de redimensionar a imagem
		    SwingUtilities.invokeLater(() -> {
		        // Caso não haja foto, carregamos uma imagem padrão
		        ImageIcon iconFoto = new ImageIcon(CardProduto.class.getResource("/img/ttilapia.jpg"));
		        System.out.println("Imagem padrão carregada.");

		        // Redimensiona a imagem padrão para o tamanho do JLabel
		        Image foto = iconFoto.getImage().getScaledInstance(imgPeixe.getWidth(), imgPeixe.getHeight(), Image.SCALE_SMOOTH);
		        System.out.println("Imagem padrão redimensionada para " + imgPeixe.getWidth() + "x" + imgPeixe.getHeight());

		        // Define a imagem redimensionada como ícone do JLabel
		        imgPeixe.setIcon(new ImageIcon(foto));
		        System.out.println("Imagem padrão definida no JLabel.");
		    });
		}

		JPanel panel = new JPanel();
		add(panel, "cell 1 0,grow");
		panel.setLayout(new MigLayout("", "[grow][]", "[30px,bottom][20px,center][]"));
		JLabel lblNome = new JLabel(p.getProduto().getNome());
		lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblNome, "flowx,cell 0 0");

		JLabel btExcluir = new JLabel("");
		btExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				PopupExcluirCarrinho excluir = new PopupExcluirCarrinho(u, p, telaCarrinho, isVendedor);
				excluir.setVisible(true);
				excluir.setLocationRelativeTo(null);

			}
		});
		panel.add(btExcluir, "cell 1 0,alignx center,aligny top");
		ImageIcon lixo = new ImageIcon(TelaInicio.class.getResource("/img/lixo.png"));
		Image iconLixo = lixo.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btExcluir.setIcon(new ImageIcon(iconLixo));

		JLabel lblPreco = new JLabel(String.valueOf(p.getPrecoTotal()));
		lblPreco.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(lblPreco, "cell 0 1");

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 0 2,grow");
		panel_1.setLayout(new MigLayout("", "[][][]", "[]"));

		JLabel imgMenos = new JLabel("");
		panel_1.add(imgMenos, "cell 0 0");
		imgMenos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int quantidade = Integer.parseInt(lblQuantidade.getText());
				if (quantidade > 1) {
					quantidade = quantidade - 1;
					
				} else if (quantidade == 0) {
					quantidade = 1;
				}
				lblQuantidade.setText(Integer.toString(quantidade));
				cDAO.atualizarQuantidade(p, p.getProduto(), quantidade);
			}
		});
		imgMenos.setIcon(new ImageIcon(CardProduto.class.getResource("/img/Menos.png")));
		imgMenos.setIcon(new ImageIcon(me));
		lblQuantidade = new JLabel(String.valueOf(p.getQuantidade()));
		lblQuantidade.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_1.add(lblQuantidade, "cell 1 0");

		JLabel imgMais = new JLabel("");
		panel_1.add(imgMais, "cell 2 0");
		imgMais.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int quantidade = Integer.parseInt(lblQuantidade.getText());
				if (quantidade >= p.getProduto().getQuantidadeEstoque()) {
					quantidade = p.getProduto().getQuantidadeEstoque();
				} else {
					quantidade++;
				}
				lblQuantidade.setText(Integer.toString(quantidade));
				cDAO.atualizarQuantidade(p, p.getProduto(), quantidade);
			}
		});
		imgMais.setIcon(new ImageIcon(CardProduto.class.getResource("/img/More.png")));
		imgMais.setIcon(new ImageIcon(m));

	}
}
