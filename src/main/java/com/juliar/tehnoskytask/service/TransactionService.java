package com.juliar.tehnoskytask.service;

import com.juliar.tehnoskytask.dto.CreateTransactionDto;
import com.juliar.tehnoskytask.dto.TransactionInfo;
import com.juliar.tehnoskytask.entity.Transaction;
import com.juliar.tehnoskytask.mapper.TransactionMapper;
import com.juliar.tehnoskytask.repository.TransactionRepository;
import com.juliar.tehnoskytask.service.api.CrudService;
import com.juliar.tehnoskytask.service.api.ITransactionService;
import com.juliar.tehnoskytask.validator.TransactionValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransactionService implements CrudService<Transaction, TransactionInfo>, ITransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final TransactionValidator validator;
    private final TransactionMapper mapper;

    @Override
    public TransactionInfo findById(Integer id) {

        var transactionFromDb = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found!"));
        return mapper.mapToTransactionInfo(transactionFromDb);
    }

    //исправить -> возвращать статус операции
    @Override
    public void transferInOneCurrency(CreateTransactionDto dto) {

        var sender = accountService.findById(dto.getSenderAccountId());
        var receiver = accountService.findById(dto.getReceiverAccountId());

        validator.checkCurrency(sender.getCurrency(), receiver.getCurrency());
        validator.checkCurrentBalance(sender.getCurrentBalance(), dto.getSum());

        var senderId = sender.getId();
        var receiverId = receiver.getId();

        if (senderId > receiverId) {
            synchronized (sender) {
                synchronized (receiver) {
                    transferHelper(dto);
                }
            }
        } else if (senderId < receiverId) {
            synchronized (receiver) {
                synchronized (sender) {
                    transferHelper(dto);
                }
            }
        }
    }

    @Override
    public List<TransactionInfo> findAll(Integer accountId) {
        accountService.findById(accountId);
        var transactions = transactionRepository.findAllByAccountId(accountId);
        return transactions.stream()
                .map(mapper::mapToTransactionInfo)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionInfo findByAccountIdAndTransactionId(Integer accountId, Integer transactionId) {
        var transaction = transactionRepository.findByAccountIdAndId(accountId, transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found!"));
        return mapper.mapToTransactionInfo(transaction);
    }

    private void transferHelper(CreateTransactionDto dto) {
        accountService.updateBalance(dto.getSenderAccountId(), dto.getSum().multiply(BigDecimal.valueOf(-1)));
        accountService.updateBalance(dto.getReceiverAccountId(), dto.getSum());
        var writeOff = mapper.getWriteOffTransaction(dto);
        transactionRepository.save(writeOff);
        var inflow = mapper.getInflowTransaction(dto);
        transactionRepository.save(inflow);
    }

}
