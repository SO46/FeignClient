package com.example.feignclient;

import com.example.feignclient.giphy.GiphyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final GiphyService giphyService;

    @GetMapping("/")
    public ResponseEntity<?> isRich() {
        byte[] bytes = giphyService.isRich();
        if (bytes == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_GIF).body(bytes);
    }

}