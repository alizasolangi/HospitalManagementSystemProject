package com.mycompany.hospitalqueuesystem;

import com.mycompany.hospitalqueuesystem.model.Doctor;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class DoctorRecords {
    private static List<Doctor> doctors = new ArrayList<>();

    static {
        // 🔹 Initial 20 doctors with Islamic names + Contact No
        doctors.add(new Doctor("D001", "Dr. Yusuf", "03001234501", true));
        doctors.add(new Doctor("D002", "Dr. Fatima", "03001234502", false));
        doctors.add(new Doctor("D003", "Dr. Omar", "03001234503", true));
        doctors.add(new Doctor("D004", "Dr. Zainab", "03001234504", true));
        doctors.add(new Doctor("D005", "Dr. Ibrahim", "03001234505", false));
        doctors.add(new Doctor("D006", "Dr. Maryam", "03001234506", true));
        doctors.add(new Doctor("D007", "Dr. Salman", "03001234507", false));
        doctors.add(new Doctor("D008", "Dr. Ayesha", "03001234508", true));
        doctors.add(new Doctor("D009", "Dr. Hamza", "03001234509", true));
        doctors.add(new Doctor("D010", "Dr. Khadija", "03001234510", true));
        doctors.add(new Doctor("D011", "Dr. Ali", "03001234511", true));
        doctors.add(new Doctor("D012", "Dr. Sumayya", "03001234512", false));
        doctors.add(new Doctor("D013", "Dr. Bilal", "03001234513", true));
        doctors.add(new Doctor("D014", "Dr. Safiya", "03001234514", true));
        doctors.add(new Doctor("D015", "Dr. Imran", "03001234515", false));
        doctors.add(new Doctor("D016", "Dr. Yasmin", "03001234516", true));
        doctors.add(new Doctor("D017", "Dr. Tariq", "03001234517", false));
        doctors.add(new Doctor("D018", "Dr. Nadia", "03001234518", true));
        doctors.add(new Doctor("D019", "Dr. Kareem", "03001234519", true));
        doctors.add(new Doctor("D020", "Dr. Zoya", "03001234520", true));
    }

    // 🔹 Add new doctor
    public static void addDoctor(Doctor d) {
        doctors.add(d);
    }

    // 🔹 Update doctor by ID
    public static boolean updateDoctor(String id, String name, String contactNo, boolean available) {
        for (Doctor d : doctors) {
            if (d.getId().equals(id)) {
                if (!name.isEmpty()) d.setName(name);
                if (!contactNo.isEmpty()) d.setContactNo(contactNo);
                d.setAvailable(available);
                return true;
            }
        }
        return false;
    }

    // 🔹 Delete doctor by ID
    public static boolean deleteDoctorById(String id) {
        return doctors.removeIf(d -> d.getId().equals(id));
    }

    // 🔹 Search doctor by ID
    public static Doctor searchDoctorById(String id) {
        for (Doctor d : doctors) {
            if (d.getId().equals(id)) return d;
        }
        return null;
    }

    // 🔹 Table model for all doctors
    public static DefaultTableModel getAllDoctorsTableModel() {
        String[] columns = {"ID", "Name", "Contact No", "Available"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Doctor d : doctors) {
            Object[] row = {d.getId(), d.getName(), d.getContactNo(), d.isAvailable() ? "Yes" : "No"};
            model.addRow(row);
        }
        return model;
    }

    // 🔹 Return available doctors list
    public static List<Doctor> getAvailableDoctors() {
        List<Doctor> available = new ArrayList<>();
        for (Doctor d : doctors) {
            if (d.isAvailable()) available.add(d);
        }
        return available;
    }

    // 🔹 Return all doctors list
    public static List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }
}
