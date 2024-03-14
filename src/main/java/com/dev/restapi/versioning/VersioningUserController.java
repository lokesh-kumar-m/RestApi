package com.dev.restapi.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningUserController {
    
    @GetMapping("/v1/person")
    public PersonV1 getVersion1OfPerson(){
        return new PersonV1("Jhon Smith");
    }

    @GetMapping("/v2/person")
    public PersonV2 getVersion2OfPerson(){
        return new PersonV2(new Name("Jhon", "smith"));
    }

    @GetMapping(path="/person",params = "version=1")
    public PersonV1 getPersonParamsO(){
        return new PersonV1("Post Malone");
    }

    @GetMapping(path="/person",params = "version=2")
    public PersonV2 getPersonParamsT(){
        return new PersonV2(new Name("Posty","Melony"));
    }

}
