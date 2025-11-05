package com.mycompany.hospitalqueuesystem;

import com.mycompany.hospitalqueuesystem.model.Patient;
import javax.swing.*;
import java.awt.*;

public class ViewPatientsForm extends JFrame {

    public ViewPatientsForm() {
        setTitle("View All Patients");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 🔹 Table from PatientRecords
        JTable table = new JTable(PatientRecords.getAllPatientsTableModel());
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        table.getTableHeader().setBackground(new Color(100, 149, 237));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        // 🔹 Back button
        JButton backButton = new JButton("Back to Home");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backButton.setBackground(new Color(65, 105, 225));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new ViewPatientsForm().setVisible(true);
    }
}
