import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    private float opacity; // Value between 0.0 (fully transparent) and 1.0 (fully opaque)

    public BackgroundPanel(String imagePath, float opacity) {
        // Load the image from the provided path
        backgroundImage = new ImageIcon(imagePath).getImage();
        this.opacity = opacity;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            // Apply the transparency
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            
            // Draw the background image
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            
            // Dispose of g2d to release resources
            g2d.dispose();
        }
    }
}
