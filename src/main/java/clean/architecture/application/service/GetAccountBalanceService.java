package clean.architecture.application.service;

import clean.architecture.application.port.in.GetAccountBalanceQuery;
import clean.architecture.application.port.out.LoadAccountPort;
import clean.architecture.domain.Account.AccountId;
import clean.architecture.domain.Money;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQuery {

    private final LoadAccountPort loadAccountPort;

    @Override
    public Money getAccountBalance(AccountId accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now()).calculateBalance();
    }
}
