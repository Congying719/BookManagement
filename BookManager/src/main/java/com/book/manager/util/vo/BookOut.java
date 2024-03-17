package com.book.manager.util.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 图书出参对象
 */
@Data
public class BookOut {

    @ApiModelProperty("书籍ID")
    private Integer id;

    @ApiModelProperty("书籍ISBN编码")
    private String isbn;

    @ApiModelProperty("书名")
    private String name;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("出版年份")
    private String year;
}
