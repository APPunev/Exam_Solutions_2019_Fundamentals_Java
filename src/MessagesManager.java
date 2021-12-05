import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MessagesManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int capacity = Integer.parseInt(scanner.nextLine());

        Map<String, Messages> messageData = new LinkedHashMap<>();


        String command = scanner.nextLine();
        while(!command.equals("Statistics")){
            String[] tokens = command.split("=");
            String action = tokens[0];
            switch (action){
                case "Add":
                    String addName = tokens[1];
                    int addSent = Integer.parseInt(tokens[2]);
                    int addReceived = Integer.parseInt(tokens[3]);

                    if (!messageData.containsKey(addName)) {
                        Messages messages = new Messages(addSent,addReceived);
                        messageData.put(addName,messages);
                    }
                    break;
                case "Message":
                    String senderName = tokens[1];
                    String receiverName = tokens[2];
                    if (messageData.containsKey(senderName) && messageData.containsKey(receiverName)) {
                        setMessages(receiverName, senderName,messageData);

                        checkLimit(senderName,receiverName,messageData,capacity);
                    }
                    break;
                case "Empty":
                    String userEmpty = tokens[1];
                    if (userEmpty.equals("All")) {
                            messageData.clear();
                    }else{
                        if (messageData.containsKey(userEmpty)) {
                            messageData.remove(userEmpty);
                        }
                    }
                    break;
            }
            command = scanner.nextLine();
        }
        System.out.println("Users count: " + messageData.size());
        messageData.entrySet()
                .stream()
                .sorted((p1,p2) -> {
                    int result = Integer.compare(p2.getValue().getReceived(),p1.getValue().getReceived());
                    if (result == 0) {
                        result = p1.getKey().compareTo(p2.getKey());
                    }
                    return result;
                }).forEach(el -> System.out.println(el.getKey() + " - " + el.getValue().getSum()));
    }

    private static void checkLimit(String senderName, String receiverName, Map<String, Messages> messageData, int capacity) {
        int senderCapacity = messageData.get(senderName).getSent() + messageData.get(senderName).getReceived();
        int receiverCapacity = messageData.get(receiverName).getSent() + messageData.get(receiverName).getReceived();
        if (senderCapacity >= capacity) {
            messageData.remove(senderName);
            System.out.println(senderName + " reached the capacity!");
        }
        if (receiverCapacity >= capacity) {
            messageData.remove(receiverName);
            System.out.println(receiverName + " reached the capacity!");
        }
    }

    private static void setMessages(String receiverName, String senderName, Map<String, Messages> messageData) {
        int currentSent = messageData.get(senderName).getSent();
        int currentReceived = messageData.get(receiverName).getReceived();

        messageData.get(senderName).setSent(currentSent + 1);
        messageData.get(receiverName).setReceived(currentReceived + 1);
    }

    public static class Messages{
        private int sent;
        private int received;

        public Messages(int sent, int received) {
            this.sent = sent;
            this.received = received;
        }

        public int getReceived() {
            return received;
        }

        public void setReceived(int received) {
            this.received = received;
        }

        public int getSent() {
            return sent;
        }

        public void setSent(int sent) {
            this.sent = sent;
        }

        public void resetRecords() {
            this.received = 0;
            this.sent = 0;
        }

        public int getSum() {
            int sent = this.sent;
            int received = this.received;
            int result = sent + received;
            return result;
        }
    }
}
