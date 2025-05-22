package com.office.library.book.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.office.library.book.BookVo;
import com.office.library.book.admin.util.DeleteFileService;
import com.office.library.book.admin.util.UploadFileService;

@Controller
@RequestMapping("/book/admin")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UploadFileService uploadFileService;
	
	@Autowired
	DeleteFileService deleteFileService;
	
	@PostMapping("/registerBookConfirm")
	public String registerBookConfirm(BookVo bookVo, @RequestParam("file") MultipartFile file) {
		System.out.println("[BookController] registerBookConfirm()");
		
		String nextPage = "admin/book/register_book_ok";
		
		// 파일 저장
		String savedFileName = uploadFileService.upload(file);
		
		if (savedFileName != null) {
			bookVo.setB_thumbnail(savedFileName);
			
			int result = bookService.registerBookConfirm(bookVo);
			
			if (result <= 0) {
				// DB 저장 실패 → 업로드한 파일 삭제
	            deleteFileService.delete(savedFileName);
	            
	            nextPage = "admin/book/register_book_ng";
			}
		} else {
			nextPage = "admin/book/register_book_ng";
		}
		
		return nextPage;
	}
	
	@GetMapping("/registerBookForm")
	public String registerBookForm() {
		System.out.println("[BookController] registerBookForm()");
		
		String nextPage = "admin/book/register_book_form";
		
		return nextPage;
	}
	
	@GetMapping("/searchBookConfirm")
	public String searchBookConfirm(BookVo bookVo, Model model) {
		System.out.println("[UserBookController] searchBookConfirm()");
		
		String nextPage = "admin/book/search_book";
		
		List<BookVo> bookVos = bookService.searchBookConfirm(bookVo);
		model.addAttribute("bookVos", bookVos);
		
		return nextPage;
	}
	
	@GetMapping("/bookDetail")
	public String bookDetail(@RequestParam("b_no") int b_no, Model model) {
		System.out.println("[BookController] bookDetail()");
		
		String nextPage = "admin/book/book_detail";
		
		BookVo bookVo = bookService.bookDetail(b_no);
		model.addAttribute("bookVo", bookVo);
		
		return nextPage;
	}
	
	@GetMapping("/modifyBookForm")
	public String modifyBookForm(@RequestParam("b_no") int b_no, Model model) {
		System.out.println("[BookController] bookDetail()");
		
		String nextPage = "admin/book/modify_book_form";
		
		BookVo bookVo = bookService.modifyBookForm(b_no);
		model.addAttribute("bookVo", bookVo);
		
		return nextPage;
	}
	
	@PostMapping("/modifyBookConfirm")
	public String modifyBookConfirm(BookVo bookVo, @RequestParam("file") MultipartFile file) {
		System.out.println("[BookController] modifyBookConfirm()");
		
		String nextPage = "admin/book/modify_book_ok";
		
		if (!file.getOriginalFilename().equals("")) {
			// 파일 저장
			String savedFileName = uploadFileService.upload(file);
			if (savedFileName != null) {
				bookVo.setB_thumbnail(savedFileName);
			}
		}
		
		int result = bookService.modifyBookConfirm(bookVo);
		
		if (result <= 0)
			nextPage = "admin/book/modify_book_ng";
		
		return nextPage;
	}
	
	@GetMapping("/deleteBookConfirm")
	public String deleteBookConfirm(@RequestParam("b_no") int b_no) {
	    System.out.println("[BookController] deleteBookConfirm()");

	    String nextPage = "admin/book/delete_book_ok";

	    // 1. 삭제할 도서 정보 조회 (썸네일 파일명 얻기 위함)
	    BookVo bookVo = bookService.bookDetail(b_no);

	    // 2. 데이터베이스에서 도서 삭제
	    int result = bookService.deleteBookConfirm(b_no);

	    // 3. 도서가 삭제되었고 썸네일이 있다면 파일도 삭제
	    if (result > 0 && bookVo != null && bookVo.getB_thumbnail() != null) {
	        deleteFileService.delete(bookVo.getB_thumbnail());
	    } else {
	        nextPage = "admin/book/delete_book_ng";
	    }

	    return nextPage;
	}

}