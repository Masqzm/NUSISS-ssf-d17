package ssf.day17.controllers;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping
public class TimeController {
    // POST /customer
    // Content-Type: application/x-www-form-urlencoded
    // Accept: text/html
    @PostMapping(path="/customer", 
                produces = MediaType.TEXT_HTML_VALUE,
                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> postCustomerFormAsHTML(@RequestBody MultiValueMap<String, String> form) {
        System.out.printf("PAYLOAD (form): %s\n", form);

        String html = "<h1>" + form.getFirst("name") + " registered!</h1>";
 
        // Response: 202 Accepted
        // Content-Type: text/html
        // X-MyHeader: abc123
        // Body: <html>
        return ResponseEntity.accepted()
                            .header("X-MyHeader", "abc123")
                            .body(html);
    }

    // POST /customer
    // Content-Type: application/json
    // Accept: application/json
    @PostMapping(path="/customer", 
                produces = MediaType.APPLICATION_JSON_VALUE,    // to client
                consumes = MediaType.APPLICATION_JSON_VALUE)    // from client
    public ResponseEntity<String> postCustomer(@RequestBody String payload) {
        System.out.printf("PAYLOAD: %s\n", payload);

        // Response: 202 Accepted
        // Content-Type: application/json
        // X-MyHeader: abc123
        // Body: {}
        return ResponseEntity.accepted()
                            .header("X-MyHeader", "abc123")
                            .body("{}");
    }



    // GET /time
    // Accept: application/json - response data format to client 
    @GetMapping(path="/time", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTime() {
        JsonObject resp = Json.createObjectBuilder()
                        .add("time", (new Date().toString()))
                        .build();

        // Response: 200 OK
        // Content-Type: application.json
        // {"time": "<time>"}


        // return ResponseEntity.status(200)
        //                     .contentType(MediaType.APPLICATION_JSON)
        //                     .body(resp.toString());
        return ResponseEntity.ok(resp.toString());
    }
    
    // GET /time-plaintxt
    // Accept: text/plain
    @GetMapping(path="/time", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getTimePlainTxt() {
        String resp = new Date().toString();
        // Response: 200 OK
        // Content-Type: text/plain
        // "<time>"

        return ResponseEntity.ok(resp);
    }
}
