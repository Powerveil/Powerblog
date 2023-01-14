package com.power.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author power
 * @Date 2023/1/14 16:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 根评论id
     */
    private Long rootId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 所回复的目标评论的userid
     */
    private Long toCommentUserId;

    /**
     * 所回复的目标评论的userName
     */
    private String toCommentUserName;

    /**
     * 回复目标评论id
     */
    private Long toCommentId;

    /**
     *
     */
    private Long createBy;

    /**
     *
     */
    private Date createTime;

    /**
     * 评论人的昵称
     */
    private String username;

    /**
     * 子评论
     */
    private List<CommentVo> children;
}
