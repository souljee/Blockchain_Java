import java.util.List;

class Block {
    private int index;
    private long timestamp;
    private List<Transaction> transactions;
    private String previousHash;
    private String merkleRoot;

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

    public String getHash() {
        return "dummyHash";
    }
}
