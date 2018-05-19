package com.coddington.poom.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.coddington.poom.service.QuestionsService;
import com.coddington.poom.vo.Question;
import com.coddington.poom.vo.Review;

@Controller
public class QuestionsController {

  private QuestionsService questionsService;

  public void setQuestionsService(QuestionsService questionsService) {
    this.questionsService = questionsService;
  }

  @RequestMapping(value = "/ajax/question/register.poom", method = RequestMethod.POST)
  public String registerQuestion(Question question, Model model) {

    questionsService.register(question);

    return "redirect:/ajax/question.poom?serviceNo=" + question.getServiceNo() + "&page=1";
  }

  @RequestMapping(value = "/ajax/question.poom", method = RequestMethod.GET)
  @ResponseBody
  public List<Question> getQuestions(int serviceNo, int page, Model model) {
    return questionsService.getQuestions(serviceNo, page);
  }

  @RequestMapping(value = "/ajax/questionReplyUpdate.poom", method = RequestMethod.POST)
  public String updateQuestionReply(Question question, String boardType) {
    
    
    Question selectedQuestion = questionsService.getQuestion(question.getNo());
    selectedQuestion.setReply(question.getReply());
    questionsService.modify(selectedQuestion);
    return "redirect:/ajax/question.poom?serviceNo=" + question.getServiceNo() + "&page=1";
  }
}
