package org.interledger.ilp.ledger;

import java.security.PublicKey;
import java.util.Currency;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.format.MonetaryAmountFormat;

import org.interledger.ilp.core.InterledgerAddress;
import org.interledger.ilp.core.ledger.model.LedgerInfo;

/**
 * LedgerInfo factory
 *
 * @author mrmx
 */
public class LedgerInfoFactory {

    private final static LedgerInfo EUR = from(Currencies.EURO);
    private final static LedgerInfo USD = from(Currencies.US_DOLLAR);

    public static LedgerInfo getEUR() {
        return EUR;
    }

    public static LedgerInfo getUSD() {
        return USD;
    }

    public static LedgerInfo from(Currencies currency) {
        return from(currency.getCode());
    }

    public static LedgerInfo from(Currency currency) {
        return from(currency.getCurrencyCode());
    }

    public static LedgerInfo from(String currencyCode) {
        return from(Monetary.getCurrency(currencyCode));
    }

    public static LedgerInfo from(CurrencyUnit currency) {
        return create(
                CurrencyUtils.getPrecision(currency),
                currency.getDefaultFractionDigits(),
                currency.getCurrencyCode()
        );
    }

    public static LedgerInfo create(int precission, int scale, String currencyCode) {
        return create(precission, scale, currencyCode, CurrencyUtils.getSymbol(currencyCode));
    }

    public static LedgerInfo create(int precission, int scale, String currencyCode, String currencySymbol) {
        return new LedgerInfoImpl(precission, scale, currencyCode, currencySymbol);
    }

    public static final class LedgerInfoImpl implements LedgerInfo {

        private int precision;
        private int scale;
        private String currencyCode;
        private String currencySymbol;

        public LedgerInfoImpl(int precision, int scale, String currencyCode, String currencySymbol) {
            this.precision = precision;
            this.scale = scale;
            this.currencyCode = currencyCode;
            this.currencySymbol = currencySymbol;
        }

        /**
         * @return the precision
         */
        public int getPrecision() {
            return precision;
        }

        /**
         * @return the scale
         */
        public int getScale() {
            return scale;
        }

        /**
         * @return the currencyCode
         */
        public String getCurrencyCode() {
            return currencyCode;
        }

        /**
         * @return the currencySymbol
         */
        public String getCurrencySymbol() {
            return currencySymbol;
        }

		@Override
		public InterledgerAddress getAddressPrefix() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PublicKey getConditionSignPublicKey() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CurrencyUnit getCurrencyUnit() {
			// TODO Auto-generated method stub
			return null;
		}
		
		// 

		@Override
		public String getId() {
			throw new RuntimeException("Not implemented"); // TODO:(0) FIXME Implement

		}

		@Override
		public MonetaryAmountFormat getMonetaryAmountFormat() {
			throw new RuntimeException("Not implemented"); // TODO:(0) FIXME Implement
		}

		@Override
		public PublicKey getNotificationSignPublicKey() {
			throw new RuntimeException("Not implemented"); // TODO:(0) FIXME Implement
		}

    }
}
