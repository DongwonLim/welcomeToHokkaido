package com.example.welcomeToHokkaido.controller;

import com.example.welcomeToHokkaido.domain.dto.RestaurantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.welcomeToHokkaido.service.RestaurantService;

import java.util.List;

@RequestMapping("restaurant")
@RequiredArgsConstructor
@Controller
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public String list(Model model) {
        List<RestaurantDTO> restaurantList = restaurantService.getList();
        model.addAttribute("restaurantList", restaurantList);
        return "restaurant/restaurant";
    }
}