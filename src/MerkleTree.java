import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// Класс для представления транзакций
class Transaction {
    private String sender;    // Отправитель
    private String recipient; // Получатель
    private double amount;    // Сумма транзакции
    private long timestamp;   // Время транзакции

    public Transaction(String sender, String recipient, double amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.timestamp = new Date().getTime();
    }

    // Геттер для получения данных транзакции
    public String getData() {
        return sender + recipient + amount + timestamp;
    }

    // Геттеры для остальных полей транзакции
    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

// Класс для представления узла дерева Меркла
class MerkleNode {
    private String hash;
    private MerkleNode left;
    private MerkleNode right;

    public MerkleNode(String data) {
        this.hash = calculateHash(data);
    }

    public MerkleNode(MerkleNode left, MerkleNode right) {
        this.left = left;
        this.right = right;
        this.hash = calculateHash(left.hash + right.hash);
    }

    public String getHash() {
        return hash;
    }

    private String calculateHash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(data.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

// Класс для представления дерева Меркла
// Класс для представления дерева Меркла
class MerkleTree {
    private MerkleNode root;

    public MerkleTree(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            // Handle the case where there are no transactions
            this.root = null; // You can set the root to null or handle it as needed
        } else {
            List<MerkleNode> nodes = new ArrayList<>();

            // Создаем узлы для каждой транзакции и добавляем их в список узлов
            for (Transaction transaction : transactions) {
                nodes.add(new MerkleNode(transaction.getData()));
            }

            // Строим дерево Меркла из списка узлов
            while (nodes.size() > 1) {
                List<MerkleNode> newNodes = new ArrayList<>();
                for (int i = 0; i < nodes.size(); i += 2) {
                    MerkleNode left = nodes.get(i);
                    MerkleNode right = (i + 1 < nodes.size()) ? nodes.get(i + 1) : left; // Если нечетное количество узлов, дублируем последний
                    newNodes.add(new MerkleNode(left, right));
                }
                nodes = newNodes;
            }

            root = nodes.get(0);
        }
    }

    public String getRootHash() {
        if (root == null) {
            // Handle the case where there are no transactions
            return "No transactions";
        }
        return root.getHash();
    }
}
