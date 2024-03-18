package com.book.manager.service;

import cn.hutool.core.bean.BeanUtil;
import com.book.manager.dao.BookMapper;
import com.book.manager.entity.Book;
import com.book.manager.repos.BookRepository;
import com.book.manager.util.vo.PageOut;
import com.book.manager.util.ro.PageIn;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Description 图书业务类
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;


    /**
     * 添加图书
     * @param book 图书
     * @return 返回添加的图书
     */
    public Book addBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    /**
     * 编辑图书
     * @param book 图书对象
     * @return true or false
     */
    public boolean updateBook(Book book) {
        return bookMapper.updateBook(BeanUtil.beanToMap(book))>0;
    }

    /**
     * 图书详情
     * @param id 主键
     * @return 图书详情
     */
    public Book findBookById(Integer id) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            Book book = optional.get();
//            BookOut out = new BookOut();
//            BeanUtil.copyProperties(book,out);
//            out.setPublishTime(DateUtil.format(book.getPublishTime(),"yyyy-MM-dd"));
            return book;
        }
        return null;
    }

    public Book findBook(Integer id) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }


    /**
     * 删除图书
     * @param id 主键
     * @return true or false
     */
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }


    /**
     * 图书搜索查询(mybatis 分页)
     * @param pageIn
     * @return
     */
    public PageOut getBookList(PageIn pageIn) {

        PageHelper.startPage(pageIn.getCurrPage(),pageIn.getPageSize());
        List<Book> list = bookMapper.findBookListByLike(pageIn.getKeyword());
        PageInfo<Book> pageInfo = new PageInfo<>(list);

        // 自定义分页返回对象
        PageOut pageOut = new PageOut();
        pageOut.setList(list);
        pageOut.setTotal((int)pageInfo.getTotal());
        pageOut.setCurrPage(pageInfo.getPageNum());
        pageOut.setPageSize(pageInfo.getPageSize());
        return pageOut;
    }


}
