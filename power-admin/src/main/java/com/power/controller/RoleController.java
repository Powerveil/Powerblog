package com.power.controller;

import com.power.domain.ResponseResult;
import com.power.domain.dto.AddRoleDto;
import com.power.domain.dto.RoleChangeStatusDto;
import com.power.domain.dto.UpdateRoleDto;
import com.power.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author power
 * @Date 2023/2/7 16:29
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult roleList(Long pageNum, Long pageSize, String roleName, String status) {
        return roleService.roleList(pageNum, pageSize , roleName, status);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleChangeStatusDto roleChangeStatusDto) {
        return roleService.changeStatus(roleChangeStatusDto);
    }

    @PostMapping
    public ResponseResult addRole(@RequestBody AddRoleDto addRoleDto) {
        return roleService.addRole(addRoleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteById(@PathVariable Long id) {
        return roleService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getRole(@PathVariable("id") Long id) {
        return roleService.getRole(id);
    }


    @PutMapping
    public ResponseResult updateRole(UpdateRoleDto updateRoleDto) {
        return roleService.updateRole(updateRoleDto);
    }

}
