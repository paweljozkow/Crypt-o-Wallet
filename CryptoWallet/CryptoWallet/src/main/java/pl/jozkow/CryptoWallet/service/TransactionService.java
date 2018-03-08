package pl.jozkow.CryptoWallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.jozkow.CryptoWallet.dao.TransactionsDao;
import pl.jozkow.CryptoWallet.model.SingleTransaction;
import pl.jozkow.CryptoWallet.model.WalletStatistics;

/**
 * @author Pawel Jozkow
 *	pawel.jozkow@gmail.com
 */

@Service
public class TransactionService {

	@Autowired
	private final TransactionsDao transactionsDao;
	
	public TransactionService(@Qualifier("h2database") TransactionsDao transactionsDao) {
		this.transactionsDao = transactionsDao;
	}

	public SingleTransaction insertNewTransaction(SingleTransaction transaction) {

		return transactionsDao.insertNewTransaction(transaction);
	}

	public SingleTransaction findTransacionById(long transactionId) {
		return transactionsDao.findTransactionById(transactionId);
	}

	public List<SingleTransaction> findTransactionsByType(int buyOrSell) {
		return transactionsDao.findTransactionsByType(buyOrSell);
	}

	public List<SingleTransaction> findAllTransactions() {
		return transactionsDao.findAllTransactions();
	}

	public WalletStatistics getStatistics(String nameOfCrypto) {

		return transactionsDao.getStatistics(nameOfCrypto);
	}
	
	public boolean checkIfTransactionExistsById(long transactionId) {
		
		return transactionsDao.checkIfTransactionExistsById(transactionId);
	}

}
