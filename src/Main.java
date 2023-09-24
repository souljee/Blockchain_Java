import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create some sample transactions
        Transaction transaction1 = new Transaction("Alice", "Bob", 10.0);
        Transaction transaction2 = new Transaction("Bob", "Charlie", 5.0);
        Transaction transaction3 = new Transaction("Charlie", "David", 8.0);

        // Create a list of transactions for the first block
        List<Transaction> transactionsBlock1 = new ArrayList<>();
        transactionsBlock1.add(transaction1);
        transactionsBlock1.add(transaction2);

        // Create the blockchain with the genesis block
        Blockchain blockchain = new Blockchain();

        // Add the first block to the blockchain
        blockchain.addBlock(transactionsBlock1);

        // Create a list of transactions for the second block
        List<Transaction> transactionsBlock2 = new ArrayList<>();
        transactionsBlock2.add(transaction3);

        // Add the second block to the blockchain
        blockchain.addBlock(transactionsBlock2);

        // Verify the integrity of the blockchain
        boolean isChainValid = blockchain.isChainValid();

        if (isChainValid) {
            System.out.println("Blockchain is valid.");
        } else {
            System.out.println("Blockchain is not valid.");
        }
    }
}
