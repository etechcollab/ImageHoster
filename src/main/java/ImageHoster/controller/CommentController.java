package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private ImageService imageService;




    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comment",method = RequestMethod.POST)
    public String createCommenting(@PathVariable(name = "imageId")Integer imageId, @PathVariable(name = "imageTitle")String title, @RequestParam(value="comment")String newComment, HttpSession session, Model model){
        User user=(User)session.getAttribute("loggeduser");
        Image image=imageService.getImage(imageId);

        Comment comment=new Comment();
        comment.setImage(image);
        comment.setText(newComment);
        LocalDate localDate=LocalDate.now();
        comment.setCreatedDate(localDate);
        comment.setId(imageId);
        return "redirect:/images/" + image.getId() + "/" + image.getTitle();
    }



}
