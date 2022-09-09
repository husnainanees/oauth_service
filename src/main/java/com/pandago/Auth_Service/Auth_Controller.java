package com.pandago.Auth_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ResponseBody
public class Auth_Controller {

    @Autowired
    private ClientService clientService;

//    @PostMapping("/addClient")
//    public Client addProduct(@RequestBody Client client){
//        return clientService.saveClient(client);
//    }
//    @GetMapping("/clients")
//     public List<Client>  findAllProducts(){
//        return clientService.getClients();
//    }
//    @PostMapping("/checkClient")
//       public ResponseEntity<?> checkLogin(@RequestBody Client client) throws Exception{
//           return ResponseEntity.ok(clientService.verifyClientId(client).getBody());
//     }
    @PostMapping("/oauth2/token")
       public ResponseEntity<?> generateToken(@RequestParam(value = "grant_type") String grant_type,@RequestParam(value = "client_id") String client_id,@RequestParam(value = "client_assertion_type") String client_assertion_type,@RequestParam(value = "client_assertion") String client_assertion,@RequestParam(value = "scope") String scope) throws Exception{
        
           return ResponseEntity.ok(clientService.getToken(client_id).getBody());
     }
    @PostMapping("/auth1")
       public ResponseEntity<?> generateToken(@RequestParam(value="token")String token) throws Exception{
        
           return ResponseEntity.ok(clientService.getJwtResponses(token)); 
     } 
    @PostMapping("/auth2")
       public ResponseEntity<?> verifyClient(@RequestParam(value = "grant_type") String grant_type,@RequestParam(value = "client_id") String client_id,@RequestParam(value = "client_assertion_type") String client_assertion_type,@RequestParam(value = "client_assertion") String client_assertion,@RequestParam(value = "scope") String scope) throws Exception{
                    if(clientService.verify(client_id,client_assertion)==true){
                        return ResponseEntity.ok(scope);
                    }else{
                        return ResponseEntity.ok("Not Valid");
                    }
 
     }
       @PostMapping("/auth3")
       public ResponseEntity<?> getAllClaims(@RequestParam(value="token")String token) throws Exception{
        
           return ResponseEntity.ok(clientService.verified(token)); 
     } 
}
