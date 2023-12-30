package com.power.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.power.domain.ResponseResult;
import com.power.domain.dto.AddCategoryDto;
import com.power.domain.dto.UpdateCategoryDto;
import com.power.domain.entity.Category;
import com.power.domain.vo.CategoryVo;
import com.power.domain.vo.ExcelCategoryVo;
import com.power.enums.AppHttpCodeEnum;
import com.power.service.CategoryService;
import com.power.utils.BeanCopyUtils;
import com.power.utils.WebUtils;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        // 设置下载文件的请求头
        try {
            // 获取需要导出的数据
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            // 把数据写入到Excel中
            List<Category> categoryVos = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);

            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class)
                     // autoCloseStream 设置是否在写入完成后自动关闭输出流。
                     // 此处传递 Boolean.FALSE 表示不自动关闭输出流，可能是为了在后续进行其他操作。
                     .autoCloseStream(Boolean.FALSE)
                     // sheet 指定要使用的工作表名称，此处设置为 "分类导出"。
                     .sheet("分类导出")
                     // doWrite 执行实际的数据写入操作，将传递的 excelCategoryVos 对象的数据写入 Excel 表格。
                     .doWrite(excelCategoryVos);
        } catch (Exception e) {
            e.printStackTrace();
            // 如果出现异常也要响应json
            // 重置 HTTP 响应，清除之前设置的响应头和状态码。
            response.reset();
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }


    @GetMapping("/list")
    public ResponseResult categoryList(Long pageNum, Long pageSize, String name, String status) {
        return categoryService.categoryList(pageNum, pageSize, name, status);
    }


    @PostMapping
    public ResponseResult addCategory(@RequestBody AddCategoryDto addCategoryDto) {
        return categoryService.addCategory(addCategoryDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getCategoryById(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping
    public ResponseResult updateCategory(@RequestBody UpdateCategoryDto updateCategoryDto) {
        return categoryService.updateCategory(updateCategoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteCategory(@PathVariable("id") Long id) {
        return categoryService.deleteCategory(id);
    }

}
