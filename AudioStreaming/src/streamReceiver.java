import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class streamReciever {
    /*JFrame frame;
    JTextArea txt;

    streamReciever() {
        frame = new JFrame();
        frame.setSize(260, 600);
        frame.setTitle("Client ChatBox");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Monaco", Font.BOLD, 15);

        txt = new JTextArea("");
        txt.setFont(font);
        txt.setForeground(Color.BLUE);
        txt.setBounds(2, 2, 400, 600);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        JScrollPane scrl = new JScrollPane(txt);
        frame.add(scrl, BorderLayout.CENTER);

        //txt.append("Harish Project.........");

        frame.setVisible(true);
    }*/

    public static void main() {
        streamReciever chat = new streamReciever();
        System.setProperty("java.net.preferIPv4Stack", "true");

        try {
            InetAddress group = InetAddress.getByName("225.6.7.8");
            MulticastSocket mSocket = new MulticastSocket(3456);
            mSocket.joinGroup(group);

            int i = 0;
            while(true) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String cur = dtf.format(now);
                byte[] buffer = new byte[100];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                mSocket.receive(packet);
                //chat.txt.append(new String(buffer));
                clientGUI.area.append(new String(buffer));
                //System.out.println(new String(buffer));
                //i++;
            }
            //mSocket.close();
            //System.out.println("Closed Socket");
        } catch (Exception e) {e.printStackTrace();}
    }

}