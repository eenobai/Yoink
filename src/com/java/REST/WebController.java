package com.java.REST;

import com.java.Repository.TestRepo;
import com.java.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class WebController {
    @Autowired
    TestRepo testRepo;


    @GetMapping("/testget")
    public List<TestModel> test(){
        return testRepo.findAll();
    }

    @PostMapping(value = "/testpost")
    public List<TestModel> persist(@RequestBody TestModel testModel){
        testRepo.save(testModel);
        return testRepo.findAll();
    }

    //TODO communication with web and other shait

}
