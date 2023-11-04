import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class streamFrame extends JFrame {

    JPanel mainPnl;

        JPanel filePnl;
            JFileChooser fileChooser;
            JTextField toFindTF;


        JPanel displayPnl;
            TextArea ogTA;
                JScrollPane ogScroll;
            TextArea newTA;
                JScrollPane newScroll;

        JPanel controlPnl;
            JButton loadBtn;
            JButton searchBtn;
            JButton quitBtn;


        streamFiltering filter;



    public streamFrame(){

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        addDisplayPnl();
        mainPnl.add(displayPnl,BorderLayout.NORTH);

        addFilePnl();
        mainPnl.add(filePnl,BorderLayout.CENTER);

        addControlPnl();
        mainPnl.add(controlPnl,BorderLayout.SOUTH);
        this.add(mainPnl);

    }

    private void addDisplayPnl(){
        ogTA = new TextArea(20,30);
        ogScroll = new JScrollPane(ogTA);
        ogTA.setName("Original");

        newTA = new TextArea(20,30);
        newScroll = new JScrollPane(newTA);
        newTA.setName("Filtered");
        displayPnl = new JPanel();

        displayPnl.setLayout(new BorderLayout());
        displayPnl.add(ogScroll,BorderLayout.WEST);
        displayPnl.add(newScroll, BorderLayout.EAST);



    }
    private void addFilePnl(){
        filePnl = new JPanel();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(workingDirectory);
        filePnl.add(fileChooser);

        toFindTF = new JTextField(30);
        toFindTF.setName("ToFind");
        filePnl.add(toFindTF);


    }
    private void addControlPnl(){
        controlPnl = new JPanel();

        loadBtn = new JButton("Load File");

        searchBtn = new JButton("Search");
        searchBtn.setVisible(false);

        quitBtn = new JButton("Quit");

        controlPnl.add(loadBtn);
        controlPnl.add(searchBtn);
        controlPnl.add(quitBtn);


        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newTA.setText("");
                filter.filter(toFindTF.getText()).forEach(n -> newTA.append(n + "\n"));
            }
        });

        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filter = new streamFiltering(fileChooser.getSelectedFile());
                ArrayList<String> ogList =  filter.getOG();
                ogTA.setText("");
                 ogList.forEach(n -> ogTA.append(n + "\n"));
                
                searchBtn.setVisible(true);
            }
        });


        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice =JOptionPane.showConfirmDialog(null,"Do you want to quit?");
                if (choice == 0){
                    System.exit(0);
                }
            }
        });



    }


    }
