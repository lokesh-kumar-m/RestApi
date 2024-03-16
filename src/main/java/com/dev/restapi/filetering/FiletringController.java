package com.dev.restapi.filetering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.restapi.versioning.Name;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FiletringController {

    //Static filtering 
    //Adding JSONIgnore will always ignore the attribute while returning the response
    //JsonIgnoreProperties will also ignore the attribute, this is used on class level. @JsonIgnoreProperties("name1","name2")
    @GetMapping("filtering")
    public Name filteringName(){
        return new Name("name1","name2");
    }

    @GetMapping("filtering/list")
    public List<Name> filteringListName(){
        return Arrays.asList(new Name("name1", "name2"));
    }

    @GetMapping("filtering/mapingjackson")
    public MappingJacksonValue filteringValue(){
        Name name=new Name("name1","name2");
        MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(name);
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("firstName");
        FilterProvider filters=new SimpleFilterProvider().addFilter("ListFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("filtering/list/mapingjackson")
    public MappingJacksonValue filteringList(){
        List<Name> name=Arrays.asList(new Name("name1", "name2"));
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(name);
        
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("lastName");
        FilterProvider filters =new SimpleFilterProvider().addFilter("ListFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
