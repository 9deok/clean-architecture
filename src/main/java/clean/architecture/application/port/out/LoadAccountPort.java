package clean.architecture.application.port.out;

import clean.architecture.domain.Account;
import clean.architecture.domain.Account.AccountId;
import java.time.LocalDateTime;

public interface LoadAccountPort {

    Account loadAccount(AccountId accountId, LocalDateTime baselineDate);

}
