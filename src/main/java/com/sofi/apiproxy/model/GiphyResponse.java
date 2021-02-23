package com.sofi.apiproxy.model;

import lombok.Data;

import java.util.List;

@Data
public class GiphyResponse {
    List<Gif> data;
}
