package com.power.controller;

import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.dto.AddCommentDto;
import com.power.domain.entity.Comment;
import com.power.service.CommentService;
import com.power.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author power
 * @Date 2023/1/14 16:26
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "评论", description = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT_TYPE, articleId, pageNum, pageSize);
    }


    @PostMapping("")
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto) {
        Comment comment = BeanCopyUtils.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }

    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表", notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页号"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小")
    })
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.LINK_COMMENT_TYPE, null, pageNum, pageSize);
    }
}
