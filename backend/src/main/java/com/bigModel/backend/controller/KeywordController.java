package com.bigModel.backend.controller;

<<<<<<< HEAD
=======
import com.bigModel.backend.advice.result.Result;
import com.bigModel.backend.config.filter.JwtAuthenticationTokenFilter;
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
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
<<<<<<< HEAD
    public void addKeyword(@RequestBody Keyword keyword) {
        keywordService.addKeyword(keyword);
    }

    @GetMapping("/delete/{id}")
    public void deleteKeyword(@PathVariable(value = "id") Integer id) {
        System.out.println(id);
        keywordService.deleteKeyword(id);
=======
    public Result addKeyword(@RequestBody Keyword keyword) {
        keywordService.addKeyword(keyword);
        return Result.success("success");
    }

    @GetMapping("/delete/{id}")
    public Result deleteKeyword(@PathVariable(value = "id") Integer id) {
        System.out.println(id);
        keywordService.deleteKeyword(id);
        return Result.success("success");
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
    }

    @GetMapping("/listAll")
    public List<Keyword> listAll() {
<<<<<<< HEAD
=======
        System.out.println(JwtAuthenticationTokenFilter.getUserBasic().getUserId());
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        return keywordService.listAllKeywords();
    }
}
