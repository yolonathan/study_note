package test.controller;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Jing Zhi Bao
 */
@RestController
@Slf4j
public class TestController {

    // reactor = jdk8 stream + jdk9 reactive stream
    // Mono 0-1 个元素
    // Flux 0-N 个元素

    @GetMapping("/1")
    public String get1() {
        log.info("get1 start");
        String str = createStr();
        log.info("get1 end");
        return str;
    }

    @GetMapping("/2")
    public Mono<String> get2() {
        log.info("get2 start");
        Mono<String> stringMono = Mono.fromSupplier(this::createStr);
        log.info("get2 end");
        return stringMono;
    }

    private String createStr() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "some thing";
    }

    @GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> flux() {
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            return "flux data--" + i;
        }));
        return result;
    }

}
