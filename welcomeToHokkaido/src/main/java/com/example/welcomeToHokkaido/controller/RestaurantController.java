package com.example.welcomeToHokkaido.controller;

import com.example.welcomeToHokkaido.domain.dto.RestaurantDTO;
import com.example.welcomeToHokkaido.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.welcomeToHokkaido.service.RestaurantService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("restaurant")
@RequiredArgsConstructor
@Controller
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final ImageService imageService;

    @GetMapping
    public String list(Model model) {
        List<RestaurantDTO> restaurantList = restaurantService.getList();
        model.addAttribute("restaurantList", restaurantList);
        return "restaurant/restaurant";
    }

    @GetMapping("write")
    public String write() {
        return "restaurant/restaurantWrite";
    }

    @PostMapping("write")
    public String write(RestaurantDTO restaurantDTO,
                        @RequestParam("images")List<MultipartFile> images) throws IOException {
        Integer restaurantId = restaurantService.write(restaurantDTO);
        imageService.uploadImage(images);
        return "redirect:/restaurant";
    }

    @GetMapping("read/{restaurantId}")
    public String read(@PathVariable("restaurantId") int restaurantId, Model model) {
        RestaurantDTO restaurantDTO = restaurantService.read(restaurantId);
        model.addAttribute("restaurantRead", restaurantDTO);
        return "restaurant/restaurantRead";
    }

    @PostMapping("delete")
    public String delete(@RequestParam("restaurantId") int restaurantId) {
        restaurantService.delete(restaurantId);
        return "redirect:/restaurant";
    }

    @GetMapping("update/{restaurantId}")
    public String update(@PathVariable("restaurantId") int restaurantId, Model model) {
        RestaurantDTO restaurantDTO = restaurantService.read(restaurantId);
        model.addAttribute("restaurantRead", restaurantDTO);
        return "restaurant/restaurantUpdate";
    }

    @PostMapping("update")
    public String update(@ModelAttribute RestaurantDTO restaurantDTO) {
        restaurantService.update(restaurantDTO);
        return "redirect:/restaurant";
    }


}