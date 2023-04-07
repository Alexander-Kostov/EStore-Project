package com.example.EStore.web;

import com.example.EStore.model.dto.ProductDetailDTO;
import com.example.EStore.model.entity.CartItemEntity;
import com.example.EStore.model.entity.OrderedProductEntity;
import com.example.EStore.model.entity.ProductEntity;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.repository.CartItemRepository;
import com.example.EStore.repository.OrderedProductRepository;
import com.example.EStore.service.ProductService;
import com.example.EStore.service.ShoppingCartService;
import com.example.EStore.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {


    private UserService userService;

    private ProductService productService;

    private ShoppingCartService cartService;

    public CartController(UserService userService, ProductService productService, ShoppingCartService cartService) {
        this.userService = userService;
        this.productService = productService;;
        this.cartService = cartService;
    }

    @GetMapping()
    public String showShoppingCart(Model model,
                                   @AuthenticationPrincipal UserDetails principal) {

        UserEntity loggedUser = this.userService.getUserByPrincipal(principal.getUsername());

        List<CartItemEntity> cartItems = this.cartService.getAllCartItemsForCurrentUser(loggedUser);

        double subTotal = this.cartService.sumAllProductsInCart(cartItems);
        double totalPrice = subTotal + 5;


        OrderedProductEntity orderedProductEntity = this.cartService.checkForEnoughQuantity(loggedUser);

        model.addAttribute("itemsNumber", cartItems.size());
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");
        model.addAttribute("subTotal", subTotal);
        model.addAttribute("totalPrice", totalPrice);

        if (orderedProductEntity != null) {
            model.addAttribute("orderedEntity", orderedProductEntity);
        }

        return "cart";
    }

    @PostMapping("/product")
    public String showShoppingCart(ProductDetailDTO productDetailDTO, @AuthenticationPrincipal UserDetails principal) {

        ProductEntity product = this.productService.getProductById(productDetailDTO.getId());

        UserEntity customer = this.userService.getUserByPrincipal(principal.getUsername());

        this.cartService.addToCart(productDetailDTO, product, customer);

        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(@AuthenticationPrincipal UserDetails principal, RedirectAttributes redirectAttributes, Model model) {

        UserEntity customer = this.userService.getUserByPrincipal(principal.getUsername());

        OrderedProductEntity orderedProductEntity = this.cartService.checkForEnoughQuantity(customer);

        if(orderedProductEntity != null) {
            redirectAttributes.addFlashAttribute("invalidQuantity", true);

            return "redirect:/cart";
        }

        return "redirect:/checkout";
    }

    @GetMapping("/delete/{id}")
    public String deleteItemFromCart(@PathVariable("id") Long id) {

        this.cartService.deleteOrderedProductById(id);


        return "redirect:/cart";
    }

}
