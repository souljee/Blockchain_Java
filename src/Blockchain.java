import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private ArrayList<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
        Block genesisBlock = createGenesisBlock();
        chain.add(genesisBlock);
    }

    private Block createGenesisBlock() {
        List<Transaction> transactions = new ArrayList<>();
        return new Block(0, transactions, "0");
    }

    public void addBlock(List<Transaction> transactions) {
        Block latestBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(latestBlock.getIndex() + 1, transactions, latestBlock.getHash());
        chain.add(newBlock);
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (currentBlock.getIndex() != previousBlock.getIndex() + 1) {
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }

            if (!currentBlock.getMerkleRoot().equals(currentBlock.calculateMerkleRoot(currentBlock.getTransactions()))) {
                return false;
            }
        }
        return true;
    }
}
