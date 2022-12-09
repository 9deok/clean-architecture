package clean.architecture.domain;

import clean.architecture.application.port.in.SendMoneyCommand;
import java.util.List;
import clean.architecture.domain.Account.AccountId;
import lombok.NonNull;

public class ActivityWindow {

    private List<Activity> activities;

    public ActivityWindow(@NonNull List<Activity> activities) {
        this.activities = activities;
    }

    public Money calculateBalance(AccountId accountId) {
        Money depositBalance = activities.stream()
            .filter(a -> a.getTargetAccountId().equals(accountId))
            .map(Activity::getMoney)
            .reduce(Money.ZERO, Money::add);

        Money withdrawalBalance = activities.stream()
            .filter(a -> a.getSourceAccountId().equals(accountId))
            .map(Activity::getMoney)
            .reduce(Money.ZERO, Money::add);

        return Money.add(depositBalance, withdrawalBalance.negate());
    }

    public void addActivity(Activity withdrawal) {
        this.activities.add(withdrawal);
    }

}
