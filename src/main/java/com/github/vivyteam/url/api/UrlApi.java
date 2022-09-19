package com.github.vivyteam.url.api;

import com.github.vivyteam.services.UrlService;
import com.github.vivyteam.url.api.contract.FullUrl;
import com.github.vivyteam.url.api.contract.ShortenedUrl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class UrlApi {

    private final UrlService urlService;

    public UrlApi(UrlService urlService) {
        this.urlService = urlService;
    }

    /*
    I have changed the PathVariable to RequestParam because the slash was making problem and when we give the url as PathVariable,
     all address becomes complicated address
     */
    @GetMapping("/short")
    public Mono<ShortenedUrl> shortUrl(@RequestParam("url") final String url) {

        return Mono.just(urlService.getShortenUrl(url));
    }

    @GetMapping("/{shortenedUrl}/full")
    public Mono<FullUrl> getFullUrl(@PathVariable final String shortenedUrl) {
        // TODO: implement logic to fetch the full url
        return Mono.just(urlService.getFullUrl(shortenedUrl));
    }

    @GetMapping("/{shortenedUrl}")
    private ResponseEntity<Object> redirect(@PathVariable final String shortenedUrl){
        FullUrl fullUrl = urlService.getFullUrl(shortenedUrl);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(fullUrl.getUrl())).build();

    }

}
