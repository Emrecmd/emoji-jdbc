package ui;

import controller.PersonController;
import entity.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainUI {
    private PersonController controller;

    private JFrame frame;
    private JPanel panel;
    private JTextField nameField, ageField, emojiField, idField;
    private JTextArea outputArea;
    private JButton addButton, deleteButton, updateButton, listButton, bulkAddButton;

    public MainUI() {
        controller = new PersonController();
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Person CRUD Operations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        addButton = new JButton("Ekle");
        deleteButton = new JButton("Sil");
        updateButton = new JButton("Güncelle");
        listButton = new JButton("Listele");
        bulkAddButton = new JButton("Toplu Ekle");

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        addButton.addActionListener(e -> {
            clearPanel();
            createAddFields();
        });

        deleteButton.addActionListener(e -> {
            clearPanel();
            createDeleteFields();
        });

        updateButton.addActionListener(e -> {
            clearPanel();
            createUpdateFields();
        });

        listButton.addActionListener(e -> {
            clearPanel();
            listPersons();
        });

        bulkAddButton.addActionListener(e -> bulkAddPersons());

        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(updateButton);
        panel.add(listButton);
        panel.add(bulkAddButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void createAddFields() {
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        emojiField = new JTextField(20);

        JButton submitButton = new JButton("Ekle");
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String emoji = emojiField.getText();
            controller.addPerson(name, age, emoji);
            JOptionPane.showMessageDialog(frame, "Kişi Eklendi!");
            clearPanel();
            resetUI();
        });

        panel.add(new JLabel("İsim:"));
        panel.add(nameField);
        panel.add(new JLabel("Yaş:"));
        panel.add(ageField);
        panel.add(new JLabel("Emoji:"));
        panel.add(emojiField);
        panel.add(submitButton);

        frame.revalidate();
        frame.repaint();
    }

    private void createDeleteFields() {
        idField = new JTextField(20);

        JButton submitButton = new JButton("Sil");
        submitButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            controller.deletePerson(id);
            JOptionPane.showMessageDialog(frame, "Kişi Silindi!");
            clearPanel();
            resetUI();
        });

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(submitButton);

        frame.revalidate();
        frame.repaint();
    }

    private void createUpdateFields() {
        idField = new JTextField(20);
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        emojiField = new JTextField(20);

        JButton submitButton = new JButton("Güncelle");
        submitButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String emoji = emojiField.getText();
            controller.updatePersons(id, name, age, emoji);
            JOptionPane.showMessageDialog(frame, "Kişi Güncellendi!");
            clearPanel();
            resetUI();
        });

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("İsim:"));
        panel.add(nameField);
        panel.add(new JLabel("Yaş:"));
        panel.add(ageField);
        panel.add(new JLabel("Emoji:"));
        panel.add(emojiField);
        panel.add(submitButton);

        frame.revalidate();
        frame.repaint();
    }

    private void listPersons() {
        outputArea.setText("");
        for (Person p : controller.getAllPersons()) {
            outputArea.append(p.getEmoji() + " - " + p.getAge() + " - " + p.getName() + "\n");
        }
    }

    private void bulkAddPersons() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("CSV Dosyası Seçin");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV Dosyaları", "csv"));

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                int count = 0;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 3) {
                        String name = data[0].trim();
                        int age = Integer.parseInt(data[1].trim());
                        String emoji = data[2].trim();
                        controller.addPerson(name, age, emoji);
                        count++;
                    }
                }
                JOptionPane.showMessageDialog(frame, count + " kişi başarıyla eklendi!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Hata: " + ex.getMessage());
            }
        }
    }

    private void clearPanel() {
        panel.removeAll();
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(updateButton);
        panel.add(listButton);
        panel.add(bulkAddButton);
    }

    private void resetUI() {
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        new MainUI();
    }
}
