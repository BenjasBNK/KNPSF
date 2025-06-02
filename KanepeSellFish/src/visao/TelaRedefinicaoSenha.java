package visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controle.UsuarioDAO;
import modelo.Usuario;
import net.miginfocom.swing.MigLayout;
import java.awt.Cursor;

public class TelaRedefinicaoSenha extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private UsuarioDAO uDAO = new UsuarioDAO();
	private JPasswordField txtAtual;
	private JPasswordField txtNova;
	private JPasswordField passwordField;
	private JButton btSalvar;
	private JButton btCancelar;
	private JLabel lblSubtitulo;
	private JLabel lblSubsubtitulo;
	private TelaRedefinicaoSenha estajanela = this;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicio frame = new TelaInicio(null, false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaRedefinicaoSenha(Usuario u, boolean isVendedor) {
		setBackground(SystemColor.activeCaption);
		setResizable(false);
		setLocationByPlatform(true);
		setMinimumSize(new Dimension(0, 0));
		setMaximumSize(new Dimension(1920, 1080));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 668, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(154, 205, 217));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[grow]", "[]"));

		JLabel lblTitulo = new JLabel("Redefinição de Senha");
		lblTitulo.setFont(new Font("Dialog", Font.PLAIN, 30));
		panel.add(lblTitulo, "cell 0 0,alignx center,aligny center");

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[grow]", "[50px][][20px][][][][grow]"));

		lblSubtitulo = new JLabel("Primeiro, precisamos ter certeza que é você.");
		lblSubtitulo.setFont(new Font("Dialog", Font.PLAIN, 11));
		panel_1.add(lblSubtitulo, "cell 0 0,alignx center,aligny center");

		txtAtual = new JPasswordField();
		txtAtual.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Atual Senha:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		txtAtual.setOpaque(false);
		panel_1.add(txtAtual, "flowx,cell 0 1,alignx center,aligny center");
		txtAtual.setColumns(20);

		txtNova = new JPasswordField();
		txtNova.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nova Senha:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		txtNova.setOpaque(false);
		panel_1.add(txtNova, "cell 0 3,alignx center,aligny center");
		txtNova.setColumns(20);

		passwordField = new JPasswordField();
		passwordField.setOpaque(false);
		passwordField.setColumns(20);
		passwordField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Repita a Senha:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(passwordField, "cell 0 4,alignx center");

		lblSubsubtitulo = new JLabel("OBS: Não utilize a mesma senha antes cadastrada.");
		lblSubsubtitulo.setForeground(Color.RED);
		lblSubsubtitulo.setFont(new Font("Dialog", Font.PLAIN, 10));
		panel_1.add(lblSubsubtitulo, "cell 0 5,alignx center,aligny center");

		btCancelar = new JButton("Cancelar");
		btCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btCancelar.setBackground(new Color(205, 92, 92));
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopupCancelarSenha cancela = new PopupCancelarSenha(estajanela, u);
				cancela.setVisible(true);
				cancela.setLocationRelativeTo(null);

			}
		});
		panel_1.add(btCancelar, "flowx,cell 0 6,alignx right,aligny bottom");

		btSalvar = new JButton("Salvar");
		btSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btSalvar.setBackground(new Color(154, 205, 217));
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String senhaAntiga = String.valueOf(txtAtual.getPassword());
				String senhaNova = String.valueOf(txtNova.getPassword());
				String senhaRepet = String.valueOf(passwordField.getPassword());

				if (senhaAntiga.isEmpty() || senhaNova.isEmpty() || senhaRepet.isEmpty()) {
					exibirErro("Preencha todos os campos");
					return;
				}

				if (uDAO.consultarUsuarioLoginSenha(u.getEmail(), senhaAntiga) == null) {
					exibirErro("Senha incorreta, tente novamente");
					return;
				}
				
				if(senhaAntiga.equals(senhaNova) && senhaAntiga.equals(senhaNova)) {
					exibirErro("Não utilize a mesma senha cadastrada!");
					return;
				}

				if (!senhaNova.equals(senhaRepet)) {
					exibirErro("As senhas não coincidem.");
					return;
				}

				boolean senhaAlterada = uDAO.alterarSenha(u.getEmail(), senhaNova);
				if (senhaAlterada) {
					exibirMensagem("Senha alterada com sucesso!");
					dispose();
				} else {
					exibirErro("Erro ao alterar senha. Verifique os dados e tente novamente.");
				}
			}

			private void exibirErro(String mensagem) {
				TelaError erro = new TelaError();
				erro.setLabelText(mensagem);
				erro.setLocationRelativeTo(null);
				erro.setVisible(true);
			}

			private void exibirMensagem(String mensagem) {
				TelaError certo = new TelaError();
				certo.setLabelText(mensagem);
				certo.setLocationRelativeTo(null);
				certo.setVisible(true);
			}
		});
		panel_1.add(btSalvar, "cell 0 6,alignx right,aligny bottom");

	}
}