import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private ArrayList<Block> chain;

    // Конструктор блокчейна
    public Blockchain() {
        chain = new ArrayList<>();
        // Инициализируем генезис-блок вручную
        Block genesisBlock = createGenesisBlock();
        chain.add(genesisBlock);
    }

    // Метод для создания генезис-блока
// Метод для создания генезис-блока
    private Block createGenesisBlock() {
        List<Transaction> transactions = new ArrayList<>();
        // You can optionally add a coinbase transaction or other initial transactions here
        return new Block(0, transactions, "0");
    }



    // Метод для добавления нового блока в цепь
    public void addBlock(List<Transaction> transactions) {
        Block latestBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(latestBlock.getIndex() + 1, transactions, latestBlock.getHash());
        chain.add(newBlock);
    }

    // Геттер для получения последнего блока в цепи
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    // Метод для проверки целостности блокчейна
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            // 1. Проверка индекса блока
            if (currentBlock.getIndex() != previousBlock.getIndex() + 1) {
                return false;
            }

            // 2. Проверка хэша предыдущего блока
            if (!currentBlock.getPreviousHash().equals(previousBlock.getMerkleRoot())) {
                return false;
            }

            // 3. Проверка хэша текущего блока
            if (!currentBlock.getMerkleRoot().equals(currentBlock.calculateMerkleRoot(currentBlock.getTransactions()))) {
                return false;
            }
        }
        return true;
    }
}
