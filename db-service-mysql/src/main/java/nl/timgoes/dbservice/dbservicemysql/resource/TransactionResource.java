package nl.timgoes.dbservice.dbservicemysql.resource;

import nl.timgoes.dbservice.dbservicemysql.model.Transaction;
import nl.timgoes.dbservice.dbservicemysql.model.TransactionStatus;
import nl.timgoes.dbservice.dbservicemysql.service.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionResource {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{username}")
    private List<Transaction> getUserCreatedTransactions(@PathVariable(value = "userName") String userName){
        return transactionService.findTransactionCreator(userName);
    }

    @GetMapping("/{username}/received")
    private List<Transaction> getUserReceivedTransactions(@PathVariable(value = "userName") String userName){
        return transactionService.findTransactionReceiver(userName);
    }

    @GetMapping("/{username}/inprogress")
    private List<Transaction> getUserInProgressTransactions(@PathVariable(value = "userName") String userName){
        return transactionService.findTransactionByStatus(userName, TransactionStatus.INPROGRESS);
    }

    @GetMapping("/{username}/completed")
    private List<Transaction> getUserCompletedTransactions(@PathVariable(value = "userName") String userName){
        return transactionService.findTransactionByStatus(userName, TransactionStatus.COMPLETE);
    }

    @GetMapping("/{username}/canceled")
    private List<Transaction> getUserCanceledTransactions(@PathVariable(value = "userName") String userName){
        return transactionService.findTransactionByStatus(userName, TransactionStatus.CANCELED);
    }

    @PostMapping("/create/gift")
    private Transaction createGift(
            @RequestParam(value = "creatorName") String creatorName,
            @RequestParam(value = "creditName") String creditName,
            @RequestParam(value = "amount") BigDecimal amount,
            @RequestParam(value = "receiverName") String receiverName) {
        return transactionService.createGiftTransaction(creatorName, creditName, amount, receiverName);
    }

    @PostMapping("/create/storage")
    private Transaction createStorage(
            @RequestParam(value = "creatorName") String creatorName,
            @RequestParam(value = "creditName") String creditName,
            @RequestParam(value = "amount") BigDecimal amount) {
        return transactionService.createStorageTransaction(creatorName, creditName, amount);
    }

    @PostMapping("/create/exchange")
    private Transaction createExchange(
            @RequestParam(value = "creatorName") String creatorName,
            @RequestParam(value = "creditCreator") String creditCreator,
            @RequestParam(value = "amountCreator") BigDecimal amountCreator,
            @RequestParam(value = "receiverName") String receiverName,
            @RequestParam(value = "creditReceiver") String creditReceiver,
            @RequestParam(value = "amountReceiver") BigDecimal amountReceiver) {
        return transactionService.createExchangeTransaction(
                creatorName,
                creditCreator,
                amountCreator,
                receiverName,
                creditReceiver,
                amountReceiver);
    }

    /*
    @GetMapping("/{userName}")
    private List<UserCredit> getUserCredits(@PathVariable(value = "userName") String userName) {
        return userCreditService.findByUserOrdered(userName);
    }


    @PostMapping("/update")
    private UserCredit updateUserCredit(@RequestParam(value = "userName") String userName,
                                        @RequestParam(value = "creditName") String creditName,
                                        @RequestParam(value = "amount") BigDecimal amount) {
        return userCreditService.updateUserCredit(creditName, userName, amount);
    }*/
}
