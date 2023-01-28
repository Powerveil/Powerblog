package com.power.controller;

import com.power.domain.ResponseResult;
import com.power.domain.dto.AddTagDto;
import com.power.domain.dto.TagListDto;
import com.power.domain.dto.UpdateTagDto;
import com.power.domain.vo.TagVo;
import com.power.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author power
 * @Date 2023/1/22 22:15
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        return tagService.pageRagList(pageNum, pageSize, tagListDto);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody AddTagDto addTagDto) {
        return tagService.addTag(addTagDto);
    }


    @DeleteMapping("/{id}")
    public ResponseResult deleteTagById(@PathVariable("id") Long id) {
        return tagService.deleteTagById(id);
    }

    @PutMapping
    public ResponseResult updateTag(@RequestBody UpdateTagDto updateTagDto) {
        return tagService.updateTag(updateTagDto);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }

//    @GetMapping("/list")
//    public String list() {
//        return "你好power";
//    }
}
