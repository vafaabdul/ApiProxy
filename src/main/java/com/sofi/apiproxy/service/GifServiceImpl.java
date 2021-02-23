package com.sofi.apiproxy.service;

import com.sofi.apiproxy.model.Constants;
import com.sofi.apiproxy.model.GiphyResponse;
import com.sofi.apiproxy.model.Gif;
import com.sofi.apiproxy.model.ProxyGif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class GifServiceImpl implements  GifService {

    @Autowired
    ServiceInvoker serviceInvoker;
    @Autowired
    private Environment env;


    /**
     *
     * @param query
     * @return
     */
    @Override
    public List<ProxyGif> getGifs(String query) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(env.getProperty("giphy.search.url"))
                .queryParam("api_key",env.getProperty("giphy.api.key"))
                .queryParam("q",query)
                .queryParam("limit",env.getProperty("giphy.result.limit"));
        ResponseEntity<GiphyResponse>  responseEntity = serviceInvoker.executeGet(builder.toUriString(),httpHeaders, GiphyResponse.class);
        //check if response body is not null
        if(responseEntity.getBody()!=null){
            return constructResponse(responseEntity.getBody().getData());
        }
        return  new ArrayList<>();
    }

    /**
     *
     * @param gifList
     * @return
     */
    private List<ProxyGif> constructResponse(List<Gif> gifList){
        List<ProxyGif> result = new ArrayList<>();
        //check if gif list is present and has 5 elements
        if(gifList!=null && gifList.size()==Constants.GIF_LIMIT){
            for(Gif gif : gifList){
                ProxyGif proxyGif = ProxyGif.builder()
                        .id(gif.getId())
                        .url(gif.getUrl()).build();
                result.add(proxyGif);
            }
        }
        return result;

    }
}
