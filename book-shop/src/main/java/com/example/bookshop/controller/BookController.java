package com.example.bookshop.controller;

import com.example.bookshop.ds.CartItem;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.Customer;
import com.example.bookshop.service.BookService;
import com.example.bookshop.service.CartService;
import com.example.bookshop.service.CustomerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final CartService cartService;
    private final BookService bookService;
    private final CustomerService customerService;

    @GetMapping("/books")
    public String listAllBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }

    @GetMapping("/books/detail")
    public String bookDetails(@RequestParam("id") int id, Model model) {
        model.addAttribute("book", bookService.findBookById(id));
        return "bookdetails";
    }

    @GetMapping("/books/add")
    public String addToCart(@RequestParam("id") int id) {
        ;
        cartService.addToCart(bookService.findBookById(id));
        return "redirect:/book/books/detail?id=" + id;
    }

    @GetMapping("/remove/all")
    public String removeAll() {
        cartService.clearCart();
        return "redirect:/book/books/view/cart";
    }

    @GetMapping("/delete")
    public String removeCartItem(@RequestParam("id") int id) {
        cartService.deleteById(id);
        return "redirect:/book/books/view/cart";
    }

    private boolean action = false;

    @RequestMapping("/checkout")
    public String checkout(Book book, Model model) {

        int i = 0;
        for (CartItem cartItem : cartService.listCartItem()) {
            cartItem.setQuantity(book.getQuantityList().get(i));
            i++;
        }
        //System.out.println(cartService.listCartItem() + "================================");
        // model.addAttribute("action", true);
        //cartService.setValueRenderer(true);
        //cartService.clearCart();
        return "redirect:/book/register";
    }

    @GetMapping("/register")
    public String registerCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "register";
    }
    @PostMapping("/save-customer")
    public String saveCustomer(Customer customer, BindingResult result){
        if(result.hasErrors()){
            return "register";
        }
        customerService.saveCustomer(customer);
        cartService.clearCart();
        return "redirect:/login";
    }

    @GetMapping("/books/view/cart")
    public String viewCart(Model model) {
        action = false;
        model.addAttribute("action", false);
        model.addAttribute("book", new Book());
        model.addAttribute("cartItems", cartService.listCartItem());
        return "viewCart";
    }

    @GetMapping("/info")
    public String showInfo(Model model) {
        model.addAttribute("cartItems", cartService.listCartItem());
        // cartService.clearCart();
        return "info";
    }

    @ModelAttribute("cartSize")
    public int cartSize() {
        return cartService.cartSize();
    }

    @ModelAttribute("totalPrice")
    public double totalPrice() {
        return cartService.listCartItem()
                .stream()
                .map(c -> {
                    c.setSum(c.getPrice() * c.getQuantity());
                    return c.getSum();
                })
                .mapToDouble(i -> i)
                .sum();
    }
}
