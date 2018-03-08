package pl.jozkow.CryptoWallet.dao;

import java.util.List;

import pl.jozkow.CryptoWallet.model.SingleTransaction;
import pl.jozkow.CryptoWallet.model.WalletStatistics;

/**
 * @author Pawel Jozkow
 *	pawel.jozkow@gmail.com
 */

public interface TransactionsDao {

	public SingleTransaction insertNewTransaction(SingleTransaction transaction);
	public SingleTransaction findTransactionById(long transactionId);
	public List<SingleTransaction> findTransactionsByType(int buyOrSell);
	public List<SingleTransaction> findAllTransactions();
	public WalletStatistics getStatistics(String nameOfCrypto);
	public boolean checkIfTransactionExistsById(long transactionId);

}
