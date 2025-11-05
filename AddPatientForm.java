package com.mycompany.hospitalqueuesystem;

import com.mycompany.hospitalqueuesystem.model.Patient;
import javax.swing.*;
import java.awt.*;

public class AddPatientForm extends JFrame {

    private JTextField idField, nameField, ageField, diseaseField;
    private JComboBox<String> genderCombo;
    private JButton saveButton, updateButton, backButton;

    public AddPatientForm() {
        setTitle("Add / Update / Delete Patient");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 🔹 Background with Image + Transparency
        JPanel background = new JPanel(null) {
            Image bg = new ImageIcon("src/main/java/com/mycompany/hospitalqueuesystem/AddPatient_bg.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                g.setColor(new Color(255, 255, 255, 130));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(background);

        // 🔹 Labels & Fields
        JLabel idLabel = new JLabel("Patient ID:");
        idLabel.setBounds(80, 60, 100, 30);
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        background.add(idLabel);

        idField = new JTextField();
        idField.setBounds(200, 60, 250, 30);
        background.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(80, 110, 100, 30);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        background.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 110, 250, 30);
        background.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(80, 160, 100, 30);
        ageLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        background.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(200, 160, 250, 30);
        background.add(ageField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(80, 210, 100, 30);
        genderLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        background.add(genderLabel);

        genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderCombo.setBounds(200, 210, 250, 30);
        background.add(genderCombo);

        JLabel diseaseLabel = new JLabel("Disease:");
        diseaseLabel.setBounds(80, 260, 100, 30);
        diseaseLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        background.add(diseaseLabel);

        diseaseField = new JTextField();
        diseaseField.setBounds(200, 260, 250, 30);
        background.add(diseaseField);

        // 🔹 Buttons
        saveButton = new JButton("Save");
        updateButton = new JButton("Update");
        backButton = new JButton("Back");

        // Buttons styling + hover
        JButton[] buttons = {saveButton, updateButton, backButton};
        for (JButton b : buttons) {
            b.setFont(new Font("Segoe UI", Font.BOLD, 16));
            b.setBackground(new Color(100, 149, 237, 220));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setBorderPainted(false);
            b.setOpaque(true);

            b.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    b.setBackground(new Color(65, 105, 225));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    b.setBackground(new Color(100, 149, 237, 220));
                }
            });
        }

        // 🔹 Buttons Panel (aligned properly)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(40, 340, 520, 50); // width for spacing
        buttonPanel.setLayout(new GridLayout(1, 3, 20, 0)); // 3 buttons, 20px horizontal gap
        buttonPanel.add(saveButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);

        background.add(buttonPanel);

        // 🔹 Save Action (FIFO add)
        saveButton.addActionListener(e -> {
            try {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String gender = genderCombo.getSelectedItem().toString();
                String disease = diseaseField.getText().trim();

                Patient p = new Patient(id, name, age, gender, disease);
                PatientRecords.addPatient(p);

                JOptionPane.showMessageDialog(this, "✅ Patient added to queue successfully!");
                clearFields();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
            }
        });

        // 🔹 Update Action (by ID)
        updateButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String gender = genderCombo.getSelectedItem().toString();
            String disease = diseaseField.getText().trim();

            boolean updated = PatientRecords.updatePatientById(id, name, age, gender, disease);
            if (updated) {
                JOptionPane.showMessageDialog(this, "✅ Patient record updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "❌ No patient found with ID: " + id);
            }
        });

        // 🔹 Back Action
        backButton.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
        });
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        diseaseField.setText("");
        genderCombo.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new AddPatientForm().setVisible(true);
    }
}
