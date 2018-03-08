package pl.jozkow.CryptoWallet;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.jozkow.CryptoWallet.dao.TransactionsDaoH2;
import pl.jozkow.CryptoWallet.model.SingleTransaction;
import pl.jozkow.CryptoWallet.model.TransactionType;

/**
 * @author Pawel Jozkow
 *	pawel.jozkow@gmail.com
 */

@Component
public class CryptoWalletDAOCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(CryptoWalletDAOCommandLineRunner.class);
	
	@Autowired
	private TransactionsDaoH2 transactionDaoH2;
	
	@Override
	public void run(String... args) throws Exception {
		
		SingleTransaction singleTransaction1 = new SingleTransaction(TransactionType.BUY, new BigDecimal("9000"), new BigDecimal("0.00012"));
		SingleTransaction singleTransaction2 = new SingleTransaction(TransactionType.BUY, new BigDecimal("6700"), new BigDecimal("0.00312"));
		SingleTransaction singleTransaction3 = new SingleTransaction(TransactionType.BUY, new BigDecimal("5200"), new BigDecimal("0.099"));
		SingleTransaction singleTransaction4 = new SingleTransaction(TransactionType.BUY, new BigDecimal("7200"), new BigDecimal("0.00956"));
		SingleTransaction singleTransaction5 = new SingleTransaction(TransactionType.SELL, new BigDecimal("8700"), new BigDecimal("0.0190"));
		SingleTransaction singleTransaction6 = new SingleTransaction(TransactionType.SELL, new BigDecimal("3200"), new BigDecimal("0.009"));
		SingleTransaction singleTransaction7 = new SingleTransaction(TransactionType.BUY, new BigDecimal("5000"), new BigDecimal("0.056"));
		
		insertSampleTransaction(singleTransaction1);
		insertSampleTransaction(singleTransaction2);
		insertSampleTransaction(singleTransaction3);
		insertSampleTransaction(singleTransaction4);
		insertSampleTransaction(singleTransaction5);
		insertSampleTransaction(singleTransaction6);
		insertSampleTransaction(singleTransaction7);
		
	
		log.info("All transctions -> {}", transactionDaoH2.findAllTransactions());
	}

	private void insertSampleTransaction(SingleTransaction singleTransaction) {
		log.info("Inserted: -> {}", transactionDaoH2.insertNewTransaction(singleTransaction));
	}

}
