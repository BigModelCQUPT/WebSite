package com.bigModel.backend.controller;

import com.bigModel.backend.advice.result.Result;
import com.bigModel.backend.pojo.Keyword;
import com.bigModel.backend.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/keyword")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @PostMapping("/add")
    public Result addKeyword(@RequestBody Keyword keyword) {
        keywordService.addKeyword(keyword);
        return Result.success("success");
    }

    @GetMapping("/delete/{id}")
    public Result deleteKeyword(@PathVariable(value = "id") Integer id) {
        System.out.println(id);
        keywordService.deleteKeyword(id);
        return Result.success("success");
    }

    @GetMapping("/listAll")
    public List<Keyword> listAll() {
        return keywordService.listAllKeywords();
    }
}
