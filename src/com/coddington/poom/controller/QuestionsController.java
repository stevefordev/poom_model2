package com.coddington.poom.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.coddington.poom.service.QuestionsService;
import com.coddington.poom.vo.Question;

@Controller
public class QuestionsController {

  private QuestionsService questionsService;

  public void setQuestionsService(QuestionsService questionsService) {
    this.questionsService = questionsService;
  }

  @RequestMapping(value = "/ajax/question/register.poom", method = RequestMethod.POST)
  @ResponseBody
  public List<Question> registerQuestion(Question question, Model model) {

    questionsService.register(question);

    return questionsService.getQuestions(question.getServiceNo());
  }
}
