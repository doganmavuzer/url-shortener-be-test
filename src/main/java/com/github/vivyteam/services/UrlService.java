package com.github.vivyteam.services;

import com.github.vivyteam.url.api.contract.FullUrl;
import com.github.vivyteam.url.api.contract.ShortenedUrl;

import javax.validation.constraints.NotBlank;

public interface UrlService {

    FullUrl getFullUrl(@NotBlank String shortUrl);

    ShortenedUrl getShortenUrl(@NotBlank String longUrl);
}
