package com.pluralsight.security.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pluralsight.security.entity.Portfolio;
import com.pluralsight.security.entity.Transaction;
import com.pluralsight.security.model.CryptoCurrencyDto;
import com.pluralsight.security.model.ListTransactionsDto;
import com.pluralsight.security.model.PortfolioPositionsDto;
import com.pluralsight.security.model.PositionDto;
import com.pluralsight.security.model.TransactionDetailsDto;
import com.pluralsight.security.repository.PortfolioRepository;

@Service
public class PortfolioQueryServiceNoSql implements PortfolioQueryService {

	private final CurrencyQueryService currencyService;
	private final PortfolioRepository portfolioRepository;
	private final PricingService pricingService;

	public PortfolioQueryServiceNoSql(CurrencyQueryService currencyService, PortfolioRepository portfolioRepository,
			PricingService pricingService) {
		this.currencyService = currencyService;
		this.portfolioRepository = portfolioRepository;
		this.pricingService = pricingService;
	}

	@Override
	public Optional<PortfolioPositionsDto> getPortfolioPositionsForUser(String username) {
		List<CryptoCurrencyDto> cryptos = currencyService.getSupportedCryptoCurrencies();
		return portfolioRepository.findByUsername(username).map(portfolio -> {
			List<PositionDto> positions = calculatePositions(cryptos, portfolio);
			Map<String, String> cryptoMap = convertCryptoListToMap(cryptos);
			return new PortfolioPositionsDto("", "", positions, cryptoMap);
		});
	}

	@Override
	public Optional<ListTransactionsDto> getPortfolioTransactionsForUser(String username) {
		return this.portfolioRepository.findByUsername(username).map(portfolio -> {
			List<Transaction> transactions = portfolio.getTransactions();
			return createListTransactionsResponse(username, transactions);
		});
	}
		

	@Override
	public Optional<PortfolioPositionsDto> getPortfolioPositions(String portfolioId) {
		List<CryptoCurrencyDto> cryptos = currencyService.getSupportedCryptoCurrencies();
		return portfolioRepository.findById(portfolioId).map(portfolio -> {
			List<PositionDto> positions = calculatePositions(cryptos, portfolio);
			Map<String, String> cryptoMap = convertCryptoListToMap(cryptos);
			return new PortfolioPositionsDto("", "", positions, cryptoMap);
		});
		
	}

	@Override
	public List<String> getPortfolioIds() {
		List<Portfolio> portfolios = this.portfolioRepository.findAll();
		return portfolios.stream().map(Portfolio::getId).collect(Collectors.toList());
	}

	private List<PositionDto> calculatePositions(List<CryptoCurrencyDto> cryptos, Portfolio portfolio) {
		List<PositionDto> positions = new ArrayList<>();
		for (CryptoCurrencyDto crypto : cryptos) {
			List<Transaction> cryptoTransactions = portfolio.getTransactionsForCoin(crypto.getSymbol());
			BigDecimal quantity = calculatePositionQuantity(cryptoTransactions);
			BigDecimal currentPrice = getCurrentPriceForCrypto(crypto);
			BigDecimal positionValue = calculatePositionValue(quantity, currentPrice);
			PositionDto position = new PositionDto(crypto, quantity, positionValue);
			positions.add(position);
		}
		return positions;
	}

	private BigDecimal getCurrentPriceForCrypto(CryptoCurrencyDto crypto) {
		BigDecimal currentPrice = pricingService.getCurrentPriceForCrypto(crypto.getSymbol());
		return currentPrice;
	}

	private BigDecimal calculatePositionValue(BigDecimal quantity, BigDecimal currentPrice) {
		BigDecimal positionValue = currentPrice.multiply(quantity);
		return positionValue;
	}

	private Map<String, String> convertCryptoListToMap(List<CryptoCurrencyDto> cryptos) {
		Map<String, String> cryptoMap = new HashMap<>();
		for (CryptoCurrencyDto crypto : cryptos) {
			cryptoMap.put(crypto.getSymbol(), crypto.getName());
		}
		return cryptoMap;
	}

	private BigDecimal calculatePositionQuantity(List<Transaction> cryptoTransactions) {
		BigDecimal quantity = BigDecimal.ZERO;
		for (Transaction transaction : cryptoTransactions) {
			switch (transaction.getType()) {
			case BUY:
				quantity = quantity.add(transaction.getQuantity());
				break;
			case SELL:
				quantity = quantity.subtract(transaction.getQuantity());
				break;
			default:
				break;
			}
		}
		return quantity;
	}

	private ListTransactionsDto createListTransactionsResponse(String username, List<Transaction> transactions) {
		List<TransactionDetailsDto> transationDetails = new ArrayList<>();
		for (Transaction transaction : transactions) {
			transationDetails.add(new TransactionDetailsDto(transaction.getId(),
					transaction.getCryptoCurrency().getSymbol(), transaction.getType().toString(),
					transaction.getQuantity().toString(), transaction.getPrice().toString()));
		}
		return new ListTransactionsDto(username, transationDetails);
	}

}