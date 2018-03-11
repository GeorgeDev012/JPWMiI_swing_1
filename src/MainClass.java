import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import javax.swing.*;
public class MainClass {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Jerzy Tuszyński");
            frame.setSize(200, 200);
            JPanel jPanel1 = new JPanel();
            jPanel1.setLayout(new BorderLayout());
            JButton jButton1 = new JButton("Wczytaj");
            JLabel jLabel1 = new JLabel("");
            jPanel1.add(jButton1, BorderLayout.EAST);
            jPanel1.add(jLabel1, BorderLayout.CENTER);
            frame.add(jPanel1);
            frame.setLocationByPlatform(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            UIManager.put("FileChooser.cancelButtonText", "Anuluj");
            UIManager.put("FileChooser.openButtonText", "Otwórz");
            JFileChooser fileChooser = new JFileChooser();
            javax.swing.filechooser.FileNameExtensionFilter filter =
                    new javax.swing.filechooser.FileNameExtensionFilter("Grafika JPG", "jpg");
            fileChooser.setFileFilter(filter);

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            jButton1.addActionListener(actionEvent -> {

                int returnVal = fileChooser.showOpenDialog(jLabel1);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    Image image = toolkit.getImage(file.toString());
                    int labelWidth = jLabel1.getWidth();
                    int labelHeight = jLabel1.getHeight();
                    Image imageTemp = image.getScaledInstance(labelWidth, labelHeight,
                            Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(imageTemp);

                    jLabel1.setIcon(imageIcon);
                }
            });

            jLabel1.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent componentEvent) {
                    if(jLabel1.getIcon() != null) {
                        int labelWidth = jLabel1.getWidth();
                        int labelHeight = jLabel1.getHeight();
                        File file = fileChooser.getSelectedFile();
                        Image image = toolkit.getImage(file.toString());
                        Image imageTemp = image.getScaledInstance(labelWidth, labelHeight,
                                Image.SCALE_SMOOTH);
                        ImageIcon imageIcon = new ImageIcon(imageTemp);
                        jLabel1.setIcon(imageIcon);
                    }
                }
            });
        });
    }
}




