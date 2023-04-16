package com.example.Estore.web;

import com.example.EStore.model.entity.CartItemEntity;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.service.ShoppingCartService;
import com.example.EStore.service.UserService;
import com.example.EStore.repository.UserRepository;

import com.example.EStore.web.ContactUsController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContactUsControllerIT {

    @Mock
    private UserService userService;

    @Mock
    private ShoppingCartService cartService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Model model;

    @Mock
    private UserDetails userDetails;

    @Mock
    private UserEntity userEntity;

    @Test
    public void testContactPage() {

        // Arrange
        ContactUsController controller = new ContactUsController(userService, cartService, userRepository);
        List<CartItemEntity> cartItems = new ArrayList<>();
        cartItems.add(new CartItemEntity());
        UserEntity userEntity = new UserEntity();
        when(userService.getUserByPrincipal(userDetails.getUsername())).thenReturn(userEntity);
        when(cartService.getAllCartItemsForCurrentUser(userEntity)).thenReturn(cartItems);

        // Act
        String viewName = controller.contactPage(model, userDetails);

        // Assert
        verify(userService, times(1)).getUserByPrincipal(userDetails.getUsername());
        verify(cartService, times(1)).getAllCartItemsForCurrentUser(userEntity);
        verify(model, times(1)).addAttribute("itemsNumber", cartItems.size());
        assert viewName.equals("contact");
    }
}