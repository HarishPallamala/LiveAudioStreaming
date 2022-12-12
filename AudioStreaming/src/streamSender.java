import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


class streamSender {


    public static void main(String message) {
        System.setProperty("java.net.preferIPv4Stack", "true");

        try {
            InetAddress group = InetAddress.getByName("225.6.7.8");

            //while(true){
                MulticastSocket socket = new MulticastSocket();
                /*Scanner sc = new Scanner(System.in);
                String message = sc.nextLine();*/

                DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), group, 3456);
                socket.send(packet);
                socket.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

}