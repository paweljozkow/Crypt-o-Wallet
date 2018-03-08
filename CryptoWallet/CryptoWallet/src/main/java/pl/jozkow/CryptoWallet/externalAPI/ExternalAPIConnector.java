package pl.jozkow.CryptoWallet.externalAPI;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Pawel Jozkow
 *	pawel.jozkow@gmail.com
 */

@Component
public class ExternalAPIConnector {

	List<CryptoJSON> listBTC;
	ObjectMapper mapper = new ObjectMapper();

	public BigDecimal getCurrentPriceofCrypto(String crypto) {

		String url = "https://api.coinmarketcap.com/v1/ticker/" + crypto + "/";
		
		try {

			listBTC = mapper.readValue(new URL(url),
					new TypeReference<List<CryptoJSON>>() {
					});

		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

		return new BigDecimal(listBTC.get(0).getPrice_usd());

	}

}
