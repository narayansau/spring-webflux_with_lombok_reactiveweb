package com.example.webflux_with_lombo_reactiveweb.Handler;

import com.example.webflux_with_lombo_reactiveweb.Entity.ToDo;
import com.example.webflux_with_lombo_reactiveweb.Repository.ToDoRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;



@Component
public class ToDoHandler{

    private ToDoRepository myrepository;

    public ToDoHandler(ToDoRepository myrepository) {
        this.myrepository=myrepository;
    }
    public Mono<ServerResponse>   getToDo (ServerRequest request) {
        String toDoId = request.pathVariable( "id" );
        Mono<ServerResponse>  notFound  =
                     ServerResponse.notFound().build();
        Mono<ToDo> toDo = this.myrepository.findById( toDoId );

        return  toDo . flatMap( t -> ServerResponse .
                                                     ok() .
                                                    contentType( MediaType.APPLICATION_JSON )
                                                . body( fromObject(t )
                                    )).switchIfEmpty( notFound );

    }

    public Mono<ServerResponse> getToDos ( ServerRequest request) {
        Flux<ToDo> toDos = this.myrepository.findAll();

        return  ServerResponse
                .ok()
                .contentType( MediaType.APPLICATION_JSON )
                .body(toDos , ToDo.class);
    }
}
