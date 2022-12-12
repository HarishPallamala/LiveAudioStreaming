import Recordtest.stackSender;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class serverGUI implements ActionListener{
    JFrame frame;
    JButton record,server,send,rec;
    JLabel Recording,started,aud;
    JTextArea txt;
    JTextField tf;
    JScrollPane js;
    serverGUI(){
        frame = new JFrame();
        frame.setSize(400,690);
        frame.setTitle("server");
        frame.setLayout(null);
        //frame.setLocationRelativeTo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Recording = new JLabel("ServerInterface");
        Recording.setFont(new Font("Verdana", Font.PLAIN,18));
        Recording.setForeground(new Color(120,80,90));
        Recording.setSize(150,30);
        Recording.setLocation(130,20);
        Border border = BorderFactory.createLineBorder(Color.red);
        Recording.setBorder(border);
        frame.add(Recording);

        started = new JLabel("#ServerStatus#");
        started.setFont(new Font("Verdana", Font.PLAIN,18));
        started.setForeground(new Color(120,80,90));
        started.setSize(350,30);
        started.setLocation(35,113);
        //started.set
        frame.add(started);

        aud = new JLabel("#AudioStatus#");
        aud.setFont(new Font("Verdana", Font.PLAIN,18));
        aud.setForeground(new Color(120,80,90));
        aud.setSize(350,30);
        aud.setLocation(35,145);
        frame.add(aud);
        /*
        Border border1 = BorderFactory.createLineBorder(Color.red);
        started.setBorder(border1);
        */



        /*record= new JButton("Record");
        record.setSize(100, 35);
        record.setLocation(30,100);
        record.addActionListener(this);
        frame.add(record);*/

        server= new JButton("StartLiveStream");
        server.setSize(200, 35);
        server.setLocation(94,65);
        server.addActionListener(this);
        frame.add(server);

        /*rec= new JButton(" ◉ Record");
        rec.setSize(150, 35);
        rec.setLocation(200,60);
        rec.addActionListener(this);
        frame.add(rec);*/


        Font font = new Font("Monaco", Font.BOLD, 12);
        txt = new JTextArea("\t\t"+ LocalDate.now()+"\n");
        txt.append("______________________________________________________\n");
        txt.append("\tSend Messages to Clients\n");
        txt.append("______________________________________________________\n");
        txt.setFont(font);
        txt.setForeground(Color.red);
        txt.setBounds(18, 190, 350, 380);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);

        js = new JScrollPane(txt);
        txt.setEditable(false);

        //js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //js.setBounds(365,100,13,370);
        //frame.getContentPane().add(js);.
        frame.add(txt);
        frame.add(js, BorderLayout.CENTER);


        tf = new JTextField();
        tf.setFont(font);
        tf.setBounds(18, 590, 250, 45);
        frame.add(tf);

        send = new JButton("send");
        send.setBounds(275,590,91,45);
        //record.addActionListener(this);
        server.addActionListener(this);
        send.addActionListener(this);
        frame.add(send);

        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == send){
            new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception {
                    String str = "";
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String cur = dtf.format(now);
                    str = tf.getText();
                    tf.setText("");
                    txt.append(cur + " : "+str+"\n");
                    //clientGUI.recv(cur + " : "+str+"\n");
                    //udpClient.main(cur + " : "+str+"\n");
                    //streamReceiver.main();
                    streamSender.main(cur + " : "+str+"\n");
                    //txt.append(string);
                    return null;
                }
            }.execute();

        }
        /*if (ae.getSource() == rec){
            new SwingWorker(){
                @Override
                protected Object doInBackground() throws Exception {
                    RecordTest.main();
                    return null;
                }
            }.execute();
        }*/
        if (ae.getSource() == server) {
            started.setText("Server Started at Port 50005");
            aud.setText("Sending Live Audio . . . ");
            new SwingWorker() {
                @Override
                protected Object doInBackground() throws Exception {
                    stackSender.main();
                    return null;
                }
            }.execute();
        }
    }
    public static void main(String args[]){
        new serverGUI();
    }
}
