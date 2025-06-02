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

import Atxy2k.CustomTextField.RestrictedTextField;
import controle.Imagem;
import controle.UsuarioDAO;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;

public class TelaEditarPerfilCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	UsuarioDAO udao = UsuarioDAO.getInstancia();
	TelaEditarPerfilCliente estajanela = this;
	private JTextField txtNome;
	private JTextField txtEmail;
	BufferedImage bufferedImage;
	private static Imagem img = Imagem.getInstancia();
	Usuario usuarioNovo = new Usuario();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaEditarPerfilCliente frame = new TelaEditarPerfilCliente();
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
	public TelaEditarPerfilCliente(Usuario u, boolean isVendedor) {
		setResizable(false);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(1176, 664));
		setMaximumSize(new Dimension(1920, 1080));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		ImageIcon iconEditar = new ImageIcon(TelaPerfilVendedor.class.getResource("/img/lapis.png"));
		Image iconE = iconEditar.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setOpaque(false);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[250,grow][450px,grow][grow]", "[60px][80][70][][][][][grow]"));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 208, 217));
		panel_1.add(panel, "cell 0 0 3 1,grow");
		panel.setLayout(new MigLayout("", "[20px][grow]", "[grow]"));

		JLabel lbleditar = new JLabel("");
		lbleditar.setIcon(new ImageIcon(TelaEditarPerfilCliente.class.getResource("/img/lapis.png")));
		panel.add(lbleditar, "cell 0 0");
		lbleditar.setIcon(new ImageIcon(iconE));

		JLabel lblNewLabel = new JLabel("Edição de perfil");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel, "flowx,cell 1 0,alignx left,aligny center");

		JPanel painelImagemProd = new JPanel();
		painelImagemProd.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		painelImagemProd.setOpaque(false);
		panel_1.add(painelImagemProd, "flowx,cell 0 2 1 3,grow");

		JLabel imgPerfil = new JLabel("");
		painelImagemProd.add(imgPerfil);
		imgPerfil.setMinimumSize(new Dimension(100, 100)); // Garantindo tamanho mínimo para o JLabel
		imgPerfil.setPreferredSize(new Dimension(200, 200)); // Tamanho preferido

		JLabel lblNome = new JLabel((String) null);
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 30));
		panel_1.add(lblNome, "cell 1 2");
		lblNome.setText(u.getNome());

		txtNome = new JTextField();
		panel_1.add(txtNome, "flowx,cell 1 3,growx,aligny center");
		txtNome.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Nome", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		txtNome.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 30));

		txtNome.setOpaque(false);
		txtNome.setColumns(10);
		txtNome.setText(u.getNome());

		txtEmail = new JTextField();
		panel_1.add(txtEmail, "cell 1 4,growx,aligny center");
		txtEmail.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtEmail.setOpaque(false);
		txtEmail.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "email", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtEmail.setText(String.valueOf(u.getEmail()));

		JLabel lblEditarFt = new JLabel("Editar Foto de Perfil");
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
							painelImagemProd.revalidate();
							painelImagemProd.repaint();

							int labelWidth = imgPerfil.getWidth();
							int labelHeight = imgPerfil.getHeight();

							// Verifica se o tamanho do JLabel é válido
							if (labelWidth > 0 && labelHeight > 0) {
								// Redimensiona a imagem para o tamanho do JLabel, mantendo a proporção
								Image scaledImage = bufferedImage.getScaledInstance(labelWidth, labelHeight,
										Image.SCALE_SMOOTH);

								// Converte a imagem redimensionada para ImageIcon
								ImageIcon icon = new ImageIcon(scaledImage);

								// Define o ícone do JLabel
								imgPerfil.setIcon(icon);
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
		panel_1.add(lblEditarFt, "cell 0 5,alignx center,aligny center");

		JLabel lblCpf = new JLabel("");
		panel_1.add(lblCpf, "cell 1 5,growx,aligny center");
		lblCpf.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "CPF", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		lblCpf.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 14));
		lblCpf.setText(u.getCpf());
		lblCpf.setToolTipText("Cpf não pode ser alterado.");

		JTextField txtTelefone = new JTextField();
		txtTelefone.setOpaque(false);
		panel_1.add(txtTelefone, "cell 1 6,growx,aligny center");
		txtTelefone.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "telefone", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		txtTelefone.setColumns(10);

		RestrictedTextField validarTel = new RestrictedTextField(txtTelefone);
		validarTel.setOnlyNums(true);
		validarTel.setLimit(11);

		if (u.getTel() == null || u.getTel().isEmpty()) {
			txtTelefone.setText("");
		} else {
			txtTelefone.setText(u.getTel());
		}

		JButton btCancelar = new JButton("Cancelar");
		btCancelar.setBackground(new Color(205, 92, 92));
		panel_1.add(btCancelar, "flowx,cell 2 7,alignx right,aligny bottom");

		JButton btSalvar = new JButton("Salvar");
		panel_1.add(btSalvar, "cell 2 7,alignx right,aligny bottom");
		btSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

//
//				// Consulta do produtor atual
//				Usuario clienteAntigo = udao.consultaUsuarioCadastrado(u.getCpf(), u.getEmail());

				// Validação de campos obrigatórios
				if (txtEmail.getText().isEmpty() || txtNome.getText().isEmpty()) {
					TelaError tela = new TelaError();
					tela.setLabelText("Preencha todos os campos obrigatórios.");
					tela.setLocationRelativeTo(null);
					tela.setVisible(true);
					return;
				}

				// Coleta de dados do formulário
				String email = txtEmail.getText();
				String nome = txtNome.getText();
				String telefone = txtTelefone.getText();

				// **Verifica diretamente se o telefone pertence a outro usuário**
				if (udao.verificarTelefone(telefone, u.getIdUsuario())) {
					TelaError erro = new TelaError();
					erro.setLabelText("Telefone já cadastrado por outro usuário.");
					erro.setVisible(true);
					erro.setLocationRelativeTo(null);
					return; // Impede a atualização no banco
				}

				// Populando os objetos
				usuarioNovo.setEmail(email);
				usuarioNovo.setNome(nome);
				usuarioNovo.setTel(telefone);
				usuarioNovo.setIdUsuario(u.getIdUsuario());

				boolean clienteAtualizado = udao.alterarUsuario(usuarioNovo);

				if (clienteAtualizado) {
					usuarioNovo = udao.consultarUsuarioLoginSenha(usuarioNovo.getEmail(), u.getSenha());

					TelaPerfilCliente v = new TelaPerfilCliente(usuarioNovo, isVendedor, bufferedImage);
					v.setLocationRelativeTo(null);
					v.setVisible(true);

					TelaError tela = new TelaError();
					tela.setLabelText("Dados atualizados com sucesso!");
					tela.setLocationRelativeTo(null);
					tela.setVisible(true);
					dispose();
				} else {
					TelaError tela = new TelaError();
					tela.setLabelText("Erro ao atualizar os dados do cliente.");
					tela.setLocationRelativeTo(null);
					tela.setVisible(true);
				}

			}
		});
		btSalvar.setBackground(new Color(154, 208, 217));

		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopupCancelar cancelar = new PopupCancelar(estajanela, null, u, false);
				cancelar.setLocationRelativeTo(null);
				cancelar.setVisible(true);
				cancelar.setLocationRelativeTo(null);
			}
		});

	}

}
