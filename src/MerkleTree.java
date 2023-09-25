import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Transaction {
    private String sender;
    private String recipient;
    private double amount;
    private long timestamp;

    public Transaction(String sender, String recipient, double amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.timestamp = new Date().getTime();
    }

    public String getData() {
        return sender + recipient + amount + timestamp;
    }

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

class MerkleTree {
    private MerkleNode root;

    public MerkleTree(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            this.root = null;
        } else {
            List<MerkleNode> nodes = new ArrayList<>();

            for (Transaction transaction : transactions) {
                nodes.add(new MerkleNode(transaction.getData()));
            }

            while (nodes.size() > 1) {
                List<MerkleNode> newNodes = new ArrayList<>();
                for (int i = 0; i < nodes.size(); i += 2) {
                    MerkleNode left = nodes.get(i);
                    MerkleNode right = (i + 1 < nodes.size()) ? nodes.get(i + 1) : left;
                    newNodes.add(new MerkleNode(left, right));
                }
                nodes = newNodes;
            }

            root = nodes.get(0);
        }
    }

    public String getRootHash() {
        if (root == null) {
            return "No transactions";
        }
        return root.getHash();
    }
}
