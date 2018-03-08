package pl.jozkow.CryptoWallet.model;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import pl.jozkow.CryptoWallet.externalAPI.ExternalAPIConnector;

/**
 * @author Pawel Jozkow
 *	pawel.jozkow@gmail.com
 */

@Component

public class WalletStatistics {

	@Autowired
	private ExternalAPIConnector connector;

	@JsonProperty("totalFIAT")
	private BigDecimal totalAmountOfFiatInvested = new BigDecimal("0");
	@JsonProperty("amountCrypto")
	private BigDecimal amountOfCryptoInWallet = new BigDecimal("0");
	@JsonProperty("valueCrypto")
	private BigDecimal currentValueOfCryptoInWallet = new BigDecimal("0");
	@JsonProperty("priceCrypto")
	private BigDecimal currentPriceOfCrypto = new BigDecimal("0");

	public void setTotalAmountOfFiatInvested(BigDecimal totalAmountOfFiatInvested) {
		this.totalAmountOfFiatInvested = totalAmountOfFiatInvested;
	}

	public BigDecimal getTotalAmountOfFiatInvested() {
		return totalAmountOfFiatInvested;
	}
	
	
	public void setAmountOfCryptoInWallet(BigDecimal amountOfCryptoInWallet) {
		this.amountOfCryptoInWallet = amountOfCryptoInWallet;
	}

	public BigDecimal getAmountOfCryptoInWallet() {
		return amountOfCryptoInWallet;
	}

	public void setCurrentValueOfCryptoInWallet(BigDecimal amountOfCryptoInWallet, String nameOfCrypto) {

		currentValueOfCryptoInWallet = amountOfCryptoInWallet.multiply(connector.getCurrentPriceofCrypto(nameOfCrypto));

	}

	public BigDecimal getCurrentValueOfCryptoInWallet() {
		return currentValueOfCryptoInWallet;
	}

	public BigDecimal getCurrentPriceOfCrypto() {
		return currentPriceOfCrypto;
	}

	public void setCurrentPriceOfCrypto(String nameOfCrypto) {
		
		currentPriceOfCrypto = connector.getCurrentPriceofCrypto(nameOfCrypto);
	}
	
}
