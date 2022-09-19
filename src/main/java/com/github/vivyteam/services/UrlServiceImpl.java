package com.github.vivyteam.services;

import com.github.vivyteam.exceptions.UrlExpiredException;
import com.github.vivyteam.exceptions.UrlNotFoundException;
import com.github.vivyteam.exceptions.UrlNotValidException;
import com.github.vivyteam.models.Url;
import com.github.vivyteam.repository.UrlRepository;
import com.github.vivyteam.url.api.contract.FullUrl;
import com.github.vivyteam.url.api.contract.ShortenedUrl;
import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService{

    private final UrlRepository urlRepository;


    private final String address;

    public UrlServiceImpl(UrlRepository urlRepository,@Value("${server.port}") int port) throws UnknownHostException {
        this.urlRepository = urlRepository;
        this.address = "http://localhost:"+port+"/";
    }

    /*
    Method accepts shortUrl and searching the short url on database then return saved full url
     */
    @Override
    public FullUrl getFullUrl(String shortUrl) {

        String hashCode = shortUrl.replace(address,"");

        Optional<Url> url = urlRepository.findById(hashCode);

        if (url.isEmpty()){
            throw new UrlNotFoundException("This URL is not found!");
        }
        if(isExpired(url.get().getCreateDate())){
            urlRepository.deleteById(url.get().getId());
            throw new UrlExpiredException("This URL is expired!");
        }

        return new FullUrl(url.get().getOriginalUrl());
    }

    /*
    This methods accepts longUrl, after hashed this url check it on database and expire date
    If it is not usable anymore, it deletes the record because there are not additional delete request requirement.
    At the end it saves the record.
     */
    @Override
    public ShortenedUrl getShortenUrl(String longUrl) {

        if (!urlValidator(longUrl)){
            throw new UrlNotValidException("This URL is not valid!");
        }

        String hashData = Hashing.murmur3_32_fixed().hashString(longUrl, Charset.defaultCharset()).toString();

        String shortedUrl = address + hashData;

        Url url = Url.builder().id(hashData).createDate(LocalDateTime.now()).originalUrl(longUrl).build();

        Optional<Url> data = urlRepository.findById(url.getId());

        if (data.isPresent() && isExpired(data.get().getCreateDate())){
            urlRepository.deleteById(url.getId());
        }

        urlRepository.save(url);

        return new ShortenedUrl(shortedUrl);
    }
    private boolean urlValidator(String url){
        UrlValidator urlValidator = new UrlValidator();

        return urlValidator.isValid(url);
    }

    private boolean isExpired(LocalDateTime createDate){
        LocalDateTime expiredDate = createDate.plusDays(2);

        return !createDate.isBefore(expiredDate);
    }


}
