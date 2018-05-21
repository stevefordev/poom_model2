package com.coddington.poom.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coddington.poom.service.RelationshipsService;
import com.coddington.poom.service.UsersService;
import com.coddington.poom.util.ResizeImageUtil;
import com.coddington.poom.vo.Relationship;
import com.coddington.poom.vo.User;

@Controller
public class UsersController {
	public static final String LOGIN = "loginUser";
	public static final String LOGIN_FAIL = "loginFail";
	private UsersService usersService;
	private RelationshipsService relationshipsService;

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public void setRelationshipsService(RelationshipsService relationshipsService) {
		this.relationshipsService = relationshipsService;
	}

	@RequestMapping(value = "/login.poom")
	public String login(@ModelAttribute User user, RedirectAttributes ra, HttpSession session,
			@RequestHeader String referer) {

		User loginUser = usersService.login(user);

		if (loginUser == null) {
			ra.addFlashAttribute(this.LOGIN_FAIL, true);
		} else {
			session.setAttribute(this.LOGIN, loginUser);
		}

		return "redirect:" + referer;
	}

	@RequestMapping(value = "/logout.poom")
	public String logout(@RequestHeader String referer, HttpSession session) {

		session.invalidate();
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/signup.poom")
	public String join(User user, RedirectAttributes ra, @RequestHeader String referer) {

		// System.out.println(user.getName());
		// System.out.println(user.getEmail());
		// System.out.println(user.getNickName());
		// System.out.println(user.getPassword());
		// System.out.println(user.getPhone());
		boolean result = usersService.join(user);

		if (result == true) {
			ra.addFlashAttribute("joinSucc", true);
		} else {
			ra.addFlashAttribute("joinFail", false);
		}

		return "redirect:" + referer;
	}

	// profile 페이지 경로 선정 및 로그인한 유저의 프로필 Controller 설정
	@RequestMapping("/profile.poom")
	public String profile(Model model, @RequestParam(value = "no", defaultValue = "1") int profileUserNo,
			@RequestParam(defaultValue = "1") int fpage, HttpSession session) {
		User loginUser = (User) session.getAttribute(LOGIN);
		int loginUserNo = loginUser == null ? 0 : loginUser.getNo();

		/*
		 * if (loginUser == null) { loginUserNo = 0; } else { loginUserNo =
		 * loginUser.getNo(); }
		 */

		model.addAllAttributes(usersService.getUserInformation(profileUserNo, fpage, loginUserNo));

		return "profile";
	}

	// 유저 닉네임 수정 부분
	@RequestMapping(value = "/updateNickName.poom", method = RequestMethod.POST)
	public String updateNickName(User user, HttpSession session) {

		usersService.ModifyUserNickname(user);
		// 바낀 db의 정보를 session 에서 빠르게 업데이트 시켜서 header 부분의 로그인 화면을
		// 빠르게 변경
		User loginUser = (User) session.getAttribute(LOGIN);
		session.setAttribute(LOGIN, usersService.login(loginUser));
		loginUser.setNickName(user.getNickName());
		return "redirect:/profile.poom?no=" + user.getNo();
	}

	// 유저 내용 수정 부분
	@RequestMapping(value = "/updateintroduction.poom", method = RequestMethod.POST)
	public String updateintroduction(User user) {

		usersService.ModifyUserIntroduction(user);
		return "redirect:/profile.poom?no=" + user.getNo();
	}

	// 팔로잉 리스트의 x버튼 클릭시 삭제됨 그리고 삭제후 "redirect:" + referer로 되어서 뒤로 감
	@RequestMapping("/removeFollowingByProfileUser.poom")
	public String removeFollowingByProfileUser(@RequestParam(value = "no") int toUserNo, HttpSession session,
			@RequestHeader String referer) {

		User loginUser = (User) session.getAttribute(this.LOGIN);
		relationshipsService.removeFollowing(toUserNo, loginUser.getNo());

		System.out.println(referer);
		if (referer.indexOf("fpage") == -1) {
			referer += "&fpage=1";
		}
		// System.out.println(no);
		return "redirect:" + referer;
	}

	// // 팔로잉 버튼 클릭시 팔로잉 취소되어 리스트에서 삭제
	@RequestMapping(value = "/removeFollowing.poom", method = RequestMethod.POST)
	public String removeFollowing(int profileNo, HttpSession session, @RequestHeader String referer) {

		// System.out.println("들어왔다_removeFollowing");
		User loginUser = (User) session.getAttribute(this.LOGIN);
		System.out.println(String.format("profileNo:%d / loginUserNo:%d", profileNo, loginUser.getNo()));
		relationshipsService.removeFollowing(profileNo, loginUser.getNo());
		return "redirect:" + referer;
	}

	// 팔로우 버튼 클릭시 리스트에 팔로잉한 유저 추가됨
	@RequestMapping(value = "/registerFollowing.poom", method = RequestMethod.POST)
	public String registerFollowing(Model model, int profileNo, HttpSession session, @RequestHeader String referer) {

		// System.out.println("들어왔다_registerFollowing");
		User loginUser = (User) session.getAttribute(this.LOGIN);
		System.out.println(String.format("profileNo:%d / loginUserNo:%d", profileNo, loginUser.getNo()));
		relationshipsService.registerFollowing(profileNo, loginUser.getNo());
		return "redirect:" + referer;
	}

	// 차단해제 버튼 클릭시 차단이 취소되어 리스트에서 삭제
	@RequestMapping(value = "/removeBlock.poom", method = RequestMethod.POST)
	public String removeBlock(int profileNo, HttpSession session, @RequestHeader String referer) {

		// System.out.println("들어왔다_removeFollowing");
		// System.out.println(String.format("profileNo:%d / loginUserNo:%d", profileNo,
		// loginUser.getNo()));
		User loginUser = (User) session.getAttribute(this.LOGIN);
		relationshipsService.removeBlock(profileNo, loginUser.getNo());
		return "redirect:" + referer;
	}

	// 차단 버튼 클릭시 리스트에 차단한 유저 추가됨
	@RequestMapping(value = "/registerBlock.poom", method = RequestMethod.POST)
	public String registerBlock(Model model, int profileNo, HttpSession session, @RequestHeader String referer) {

		// System.out.println("들어왔다_registerFollowing");
		// System.out.println(String.format("profileNo:%d / loginUserNo:%d", profileNo,
		// loginUser.getNo()));
		User loginUser = (User) session.getAttribute(this.LOGIN);
		relationshipsService.registerBlock(profileNo, loginUser.getNo());
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/ajax/registerUserPhoto.poom", method = RequestMethod.POST)
	@ResponseBody
	public String registerUserPhoto(@RequestParam(required = false) Integer width,
			@RequestParam(required = false) Integer height, @RequestParam(required = false) Integer size,
			HttpSession session, MultipartFile upload, HttpServletRequest request) throws Exception {
 
		// 1) ServletContext얻기
		ServletContext sc = request.getServletContext();

		// 2) 기본경로 얻기
		String path = sc.getRealPath("");

		// 3) upload경로
		String uploadPath = path + "img/upload" + File.separator;

		// 4) image경로
		String resizePath = path + "img/profile" + File.separator;

		// 5) 고유한 값을 위한 UUID
		UUID uuid = UUID.randomUUID();

		String ext = upload.getOriginalFilename();

		int dotIdx = ext.lastIndexOf(".");

		ext = ext.substring(dotIdx, ext.length());

		System.out.println(ext);

		String fileName = uuid + ext;

		// 6) 경로+파일이름
		String fullPath = uploadPath + fileName;

		// 7) 실제 생성될 파일
		File file = new File(fullPath);

		// 8) 파일 옮기기
		upload.transferTo(file);
		System.out.println("파일 생성 성공");
		// 9) 리사이징 (200x200)
		if (size != null) {
			width = size;
			height = size;
		}

		ResizeImageUtil.resize(fullPath, resizePath + fileName, width, height);

		User loginUser = (User)session.getAttribute(this.LOGIN);
		loginUser.setPhotoUrl(fileName);
		usersService.ModifyUser(loginUser);
		return "{\"name\":\"" + fileName + "\"}";
	}

	//차단목록이동
		@RequestMapping(value="/dashboard_block.poom", method=RequestMethod.GET)
		public void dashboardBlock() {
			System.out.println("GET /dashboard_block.poom");

		}//dashboardBlock() end
		
		//차단목록불러오기 ajax
		@RequestMapping(value="/ajax/getBlockList.json", method=RequestMethod.POST)
		@ResponseBody
		public List<Relationship> getBlockList(
					HttpSession session) {
			
			System.out.println("POST /ajax/getBlockList.json");
			
			User loginUser = (User)session.getAttribute(UsersController.LOGIN);
			
			return usersService.blockList(loginUser.getNo());
			
		}//getBlockList() end
		
		//차단목록에서 삭제 (ajax)
		@RequestMapping(value="/ajax/deleteBlock.json", method=RequestMethod.POST)
		@ResponseBody
		public boolean deleteBlock(
					@RequestParam int no) {
			
			System.out.println("POST /ajax/deleteBlock.json");
			
			return usersService.deleteBlock(no);
			
		}//deleteBlock() end
		
}
