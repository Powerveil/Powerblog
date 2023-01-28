package com.power.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.power.domain.ResponseResult;
import com.power.domain.entity.Category;
import com.power.domain.vo.CategoryVo;
import com.power.domain.vo.ExcelCategoryVo;
import com.power.enums.AppHttpCodeEnum;
import com.power.service.CategoryService;
import com.power.utils.BeanCopyUtils;
import com.power.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author power
 * @Date 2023/1/28 16:45
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory() {
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    public void export(HttpServletResponse response) {
        // 设置下载文件的请求头
        try {
            // 获取需要导出的数据
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            // 把数据写入到Excel中
            List<Category> categoryVos = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);

            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);
        } catch (Exception e) {
            e.printStackTrace();
            // 如果出现异常也要响应json
            response.reset();
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }


    }


}
