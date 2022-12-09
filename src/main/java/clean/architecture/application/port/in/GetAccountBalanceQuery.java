package clean.architecture.application.port.in;

import clean.architecture.domain.Account.AccountId;
import clean.architecture.domain.Money;

public interface GetAccountBalanceQuery {

    Money getAccountBalance(AccountId accountId);
}
