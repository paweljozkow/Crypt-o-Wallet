package pl.jozkow.CryptoWallet.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.jozkow.CryptoWallet.model.SingleTransaction;
import pl.jozkow.CryptoWallet.model.WalletStatistics;


/**
 * @author Pawel Jozkow
 *	pawel.jozkow@gmail.com
 */

@Repository("h2database")
@Transactional
public class TransactionsDaoH2 implements TransactionsDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private WalletStatistics walletStatistics;

	@Override
	public SingleTransaction insertNewTransaction(SingleTransaction transaction) {
		entityManager.persist(transaction);

		return transaction;
	}

	@Override
	public SingleTransaction findTransactionById(long transactionId) {

		return entityManager.find(SingleTransaction.class, transactionId);
	}

	@Override
	public List<SingleTransaction> findTransactionsByType(int buyOrSell) {

		
		TypedQuery<SingleTransaction> query = entityManager
				.createQuery("SELECT c FROM SingleTransaction c WHERE Type=" + buyOrSell, SingleTransaction.class);

		return query.getResultList();
	}

	@Override
	public List<SingleTransaction> findAllTransactions() {

		TypedQuery<SingleTransaction> query = entityManager.createQuery("Select p from SingleTransaction p",
				SingleTransaction.class);


		return query.getResultList();

	}

	@Override
	public WalletStatistics getStatistics(String nameOfCrypto) {

		TypedQuery<BigDecimal> totalOfFiatInvested = entityManager
				.createQuery("Select sum(p.transactionValue) from SingleTransaction p", BigDecimal.class);

		walletStatistics.setTotalAmountOfFiatInvested(totalOfFiatInvested.getSingleResult());

		TypedQuery<BigDecimal> amountOfCryptoInWallet = entityManager
				.createQuery("Select sum(p.transactionAmount) from SingleTransaction p", BigDecimal.class);

		walletStatistics.setAmountOfCryptoInWallet(amountOfCryptoInWallet.getSingleResult());

		walletStatistics.setCurrentPriceOfCrypto(nameOfCrypto);

		walletStatistics.setCurrentValueOfCryptoInWallet(amountOfCryptoInWallet.getSingleResult(), nameOfCrypto);

		return walletStatistics;
	}
	
	@Override
	public boolean checkIfTransactionExistsById(long transactionId) {

		Optional<SingleTransaction> check = Optional.ofNullable(entityManager.find(SingleTransaction.class, transactionId));
		
		return check.isPresent();
	}

}
