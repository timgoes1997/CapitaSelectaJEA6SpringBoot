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
    private List<Transaction> getUserCreatedTransactions(@PathVariable(value = "username") String userName){
        return transactionService.findTransactionCreator(userName);
    }

    @GetMapping("/{username}/received")
    private List<Transaction> getUserReceivedTransactions(@PathVariable(value = "username") String userName){
        return transactionService.findTransactionReceiver(userName);
    }

    @GetMapping("/{username}/received/inprogress")
    private List<Transaction> getUserReceivedInProgressTransactions(@PathVariable(value = "username") String userName){
        return transactionService.findTransactionByStatusAndReceiver(userName, TransactionStatus.INPROGRESS);
    }

    @GetMapping("/{username}/received/completed")
    private List<Transaction> getUserReceivedCompletedTransactions(@PathVariable(value = "username") String userName){
        return transactionService.findTransactionByStatusAndReceiver(userName, TransactionStatus.COMPLETE);
    }

    @GetMapping("/{username}/received/canceled")
    private List<Transaction> getUserReceivedCanceledTransactions(@PathVariable(value = "username") String userName){
        return transactionService.findTransactionByStatusAndReceiver(userName, TransactionStatus.CANCELED);
    }

    @GetMapping("/{username}/created/inprogress")
    private List<Transaction> getUserCreatedInProgressTransactions(@PathVariable(value = "username") String userName){
        return transactionService.findTransactionByStatusAndCreator(userName, TransactionStatus.INPROGRESS);
    }

    @GetMapping("/{username}/created/completed")
    private List<Transaction> getUserCreatedCompletedTransactions(@PathVariable(value = "username") String userName){
        return transactionService.findTransactionByStatusAndCreator(userName, TransactionStatus.COMPLETE);
    }

    @GetMapping("/{username}/created/canceled")
    private List<Transaction> getUserCreatedCanceledTransactions(@PathVariable(value = "username") String userName){
        return transactionService.findTransactionByStatusAndCreator(userName, TransactionStatus.CANCELED);
    }

    @PostMapping("{id}/accept")
    private Transaction acceptTransfer(@PathVariable(value = "id") Long id){
        return transactionService.acceptTransaction(id);
    }

    @PostMapping("{id}/decline")
    private Transaction declineTransfer(@PathVariable(value = "id") Long id){
        return transactionService.denyTransaction(id);
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
