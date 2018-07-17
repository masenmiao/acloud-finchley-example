/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package acloud.simple.web;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@RestController
public class FluxController {

    @GetMapping(path="/flux",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> welcome() {

        final AtomicInteger count = new AtomicInteger(1);   // 1 用于计数；
        return Flux.create(s ->
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        s.next(count.getAndIncrement());
                        if (count.get() == 10) {
                            s.complete();
                        }
                    }
                }, 100, 100)
            ,FluxSink.OverflowStrategy.BUFFER);
    }

}
