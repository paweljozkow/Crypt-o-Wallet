package pl.jozkow.CryptoWallet.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Pawel Jozkow
 *	pawel.jozkow@gmail.com
 */

@Entity
@Table(name = "Transactions")
public class SingleTransaction {

	@Id
	@GeneratedValue
	private long TransactionId;

	@Column(name = "Type", nullable = false)

	private TransactionType buyOrSell;

	@Column(name = "Rate", precision = 10, scale = 4, nullable = false)
	private BigDecimal transactionRate;

	@Column(name = "Amount", columnDefinition = "Decimal(16,8)", nullable = false)
	private BigDecimal transactionAmount;

	@Column(name = "Value", columnDefinition = "Decimal(16,4)")
	private BigDecimal transactionValue;

	public SingleTransaction() {
	};

	@JsonCreator
	public SingleTransaction(

			@JsonProperty("type") TransactionType buyOrSell, 
			@JsonProperty("rate") BigDecimal trRate,
			@JsonProperty("amount") BigDecimal trAmount) {

		this.buyOrSell = buyOrSell;
		this.transactionRate = trRate.setScale(4, RoundingMode.HALF_EVEN);
		this.transactionAmount = trAmount.setScale(8, RoundingMode.HALF_EVEN);
		this.transactionValue = trRate.multiply(trAmount).setScale(4, RoundingMode.HALF_EVEN);

	}

	public long getTransactionId() {
		return TransactionId;
	}

	public long checkTransactionId(long idToCheck) {
		return TransactionId;
	}

	public TransactionType getTransactionType() {
		return buyOrSell;
	}

	public BigDecimal getTransactionRate() {
		return transactionRate;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public BigDecimal getTransactionValue() {
		return transactionValue;
	}

	public void setTransactionRate(BigDecimal transactionRate) {
		this.transactionRate = transactionRate;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public void setTransactionValue(BigDecimal transactionValue) {
		this.transactionValue = transactionValue;
	}

	@Override
	public String toString() {
		return "SingleTransaction [TransactionId=" + TransactionId + ", transactionRate=" + transactionRate
				+ ", transactionAmount=" + transactionAmount + ", transactionValue=" + transactionValue + "]";
	}

}
