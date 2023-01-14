package com.power.service;

import com.power.domain.ResponseResult;
import com.power.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author power
* @description 针对表【power_comment(评论表)】的数据库操作Service
* @createDate 2023-01-14 16:16:41
*/
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);
}
