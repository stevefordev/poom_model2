package com.coddington.poom.controller;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.coddington.poom.service.ReviewsService;
import com.coddington.poom.vo.Review;

@Controller
public class ReviewsController {

  private ReviewsService reviewsService;

  public void setReviewsService(ReviewsService reviewsService) {
    this.reviewsService = reviewsService;
  }

  @RequestMapping(value = "/ajax/review.poom", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, Object> getReviews(int serviceNo, int page, Model model) {
    return reviewsService.getReviews(serviceNo, page);
  }

  @RequestMapping(value = "/ajax/reviewReplyUpdate.poom", method = RequestMethod.POST)
  public String updateReviewReply(Review review, String boardType, int page) {
    
    Review selectedReview = reviewsService.getReview(review.getNo());
    selectedReview.setReply(review.getReply());
    reviewsService.modify(selectedReview);
    return "redirect:/ajax/review.poom?serviceNo=" + review.getServiceNo() + "&page=" + page;
  }
}
