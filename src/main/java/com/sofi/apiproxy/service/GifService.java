package com.sofi.apiproxy.service;

import com.sofi.apiproxy.model.ProxyGif;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GifService {
    List<ProxyGif> getGifs(String query);
}
