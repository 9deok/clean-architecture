package clean.architecture.application.port.in;

import static java.util.Objects.requireNonNull;

import clean.architecture.domain.Account.AccountId;
import clean.architecture.domain.Money;
import clean.architecture.shared.SelfValidating;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

    @NonNull
    private final AccountId sourceAccountId;
    @NonNull
    private final AccountId targetAccountId;
    @NonNull
    private final Money money;

    public SendMoneyCommand(
        AccountId sourceAccountId,
        AccountId targetAccountId,
        Money money
    ) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;
//        requireGreaterThan(money, 0);
    }

}
