import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Transaction transaction1 = new Transaction("Sanzhar", "Nygmetzhan", 10.0);
        Transaction transaction2 = new Transaction("Nygmetzhan", "Kirill", 5.0);
        Transaction transaction3 = new Transaction("Kirill", "Sanzhar", 8.0);

        List<Transaction> transactionsBlock1 = new ArrayList<>();
        transactionsBlock1.add(transaction1);
        transactionsBlock1.add(transaction2);

        Blockchain blockchain = new Blockchain();

        blockchain.addBlock(transactionsBlock1);

        List<Transaction> transactionsBlock2 = new ArrayList<>();
        transactionsBlock2.add(transaction3);

        blockchain.addBlock(transactionsBlock2);

        boolean isChainValid = blockchain.isChainValid();

        if (isChainValid) {
            System.out.println("Blockchain is valid.");
        } else {
            System.out.println("Blockchain is not valid.");
        }
    }
}
