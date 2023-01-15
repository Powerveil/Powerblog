package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.constants.SystemConstants;
import com.power.domain.ResponseResult;
import com.power.domain.entity.Comment;
import com.power.domain.entity.User;
import com.power.domain.vo.CommentVo;
import com.power.domain.vo.PageVo;
import com.power.enums.AppHttpCodeEnum;
import com.power.exception.SystemException;
import com.power.service.CommentService;
import com.power.mapper.CommentMapper;
import com.power.service.UserService;
import com.power.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

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
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        // 查询对应文章的跟评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        // 对articleId进行判断
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT_TYPE.equals(commentType) && articleId != null, Comment::getArticleId, articleId);
        // 根评论 rootId为-1
        queryWrapper.eq(Comment::getRootId, SystemConstants.COMMENT_ROOT_ID);
        // 评论类型
        queryWrapper.eq(Comment::getType, commentType);
        // 分页查询
        Page<Comment> pageInfo = new Page<>(pageNum, pageSize);
        page(pageInfo,queryWrapper);

        List<CommentVo> commentVoList = toCommentVoList(pageInfo.getRecords());

        // 查询所有根评论对应的子评论集合，并且赋值给对应的属性
        for (CommentVo commentVo : commentVoList) {
            // 查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            // 赋值
            commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new PageVo(commentVoList, pageInfo.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        // 评论不能为空 暂时找不到怎么传入null
//        if (null == comment) {
//            throw new SystemException(AppHttpCodeEnum.COMMENT_NOT_NULL);
//        }
        // 评论内容不能为空
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.COMMENT_CONTENT_NOT_NULL);
        }
        // TODO 处理敏感词
        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * 根据跟评论的id查询所对应的子评论的集合
     * @param id 根评论的id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(id), Comment::getRootId, id);
        queryWrapper.orderByDesc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(comments);
        return commentVoList;
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




