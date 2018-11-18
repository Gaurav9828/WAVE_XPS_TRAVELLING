package wave.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import wave.spring.model.VechileAttributes;
@Controller

public class AdminActionController {
	@Autowired
	// added by Gaurav Sriavstava
	@RequestMapping(value = "/newVechileAdd", method = RequestMethod.POST)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("AdminAddNewVechile");
		mav.addObject("AdminAddNewVechile", new VechileAttributes());
		return mav;
	}
	////////////////////////////UNDER PROCESS
	@RequestMapping(value = "/newVechileAddProcess", method = RequestMethod.POST)
	public ModelAndView newVechileAddProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("AdminAddNewVechile") VechileAttributes vechileAttributes){
		Part filePart = null;
		try {
			filePart = request.getPart("image");
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ServletException e1) {
			e1.printStackTrace();
		}
	    final String fileName = filePart.getName();
		  File file = new File(fileName);
	        byte[] bFile = new byte[(int) file.length()];
	        try {
	            FileInputStream fileInputStream = new FileInputStream(file);
	            fileInputStream.read(bFile);
	            fileInputStream.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		ModelAndView mav = new ModelAndView("AdminAddNewVechile");
		return mav;
	}
}
