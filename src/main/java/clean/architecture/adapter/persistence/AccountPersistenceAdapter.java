package clean.architecture.adapter.persistence;

import clean.architecture.application.port.out.LoadAccountPort;
import clean.architecture.application.port.out.UpdateAccountStatePort;
import clean.architecture.domain.Account;
import clean.architecture.domain.Account.AccountId;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {

    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;
    @Override
    public Account loadAccount(AccountId accountId, LocalDateTime baselineDate) {
        AccountJpaEntity account = accountRepository.findById(accountId.getValue())
            .orElseThrow(EntityNotFoundException::new);

        List<ActivityJpaEntity> activities = activityRepository.findByOwnerSince(
            accountId.getValue(), baselineDate
        );

        Long withdrawalBalance = orZero(
            activityRepository.getWithdrawalBalanceUntil(accountId.getValue(), baselineDate));

        Long depositBalance = orZero(
            activityRepository.getDepositBalanceUntil(accountId.getValue(), baselineDate));

        return accountMapper
            .mapToDomainEntity(account, activities, withdrawalBalance, depositBalance);
    }

    private Long orZero(Long value) {
        return value == null ? 0L : value;
    }
}
