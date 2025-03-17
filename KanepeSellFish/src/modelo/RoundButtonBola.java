package modelo;

import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Shape;

public class RoundButtonBola extends JButton {
    private static final long serialVersionUID = 1L;
    private Shape shape;

    // Construtor que aceita uma String como rótulo do botão
    public RoundButtonBola(String label) {
        super(label);
        setOpaque(false);
    }

    // Removei o construtor que aceita um int porque não estava sendo utilizado
    
    public RoundButtonBola(int i) {
    }
    

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillOval(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}
