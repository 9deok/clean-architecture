package clean.architecture.application.service;

import clean.architecture.application.port.in.SendMoneyCommand;
import clean.architecture.application.port.in.SendMoneyUseCase;
import clean.architecture.application.port.out.AccountLock;
import clean.architecture.application.port.out.LoadAccountPort;
import clean.architecture.application.port.out.UpdateAccountStatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class SendMoneyService implements SendMoneyUseCase {

    private final LoadAccountPort loadAccountPort;
    private final AccountLock accountLock;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        return false;
    }
}
