package com.power.domain.vo;

import com.power.domain.vo.MenuListVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author power
 * @Date 2023/2/12 16:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuVo {
    List<MenuListVo> menus;
    List<Long> checkedKeys;
}
