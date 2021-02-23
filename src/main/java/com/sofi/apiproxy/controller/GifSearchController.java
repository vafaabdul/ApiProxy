package com.sofi.apiproxy.controller;

import com.sofi.apiproxy.model.ApiResponse;
import com.sofi.apiproxy.model.ProxyGif;
import com.sofi.apiproxy.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping("/gifs")
public class GifSearchController {

    @Autowired
    GifService gifService;


    /**
     *
     * @param searchTerm
     * @return
     */
    @RequestMapping(value="/search/{term}" ,method= RequestMethod.GET ,produces = "application/json")
    public ResponseEntity<ApiResponse> searchGif(@PathVariable("term") String searchTerm){

            ApiResponse giphyResponse = new ApiResponse();
            List<ProxyGif> gifs=  gifService.getGifs(searchTerm);
            giphyResponse.setData(gifs);
            return new ResponseEntity<>(giphyResponse, HttpStatus.OK);
    }

}
