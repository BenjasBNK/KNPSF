
package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controle.EnderecoDAO;
import controle.Imagem;
import controle.ProdutorDAO;
import controle.UsuarioDAO;
import modelo.Endereco;
import modelo.Produtor;
import modelo.RoundButton;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;
import java.awt.Insets;

public class TelaEditarPerfilVendedor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtCPF;
	private JTextField txtCNPJ;
	private JTextField txtCidade;
	private JTextField txtBairro;
	private JTextField txtLogradouro;
	private JTextField txtNum;
	private TelaEditarPerfilVendedor estajanela = this;
	private static UsuarioDAO uDAO = UsuarioDAO.getInstancia();
	private static EnderecoDAO eDAO = EnderecoDAO.getInstancia();
	private static ProdutorDAO pDAO = ProdutorDAO.getInstancia();
	private JTextField txtCelular;
	private JTextField txtCEP;
	private JTextField txtNomeComercio;
	private JLabel lblImagem;
	private JTextField txtNome;
	private JPanel panel;
	private JLabel lbleditar;
	private JLabel lblNewLabel;
	private JLabel lblEditarFt;
	private JTextField txtChavePix;
	private JPanel panel_2;
	private JLabel imgAvatar;
	BufferedImage bufferedImage;
	private static Imagem img = Imagem.getInstancia();
	Produtor produtorNovo = new Produtor();
	Endereco enderecoNovo = new Endereco();
	Usuario usuarioNovo = new Usuario();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaEditarVendedor frame = new TelaEditarVendedor();
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
	public TelaEditarPerfilVendedor(Usuario u) {
		setResizable(false);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(1176, 664));
		setMaximumSize(new Dimension(1920, 1080));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(154, 208, 217));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		ImageIcon iconEditar = new ImageIcon(TelaPerfilVendedor.class.getResource("/img/lapis.png"));
		Image iconE = iconEditar.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setOpaque(false);
		panel_1.setLayout(new MigLayout("", "[250,grow][400px,grow][grow]", "[60px][100px][][][][][][][grow]"));

		panel = new JPanel();
		panel.setBackground(new Color(154, 208, 217));
		panel_1.add(panel, "cell 0 0 3 1,grow");
		panel.setLayout(new MigLayout("", "[20px][grow]", "[grow]"));

		lbleditar = new JLabel("");
		panel.add(lbleditar, "cell 0 0");
		lbleditar.setIcon(new ImageIcon(iconE));

		lblNewLabel = new JLabel("Edição de perfil");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel, "cell 1 0");

		JLabel lblNome = new JLabel("");
		lblNome.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 30));
		panel_1.add(lblNome, "cell 1 1,alignx left,aligny bottom");
		lblNome.setText(u.getNome());

		panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_1.add(panel_2, "flowy,cell 0 2 1 5,grow");

		imgAvatar = new JLabel("");
		panel_2.add(imgAvatar);
		imgAvatar.setMinimumSize(new Dimension(100, 100)); // Garantindo tamanho mínimo para o JLabel
		imgAvatar.setPreferredSize(new Dimension(200, 200));

		txtNomeComercio = new JTextField();
		txtNomeComercio.setToolTipText("");
		txtNomeComercio.setText((String) null);
		txtNomeComercio.setOpaque(false);
		txtNomeComercio.setColumns(10);
		txtNomeComercio.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"<html>Nome do comercio<span style='color: red;'>*</span></html>", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtNomeComercio.setBackground(SystemColor.menu);
		txtNomeComercio.setText(pDAO.consultaProdutor(u).getNomeComercio());
		panel_1.add(txtNomeComercio, "cell 1 2,grow");

		txtCidade = new JTextField();
		panel_1.add(txtCidade, "flowx,cell 2 2");
		txtCidade.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtCidade.setOpaque(false);
		txtCidade.setToolTipText("");
		txtCidade.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"<html>Cidade<span style='color: red;'>*</span></html>", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		txtCidade.setBackground(SystemColor.menu);
		txtCidade.setColumns(10);
		txtCidade.setText(pDAO.consultaProdutor(u).getEnd().getCidade());

		txtBairro = new JTextField();
		panel_1.add(txtBairro, "cell 2 2");
		txtBairro.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtBairro.setOpaque(false);
		txtBairro.setToolTipText("");
		txtBairro.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"<html>Bairro<span style='color: red;'>*</span></html>", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		txtBairro.setBackground(SystemColor.menu);
		txtBairro.setColumns(10);
		txtBairro.setText(pDAO.consultaProdutor(u).getEnd().getBairro());

		txtNome = new JTextField();
		txtNome.setOpaque(false);
		txtNome.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Nome", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		txtNome.setText(u.getNome());
		panel_1.add(txtNome, "cell 1 3,growx");
		txtNome.setColumns(10);
		txtCPF = new JTextField();
		txtCPF.setDisabledTextColor(new Color(0, 0, 0));
		txtCPF.setCaretColor(new Color(0, 0, 0));
		txtCPF.setEnabled(false);
		txtCPF.setEditable(false);
		panel_1.add(txtCPF, "flowx,cell 1 4");
		txtCPF.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtCPF.setOpaque(false);
		txtCPF.setToolTipText("O cpf não pode ser alterado");
		txtCPF.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "CPF", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtCPF.setBackground(SystemColor.menu);
		txtCPF.setColumns(10);
		txtCPF.setText(u.getCpf());

		txtCEP = new JTextField();
		txtCEP.setToolTipText("");
		txtCEP.setText("0");
		txtCEP.setOpaque(false);
		txtCEP.setColumns(10);
		txtCEP.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"<html>CEP<span style='color: red;'>*</span></html>", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		txtCEP.setBackground(SystemColor.menu);
		txtCEP.setText(pDAO.consultaProdutor(u).getEnd().getCep());
		panel_1.add(txtCEP, "flowx,cell 2 4");

		txtEmail = new JTextField();
		panel_1.add(txtEmail, "flowx,cell 1 5,growy");
		txtEmail.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtEmail.setOpaque(false);
		txtEmail.setToolTipText("");
		txtEmail.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"<html>Email<span style='color: red;'>*</span></html>", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		txtEmail.setBackground(SystemColor.menu);
		txtEmail.setColumns(10);
		txtEmail.setText(u.getEmail());

		txtLogradouro = new JTextField();
		panel_1.add(txtLogradouro, "flowx,cell 2 5");
		txtLogradouro.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtLogradouro.setOpaque(false);
		txtLogradouro.setToolTipText("");
		txtLogradouro.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"<html>Logadouro<span style='color: red;'>*</span></html>", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		txtLogradouro.setBackground(SystemColor.menu);
		txtLogradouro.setColumns(10);
		txtLogradouro.setText(pDAO.consultaProdutor(u).getEnd().getLogradouro());

		lblEditarFt = new JLabel("Editar Foto de Perfil");
		lblEditarFt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					// Obtém o InputStream da imagem
					FileInputStream fis = img.Imagem();

					// Verifica se nenhuma imagem foi selecionada
					if (fis == null) {
						throw new IOException("Nenhuma imagem foi selecionada.");
					}

					bufferedImage = ImageIO.read(fis);
					usuarioNovo.setFoto(bufferedImage);
					if (bufferedImage == null) {
						throw new IOException("Falha ao carregar a imagem.");
					}

					usuarioNovo.setFotoC(fis);

					// Redimensiona a imagem dentro de invokeLater
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							// Força o layout a ser recalculado
							panel_2.revalidate();
							panel_2.repaint();

							int labelWidth = imgAvatar.getWidth();
							int labelHeight = imgAvatar.getHeight();

							// Verifica se o tamanho do JLabel é válido
							if (labelWidth > 0 && labelHeight > 0) {
								// Redimensiona a imagem para o tamanho do JLabel, mantendo a proporção
								Image scaledImage = bufferedImage.getScaledInstance(labelWidth, labelHeight,
										Image.SCALE_SMOOTH);

								// Converte a imagem redimensionada para ImageIcon
								ImageIcon icon = new ImageIcon(scaledImage);

								// Define o ícone do JLabel
								imgAvatar.setIcon(icon);
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
		lblEditarFt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEditarFt.setForeground(SystemColor.textHighlight);
		lblEditarFt.setFont(new Font("Dialog", Font.ITALIC, 13));
		panel_1.add(lblEditarFt, "cell 0 7,alignx center,aligny center");

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setMargin(new Insets(4, 14, 4, 14));
		panel_1.add(btnNewButton, "flowx,cell 2 8,alignx right,aligny bottom");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPerfilVendedor frame = new TelaPerfilVendedor(u, true, null);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(205, 92, 92));

		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.setMargin(new Insets(4, 14, 4, 14));
		btnNewButton_1.setDisplayedMnemonicIndex(0);
		panel_1.add(btnNewButton_1, "cell 2 8,alignx right,aligny bottom");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Consulta do produtor atual
				Produtor produtorAntigo = pDAO.consultaProdutor(u);

				// Validação de campos obrigatórios
				if (txtCNPJ.getText().isEmpty() || txtEmail.getText().isEmpty() || txtNomeComercio.getText().isEmpty()
						|| txtBairro.getText().isEmpty() || txtCEP.getText().isEmpty() || txtCidade.getText().isEmpty()
						|| txtLogradouro.getText().isEmpty() || txtNum.getText().isEmpty()) {

					TelaError tela = new TelaError();
					tela.setLabelText("Dados preenchidos incorretamente. Preencha todos os campos obrigatórios.");
					tela.setLocationRelativeTo(null);
					tela.setVisible(true);
				}

				// Coleta de dados do formulário
				String cnpj = txtCNPJ.getText();
				String email = txtEmail.getText();
				String nomeComercio = txtNomeComercio.getText();
				String telefone = txtCelular.getText();
				String bairro = txtBairro.getText();
				String cep = txtCEP.getText();
				String cidade = txtCidade.getText();
				String logradouro = txtLogradouro.getText();
				Integer numero = Integer.valueOf(txtNum.getText());
				String nome = txtNome.getText();
				String chavePix = txtChavePix.getText();

				if (uDAO.verificarTelefone(telefone, u.getIdUsuario())) {
					TelaError erro = new TelaError();
					erro.setLabelText("Telefone já cadastrado por outro usuário.");
					erro.setVisible(true);
					erro.setLocationRelativeTo(null);
					return; // Impede a atualização no banco
				}

				// Populando os objetos
				enderecoNovo.setBairro(bairro);
				enderecoNovo.setCep(cep);
				enderecoNovo.setCidade(cidade);
				enderecoNovo.setLogradouro(logradouro);
				enderecoNovo.setNumero(numero);

				produtorNovo.setCnpj(cnpj);
				produtorNovo.setEmail(email);
				produtorNovo.setEnd(enderecoNovo);
				produtorNovo.setNomeComercio(nomeComercio);
				produtorNovo.setTel(telefone);
				produtorNovo.setChavePix(chavePix);

				usuarioNovo.setEmail(email);
				usuarioNovo.setTel(telefone);
				usuarioNovo.setNome(nome);
				usuarioNovo.setIdUsuario(u.getIdUsuario());
				usuarioNovo.setCpf(u.getCpf());

				// Atualização ou inserção
				if (produtorAntigo != null) {
					enderecoNovo.setIdEndereco(produtorAntigo.getEnd().getIdEndereco());
					int enderecoAtualizado = eDAO.atualizarEndereco(enderecoNovo);

					if (enderecoAtualizado > 0) {
						produtorNovo.setIdUsuario(u.getIdUsuario());
						produtorNovo.setEndereco(enderecoNovo.getIdEndereco());
						boolean produtorAtualizado = pDAO.alterarProdutor(produtorNovo, usuarioNovo);

						if (produtorAtualizado) {
							uDAO.consultarUsuarioLoginSenha(usuarioNovo.getSenha(), usuarioNovo.getEmail());
							TelaPerfilVendedor v = new TelaPerfilVendedor(usuarioNovo, true, bufferedImage);
							v.setLocationRelativeTo(null);
							v.setVisible(true);
							dispose();
							TelaError tela = new TelaError();
							tela.setLabelText("Dados atualizados com sucesso!");
							tela.setLocationRelativeTo(null);
							tela.setVisible(true);
						} else {
							TelaError tela = new TelaError();
							tela.setLabelText("Erro ao atualizar os dados do produtor.");
							tela.setLocationRelativeTo(null);
							tela.setVisible(true);
						}
					} else {
						TelaError tela = new TelaError();
						tela.setLabelText("Erro ao atualizar o endereço.");
						tela.setLocationRelativeTo(null);
						tela.setVisible(true);
					}
				} else {
					int enderecoId = eDAO.inserirEnderecoDoComercio(enderecoNovo);

					if (enderecoId > 0) {
						produtorNovo.setIdUsuario(u.getIdUsuario());
						produtorNovo.setEndereco(enderecoId);
						boolean produtorInserido = pDAO.inserirProdutor(produtorNovo);

						if (produtorInserido) {
							uDAO.consultarUsuarioLoginSenha(usuarioNovo.getSenha(), usuarioNovo.getEmail());
							TelaPerfilVendedor v = new TelaPerfilVendedor(u, true, bufferedImage);
							v.setLocationRelativeTo(null);
							v.setVisible(true);
							dispose();
							TelaError tela = new TelaError();
							tela.setLabelText("Dados salvos com sucesso!");
							tela.setLocationRelativeTo(null);
							tela.setVisible(true);
						} else {
							TelaError tela = new TelaError();
							tela.setLabelText("Erro ao salvar os dados do produtor.");
							tela.setLocationRelativeTo(null);
							tela.setVisible(true);
						}
					} else {
						TelaError tela = new TelaError();
						tela.setLabelText("Erro ao salvar o endereço.");
						tela.setLocationRelativeTo(null);
						tela.setVisible(true);
					}
				}
			}

		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(2, 73, 89));

		txtCelular = new JTextField();
		txtCelular.setToolTipText("");
		txtCelular.setOpaque(false);
		txtCelular.setColumns(10);
		txtCelular.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtCelular.setOpaque(false);
		txtCelular.setToolTipText("");
		txtCelular.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Celular", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtCelular.setBackground(SystemColor.menu);
		txtCelular.setText(u.getTel());

		panel_1.add(txtCelular, "cell 1 5");

		txtCNPJ = new JTextField();
		panel_1.add(txtCNPJ, "cell 1 4");
		txtCNPJ.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtCNPJ.setOpaque(false);
		txtCNPJ.setToolTipText("");
		txtCNPJ.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"<html>CNPJ<span style='color: red;'>*</span></html>", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		txtCNPJ.setBackground(SystemColor.menu);
		txtCNPJ.setColumns(10);
		txtCNPJ.setText(pDAO.consultaProdutor(u).getCnpj());

		txtNum = new JTextField();
		panel_1.add(txtNum, "cell 2 5");
		txtNum.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtNum.setOpaque(false);
		txtNum.setToolTipText("");
		txtNum.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"<html>Num<span style='color: red;'>*</span></html>", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		txtNum.setBackground(SystemColor.menu);
		txtNum.setColumns(10);
		txtNum.setText(String.valueOf(pDAO.consultaProdutor(u).getEnd().getNumero()));

		lblImagem = new JLabel("");
		panel_1.add(lblImagem, "cell 2 4");

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
		ImageIcon iconProcurar = new ImageIcon(TelaCadastroComercio.class.getResource("/IMG/procurar.png"));
		Image iconP = iconProcurar.getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
		lblImagem.setIcon(new ImageIcon(iconP));

		txtChavePix = new JTextField();
		txtChavePix.setToolTipText("");
		txtChavePix.setText((String) null);
		txtChavePix.setOpaque(false);
		txtChavePix.setColumns(10);
		txtChavePix.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Chave pix", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtChavePix.setBackground(SystemColor.menu);
		Produtor p = pDAO.consultaProdutor(u);
		
		try {
			txtChavePix.setText(p.getChavePix());
			
		} catch (Exception e) {
			txtChavePix.setText("");
		}
		
			
		
		panel_1.add(txtChavePix, "cell 1 4");

		ImageIcon conta = new ImageIcon(TelaInicio.class.getResource("/IMG/do-utilizador.png"));
		Image iconConta = conta.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

	}
}