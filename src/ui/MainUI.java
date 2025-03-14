package ui;

import controller.PersonController;
import entity.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {
    private PersonController controller;

    // Swing bileşenleri
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField, ageField, emojiField, idField;
    private JTextArea outputArea;
    private JButton addButton, deleteButton, updateButton, listButton;

    public MainUI() {
        controller = new PersonController();
        initializeUI();
    }

    private void initializeUI() {
        // Ana pencere
        frame = new JFrame("Person CRUD Operations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // Ana panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Butonlar
        addButton = new JButton("Ekle");
        deleteButton = new JButton("Sil");
        updateButton = new JButton("Güncelle");
        listButton = new JButton("Listele");

        // Listeleme alanı
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Buton aksiyonları
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPanel();
                createAddFields();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPanel();
                createDeleteFields();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPanel();
                createUpdateFields();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPanel();
                listPersons();
            }
        });

        // Butonları panel'e ekleme
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(updateButton);
        panel.add(listButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Ekleme input alanlarını oluştur
    private void createAddFields() {
        // Temizlemeden sonra inputları ekle
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        emojiField = new JTextField(20);

        JButton submitButton = new JButton("Ekle");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String emoji = emojiField.getText();
                controller.addPerson(name, age, emoji);
                JOptionPane.showMessageDialog(frame, "Kişi Eklendi!");
                clearPanel();
                resetUI();
            }
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

    // Silme input alanlarını oluştur
    private void createDeleteFields() {
        idField = new JTextField(20);

        JButton submitButton = new JButton("Sil");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                controller.deletePerson(id);
                JOptionPane.showMessageDialog(frame, "Kişi Silindi!");
                clearPanel();
                resetUI();
            }
        });

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(submitButton);

        frame.revalidate();
        frame.repaint();
    }

    // Güncelleme input alanlarını oluştur
    private void createUpdateFields() {
        idField = new JTextField(20);
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        emojiField = new JTextField(20);

        JButton submitButton = new JButton("Güncelle");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String emoji = emojiField.getText();
                controller.updatePersons(id, name, age, emoji);
                JOptionPane.showMessageDialog(frame, "Kişi Güncellendi!");
                clearPanel();
                resetUI();
            }
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

    // Listeleme alanını oluştur
    private void listPersons() {
        outputArea.setText(""); // Önceki sonuçları temizle
        for (Person p : controller.getAllPersons()) {
            outputArea.append(p.getEmoji() + " - " + p.getAge() + " - " + p.getName() + "\n");
        }
    }

    // Paneli temizle
    private void clearPanel() {
        panel.removeAll();
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(updateButton);
        panel.add(listButton);
    }

    // UI'yi sıfırla
    private void resetUI() {
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        new MainUI();
    }
}
