import java.util.List;

// Класс для представления блока с транзакциями и деревом Меркла
class Block {
    private int index;              // Номер блока в цепи
    private long timestamp;         // Время создания блока в миллисекундах
    private List<Transaction> transactions; // Список транзакций в блоке
    private String previousHash;    // Хэш предыдущего блока
    private String merkleRoot;      // Корень дерева Меркла

    public Block(int index, List<Transaction> transactions, String previousHash) {
        this.index = index;
        this.timestamp = System.currentTimeMillis();
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.merkleRoot = calculateMerkleRoot(transactions);
    }

    public String calculateMerkleRoot(List<Transaction> transactions) {
        MerkleTree merkleTree = new MerkleTree(transactions);
        return merkleTree.getRootHash();
    }

    // Геттеры и сеттеры для полей блока
    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    // Геттер для получения хэша блока
    public String getHash() {
        // You can calculate the hash of the block here if needed
        // For simplicity, you can return "dummyHash" for now
        return "dummyHash";
    }
}
