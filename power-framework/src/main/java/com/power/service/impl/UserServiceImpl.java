package com.power.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.power.domain.ResponseResult;
import com.power.domain.dto.AddUserDto;
import com.power.domain.dto.UpdateUserDto;
import com.power.domain.entity.LoginUser;
import com.power.domain.entity.Role;
import com.power.domain.entity.User;
import com.power.domain.entity.UserRole;
import com.power.domain.vo.*;
import com.power.enums.AppHttpCodeEnum;
import com.power.exception.SystemException;
import com.power.service.RoleService;
import com.power.service.UserRoleService;
import com.power.service.UserService;
import com.power.mapper.UserMapper;
import com.power.utils.BeanCopyUtils;
import com.power.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author power
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2023-01-12 16:47:58
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Override
    public ResponseResult userInfo() {
        // 获取当前用户id
        Long userId = SecurityUtils.getUserId();
        // 根据用户id查询用户信息
        User user = getById(userId);
        // 封装成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        // TODO 代码太烂 需要改进
        // 判断传递参数是否为空
        if (Objects.isNull(user)) {
            throw new SystemException(AppHttpCodeEnum.USER_NOT_NULL);
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 以下信息不可含有
        boolean flag = Objects.isNull(user.getUserName())
                && Objects.isNull(user.getPassword())
                && Objects.isNull(user.getType())
                && Objects.isNull(user.getStatus())
                && Objects.isNull(user.getPhonenumber())
                && Objects.isNull(user.getCreateBy())
                && Objects.isNull(user.getCreateTime())
                && Objects.isNull(user.getDelFlag());

//        System.out.println("=============================================");
//        System.out.println("=============================================");
//        System.out.println("=============================================");
//        System.out.println(user);
//        System.out.println("flag:" + flag);
//        System.out.println("=============================================");
//        System.out.println("=============================================");
//        System.out.println("=============================================");

//        queryWrapper.eq(!Objects.isNull(user.getId()) && flag, User::getId, user.getId());

//        queryWrapper.eq(Objects.isNull(user.getUserName()), User::getUserName, null);
//        queryWrapper.eq(Objects.isNull(user.getPassword()), User::getPassword, null);
//        queryWrapper.eq(Objects.isNull(user.getType()), User::getType, null);
//        queryWrapper.eq(Objects.isNull(user.getStatus()), User::getStatus, null);
//        queryWrapper.eq(Objects.isNull(user.getPhonenumber()), User::getPhonenumber, null);
//        queryWrapper.eq(Objects.isNull(user.getCreateBy()), User::getCreateBy, null);
//        queryWrapper.eq(Objects.isNull(user.getCreateTime()), User::getCreateTime, null);
//        queryWrapper.eq(Objects.isNull(user.getDelFlag()), User::getDelFlag, null);
        // 修改用户信息
//        boolean update = update(queryWrapper);
        boolean update = true;
        if (flag && !Objects.isNull(user.getId())) {
            update = updateById(user);
        }
        String modificationMessage = "修改成功";
        // 判断修改用户信息是否成功
        if (!update) {
            modificationMessage = "修改失败";
        }
        // 返回结果
        return ResponseResult.okResult(modificationMessage);
    }

    @Override
    public ResponseResult register(User user) {
        // 对数据进行非空判断
        System.out.println("=======================================");
        System.out.println("=======================================");
        System.out.println("=======================================");
        System.out.println(user);
        System.out.println(Objects.isNull(user));
        System.out.println("=======================================");
        System.out.println("=======================================");
        System.out.println("=======================================");
        if (Objects.isNull(user)) {
            throw new SystemException(AppHttpCodeEnum.USER_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        // 对数据进行是否存在的判断
        if (userNameExist(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (nickNameExist(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        if (emailExist(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        // 对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        // 存入数据库
        user.setPassword(encodePassword);
        save(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult userList(Long pageNum, Long pageSize, String userName, String phonenumber, String status) {
        Page<User> pageInfo = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!Objects.isNull(userName), User::getUserName, userName);
        queryWrapper.eq(!Objects.isNull(phonenumber), User::getPhonenumber, phonenumber);
        queryWrapper.eq(!Objects.isNull(status), User::getStatus, status);

        page(pageInfo, queryWrapper);
        List<User> records = pageInfo.getRecords();
        List<ListUserVo> listUserVos = BeanCopyUtils.copyBeanList(records, ListUserVo.class);

        PageVo pageVo = new PageVo(listUserVos, pageInfo.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addUser(AddUserDto addUserDto) {
        // 用戶名不能為空
        if (!StringUtils.hasText(addUserDto.getUserName())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        List<User> list = list();
        //用户名必须之前未存在
        Set<String> userNameSet = list.stream()
                .map(new Function<User, String>() {
                    @Override
                    public String apply(User user) {
                        return user.getUserName();
                    }
                }).collect(Collectors.toSet());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (userNameSet.contains(addUserDto.getUserName())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST);
        }
//        queryWrapper.eq(!StringUtils.hasText(addUserDto.getUserName()), User::getUserName, addUserDto.getUserName());

        //手机号必须之前未存在
//        queryWrapper.eq(!StringUtils.hasText(addUserDto.getPhonenumber()), User::getPhonenumber, addUserDto.getPhonenumber());

        Set<String> phonenumberSet = list.stream()
                .map(new Function<User, String>() {
                    @Override
                    public String apply(User user) {
                        return user.getPhonenumber();
                    }
                }).collect(Collectors.toSet());
        if (phonenumberSet.contains(addUserDto.getPhonenumber())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        //邮箱必须之前未存在
//        queryWrapper.eq(!StringUtils.hasText(addUserDto.getEmail()), User::getEmail, addUserDto.getEmail());

        Set<String> emailSet = list.stream()
                .map(new Function<User, String>() {
                    @Override
                    public String apply(User user) {
                        return user.getEmail();
                    }
                }).collect(Collectors.toSet());
        if (emailSet.contains(addUserDto.getEmail())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.EMAIL_EXIST);
        }

//        if (count(queryWrapper) > 0) {
//            return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST);
//        }

        //密碼需要加密存儲
        String encode = passwordEncoder.encode(addUserDto.getPassword());
        addUserDto.setPassword(encode);


        User user = BeanCopyUtils.copyBean(addUserDto, User.class);
        save(user);
        LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(StringUtils.hasText(addUserDto.getUserName()), User::getUserName, addUserDto.getUserName());

        Long userId = getOne(queryWrapper1).getId();

        List<Long> roleIds = addUserDto.getRoleIds();

        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        for (Long roleId : roleIds) {
            userRole.setRoleId(roleId);
            userRoleService.save(userRole);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult userDelete(Long id) {
        Long loginUserId = SecurityUtils.getUserId();
        if (loginUserId.equals(id)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DELETE_ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUserById(Long id) {
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!Objects.isNull(id), UserRole::getUserId, id);
        List<UserRole> userRoles = userRoleService.list(queryWrapper);
        // roleIds
        List<Long> roleIds = userRoles.stream()
                .map(UserRole::getRoleId).collect(Collectors.toList());
        log.info("roleIds:{}", roleIds);

        ResponseResult result = roleService.listAllRole();
        List<Role> roles = (List<Role>) result.getData();
        log.info("roles:{}", roles);

        User user = getById(id);
        log.info("user:{}", user);
        GetUserVo getUserVo = BeanCopyUtils.copyBean(user, GetUserVo.class);

        UserDetailsVo userDetailsVo = BeanCopyUtils.copyBean(roleIds, UserDetailsVo.class);
        userDetailsVo.setRoleIds(roleIds);
        userDetailsVo.setRoles(roles);
        userDetailsVo.setUser(getUserVo);

        return ResponseResult.okResult(userDetailsVo);
    }

    @Override
    public ResponseResult updateUser(UpdateUserDto updateUserDto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!Objects.isNull(updateUserDto.getId()), User::getId, updateUserDto.getId());
        User user = BeanCopyUtils.copyBean(updateUserDto, User.class);
        updateById(user);
        Long userId = updateUserDto.getId();
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);

        List<Long> roleIds = updateUserDto.getRoleIds();
        for (Long roleId : roleIds) {
            userRole.setRoleId(roleId);
            userRoleService.save(userRole);
        }
        return ResponseResult.okResult();
    }

    private boolean emailExist(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!Objects.isNull(email), User::getEmail, email);
        return count(queryWrapper) > 0;
    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!Objects.isNull(nickName), User::getNickName, nickName);
        return count(queryWrapper) > 0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!Objects.isNull(userName), User::getUserName, userName);
        return count(queryWrapper) > 0;
    }

//    private List<Long> getRoleIds(Long userId) {
//        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(!Objects.isNull(userId), UserRole::getUserId, userId);
//        List<UserRole> userRoles = userRoleService.list(queryWrapper);
//        List<Long> roleIds = userRoles.stream()
//                .map(new Function<UserRole, Long>() {
//                    @Override
//                    public Long apply(UserRole userRole) {
//                        return userRole.getRoleId();
//                    }
//                }).collect(Collectors.toList());
//        return roleIds;
//    }
}




