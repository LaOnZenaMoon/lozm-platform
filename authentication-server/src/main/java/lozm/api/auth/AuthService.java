package lozm.api.auth;

import lombok.RequiredArgsConstructor;
import lozm.entity.auth.Account;
import lozm.global.exception.ServiceException;
import lozm.object.dto.auth.AccountGetDto;
import lozm.object.vo.auth.AccountVo;
import lozm.repository.auth.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository accountRepository;


    public List<AccountGetDto> getAccountList() throws Exception {
        List<Account> accountList = accountRepository.selectAccountList();
        List<AccountGetDto> rtnList = new ArrayList<>();
        for (Account account : accountList) {
            AccountGetDto dto = AccountGetDto.builder()
                    .id(account.getId())
                    .name(account.getName())
                    .identifier(account.getIdentifier())
                    .password(null)
                    .type(account.getType())
                    .build();

            rtnList.add(dto);
        }

        return rtnList;
    }

    @Transactional
    public void save(AccountVo accountVo) throws Exception {
        Account account = new Account();
        account.insertAccount(accountVo);

        //1. check ID duplicated
        List<Account> findAccountsIdDuplicated = accountRepository.findByIdentifier(account.getIdentifier());
        if(findAccountsIdDuplicated.size() > 0) throw new ServiceException("USER_0001", "Account Identifier is duplicated.");

        accountRepository.save(account);
    }

    @Transactional
    public void update(AccountVo accountVo) throws Exception {
        Optional<Account> findAccount = findAccount(accountVo.getId());
        findAccount.get().updateAccount(accountVo);
    }

    @Transactional
    public void delete(AccountVo accountVo) throws Exception {
        Optional<Account> findAccount = findAccount(accountVo.getId());
        findAccount.get().deleteAccount(accountVo);
    }

    private Optional<Account> findAccount(Long userId) {
        Optional<Account> findAccount = accountRepository.findById(userId);
        findAccount.orElseThrow(() -> {
            throw new ServiceException("USER_0002", "Account doesn't exist.");
        });
        return findAccount;
    }

}

