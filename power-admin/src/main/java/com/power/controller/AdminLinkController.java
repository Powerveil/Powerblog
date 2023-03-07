package com.power.controller;

import com.power.domain.ResponseResult;
import com.power.domain.dto.AddLinkDto;
import com.power.domain.vo.PageListVo;
import com.power.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author power
 * @Date 2023/3/7 14:57
 */
@RestController
@RequestMapping("/content/link")
public class AdminLinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult getPageListLink(Long pageNum, Long pageSize, String name, String status) {
        return linkService.getPageListLink(pageNum, pageSize, name, status);
    }


    @PostMapping
    public ResponseResult addLink(@RequestBody AddLinkDto addLinkDto) {
        return linkService.addLink(addLinkDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getLinkById(@PathVariable("id") Long id) {
        return linkService.getLinkById(id);
    }

    @PutMapping
    public ResponseResult updateLinkById(@RequestBody PageListVo updateDto) {
        return linkService.updateLinkById(updateDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteLinkById(@PathVariable("id") Long id) {
        return linkService.deleteLinkById(id);
    }
}
