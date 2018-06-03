package nl.timgoes.transactionservice.transactionservice.resource;

import nl.timgoes.dbservice.modals.constant.Constant;
import nl.timgoes.dbservice.modals.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("")
public class TransactionResource {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{username}")
    private List<Transaction> getUserCreatedTransactions(@PathVariable(value = "username") String userName){
        String url = Constant.DB_SERVICE_URL + "/transaction/" + userName;

        ResponseEntity<List<Transaction>> userResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>() {
                });

        return userResponseEntity.getBody();
    }

    @GetMapping("/{username}/received")
    private List<Transaction> getUserReceivedTransactions(@PathVariable(value = "username") String userName){
        String url = Constant.DB_SERVICE_URL + "/transaction/" + userName + "/received";

        ResponseEntity<List<Transaction>> userResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>() {
                });

        return userResponseEntity.getBody();
    }

    @GetMapping("/{username}/received/inprogress")
    private List<Transaction> getUserReceivedInProgressTransactions(@PathVariable(value = "username") String userName){
        String url = Constant.DB_SERVICE_URL + "/transaction/" + userName + "/received/inprogress";

        ResponseEntity<List<Transaction>> userResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>() {
                });

        return userResponseEntity.getBody();
    }

    @GetMapping("/{username}/received/completed")
    private List<Transaction> getUserReceivedCompletedTransactions(@PathVariable(value = "username") String userName){
        String url = Constant.DB_SERVICE_URL + "/transaction/" + userName + "/received/completed";

        ResponseEntity<List<Transaction>> userResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>() {
                });

        return userResponseEntity.getBody();
    }

    @GetMapping("/{username}/received/canceled")
    private List<Transaction> getUserReceivedCanceledTransactions(@PathVariable(value = "username") String userName){
        String url = Constant.DB_SERVICE_URL + "/transaction/" + userName + "/received/canceled";

        ResponseEntity<List<Transaction>> userResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>() {
                });

        return userResponseEntity.getBody();
    }

    @GetMapping("/{username}/created")
    private List<Transaction> getUserCreatedInProgressTransactions(@PathVariable(value = "username") String userName){
        String url = Constant.DB_SERVICE_URL + "/transaction/" + userName + "/created/inprogress";

        ResponseEntity<List<Transaction>> userResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>() {
                });

        return userResponseEntity.getBody();
    }

    @GetMapping("/{username}/created/completed")
    private List<Transaction> getUserCreatedCompletedTransactions(@PathVariable(value = "username") String userName){
        String url = Constant.DB_SERVICE_URL + "/transaction/" + userName + "/created/completed";

        ResponseEntity<List<Transaction>> userResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>() {
                });

        return userResponseEntity.getBody();
    }

    @GetMapping("/{username}/created/canceled")
    private List<Transaction> getUserCreatedCanceledTransactions(@PathVariable(value = "username") String userName){
        String url = Constant.DB_SERVICE_URL + "/transaction/" + userName + "/created/canceled";

        ResponseEntity<List<Transaction>> userResponseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>() {
                });

        return userResponseEntity.getBody();
    }

    @PostMapping("{id}/accept")
    private Transaction acceptTransfer(@PathVariable(value = "id") Long id){
        String url = Constant.DB_SERVICE_URL + "/transaction/" + id + "/accept";
        return restTemplate.postForObject(url, null, Transaction.class);
    }

    @PostMapping("{id}/decline")
    private Transaction declineTransfer(@PathVariable(value = "id") Long id){
        String url = Constant.DB_SERVICE_URL + "/transaction/" + id + "/decline";
        return restTemplate.postForObject(url, null, Transaction.class);
    }

    @PostMapping("/create/gift")
    private Transaction createGift(
            @RequestParam(value = "creatorName") String creatorName,
            @RequestParam(value = "creditName") String creditName,
            @RequestParam(value = "amount") BigDecimal amount,
            @RequestParam(value = "receiverName") String receiverName) {
        String url = Constant.DB_SERVICE_URL + "/transaction/create/gift";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("creatorName", creatorName);
        params.add("creditName", creditName);
        params.add("amount", amount.toString());
        params.add("receiverName", receiverName);

        return restTemplate.postForObject(url, params, Transaction.class);
    }

    @PostMapping("/create/storage")
    private Transaction createGift(
            @RequestParam(value = "creatorName") String creatorName,
            @RequestParam(value = "creditName") String creditName,
            @RequestParam(value = "amount") BigDecimal amount) {
        String url = Constant.DB_SERVICE_URL + "/transaction/create/storage";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("creatorName", creatorName);
        params.add("creditName", creditName);
        params.add("amount", amount.toString());

        return restTemplate.postForObject(url, params, Transaction.class);
    }

    @PostMapping("/create/exchange")
    private Transaction createGift(
            @RequestParam(value = "creatorName") String creatorName,
            @RequestParam(value = "creditCreator") String creditCreator,
            @RequestParam(value = "amountCreator") BigDecimal amountCreator,
            @RequestParam(value = "receiverName") String receiverName,
            @RequestParam(value = "creditReceiver") String creditReceiver,
            @RequestParam(value = "amountReceiver") BigDecimal amountReceiver) {
        String url = Constant.DB_SERVICE_URL + "/transaction/create/exchange";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("creatorName", creatorName);
        params.add("creditCreator", creditCreator);
        params.add("amountCreator", amountCreator.toString());
        params.add("receiverName", receiverName);
        params.add("creditReceiver", creditReceiver);
        params.add("amountReceiver", amountReceiver.toString());

        return restTemplate.postForObject(url, params, Transaction.class);
    }
}
