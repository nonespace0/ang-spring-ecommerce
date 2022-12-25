package com.angularecommerce.Controller;

import com.angularecommerce.Model.Categories;
import com.angularecommerce.Model.ImageUpload;
import com.angularecommerce.Model.User;
import com.angularecommerce.Repository.ImageRepository;

import com.angularecommerce.Model.UsersDetails;
import com.angularecommerce.Service.CategoryService;
import com.angularecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String getPage(){
        return "welcome";
    }
    @GetMapping("/get")
    public List<Categories> get() {
        return categoryService.getData();
    }
    //categories add
    @PostMapping("/save")
    public ResponseEntity<Categories> save(@RequestBody Categories categories) {
        categoryService.saveAll(categories);
        return ResponseEntity.ok(categories);
    }

    //new user details  register
    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userService.registerData(user);
        return ResponseEntity.ok(user);
    }
    //login user
    @PostMapping("/login")
    public User login(@RequestBody User loginUser ) throws Exception {
        User user = null;
        /*UsersDetails oauthUser = userDetailService.login(loginUser.getEmail(), loginUser.getPassword());
        if(Objects.nonNull(oauthUser)) {
            return "welcome";
        }
        else {
            return  "Failed";
        }*/
        String loginUserName = loginUser.getUserName();
        String loginPassword = loginUser.getPassword();
        if (loginUserName != null && loginPassword != null) {
            user = userService.login(loginUserName, loginPassword);

        }
        if(user== null){
            throw new Exception("Invalid");

        }
        return user;

    }
    //

    @PostMapping("/upload")
    public ImageUpload uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        String name = file.getOriginalFilename();
        ImageUpload image = new ImageUpload(name, file.getContentType(), file.getBytes());
        return imageRepository.save(image);


    }
    @GetMapping(path = { "/get/{imageName}" })
    public ImageUpload getImage(@PathVariable("imageName") String imageName) throws IOException {

        final Optional<ImageUpload> retrievedImage = imageRepository.findByName(imageName);
        ImageUpload img = new ImageUpload(retrievedImage.get().getName(), retrievedImage.get().getType(),
                (retrievedImage.get().getPicByte()));
        return img;
    }

}


