package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.Usuario;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PopupCancelar extends JFrame {

	private JPanel contentPane;

	public PopupCancelar (TelaEditarPerfilCliente telaEditarC, TelaEditarPerfilVendedor telaEditarV, Usuario u, boolean vendedor) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(154, 205, 217));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblExcluirProduto = new JLabel("Tem certeza que deseja cancelar?");
		lblExcluirProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluirProduto.setForeground(Color.BLACK);
		lblExcluirProduto.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_1.add(lblExcluirProduto);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[100px][50px][50px][100px]", "[20px][30px]"));

		JButton btExcluir = new JButton("Sim");
		btExcluir.setBorderPainted(false);
		btExcluir.setBorder(null);
		btExcluir.setForeground(new Color(0, 0, 0));
		btExcluir.setBounds(100, 100, 50, 20);
		btExcluir.setBackground(new Color(8, 127, 140));
		btExcluir.setFont(new Font("/Fontes/Roboto-Black.ttf", Font.PLAIN, 13));
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(vendedor == true) {
					TelaPerfilVendedor v = new TelaPerfilVendedor(u, vendedor, null);
					v.setVisible(true);
					v.setLocationRelativeTo(null);
					telaEditarV.dispose();
					dispose();
				} else {
					TelaPerfilCliente c = new TelaPerfilCliente(u, vendedor, null);
					c.setVisible(true);
					c.setLocationRelativeTo(null);
					telaEditarC.dispose();
					dispose();
				}
			}
		});

		JButton btCancelar = new JButton("Não");
		btCancelar.setForeground(new Color(255, 255, 255));
		btCancelar.setBorderPainted(false);
		btCancelar.setBorder(null);
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btCancelar.setFont(new Font("Dialog", Font.PLAIN, 13));
		btCancelar.setBackground(new Color(205, 92, 92));
		panel.add(btCancelar, "cell 0 1,grow");
		panel.add(btExcluir, "cell 3 1,grow");
	}
}
