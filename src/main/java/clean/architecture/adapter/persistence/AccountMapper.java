package clean.architecture.adapter.persistence;

import clean.architecture.domain.Account;
import clean.architecture.domain.Account.AccountId;
import clean.architecture.domain.Activity;
import clean.architecture.domain.Activity.ActivityId;
import clean.architecture.domain.ActivityWindow;
import clean.architecture.domain.Money;
import java.util.ArrayList;
import java.util.List;

public class AccountMapper {

    public Account mapToDomainEntity(
        AccountJpaEntity account,
        List<ActivityJpaEntity> activities,
        Long withdrawalBalance,
        Long depositBalance
    ) {
        Money baselineBalance = Money.subtract(
            Money.of(depositBalance),
            Money.of(withdrawalBalance));

        return Account.withId(
            new AccountId(account.getId()),
            baselineBalance,
            mapToActivityWindow(activities));
    }

    ActivityWindow mapToActivityWindow(List<ActivityJpaEntity> activities) {
        List<Activity> mappedActivities = new ArrayList<>();

        for (ActivityJpaEntity activity : activities) {
            mappedActivities.add(new Activity(
                new ActivityId(activity.getId()),
                new AccountId(activity.getOwnerAccountId()),
                new AccountId(activity.getSourceAccountId()),
                new AccountId(activity.getTargetAccountId()),
                activity.getTimestamp(),
                Money.of(activity.getAmount())));
        }

        return new ActivityWindow(mappedActivities);
    }
}
