package com.bigModel.backend.controller;

import com.bigModel.backend.pojo.Keyword;
import com.bigModel.backend.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/keyword")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @PostMapping("/add")
    public void addKeyword(@RequestBody Keyword keyword) {
        System.out.println(keyword.getKeyword());
        keywordService.addKeyword(keyword);
    }

    @GetMapping("/delete/{id}")
    public void deleteKeyword(@PathVariable(value = "id") Integer id) {
        System.out.println(id);
        keywordService.deleteKeyword(id);
    }

    @GetMapping("/listAll")
    public List<Keyword> listAll() {
        return keywordService.listAllKeywords();
    }
}
