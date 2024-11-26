package ssf.day17.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import ssf.day17.services.RandomNumberService;

@Controller
@RequestMapping(path="/random")
public class RandomNumberController {

    @Autowired
    private RandomNumberService randNumSvc; 
    
    // GET /random
    // Accept: text/html
    @GetMapping
    public ModelAndView getRandom() {
        ModelAndView mav = new ModelAndView("random-num");
        mav.addObject("randNum", randNumSvc.getRandomInt());

        return mav;
    }

    // GET /random
    // Accept: application/json
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody   // to stop controller from converting resp to view
    public ResponseEntity<String> getRandomAsJson(
        @RequestParam(defaultValue = "100") int bounds) {
            
        JsonObject result = Json.createObjectBuilder()
                        .add("randNum", randNumSvc.getRandomInt(bounds))
                        .build();

        return ResponseEntity.ok(result.toString());
    }
}
