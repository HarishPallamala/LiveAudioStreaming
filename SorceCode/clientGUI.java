import Recordtest.RecordTest;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class clientGUI implements ActionListener{
    //static clientGUI cli = new clientGUI();
    JFrame frame;
    JButton record,client,stop;
    JLabel Recording,prt;
    static JLabel started;
    static JTextArea area;
    clientGUI(){
        frame = new JFrame();
        frame.setSize(400,690);
        frame.setTitle("Client");
        frame.setLayout(null);
        //frame.getContentPane().setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Recording = new JLabel("ClientInterface");
        Recording.setFont(new Font("Verdana", Font.PLAIN,18));
        Recording.setForeground(new Color(120,80,90));
        Recording.setSize(150,30);
        Recording.setLocation(130,20);
        Border border = BorderFactory.createLineBorder(Color.red);
        Recording.setBorder(border);
        frame.add(Recording);

        record= new JButton(" O Record Live Stream");
        record.setSize(175, 35);
        record.setLocation(110,110);
        record.addActionListener(this);
        frame.add(record);


        stop = new JButton("Stop");
        stop.setSize(120, 35);
        stop.setLocation(220,60);
        stop.addActionListener(this);
        frame.add(stop);

        client= new JButton("ConnectLiveStream");
        client.setSize(150, 35);
        client.setLocation(50,60);
        client.addActionListener(this);
        frame.add(client);

        prt = new JLabel("#Client Status#");
        prt.setFont(new Font("Verdana", Font.PLAIN,18));
        prt.setForeground(new Color(120,80,90));
        prt.setSize(350,30);
        prt.setLocation(35,160);
        //started.set
        frame.add(prt);


        started = new JLabel("#Audio Status#");
        started.setFont(new Font("Verdana", Font.PLAIN,18));
        started.setForeground(new Color(120,80,90));
        started.setSize(350,30);
        started.setLocation(35,193);
        //started.set
        frame.add(started);

        /*chat= new JButton("ChatBox");
        chat.setSize(120, 35);
        chat.setLocation(150,150);
        chat.addActionListener(this);
        frame.add(chat);*/

        area = new JTextArea("\t\t"+java.time.LocalDate.now()+"\n");
        area.append("________________________________________________________\n");
        area.append("\t     Chat is connected with Server\n");
        area.append("________________________________________________________\n");
        area.setBounds(13,240,360,390);
        Font font = new Font("Monaco", Font.BOLD, 12);
        area.setFont(font);
        area.setForeground(Color.BLUE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        frame.add(area);

        frame.setVisible(true);
    }
    public static void recv(String s) {
        area.append(s);
        //streamReceiver.main();
    }



    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == record){
            new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception {
                    RecordTest.main();
                    return null;
                }
            }.execute();
        }
        if (ae.getSource() == client){
            prt.setText("Client started at port:50005");
            new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception {
                    try {
                        stackClient.main();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute();
        }
        if (ae.getSource() == stop){
            new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception {
                    stackClient.stop();
                    return null;
                }
            }.execute();
        }
    }
    public static void main(String args[]){
       clientGUI cli = new clientGUI();
       streamReciever.main();
    }

}