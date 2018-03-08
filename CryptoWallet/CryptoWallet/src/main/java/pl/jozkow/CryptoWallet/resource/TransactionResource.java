package pl.jozkow.CryptoWallet.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.jozkow.CryptoWallet.model.SingleTransaction;
import pl.jozkow.CryptoWallet.model.WalletStatistics;
import pl.jozkow.CryptoWallet.service.TransactionService;

/**
 * @author Pawel Jozkow
 *	pawel.jozkow@gmail.com
 */

@RestController
@RequestMapping("wallet")
public class TransactionResource {

	@Autowired
	private final TransactionService transactionService;

	public TransactionResource(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, path = "transactions")
	public ResponseEntity<Object> insertNewTransaction(@RequestBody SingleTransaction transaction) {

		SingleTransaction singleTrans = transactionService.insertNewTransaction(transaction);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(singleTrans.getTransactionId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "transactions/{transactionId}")
	public Resource<SingleTransaction> findTransactionById(@PathVariable("transactionId") long transactionId) {

		SingleTransaction singleTrans = transactionService.findTransacionById(transactionId);

		Resource<SingleTransaction> resource = new Resource<>(singleTrans);
		ControllerLinkBuilder linkToAllTrans = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAllTransactions());
		resource.add(linkToAllTrans.withRel("Find all transactions"));

		if (this.checkIfTransactionExistsById(transactionId + 1)) {
			ControllerLinkBuilder linkToNextTrans = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findTransactionById(transactionId + 1));
			resource.add(linkToNextTrans.withRel("Find next transaction"));
		}

		if (this.checkIfTransactionExistsById(transactionId - 1)) {
			ControllerLinkBuilder linkToPrevTrans = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findTransactionById(transactionId - 1));
			resource.add(linkToPrevTrans.withRel("Find previous transaction"));

		}
		return resource;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "query/{buyOrSell}")
	public List<SingleTransaction> findTransactionsByType(@PathVariable("buyOrSell") int buyOrSell) {
		return transactionService.findTransactionsByType(buyOrSell);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "transactions")
	public List<SingleTransaction> findAllTransactions() {

		return transactionService.findAllTransactions();
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, path = "stat/{nameOfCrypto}")
	public WalletStatistics getStatistics(@PathVariable("nameOfCrypto") String nameOfCrypto) {

		// this is made temporarily
		String name = nameOfCrypto.toLowerCase();

		return transactionService.getStatistics(name);
	}

	private boolean checkIfTransactionExistsById(long transactionId) {
		return transactionService.checkIfTransactionExistsById(transactionId);
	}

}
