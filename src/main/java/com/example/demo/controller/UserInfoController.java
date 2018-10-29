package com.example.demo.controller;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaonl on 2018/10/6.
 */
@Controller
@RequestMapping({"/user"})
public class UserInfoController {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public String showUserInfo(@PathVariable("userId") Integer userId, Model model) {

//        if(System.currentTimeMillis() >100){
//            throw new DuplicateDomainException();
//        }

        User user = userDao.getById(userId);
        model.addAttribute("user", user);
        return "user/info";
    }

    /**
     * restful api
     * produces = "application/json" 表明这个方法只产生json数据，即发送请求时，声明Accept头部要么不设置（说明可以接受任意类型），要么为[Accept: application/json]，其他调用此方法将报错
     * 标注@ResponseBody 表明这个方法的返回值是http resopnse body，并启用相应的Converter将返回值转换成相应的数据形式，入json
     * 当返回ResponseEntity<T>的时候，可以不用标注@ResponseBody，功能同上，可以添加响应状态码，头部信息等等
     *
     * @param userId
     * @return
     */
    @RequestMapping(path = "/restful/{userId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> showUserInfoRest(@PathVariable("userId") Integer userId) {
        User user = userDao.getById(userId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (user == null) {
            httpStatus = HttpStatus.NOT_FOUND;
            Map<String, Object> error = new HashMap<>();
            error.put("code", 404);
            error.put("msg", "user with id :" + userId + " not found");
            return new ResponseEntity<>(error, httpStatus);
        }

        return new ResponseEntity<>(user, httpStatus);
    }

    /**
     * restful api
     * consumes = "application/json" 表明这个方法只接受json数据，即发送请求时，声明Content-Type头部要么不设置（说明可以接受任意类型），要么为[Content-Type: application/json]，其他调用此方法将报错
     * 标注@RequestBody 表明这个方法的参数是从http resopnse body转换而来的，并启用相应的Converter将body转换成相应的数据形式，如：json
     * RequestEntity<T> 请求提参数，可以不用加标注@RequestBody
     * <p/>
     * 标注@ResponseBody 表明这个方法的返回值是http resopnse body，并启用相应的Converter将返回值转换成相应的数据形式，如：json
     * 当返回ResponseEntity<T>的时候，可以不用标注@ResponseBody，功能同上，可以添加响应状态码，头部信息等等
     *
     * @param requestEntity
     * @return
     */
    @RequestMapping(path = "/restful", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
//    public ResponseEntity<?> showUserInfoRest(@RequestBody User user) {
    public ResponseEntity<?> showUserInfoRest(RequestEntity<User> requestEntity) {
        System.out.println("type: " + requestEntity.getType());//表示的是RequestEntity.getBody()类型，即：type: class com.magicli.ioc.domain.User
        System.out.println("url: " + requestEntity.getUrl());//http://localhost:8080/user/restful
        System.out.println("method: " + requestEntity.getMethod());//POST
        User userSaved = userDao.save(requestEntity.getBody());
        HttpStatus httpStatus = HttpStatus.CREATED;
        if (userSaved == null || userSaved.getId() == null) {
            httpStatus = HttpStatus.FORBIDDEN;
            Map<String, Object> error = new HashMap<>();
            error.put("code", 403);
            error.put("msg", "user save FORBIDDEN");
            return new ResponseEntity<>(error, httpStatus);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/user/" + userSaved.getId()));

        ResponseEntity responseEntity = new ResponseEntity<>(userSaved, headers, httpStatus);

        return responseEntity;
    }

    /**
     * 也可以使用javax.servlet.http.Part
     * 如果使用javax.servlet.http.Part，就可以不用配置MultipartResolver了，方便快捷（不过只能在servlet3.0中使用）
     */
    @RequestMapping(path = "/profile/upload", method = RequestMethod.POST)
    public String profileUpload(Integer userId, String title, @RequestPart MultipartFile profile, Model model,RedirectAttributes redirectAttributes) {
        File baseDir = new File("F:\\my\\my_projects\\spring_word_template_files\\upload");

        String originalFilename = profile.getOriginalFilename();
        String name = profile.getName();
        long size = profile.getSize();


        System.out.println("originalFilename: " + originalFilename);
        System.out.println("name: " + name);
        System.out.println("size: " + size);
        System.out.println("userId: " + userId);
        System.out.println("title: " + title);

        File dest = new File(baseDir, originalFilename);

        try {
            profile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
         * spring5.x过后好像就不能用这种方式了
         */
//        model.addAttribute("userId", userId);
//        model.addAttribute("title", title);

        /**
         * 都用这种方式吧
         */
        redirectAttributes.addAttribute("userId", userId);
        redirectAttributes.addAttribute("title", title);

        //url重定向，spring会吧model里面的参数放到URL下面去（不要用字符串拼接的，用占位符）
        //假设userId=1 title='china' 如下的URL会重定向到  /user/1?title=china
        return "redirect:/user/{userId}";

    }

    /**
     * 当这个controller抛出DuplicateDomainException异常时，调用这个方法，不知道能不能带请求参数，算了，不试了
     * @return
     */
//    @ExceptionHandler(DuplicateDomainException.class)
//    public String exceptionHandler(){
//        return "error/500";
//    }

}
