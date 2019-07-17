package com.example.webflux_with_lombo_reactiveweb.Repository;

import com.example.webflux_with_lombo_reactiveweb.Entity.ToDo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class ToDoRepository {

    private Flux<ToDo>  toDoFlux =
      Flux.fromIterable( Arrays.asList( new  ToDo("Hi DO your Home work please"),
                                          new   ToDo("Workout in the morning " , true),
                                            new ToDo("MAking Dinner  toNight "),
                                            new ToDo("Going to Delhi " , true)                     ) ) ;

    public Mono<ToDo> findById ( String id) {
        return  Mono.from( toDoFlux.filter( todo -> todo.getId().equals(id ) ) );
    }

    public Flux<ToDo>  findAll() {
        return toDoFlux;
    }
}
