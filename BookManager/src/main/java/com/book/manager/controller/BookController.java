package com.book.manager.controller;

import com.book.manager.entity.Book;
import com.book.manager.service.BookService;
import com.book.manager.util.Response;
import com.book.manager.util.http.CodeEnum;
import com.book.manager.util.ro.PageIn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 用户管理

 */
@Api(tags = "图书管理")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation("图书搜索列表")
    @PostMapping("/list")
    public Response getBookList(@RequestBody PageIn pageIn) {

        if (pageIn == null) {
            return Response.fail(CodeEnum.PARAM_ERROR);
        }
        return Response.success(CodeEnum.SUCCESS,bookService.getBookList(pageIn));
    }

    /**
     * 添加图书操作
     * @param book
     * @return
     */
    @ApiOperation("添加图书")
    @ResponseBody
    @PostMapping("/add")
    public Response addBook(Book book) {
        if(StringUtils.isEmpty(book.getName())){
            return Response.fail(CodeEnum.BOOK_NAME_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(book.getIsbn())){
            return Response.fail(CodeEnum.BOOK_ISBN_NOT_EXIST_ERROR);
        }

        if(StringUtils.isEmpty(book.getAuthor())){
            return Response.fail(CodeEnum.BOOK_AUTHOR_NOT_EXIST_ERROR);
        }

        if(bookService.addBook(book)==null){
            return Response.fail(CodeEnum.BOOK_ADD_ERROR);
        }
        return Response.success(CodeEnum.SUCCESS);
    }

    /**
     * 编辑图书
     * @param book
     * @return
     */
    @ApiOperation("编辑图书")
    @ResponseBody
    @PostMapping("/edit")
    public Response editBook(Book book) {
        if(StringUtils.isEmpty(book.getName())){
            return Response.fail(CodeEnum.BOOK_NAME_NOT_EXIST_ERROR);
        }
        if(StringUtils.isEmpty(book.getIsbn())){
            return Response.fail(CodeEnum.BOOK_ISBN_NOT_EXIST_ERROR);
        }

        if(StringUtils.isEmpty(book.getAuthor())){
            return Response.fail(CodeEnum.BOOK_AUTHOR_NOT_EXIST_ERROR);
        }

        if(!bookService.updateBook(book)){
            return Response.fail(CodeEnum.BOOK_EDIT_ERROR);
        }
        return Response.success(CodeEnum.SUCCESS);
    }


    @ApiOperation("图书详情")
    @GetMapping("/detail")
    public Response bookDetail(Integer id) {
        return Response.success(CodeEnum.SUCCESS,bookService.findBookById(id));
    }

    @ApiOperation("删除图书")
    @GetMapping("/delete")
    public Response delBook(Integer id) {
        bookService.deleteBook(id);
        return Response.success(CodeEnum.SUCCESS);
    }

}
