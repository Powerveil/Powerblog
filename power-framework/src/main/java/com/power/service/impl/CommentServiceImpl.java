package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.entity.Article;
import com.power.domain.entity.Comment;
import com.power.domain.entity.User;
import com.power.domain.vo.CommentVo;
import com.power.domain.vo.PageVo;
import com.power.service.ArticleService;
import com.power.service.CommentService;
import com.power.mapper.CommentMapper;
import com.power.service.UserService;
import com.power.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
* @author power
* @description 针对表【power_comment(评论表)】的数据库操作Service实现
* @createDate 2023-01-14 16:16:41
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    private UserService userService;


    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        // 查询对应文章的跟评论
        // 对articleId进行判断
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(articleId != null, Comment::getArticleId, articleId);
        // 根评论 rootId为-1
        queryWrapper.eq(Comment::getRootId, SystemConstants.COMMENT_ROOT_ID);
        // 分页查询
        Page<Comment> pageInfo = new Page<>(pageNum, pageSize);
        page(pageInfo,queryWrapper);

        List<CommentVo> result = toCommentVoList(pageInfo.getRecords());

        // 查询所有根评论对应的子评论集合，并且赋值给对应的属性


        return ResponseResult.okResult(new PageVo(result, pageInfo.getTotal()));
    }

    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        // 遍历vo集合
        for (CommentVo commentVo : commentVos) {
            // 通过createBy查询用户的昵称并赋值
            User creator = userService.getById(commentVo.getCreateBy());
            commentVo.setUsername(creator.getNickName());
            // 如果被回复人不是作者
            if (!commentVo.getToCommentUserId().equals(SystemConstants.COMMENT_ROOT_ID)) {
                // 通过toCommentUserId查询用户的昵称并赋值
                User toCommentUser = userService.getById(commentVo.getToCommentUserId());
                commentVo.setToCommentUserName(toCommentUser.getNickName());
            }
        }
        return commentVos;
    }
}




