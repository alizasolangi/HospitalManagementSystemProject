package com.mycompany.hospitalqueuesystem;

import com.mycompany.hospitalqueuesystem.model.Patient;
import java.util.LinkedList;
import java.util.Queue;

public class PatientRecords {

    private static Queue<Patient> patientQueue = new LinkedList<>();

    public static void addPatient(Patient p) {
        patientQueue.offer(p);
    }

    public static boolean updatePatientById(String id, String name, int age, String gender, String disease) {
        for (Patient p : patientQueue) {
            if (p.getId().equals(id)) {
                p.setName(name);
                p.setAge(age);
                p.setGender(gender);
                p.setDisease(disease);
                return true;
            }
        }
        return false;
    }

    // ✅ Add this method here
    public static boolean deletePatientFIFO() {
        if (patientQueue.isEmpty()) {
            return false;
        }
        patientQueue.poll(); // FIFO remove
        return true;
    }

    public static Patient treatNext() {
        if (patientQueue.isEmpty()) {
            return null;
        }
        return patientQueue.poll(); // remove first patient (FIFO)
    }

    public static Queue<Patient> getAllPatients() {
        return patientQueue;
    }

    public static javax.swing.table.DefaultTableModel getAllPatientsTableModel() {
        String[] columns = {"ID", "Name", "Age", "Gender", "Disease"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(columns, 0);

        for (Patient p : patientQueue) {
            Object[] row = {p.getId(), p.getName(), p.getAge(), p.getGender(), p.getDisease()};
            model.addRow(row);
        }

        return model;
    }
}
