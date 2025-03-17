package controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;

public class Imagem {
    private static Imagem instancia;

    public Imagem() {
    }

    public static Imagem getInstancia() {
        if (instancia == null) {
            instancia = new Imagem();
        }
        return instancia;
    }

    // Alterando para variável local (não global)
    public FileInputStream Imagem() {
        JFileChooser fileChooser = new JFileChooser();
        // Definindo um filtro de extensão para limitar o tipo de arquivos que o usuário pode selecionar
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens (*.jpg, *.png, *.gif)", "jpg", "png", "gif"));
        
        int result = fileChooser.showOpenDialog(null);

        // Se o usuário selecionou um arquivo
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            // Tentando abrir o arquivo
            try {
                FileInputStream fin = new FileInputStream(selectedFile);
                
                // Verificando se o fluxo foi aberto com sucesso
                if (fin != null) {
                    System.out.println("Imagem selecionada com sucesso: " + selectedFile.getAbsolutePath());
                    return fin; // Retorna o FileInputStream
                } else {
                    System.out.println("Não foi possível abrir a imagem.");
                    return null;
                }
            } catch (IOException e1) {
                System.out.println("Erro ao tentar abrir a imagem: " + e1.getMessage());
                return null;
            }
        }
        
        System.out.println("Nenhuma imagem foi selecionada.");
        return null; // Caso o usuário cancele a seleção
    }
}
