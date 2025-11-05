package com.mycompany.hospitalqueuesystem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("Hospital Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // 🔹 Background Panel
        JPanel background = new JPanel() {
            Image bg = new ImageIcon("src/main/java/com/mycompany/hospitalqueuesystem/hospital_bg.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        background.setLayout(new BorderLayout());
        setContentPane(background);

        // ✅ Transparent panel
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // 🔹 Title
        JLabel title = new JLabel("Hospital Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(25, 0, 40, 0)); // title spacing
        background.add(title, BorderLayout.NORTH);

        // ✅ Buttons list
        String[] btnNames = {
            "Add Patient",
            "View All Patients",
            "Doctor Management",
            "Treat Next Patient",
            "Exit"
        };

        // ✅ Buttons loop
        for (String n : btnNames) {
            JButton button = new JButton(n);
            button.setFont(new Font("Segoe UI", Font.BOLD, 18));
            button.setBackground(new Color(100 / 255f, 149 / 255f, 237 / 255f, 180 / 255f));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(250, 60));
            button.setBorder(new RoundedBorder(15));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setMaximumSize(new Dimension(300, 60));
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Hover effect
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(65 / 255f, 105 / 255f, 225 / 255f, 200 / 255f));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(100 / 255f, 149 / 255f, 237 / 255f, 180 / 255f));
                }
            });

            // Button actions
            button.addActionListener(e -> {
                switch (n) {
                    case "Add Patient":
                        new AddPatientForm().setVisible(true); // 👈 dispose() hata diya
                        break;

                    case "View All Patients":
                        new ViewPatientsForm().setVisible(true);
                        break;

                    case "Doctor Management":
                        new DoctorManagementForm().setVisible(true);
                        break;

                    case "Treat Next Patient":
                        new TreatNextForm().setVisible(true);
                        break;
                    case "Exit":
                        System.exit(0);
                        break;
                }
            });

            panel.add(button);
            panel.add(Box.createVerticalStrut(15)); // space between buttons
        }

        // ✅ Add to background (center)
        background.add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}

// 🔹 Rounded Border Class
class RoundedBorder implements Border {

    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
