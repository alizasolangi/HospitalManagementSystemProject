package com.mycompany.hospitalqueuesystem;

import com.mycompany.hospitalqueuesystem.model.Doctor;
import javax.swing.*;
import java.awt.*;

public class DoctorManagementForm extends JFrame {

    private JTextField idField, nameField, contactField;
    private JCheckBox availableCheck;
    private JTable doctorTable;

    public DoctorManagementForm() {
        setTitle("👨‍⚕️ Doctor Management");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 🔹 Title
        JLabel title = new JLabel("Doctor Management", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // 🔹 Top Panel (Form + Buttons)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 40, 10, 40));

        formPanel.add(new JLabel("Doctor ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Doctor Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Contact No:"));
        contactField = new JTextField();
        formPanel.add(contactField);

        formPanel.add(new JLabel("Available:"));
        availableCheck = new JCheckBox("Available");
        availableCheck.setSelected(true);
        formPanel.add(availableCheck);

        topPanel.add(formPanel);

        // Buttons Panel (Add, Update, Search)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton addBtn = new JButton("Add Doctor");
        JButton updateBtn = new JButton("Update Doctor");
        JButton searchBtn = new JButton("Search Doctor");

        JButton[] buttons = {addBtn, updateBtn, searchBtn};
        for (JButton b : buttons) {
            b.setBackground(new Color(100, 149, 237));
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setFocusPainted(false);
            b.setPreferredSize(new Dimension(150, 35));
        }
        searchBtn.setBackground(new Color(34, 139, 34)); // Green for search

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(searchBtn);
        topPanel.add(buttonPanel);

        add(topPanel, BorderLayout.NORTH);

        // 🔹 Table Panel
        doctorTable = new JTable(DoctorRecords.getAllDoctorsTableModel());
        JScrollPane scrollPane = new JScrollPane(doctorTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Doctor List"));
        add(scrollPane, BorderLayout.CENTER);

        doctorTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            private int hoveredRow = -1;

            {
                // Mouse motion listener to track hovered row
                doctorTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(java.awt.event.MouseEvent e) {
                        int row = doctorTable.rowAtPoint(e.getPoint());
                        if (row != hoveredRow) {
                            hoveredRow = row;
                            doctorTable.repaint();
                        }
                    }
                });

                // Reset hovered row when mouse exits table
                doctorTable.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        hoveredRow = -1;
                        doctorTable.repaint();
                    }
                });
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (row == hoveredRow) {
                    c.setBackground(new Color(220, 240, 255)); // Hover color
                } else {
                    c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
                }
                return c;
            }
        });

        // 🔹 Bottom Panel (Back button)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 40, 10));
        JButton backBtn = new JButton("Back");
        backBtn.setBackground(new Color(178, 34, 34));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.setPreferredSize(new Dimension(120, 35));
        bottomPanel.add(backBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // 🟢 Add Doctor
        addBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String contactNo = contactField.getText().trim(); // changed
            boolean available = availableCheck.isSelected();

            if (id.isEmpty() || name.isEmpty() || contactNo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠ Please fill all fields!");
                return;
            }

            Doctor d = new Doctor(id, name, contactNo, available);
            DoctorRecords.addDoctor(d);

            JOptionPane.showMessageDialog(this, "✅ Doctor Added Successfully!");
            refreshTable();
            clearFields();
        });

        // 🟡 Update Doctor (by ID)
        updateBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String contactNo = contactField.getText().trim();
            boolean available = availableCheck.isSelected();

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠ Please enter Doctor ID!");
                return;
            }

            boolean updated = DoctorRecords.updateDoctor(id, name, contactNo, available);
            if (updated) {
                JOptionPane.showMessageDialog(this, "✅ Doctor Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Doctor not found!");
            }
            refreshTable();
            clearFields();
        });

        // 🔵 Search by ID
        searchBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠ Please enter Doctor ID!");
                return;
            }
            Doctor d = DoctorRecords.searchDoctorById(id);
            if (d != null) {
                nameField.setText(d.getName());
                contactField.setText(d.getContactNo()); // changed
                availableCheck.setSelected(d.isAvailable());
                JOptionPane.showMessageDialog(this, "✅ Doctor Found!");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Doctor not found!");
            }
        });

        // 🔙 Back
        backBtn.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
        });
    }

    // Helper methods
    private void refreshTable() {
        doctorTable.setModel(DoctorRecords.getAllDoctorsTableModel());
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        contactField.setText(""); // changed
        availableCheck.setSelected(true);
    }
}
