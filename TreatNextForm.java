package com.mycompany.hospitalqueuesystem;

import com.mycompany.hospitalqueuesystem.model.Patient;
import com.mycompany.hospitalqueuesystem.model.Doctor;
import javax.swing.*;
import java.awt.*;

public class TreatNextForm extends JFrame {

    private JLabel idLabel, nameLabel, ageLabel, genderLabel, diseaseLabel;
    private JComboBox<String> doctorComboBox;
    private JButton treatBtn, backBtn;

    public TreatNextForm() {
        setTitle("Treat Next Patient");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 🔹 Background panel
        JPanel background = new JPanel(null) {
            Image bg = new ImageIcon("src/main/java/com/mycompany/hospitalqueuesystem/backgroundImage.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                g.setColor(new Color(255, 255, 255, 120));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(background);

        // 🔹 Title
        JLabel title = new JLabel("Treat Next Patient", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setBounds(0, 10, 600, 40);
        background.add(title);

        // 🔹 Patient Info Labels
        idLabel = createLabel(""); nameLabel = createLabel(""); ageLabel = createLabel("");
        genderLabel = createLabel(""); diseaseLabel = createLabel("");

        int y = 70;
        JLabel[] labels = {idLabel, nameLabel, ageLabel, genderLabel, diseaseLabel};
        for (JLabel l : labels) {
            l.setBounds(40, y, 500, 30);
            background.add(l);
            y += 40;
        }

        // 🔹 Doctor selection
        JLabel doctorLabel = new JLabel("Select Doctor:");
        doctorLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        doctorLabel.setBounds(40, y, 200, 25);
        background.add(doctorLabel);

        doctorComboBox = new JComboBox<>();
        for (Doctor d : DoctorRecords.getAllDoctors()) doctorComboBox.addItem(d.getName());
        doctorComboBox.setBounds(200, y, 250, 30);
        background.add(doctorComboBox);
        y += 50;

        // 🔹 Treat button
        treatBtn = new JButton("Treat Next");
        treatBtn.setBounds(40, y, 150, 35);
        treatBtn.setBackground(new Color(100, 149, 237));
        treatBtn.setForeground(Color.WHITE);
        treatBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        treatBtn.setFocusPainted(false);
        background.add(treatBtn);

        // 🔹 Back button
        backBtn = new JButton("Back");
        backBtn.setBounds(400, y, 120, 35);
        backBtn.setBackground(new Color(178, 34, 34));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        background.add(backBtn);

        // 🔹 Show next patient
        showNextPatient();

        // 🔹 Action listeners
        treatBtn.addActionListener(e -> treatNextPatient());
        backBtn.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
        });
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 20));
        return label;
    }

    private void showNextPatient() {
        Patient p = PatientRecords.getAllPatients().peek();
        if (p == null) {
            idLabel.setText("No patients in queue!");
            nameLabel.setText(""); ageLabel.setText("");
            genderLabel.setText(""); diseaseLabel.setText("");
            treatBtn.setEnabled(false);
            return;
        }
        idLabel.setText("Patient ID: " + p.getId());
        nameLabel.setText("Name: " + p.getName());
        ageLabel.setText("Age: " + p.getAge());
        genderLabel.setText("Gender: " + p.getGender());
        diseaseLabel.setText("Disease: " + p.getDisease());
        treatBtn.setEnabled(true);
    }

    private void treatNextPatient() {
        Patient p = PatientRecords.getAllPatients().peek();
        if (p == null) return;

        String doctorName = (String) doctorComboBox.getSelectedItem();
        Doctor selected = null;
        for (Doctor d : DoctorRecords.getAllDoctors()) {
            if (d.getName().equals(doctorName)) selected = d;
        }

        if (selected == null) {
            JOptionPane.showMessageDialog(this, "❌ Doctor not found!");
            return;
        }

        if (!selected.isAvailable()) {
            JOptionPane.showMessageDialog(this, "⚠ Doctor " + selected.getName() + " is not available!");
            return;
        }

        PatientRecords.treatNext();
        JOptionPane.showMessageDialog(this, "✅ Treating Patient: " + p.getName() + "\nWith Doctor: " + selected.getName());
        showNextPatient();
    }

    public static void main(String[] args) {
        new TreatNextForm().setVisible(true);
    }
}
