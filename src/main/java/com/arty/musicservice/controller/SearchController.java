package com.arty.musicservice.controller;

import com.arty.musicservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Map<String, Object>> searchApi(@RequestParam String query,
                                                         @RequestHeader(value = "Accept", defaultValue = "application/json") String acceptHeader) {
        if (acceptHeader.contains("text/html")) {
            // Serve the HTML page when the Accept header is "text/html"
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", "/search.html")
                    .build();
        }
        // Serve JSON for API requests
        Map<String, Object> results = searchService.search(query);
        return ResponseEntity.ok(results);
    }
}