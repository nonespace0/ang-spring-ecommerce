package com.angularecommerce.Controller;

import com.angularecommerce.Model.Categories;
import com.angularecommerce.Model.ImageUpload;
import com.angularecommerce.Repository.ImageRepository;
import com.angularecommerce.Service.UserDetailService;
import com.angularecommerce.Model.UsersDetails;
import com.angularecommerce.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserDetailService userDetailService;

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
    public ResponseEntity<UsersDetails> saveUser(@RequestBody UsersDetails usersDetails) {
        userDetailService.registerData(usersDetails);
        return ResponseEntity.ok(usersDetails);
    }
    //login user
    @PostMapping("/login")
    public UsersDetails login(@RequestBody UsersDetails loginUser ) throws Exception {
        UsersDetails user = null;
        /*UsersDetails oauthUser = userDetailService.login(loginUser.getEmail(), loginUser.getPassword());
        if(Objects.nonNull(oauthUser)) {
            return "welcome";
        }
        else {
            return  "Failed";
        }*/
        String loginMail = loginUser.getEmail();
        String loginPassword = loginUser.getPassword();
        if (loginMail != null && loginPassword != null) {
            user = userDetailService.login(loginMail, loginPassword);

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


