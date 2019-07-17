package com.example.webflux_with_lombo_reactiveweb.Controller;


import com.example.webflux_with_lombo_reactiveweb.Entity.ToDo;
import com.example.webflux_with_lombo_reactiveweb.Repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class ToDoController{
    private ToDoRepository myRepository;

@Autowired
    public ToDoController(ToDoRepository mypository) {
        this.myRepository=mypository;
    }

    @GetMapping("/todo")
    public Flux<ToDo >  getToDOs () {
        return  this.myRepository.findAll();
    }

    @GetMapping("/todo/{id}")
    public Mono<ToDo>  getTODO (@PathVariable String id) {
        return this.myRepository.findById( id );
    }
}
