package visao;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CustomCheckBoxIcon implements Icon {
    private int size;
    private ImageIcon checkIcon; // Ícone de certo (selecionado)

    // Construtor que redimensiona o ícone se necessário
    public CustomCheckBoxIcon(int size) {
        this.size = size;
        
        // Tentar carregar o ícone do pacote IMG
        try {
            BufferedImage img = ImageIO.read(getClass().getResource("/IMG/verifica.png"));
            Image scaledImg = img.getScaledInstance(13, 13, Image.SCALE_SMOOTH);
            this.checkIcon = new ImageIcon(scaledImg);
        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem: " + e.getMessage());
            this.checkIcon = null;
        }
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        JCheckBox checkBox = (JCheckBox) c;

        // Desenha o fundo da caixinha com a cor RGB (8, 127, 140)
        g.setColor(new Color(2, 73, 89)); 
        g.fillRect(x, y, size, size);

        // Remover as bordas
        // Removemos o desenho das bordas aqui, mas você pode manter se desejar.
        // g.setColor(Color.BLACK);
        // g.drawRect(x, y, size, size);

        // Se o checkbox está selecionado, desenha o ícone de certo
        if (checkBox.isSelected() && checkIcon != null) {
            // Ajusta a posição do ícone (mais para esquerda e para cima)
            int iconX = x+1;  // Ajuste para a esquerda
            int iconY = y+2;  // Ajuste para cima
            checkIcon.paintIcon(c, g, iconX, iconY);  // Desenha o ícone na posição ajustada
        }
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }

    
}
