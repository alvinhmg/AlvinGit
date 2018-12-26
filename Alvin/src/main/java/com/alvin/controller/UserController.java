package com.alvin.controller;

import com.alvin.model.User;
import com.alvin.services.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprucetec.commons.data.*;
import com.sprucetec.commons.data.exception.BusinessException;
import com.sprucetec.commons.data.exception.RepositoryException;
import com.sprucetec.commons.web.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;


    @RequestMapping("showUser")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        long userId = Long.parseLong(request.getParameter("id"));
        User user = this.userService.selectUser(userId);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();

    }

    /**
     * @Author hemingguang
     * @Description //根据用户ID查询用户信息
     * @Date 4:21 PM 2018/12/25
     * @Param [id]
     * @return com.sprucetec.commons.web.model.Response
     **/

    @ResponseBody
    @RequestMapping(value = "/queryUserById/{id}",method = RequestMethod.GET)

    public Response queryUserById(@PathVariable("id") long id){
        Response res = new Response();
        User user = userService.selectUser(id);
        res.setResult(user);
        logger.info("username ；"+ user.getUsername());
        res.success();
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/views/showUserList",method ={RequestMethod.GET,RequestMethod.POST})
    public Response showUserList(){
        Response res = new Response();
        List<User> userList = new ArrayList<>();
        try{
            userList = userService.findAll();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        res.setResult(userList);
        res.success();
        return res;
    }


    @ResponseBody
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Response addUser(User user){
        logger.info("参数："+user.getUsername());
        try{
            ((Addable)userService).add(user);
            return Response.build().success();
        } catch (Exception e){
            return this.OnException(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser",method = RequestMethod.GET)
    public Response updateUser(User user){
        try{
            ((Updatable)userService).update(user);
            return Response.build().success();
        }catch (Exception e){
            return OnException(e);
        }
    }

    @ResponseBody
    @RequestMapping(value = "deleteUser/{id}",method = RequestMethod.DELETE)
    public Response deleteUser(@PathVariable("id") long id){
        try{
            User user = (User) ((Identifiable)userService).findById(id);
            if(user != null){
                ((Deletable)userService).delete(user);
            }
            return Response.build().success();
        }catch (Exception e){
            return OnException(e);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/enableUser{id}",method = RequestMethod.GET)
    public Response enableUser(@PathVariable("id") long id){
        try{
            User user = (User) ((Identifiable)userService).findById(id);
            if(user != null){
                ((Lifeable)userService).enable(user);
            }
            return Response.build().success();
        }catch (Exception e){
            return OnException(e);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/disableUser/{id}",method = RequestMethod.GET)
    public Response disableUser(@PathVariable("id") long id){
        try{
            User user = (User) ((Identifiable)userService).findById(id);
            if(user != null){
                ((Lifeable)userService).disable(user);
            }
            return Response.build().success();
        }catch (Exception e ){
            return OnException(e);
        }
    }


    protected Response OnException(Exception e){
        if(!(e instanceof BusinessException)){
            logger.error(e.getMessage(),e);
            return e instanceof RepositoryException ? Response.build().fail("存储错误") : Response.build().fail("系统错误");
        }else {
            return Response.build().fail(e.getMessage());
        }

    }








}
