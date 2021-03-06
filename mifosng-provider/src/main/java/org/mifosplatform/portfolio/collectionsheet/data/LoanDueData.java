/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.collectionsheet.data;

import java.math.BigDecimal;

/**
 * Immutable data object for representing loan with dues (example: loan is due for disbursement, repayments).
 */
public class LoanDueData {

    private final Long loanId;
    private final String accountId;
    private final Integer accountStatusId;
    private final String productShortName;
    private final Long productId;
    private final String currencyCode;
    private final Integer currencyDigits;
    private BigDecimal disbursementAmount = BigDecimal.ZERO;
    private BigDecimal principalDue = BigDecimal.ZERO;
    private BigDecimal principalPaid = BigDecimal.ZERO;
    private BigDecimal interestDue = BigDecimal.ZERO;
    private BigDecimal interestPaid = BigDecimal.ZERO;
    private BigDecimal chargesDue = BigDecimal.ZERO;

    public LoanDueData(final Long loanId, final String accountId, final Integer accountStatusId, final String productShortName,
            final Long productId, final String currencyCode, final Integer currencyDigits, final BigDecimal disbursementAmount,
            final BigDecimal principalDue, final BigDecimal principalPaid, final BigDecimal interestDue, final BigDecimal interestPaid,
            final BigDecimal chargesDue) {
        this.loanId = loanId;
        this.accountId = accountId;
        this.accountStatusId = accountStatusId;
        this.productShortName = productShortName;
        this.productId = productId;
        this.currencyCode = currencyCode;
        this.currencyDigits = currencyDigits;
        this.disbursementAmount = disbursementAmount;
        this.principalDue = principalDue;
        this.principalPaid = principalPaid;
        this.interestDue = interestDue;
        this.interestPaid = interestPaid;
        this.chargesDue = chargesDue;
    }

    public Long getLoanId() {
        return this.loanId;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public Integer getAccountStatusId() {
        return this.accountStatusId;
    }

    public String getProductShortName() {
        return this.productShortName;
    }

    public Long getProductId() {
        return this.productId;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public Integer getCurrencyDigits() {
        return this.currencyDigits;
    }

    public BigDecimal getDisbursementAmount() {
        return this.disbursementAmount;
    }

    public BigDecimal getPrincipalDue() {
        return this.principalDue;
    }

    public BigDecimal getPrincipalPaid() {
        return this.principalPaid;
    }

    public BigDecimal getInterestDue() {
        return this.interestDue;
    }

    public BigDecimal getInterestPaid() {
        return this.interestPaid;
    }

    public BigDecimal getChargesDue() {
        return this.chargesDue;
    }

}