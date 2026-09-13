package com.gamar.project_netbeans;

import javax.swing.*;
import java.util.logging.Logger;

public class firstFrame extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(firstFrame.class.getName());
    private toDoList myList;

    public firstFrame(toDoList list) {
        this.myList = list;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jButtonAddPersonal = new javax.swing.JButton();
        jButtonAddImportant = new javax.swing.JButton();
        jButtonAddWork = new javax.swing.JButton();
        jButtonDisplay = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jButtonMarkDone = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ToDo List");

        jLabelTitle.setFont(new java.awt.Font("Arial", 1, 28));
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelTitle.setText("To Do");

        jButtonAddPersonal.setText("📝 Personal Task");
        jButtonAddPersonal.addActionListener(evt -> {
            AddPersonalTaskFrame addFrame = new AddPersonalTaskFrame(myList);
            addFrame.setVisible(true);
        });

        jButtonAddImportant.setText("📝 Important Task");
        jButtonAddImportant.addActionListener(evt -> {
            AddImportantTaskFrame addImp = new AddImportantTaskFrame(myList);
            addImp.setVisible(true);
        });

        jButtonAddWork.setText("📝 Work Task");
        jButtonAddWork.addActionListener(evt -> {
            AddWorkTaskFrame addWork = new AddWorkTaskFrame(myList);
            addWork.setVisible(true);
        });

        jButtonDisplay.setText("Display All Tasks");
        jButtonDisplay.addActionListener(evt -> myList.display());

        jButtonRemove.setText("🗑️ Remove Task");
        jButtonRemove.addActionListener(evt -> {
            String title = JOptionPane.showInputDialog(this, "Enter task title to remove:");
            if(title != null && !title.trim().isEmpty()) myList.removeTask(title.trim());
        });

        jButtonMarkDone.setText("✅ Mark Task as Done");
        jButtonMarkDone.addActionListener(evt -> {
            String title = JOptionPane.showInputDialog(this, "Enter task title to mark as done:");
            if(title != null && !title.trim().isEmpty()) {
                try {
                    Tasks t = myList.searchTask(title.trim());
                    t.isDone();
                    JOptionPane.showMessageDialog(this, "Task marked as done!");
                } catch (TaskNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        });

        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(evt -> {
            JOptionPane.showMessageDialog(this, "Saved! Exiting... Goodbye!!");
            myList.saveToFile();
            System.exit(0);
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAddPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAddImportant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAddWork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonMarkDone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20,20,20)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30,30,30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddPersonal)
                    .addComponent(jButtonRemove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddImportant)
                    .addComponent(jButtonMarkDone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddWork)
                    .addComponent(jButtonExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonDisplay)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        toDoList myList = new toDoList("My List", 10000);
        java.awt.EventQueue.invokeLater(() -> new firstFrame(myList).setVisible(true));
    }

    private javax.swing.JButton jButtonAddPersonal;
    private javax.swing.JButton jButtonAddImportant;
    private javax.swing.JButton jButtonAddWork;
    private javax.swing.JButton jButtonDisplay;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonMarkDone;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JLabel jLabelTitle;
}

